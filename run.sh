#!/bin/bash

cd src
javac FlyWheel.java MarchingBand.java MarchingDude.java SmellyFreshman.java
java FlyWheel
rm `ls | grep ".class"`
cd ..
