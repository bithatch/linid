package uk.co.bithatch.linid;

import java.util.Optional;
import java.util.function.Supplier;

public final class PasswordChangeRequest {

	public final static class Builder {

		private Optional<Supplier<Optional<char[]>>> currentPassword = Optional.empty();
		private Optional<Supplier<Optional<char[]>>> password = Optional.empty();

		public Builder withPassword(String password) {
			return withPassword(password.toCharArray());
		}

		public Builder withPassword(char[] password) {
			return withPassword(() -> Optional.of(password));
		}

		public Builder withPassword(Supplier<Optional<char[]>> password) {
			this.password = Optional.of(password);
			return this;
		}

		public Builder withCurrentPassword(String currentPassword) {
			return withCurrentPassword(currentPassword.toCharArray());
		}

		public Builder withCurrentPassword(char[] currentPassword) {
			return withCurrentPassword(() -> Optional.of(currentPassword));
		}

		public Builder withCurrentPassword(Supplier<Optional<char[]>> currentPassword) {
			this.currentPassword = Optional.of(currentPassword);
			return this;
		}
		
		public PasswordChangeRequest build() {
			return new PasswordChangeRequest(this);
		}

	}

	private final Optional<Supplier<Optional<char[]>>> currentPassword;
	private final Optional<Supplier<Optional<char[]>>> password;

	private PasswordChangeRequest(Builder bldr) {
		this.currentPassword = bldr.currentPassword;
		this.password = bldr.password;
	}
	
	public char[] password() {
		return password.map(s -> s.get().orElseThrow(() -> new IllegalStateException("No password, aborted by user."))).orElseThrow(() -> new IllegalStateException("No password supplier provided."));
	}

	
	public Optional<char[]> currentPasswordOr() {
		return currentPassword.map(s -> s.get()).orElseGet(() -> Optional.empty());		
	}
	
	public char[] currentPassword() {
		return currentPassword.map(s -> s.get().orElseThrow(() -> new IllegalStateException("Current password unknown, aborted by user."))).orElseThrow(() -> new IllegalStateException("No current password supplier provided."));
	}
}
