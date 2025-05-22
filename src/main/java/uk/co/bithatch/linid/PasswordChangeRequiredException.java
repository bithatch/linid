package uk.co.bithatch.linid;

@SuppressWarnings("serial")
public final class PasswordChangeRequiredException extends RuntimeException {

	private final Id id;

	public PasswordChangeRequiredException(Id id) {
		this.id = id;
	}
	
	public Id id() {
		return id;
	}
}
