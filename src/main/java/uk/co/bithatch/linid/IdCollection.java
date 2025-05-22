package uk.co.bithatch.linid;

import java.security.Principal;
import java.util.stream.Stream;

public interface IdCollection extends Principal {

	default String key() {
		return getName();
	}
	
	String[] memberNames();
	
	Stream<Id> members();
	
}
