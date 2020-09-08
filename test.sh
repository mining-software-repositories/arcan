#!/bin/bash

echo "Start Testing Arcan parameter"
echo "test 1: java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar
echo "test 2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -h"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -h
echo "test 3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit
echo "test 4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter
echo "test 5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all
echo "test 5.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD
echo "test 5.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD
echo "test 5.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL
echo "test 5.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM
echo "test 5.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM
echo "test 6 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d .
echo "test 7 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d folder
echo "test 8 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -out .
echo "test 9 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -out output2
echo "test 10 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all
echo "test 10.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD
echo "test 10.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL
echo "test 10.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD
echo "test 10.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM
echo "test 10.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM
echo "test 11 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d .
echo "test 12 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d folder
echo "test 13 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -out .
echo "test 14 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -out output2
echo "test 15 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -neo4j
echo "test 15.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD -neo4j
echo "test 15.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL -neo4j
echo "test 15.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD -neo4j
echo "test 15.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM -neo4j
echo "test 15.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM -neo4j
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -neo4j -d .
echo "test 16 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -neo4j -d folder
echo "test 16.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD -neo4j -d folder
echo "test 16.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL -neo4j -d folder
echo "test 16.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD -neo4j -d folder
echo "test 16.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM -neo4j -d folder
echo "test 16.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM -neo4j -d folder
echo "test 17 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -out .
echo "test 17.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD -out .
echo "test 17.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL -out .
echo "test 17.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD -out .
echo "test 17.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM -out .
echo "test 17.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM -out .
echo "test 18 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -all -out output2
echo "test 18.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CD -out output2
echo "test 18.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -HL -out output2
echo "test 18.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -UD -out output2
echo "test 18.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -CM -out output2
echo "test 18.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -PM -out output2
echo "test 19 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -out .
echo "test 20 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -out output2
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d . -out .
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d . -out output2
echo "test 21 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d folder -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d folder -out .
echo "test 22 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d folder -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -neo4j -d folder -out output2
echo "test 23 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -out .
echo "test 23.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -out .
echo "test 23.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -out .
echo "test 23.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -out .
echo "test 23.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -out .
echo "test 23.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -out .
echo "test 24 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -out .
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d . -out .
echo "test 25 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d folder -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d folder -out .
echo "test 26 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -out output2
echo "test 26.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -out output2
echo "test 26.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -out output2
echo "test 26.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -out output2
echo "test 26.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -out output2
echo "test 26.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -out output2
echo "test 27 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -out output2
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d . -out output2
echo "test 28 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d folder -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -neo4j -d folder -out output2
echo "test 29 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j
echo "test 29.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j
echo "test 29.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j
echo "test 29.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j
echo "test 29.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j
echo "test 29.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d .
echo "test 30 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d folder"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d folder
echo "test 31 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -out .
echo "test 31.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j -out .
echo "test 31.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j -out .
echo "test 31.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j -out .
echo "test 31.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j -out .
echo "test 31.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j -out .
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d . -out .
echo "test 32 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d folder -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d folder -out .
echo "test 32.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j -d folder -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j -d folder -out .
echo "test 32.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j -d folder -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j -d folder -out .
echo "test 32.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j -d folder -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j -d folder -out .
echo "test 32.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j -d folder -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j -d folder -out .
echo "test 32.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j -d folder -out ."
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j -d folder -out .
echo "test 33 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -out output2
echo "test 33.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j -out output2
echo "test 33.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j -out output2
echo "test 33.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j -out output2
echo "test 33.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j -out output2
echo "test 33.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j -out output2
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d . -out output2
echo "test 34 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d folder -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -all -neo4j -d folder -out output2
echo "test 34.1 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j -d folder -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CD -neo4j -d folder -out output2
echo "test 34.2 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j -d folder -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -HL -neo4j -d folder -out output2
echo "test 34.3 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j -d folder -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -UD -neo4j -d folder -out output2
echo "test 34.4 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j -d folder -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -PM -neo4j -d folder -out output2
echo "test 34.5 java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j -d folder -out output2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -p junit -filter -CM -neo4j -d folder -out output2
echo "End Testing Arcan parameter"