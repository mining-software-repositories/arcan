#!/bin/bash

echo "Start Testing Arcan parameter"
echo "test 1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar
echo "test 2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar -h
echo "test 3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar
echo "test 4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter
echo "test 5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -all
echo "test 5.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CD
echo "test 5.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -UD
echo "test 5.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -HL
echo "test 5.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CM
echo "test 5.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -PM
echo "test 6"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j -d .
echo "test 7"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j -d folder
echo "test 8"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -out .
echo "test 9"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -out output2
echo "test 10"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all
echo "test 10"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CD
echo "test 10"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -HL
echo "test 10"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -UD
echo "test 10"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CM
echo "test 10"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -PM
echo "test 11"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j -d .
echo "test 12"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j -d folder
echo "test 13"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -out .
echo "test 14"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -out output2
echo "test 15"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -all -neo4j
echo "test 15.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CD -neo4j
echo "test 15.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -HL -neo4j
echo "test 15.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -UD -neo4j
echo "test 15.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CM -neo4j
echo "test 15.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -PM -neo4j
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -all -neo4j -d .
echo "test 16"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -all -neo4j -d folder
echo "test 16.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CD -neo4j -d folder
echo "test 16.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -HL -neo4j -d folder
echo "test 16.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -UD -neo4j -d folder
echo "test 16.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CM -neo4j -d folder
echo "test 16.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -PM -neo4j -d folder
echo "test 17"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -all -out .
echo "test 17.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CD -out .
echo "test 17.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -HL -out .
echo "test 17.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -UD -out .
echo "test 17.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CM -out .
echo "test 17.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -PM -out .
echo "test 18"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -all -out output2
echo "test 18.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CD -out output2
echo "test 18.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -HL -out output2
echo "test 18.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -UD -out output2
echo "test 18.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -CM -out output2
echo "test 18.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -PM -out output2
echo "test 19"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j -out .
echo "test 20"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j -out output2
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j -d . -out .
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j -d . -out output2
echo "test 21"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j -d folder -out .
echo "test 22"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -neo4j -d folder -out output2
echo "test 23"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -out .
echo "test 23.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CD -out .
echo "test 23.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -HL -out .
echo "test 23.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -UD -out .
echo "test 23.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CM -out .
echo "test 23.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -PM -out .
echo "test 24"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j -out .
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j -d . -out .
echo "test 25"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j -d folder -out .
echo "test 26"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -out output2
echo "test 26.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CD -out output2
echo "test 26.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -HL -out output2
echo "test 26.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -UD -out output2
echo "test 26.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CM -out output2
echo "test 26.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -PM -out output2
echo "test 27"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j -out output2
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j -d . -out output2
echo "test 28"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -neo4j -d folder -out output2
echo "test 29"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j
echo "test 29.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CD -neo4j
echo "test 29.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -HL -neo4j
echo "test 29.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -UD -neo4j
echo "test 29.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CM -neo4j
echo "test 29.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -PM -neo4j
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j -d .
echo "test 30"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j -d folder
echo "test 31"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j -out .
echo "test 31.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CD -neo4j -out .
echo "test 31.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -HL -neo4j -out .
echo "test 31.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -UD -neo4j -out .
echo "test 31.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CM -neo4j -out .
echo "test 31.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -PM -neo4j -out .
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j -d . -out .
echo "test 32"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j -d folder -out .
echo "test 32.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CD -neo4j -d folder -out .
echo "test 32.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -HL -neo4j -d folder -out .
echo "test 32.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -UD -neo4j -d folder -out .
echo "test 32.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -PM -neo4j -d folder -out .
echo "test 32.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CM -neo4j -d folder -out .
echo "test 33"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j -out output2
echo "test 33.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CD -neo4j -out output2
echo "test 33.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -UD -neo4j -out output2
echo "test 33.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -HL -neo4j -out output2
echo "test 33.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -PM -neo4j -out output2
echo "test 33.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CM -neo4j -out output2
#java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j -d . -out output2
echo "test 34"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -all -neo4j -d folder -out output2
echo "test 34.1"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CD -neo4j -d folder -out output2
echo "test 34.2"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -HL -neo4j -d folder -out output2
echo "test 34.3"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -UD -neo4j -d folder -out output2
echo "test 34.4"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -PM -neo4j -d folder -out output2
echo "test 34.5"
java -jar ToySystem-1.2.1-SNAPSHOT/ToySystem-1.2.1-SNAPSHOT.jar  -JR -p junit-4.12.jar -filter -CM -neo4j -d folder -out output2
echo "End Testing Arcan parameter"