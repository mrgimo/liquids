.SILENT:

JAVADIR = /usr/lib/jvm/java-6-sun/include

all : Example.class libexample.so

Example.class : Example.java
	javac Example.java

Example.h : Example.class
	javah Example

libexample.so : example.cpp Example.h
	g++ -Wall -o libexample.so -shared -Wl,-soname,libexample.so \
	-I $(JAVADIR) \
	-I $(JAVADIR)/linux \
	example.cpp -static -lc

test : libexample.so
	java -Djava.library.path=. Example
