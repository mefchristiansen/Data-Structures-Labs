JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	TestSudoku.java \
	Sudoku.java \
	Board.java \
	Move.java \
	Stack.java \
	StackType.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

run:
	@java TestSudoku
