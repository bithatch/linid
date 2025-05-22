package uk.co.bithatch.linid;

import java.util.Optional;
import java.util.stream.Stream;

public interface IdDb {

	default Optional<Id> authenticate(String name, String password) {
		return authenticate(name, password.toCharArray());
	}
	
	default Optional<Id> authenticate(String name, char[] password) {
		return idByName(name).map(u ->  {
			try {
				u.verify(password);
				return u;
			}
			catch(IllegalArgumentException iae) {
			}
			return null;
		});
	}

	default void change(String name, PasswordChangeRequest changeRequest) {
		idByName(name).ifPresent(u -> 
			u.change(changeRequest)
		);
	}
	
	Stream<IdCollection> idCollections();
	
	default Optional<IdCollection> idCollectionByName(String username) {
		return idCollections().filter(n -> n.getName().equals(username)).findFirst();
	}
	
	default Optional<IdCollection> idCollectionByKey(String key) {
		return idCollections().filter(n -> n.key().equals(key)).findFirst();
	}
	
	Stream<Id> ids();
	
	default Optional<Id> idByName(String name) {
		return ids().filter(n -> n.getName().equals(name)).findFirst();
	}
	
	default Optional<Id> idByKey(String key) {
		return ids().filter(n -> n.key().equals(key)).findFirst();
	} 
}
