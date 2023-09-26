all : compile run

compile : src/HtmlReader.java src/HtmlValidator.java
	javac src/HtmlReader.java src/HtmlValidator.java

run : src/HtmlValidator.class
	java src/HtmlValidator

clean :
	del src/HtmlReader.class
	del src/HtmlValidator.class
	del src/HtmlTag.class
