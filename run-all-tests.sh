#!/bin/bash

echo "Test leggendo dalle classi avviato"
./test.sh >output.out 2> output.err
echo "Test leggendo dal jar avviato"
./test-singlejar.sh >output-singlejar.out 2> output-singlejar.err
echo "Test leggendo dalla cartella di jars avviato"
./test-folderjar.sh >output-folderjar.out 2> output-folderjar.err