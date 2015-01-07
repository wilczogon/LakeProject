 dir /s /B src/*.java > sources.txt
 dir /s /B externalFishes/*.java > sources.txt
 
 javac -d bin -cp .;libs/* -sourcepath .;src;externalFishes @sources.txt
 
 java -cp .;bin;libs/* LakeMain