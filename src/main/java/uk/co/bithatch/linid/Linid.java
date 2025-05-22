package uk.co.bithatch.linid;

import com.sshtools.nih.Platform;

import uk.co.bithatch.linid.impl.LinuxIdDbImpl;

public final class Linid {

	private final static class Defaults {
		private final static IdDb DEFAULT = createDefault();
		
		private static IdDb createDefault() {
			if(Platform.isLinux()) {
				return new LinuxIdDbImpl();
			}
			else
				throw new UnsupportedOperationException();
		}
	}
	
	public static IdDb get() {
		return Defaults.DEFAULT;
	}
}
