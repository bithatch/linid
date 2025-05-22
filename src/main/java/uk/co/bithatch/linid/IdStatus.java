package uk.co.bithatch.linid;

import java.time.Instant;
import java.util.Optional;

public interface IdStatus {
	
	Optional<Instant> passwordChanged();
	
	Optional<Instant> passwordExpires();
	
	Optional<Instant> passwordUnlocked();
	
	Optional<Instant> accountExpires();
}
