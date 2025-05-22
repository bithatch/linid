#!/bin/bash
#~/Applications/jextract-22/jextract --output src/main/java \
	#-t uk.co.bithatch.linid.impl.pam \
	#--library pam  \
	#src/main/c/pam_proto.h
	
~/Applications/jextract-22/bin/jextract --output src/main/java \
    -I /usr/include/security \
	-t uk.co.bithatch.linid.impl.linux \
	--library c  \
	src/main/c/nss_proto.h