#!/bin/bash

echo "Compilando Sistema de Biblioteca..."

if [ ! -d "bin" ]; then
    mkdir bin
fi

javac -d bin -sourcepath src \
    src/model/*.java \
    src/enums/*.java \
    src/exception/*.java \
    src/util/*.java \
    src/controller/*.java \
    src/SistemaBiblioteca.java

if [ $? -eq 0 ]; then
    echo "Compilação concluída com sucesso!"
else
    echo "Erro na compilação!"
    exit 1
fi

