package uk.co.bithatch.linid;

import java.util.Optional;
import java.util.stream.Stream;

public interface LinuxIdDb extends IdDb {

	default Stream<LinuxGroupId> groups() {
		return idCollections().map(i -> (LinuxGroupId)i);
	}
	
	default Optional<LinuxGroupId> groupByName(String name) {
		return idCollectionByName(name).map(grp -> (LinuxGroupId)grp);
	}
	
	default Optional<LinuxGroupId> groupByGid(int gid) {
		return idCollectionByKey(String.valueOf(gid)).map(grp -> (LinuxGroupId)grp);
	}
	
	default Stream<LinuxUserId> users() {
		return ids().map(id -> (LinuxUserId)id);
	}
	
	default Optional<LinuxUserId> userByName(String name) {
		return idByName(name).map(id -> (LinuxUserId)id);
	}
	
	default Optional<LinuxUserId> userByUid(int uid) {
		return idByKey(String.valueOf(uid)).map(id -> (LinuxUserId)id);
	} 
	
	void setPAMService(String pamService);
}
