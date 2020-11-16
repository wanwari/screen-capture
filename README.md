# screen-capture
Screen capturing program built using Java

## [Linux] Build Instructions

Clone the Repo and cd into it

`git clone git@github.com:wiesa56/screen-capture.git && cd screen-capture/src`

Compile the .java files

`javac ca/wanwari/*.java`

Build the JAR

`jar cfvm screen-capture.jar META-INF/MANIFEST.MF ca/wanwari/*.class`

Allow the file to be executed

`chmod u+x screen-capture.jar`

Run the program (or double click the file)

`java -jar screen-capture.jar`
