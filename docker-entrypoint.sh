#!/bin/bash

if [ ! -f $1 ]
then
    echo File $1 not found
    exit
fi

mas2j $1
ant -f bin/build.xml jar
