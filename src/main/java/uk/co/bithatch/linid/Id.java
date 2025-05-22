package uk.co.bithatch.linid;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;
import java.util.stream.Stream;

public interface Id extends UserPrincipal {

	default String key() {
		return getName();
	}
	
	Optional<String> email();
	
	Optional<String> fullName();
	
	Optional<String> phone();
	
	Optional<IdStatus> status();
	
	void verify(char[] password);
	
	void expirePassword();
	
	Optional<String> primaryCollectionName();
	
	Optional<IdCollection> primaryCollection();
	
	default String[] collectionNames() {
		return collections().map(IdCollection::getName).toList().toArray(new String[0]);
	}
	
	default Stream<IdCollection> collections() {
		throw new UnsupportedOperationException();
	}

	void change(PasswordChangeRequest changeRequest);
}
