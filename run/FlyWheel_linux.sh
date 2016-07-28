#!/bin/bash

echo "Now running FLYWHEEL! The Marching Band Game for Linux"
cd ../src
javac FlyWheel.java -d ../classes
cd ../classes
java FlyWheel
