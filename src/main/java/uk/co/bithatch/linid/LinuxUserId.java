package uk.co.bithatch.linid;

import java.util.Optional;

public interface LinuxUserId extends Id {
	
	int uid();
	
	int gid();
	
	String username();

	Optional<String> email();
	
	String home();
	
	String shell();
	
	String[] gecos();

	Optional<String> other();

	Optional<String> workPhone();

	Optional<String> homePhone();

	Optional<String> office();
}
