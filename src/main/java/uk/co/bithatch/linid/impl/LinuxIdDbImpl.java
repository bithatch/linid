/*
 * Copyright © 2025 Bithatch (tanktarta@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the “Software”), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package uk.co.bithatch.linid.impl;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.ProcessBuilder.Redirect;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.security.Principal;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import uk.co.bithatch.linid.Id;
import uk.co.bithatch.linid.IdCollection;
import uk.co.bithatch.linid.IdStatus;
import uk.co.bithatch.linid.LinuxGroupId;
import uk.co.bithatch.linid.LinuxIdDb;
import uk.co.bithatch.linid.LinuxUserId;
import uk.co.bithatch.linid.PasswordChangeRequest;
import uk.co.bithatch.linid.PasswordChangeRequiredException;
import uk.co.bithatch.linid.impl.ZPAM.ZPAMPasswordChangeRequired;
import uk.co.bithatch.linid.impl.linux.group;
import uk.co.bithatch.linid.impl.linux.nss_proto_h;
import uk.co.bithatch.linid.impl.linux.passwd;
import uk.co.bithatch.linid.impl.linux.spwd;

public class LinuxIdDbImpl implements LinuxIdDb {

	private String pamService = System.getProperty("linid.linux.auth.pamService", "login");

	@Override
	public Optional<Id> idByName(String name) {
		try {
			Integer.parseInt(name);
			throw new IllegalArgumentException("Must be non-numeric.");
		}
		catch(NumberFormatException nfe) {
		}
		try(var arena = Arena.ofConfined()) {
			var seg = nss_proto_h.getpwnam(arena.allocateFrom(name));
			return seg.equals(MemorySegment.NULL) ? Optional.empty() : Optional.of(new LinuxNssUserImpl(seg, this));
		}
	}

	@Override
	public Optional<Id> idByKey(String key) {
		try {
			var seg = nss_proto_h.getpwuid(Integer.parseInt(key));
			return seg.equals(MemorySegment.NULL) ? Optional.empty() : Optional.of(new LinuxNssUserImpl(seg, this));
		}
		catch( Exception e) {}
		throw new IllegalArgumentException("Must be numberic.");
	}	
	
	@Override	
	public Optional<IdCollection> idCollectionByName(String name) {
		try {
			Integer.parseInt(name);
			throw new IllegalArgumentException("Must be non-numeric.");
		}
		catch(NumberFormatException nfe) {
		}
		try(var arena = Arena.ofConfined()) {
			var seg = nss_proto_h.getgrnam(arena.allocateFrom(name));
			return seg.equals(MemorySegment.NULL) ? Optional.empty() : Optional.of(new LinuxNssGroupImpl(seg, this));
		}
	}

	@Override
	public Optional<IdCollection> idCollectionByKey(String key) {
		try {
			var seg = nss_proto_h.getgrgid(Integer.parseInt(key));
			return seg.equals(MemorySegment.NULL) ? Optional.empty() : Optional.of(new LinuxNssGroupImpl(seg, this));
		}
		catch( Exception e) {}
		throw new IllegalArgumentException("Must be numeric.");
	}

	@Override
	public void setPAMService(String pamService) {
		this.pamService = pamService;
	}

	@Override
	public Stream<IdCollection> idCollections() {
		nss_proto_h.setgrent();
		return closeableIteratorStream(() -> {
			nss_proto_h.endgrent();
		}, new Iterator<>() {
			MemorySegment usr = MemorySegment.NULL;

			@Override
			public boolean hasNext() {
				checkNext();
				return !MemorySegment.NULL.equals(usr);
			}

			@Override
			public IdCollection next() {
				try {
					checkNext();
					if(MemorySegment.NULL.equals(usr)) {
						throw new NoSuchElementException();
					}
					return new LinuxNssGroupImpl(usr, LinuxIdDbImpl.this);
				}
				finally {
					usr = MemorySegment.NULL;
				}
			}
			
			private void checkNext() {
				if(MemorySegment.NULL.equals(usr)) {
					usr= nss_proto_h.getgrent();
				}
			}
		});
	}

	@Override
	public Stream<Id> ids() {
		nss_proto_h.setpwent();
		return closeableIteratorStream(() -> {
			nss_proto_h.endpwent();
		}, new Iterator<>() {
			MemorySegment usr = MemorySegment.NULL;

			@Override
			public boolean hasNext() {
				checkNext();
				return !MemorySegment.NULL.equals(usr);
			}

			@Override
			public Id next() {
				try {
					checkNext();
					if(MemorySegment.NULL.equals(usr)) {
						throw new NoSuchElementException();
					}
					return new LinuxNssUserImpl(usr, LinuxIdDbImpl.this);
				}
				finally {
					usr = MemorySegment.NULL;
				}
			}
			
			private void checkNext() {
				if(MemorySegment.NULL.equals(usr)) {
					usr= nss_proto_h.getpwent();
				}
			}
		});
	}

	private <E extends Principal> Stream<E> closeableIteratorStream(Runnable r, Iterator<E> it) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, Spliterator.ORDERED), false)
				.onClose(r::run);
	}
	
	
	private final static class LinuxNssGroupImpl implements LinuxGroupId {

		private final int gid;
		private final String name;
		private final String[] members;
		private final LinuxIdDbImpl db;

		private LinuxNssGroupImpl(MemorySegment seg, LinuxIdDbImpl db) {
			this.db = db;
			name = group.gr_name(seg).getString(0);
			gid = group.gr_gid(seg);
			var membrs = group.gr_mem(seg);
			var membrl = new ArrayList<String>();
			for(int i = 0 ; i < Integer.MAX_VALUE; i++) {
				var mseg = membrs.getAtIndex(group.gr_mem$layout(), i);
				if(mseg.equals(MemorySegment.NULL)) {
					break;
				}
				membrl.add(mseg.getString(0));
			}
			members = membrl.toArray(new String[0]);
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String key() {
			return String.valueOf(gid);
		}

		@Override
		public String[] memberNames() {
			return members;
		}

		@Override
		public String toString() {
			return "LinuxIdCollection [gid=" + gid + ", name=" + name + ", members=" + Arrays.toString(members) + "]";
		}

		@Override
		public Stream<Id> members() {
			return Arrays.asList(members).stream().map(m -> db.idByName(m).orElseThrow(() -> new IllegalStateException(MessageFormat.format("Group {0} has a membmer, `{1}` which does not exist.", getName(), m))));
		}

		@Override
		public int gid() {
			return gid;
		}
		
	}
	
	

	private final static class LinuxNssUserStatusImpl implements IdStatus {

		private final Instant passwordChanged;
		private final Optional<Instant> accountExpires;
		private final Optional<Instant> passwordExpires;
		private final Optional<Instant> passwordUnlocked;

		private LinuxNssUserStatusImpl(MemorySegment seg, LinuxNssUserImpl usr) {
			var lstchg = spwd.sp_lstchg(seg);
			passwordChanged = Instant.ofEpochSecond(lstchg * Duration.ofDays(1).toSeconds());
			
			var accexp = spwd.sp_expire(seg);
			accountExpires = accexp == 0 ? Optional.empty() : Optional.of(Instant.ofEpochSecond(accexp * Duration.ofDays(1).toSeconds()));
			
			var minage = spwd.sp_min(seg);
			var maxage = spwd.sp_max(seg);

			passwordUnlocked = minage == 0 ? 
					Optional.empty() : 
					Optional.of(passwordChanged.plus(minage, ChronoUnit.DAYS));

			passwordExpires = maxage == 0 ? 
					Optional.empty() : 
					Optional.of(passwordChanged.plus(maxage, ChronoUnit.DAYS));
			
		}

		@Override
		public String toString() {
			return "LinuxNssUserStatusImpl [passwordChanged=" + passwordChanged + ", accountExpires=" + accountExpires
					+ ", passwordExpires=" + passwordExpires + ", passwordUnlocked=" + passwordUnlocked + "]";
		}

		@Override
		public Optional<Instant> passwordChanged() {
			return Optional.of(passwordChanged);
		}

		@Override
		public Optional<Instant> passwordExpires() {
			return passwordExpires;
		}

		@Override
		public Optional<Instant> passwordUnlocked() {
			return passwordUnlocked;
		}

		@Override
		public Optional<Instant> accountExpires() {
			return accountExpires;
		}
		
	}

	private final static class LinuxNssUserImpl implements LinuxUserId {

		private final int uid;
		private final int gid;
		private final LinuxIdDbImpl db;
		private final String name;
		private final Optional<String> fullName;
		private final String home;
		private final String shell;
		private final Optional<String> other;
		private final Optional<String> office;
		private final Optional<String> workPhone;
		private final Optional<String> homePhone;
		private final String[] gecos;
		private Optional<IdStatus> status;
		
		LinuxNssUserImpl(MemorySegment seg, LinuxIdDbImpl db) {
			this.db = db;
			
			gecos = passwd.pw_gecos(seg).getString(0).split(",");

			name = passwd.pw_name(seg).getString(0);
			uid = passwd.pw_uid(seg);
			gid = passwd.pw_uid(seg);
			fullName = stringOpt(gecos[0]);
			
			if(gecos.length > 1) {
				office = stringOpt(gecos[1]);
			}
			else {
				office = Optional.empty();
			}
			
			if(gecos.length > 2) {
				workPhone = stringOpt(gecos[2]);
			}
			else {
				workPhone = Optional.empty();
			}

			if(gecos.length > 2) {
				homePhone = stringOpt(gecos[3]);
			}
			else {
				homePhone = Optional.empty();
			}

			if(gecos.length > 4) {
				other = stringOpt(gecos[4]);
			}
			else {
				other = Optional.empty();
			}
				
			home = passwd.pw_dir(seg).getString(0);
			shell = passwd.pw_shell(seg).getString(0);
		}

		@Override
		public Optional<String> phone() {
			return homePhone;
		}

		@Override
		public Optional<String> fullName() {
			return fullName;
		}

		@Override
		public Optional<String> office() {
			return office;
		}

		@Override
		public  Optional<String> homePhone() {
			return homePhone;
		}

		@Override
		public  Optional<String> workPhone() {
			return workPhone;
		}

		@Override
		public  Optional<String> other() {
			return other;
		}

		@Override
		public String home() {
			return home;
		}

		@Override
		public String shell() {
			return shell;
		}

		@Override
		public String key() {
			return String.valueOf(uid);
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "LinuxIdImpl [uid=" + uid + ", gid=" + gid + ", name=" + name + ", fullName=" + fullName + ", home="
					+ home + ", shell=" + shell + ", other=" + other + ", office="
					+ office + ", workPhone=" + workPhone + ", homePhone=" + homePhone + "]";
		}

		@Override
		public void verify(char[] password) throws IllegalArgumentException {
			try(var convo = ZPAM.conversation(db.pamService)) {
				convo.authenticate(name, password);
			}
			catch(ZPAMPasswordChangeRequired prce) {
				throw new PasswordChangeRequiredException(this);
			}
		}

		@Override
		public Optional<String> email() {
			return other;
		}

		@Override
		public Optional<String> primaryCollectionName() {
			return Optional.of(String.valueOf(gid));
		}

		@Override
		public Optional<IdCollection> primaryCollection() {
			return db.groupByGid(gid).map(g -> (IdCollection)g);
		}

		@Override
		public int uid() {
			return uid;
		}

		@Override
		public int gid() {
			return gid;
		}

		@Override
		public Stream<IdCollection> collections() {
			Stream<IdCollection> str2 = db.idCollections().filter(idc -> Arrays.asList(idc.memberNames()).contains(username()));
			if(primaryCollection().isPresent()) {
				return Stream.concat(
					Stream.of(primaryCollection().get()), 
					str2
				).distinct();
			}
			else {
				return str2.distinct();
			}
		}

		@Override
		public String username() {
			return getName();
		}

		@Override
		public void change(PasswordChangeRequest changeRequest) {
			try(var convo = ZPAM.conversation(db.pamService)) {
				convo.change(getName(), changeRequest.currentPasswordOr(), changeRequest.password());
			}
		}

		@Override
		public void expirePassword() {
			var pb = new ProcessBuilder("passwd", "--expire", username());
			pb.redirectError(Redirect.INHERIT);
			pb.redirectInput(Redirect.INHERIT);
			try {
				var proc = pb.start();
				if(proc.waitFor() != 0) {
					throw new IOException("Unexpected exit value " + proc.exitValue() + ".");
				}
			} catch (IOException ioe) {
				throw new UncheckedIOException(ioe);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}

		@Override
		public String[] gecos() {
			return gecos;
		}

		@Override
		public synchronized Optional<IdStatus> status() {
			if(status == null) {
				status = Optional.of(calcStatus());
			}
			return status;
		}
		
		IdStatus calcStatus() {
			try(var arena = Arena.ofConfined()) {
				return new LinuxNssUserStatusImpl(nss_proto_h.getspnam(arena.allocateFrom(username())), this);
			}
		}
	}

	private static Optional<String> stringOpt(String str) {
		return str == null || str.length() == 0 ? Optional.empty() : Optional.of(str);
	}
}
