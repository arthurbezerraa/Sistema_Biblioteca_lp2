#!/bin/bash

if [ ! -d "bin" ]; then
    echo "Erro: Diretório bin não encontrado. Execute compile.sh primeiro."
    exit 1
fi

echo "Executando Sistema de Biblioteca..."
java -cp bin SistemaBiblioteca

