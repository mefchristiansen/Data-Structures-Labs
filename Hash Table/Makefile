JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	TestHashTables.java \
	HashTable.java \
	GameBoard.java \
	Entry.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	@java TestHashTables
