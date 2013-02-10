#!/bin/bash

# should be run only from Makefile in Thresher root directory

BASE=$(pwd)
Z3_VERSION=4.3

# build WALA
cp build/Makefile_wala lib/WALA/Makefile
cd lib/WALA/ && make
# build z3 
cd $BASE/lib/z3 && python scripts/mk_make.py && cd build && make
# move z3 components where ScalaZ3 wants them
cd $BASE/lib/ScalaZ3/z3/x64/ && mkdir $Z3_VERSION && cd $Z3_VERSION
mkdir lib
mkdir include
cp $BASE/lib/z3/build/libz3.so lib/
cp $BASE/lib/z3/src/api/*.h include/
# build ScalaZ3 (requires sbt version .10 or less)
cd $BASE/lib/ScalaZ3 && sbt package && sbt update