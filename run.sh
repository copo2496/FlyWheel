#!/bin/bash

cd src
javac FlyWheel.java FootballField.java MarchingDude.java SmellyFreshman.java
java FlyWheel
rm `ls | grep ".class"`
cd ..
