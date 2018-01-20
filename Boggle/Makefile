JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	PlayBoggle.java \
	Boggle.java \
	Dictionary.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	@java PlayBoggle
