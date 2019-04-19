JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	DriverProgram.java \
	AmericanPutOption.java

default: americanPut.jar

americanPut.jar: DriverProgram.class AmericanPutOption.class
	jar cmvf META-INF/MANIFEST.MF americanPut.jar DriverProgram.class AmericanPutOption.class

AmericanPutOption.class: AmericanPutOption.java
	javac -g AmericanPutOption.java

DriverProgram.class: DriverProgram.java AmericanPutOption.java
	javac -g DriverProgram.java AmericanPutOption.java

clean:
	$(RM) *.class *.jar
