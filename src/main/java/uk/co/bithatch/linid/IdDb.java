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
