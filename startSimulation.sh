 #!/bin/bash
 
 find . -name "*.class" -type f -delete
 
 #git pull origin
 
 find src/ externalFishes/ -name "*.java" -printf %p\\n | xargs javac -d bin -cp .:libs/* -sourcepath .:src:externalFishes
 
 java -cp .:bin:libs/* LakeMain