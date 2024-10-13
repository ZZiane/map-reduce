#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
PROJECT_ROOT="$( cd "$SCRIPT_DIR/.." && pwd )"
BUILD_DIR="$PROJECT_ROOT/build"

if [ ! -d "$BUILD_DIR" ]; then
    echo "Build directory does not exist. Please compile the project first."
    exit 1
fi

echo "Running Map Reduce Console Application..."
java -cp "$BUILD_DIR" org.zzach.mapreduce.example.Test