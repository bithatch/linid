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
