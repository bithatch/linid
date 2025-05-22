# linid

![Maven Build/Test JDK 17](https://github.com/bithatch/linid/actions/workflows/maven.yml/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/uk.co.bithatch/linid/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uk.co.bithatch/linid)
[![Coverage Status](https://coveralls.io/repos/github/bithatch/linid/badge.svg)](https://coveralls.io/github/sshtools/jini)
[![javadoc](https://javadoc.io/badge2/uk.co.bithatch/linid/javadoc.svg)](https://javadoc.io/doc/uk.co.bithatch/linid)
![JPMS](https://img.shields.io/badge/JPMS-uk.co.bithatch.linid-purple) 

A small Java library that makes of use of Java 22's FFM API to interact with user and group accounts on Linux.
 
## Installation

Available on Maven Central, so just add the following dependency to your project's `pom.xml`.

```xml
<dependency>
    <groupId>uk.co.bithatch</groupId>
    <artifactId>linid</artifactId>
    <version>0.9.0</version>
</dependency>
```

### JPMS

If you are using [JPMS](https://en.wikipedia.org/wiki/Java_Platform_Module_System), add `uk.co.bithatch.linid` to your `module-info.java`.

### Build From Source

Using [Apache Maven](maven.apache.org/) is recommended.

 * Clone this module
 * Change directory to where you cloned to
 * Run `mvn package`
 * Jar Artifacts will be in the `target` directory.
