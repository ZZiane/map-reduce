#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

PROJECT_ROOT="$( cd "$SCRIPT_DIR/.." && pwd )"

SOURCE_DIR="$PROJECT_ROOT/src"

BUILD_DIR="$PROJECT_ROOT/build/."

mkdir -p "$BUILD_DIR"

cd "$SOURCE_DIR"

echo "Generating list of source files..."

find . -name "*.java" > ../sources.txt

echo "Compiling Java classes..."

javac -d $BUILD_DIR @../sources.txt

if [ $? -eq 0 ]; then
    echo "Compilation successful."
else
    echo "Compilation failed."
    exit 1
fi

rm ../sources.txt