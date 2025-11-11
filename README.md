# Sistema de Gestão de Biblioteca Digital

## Informações do Projeto

**Disciplina:** Linguagem de Programação 2 (LP2)  
**Professor:** Jefferson Gomes Dutra  
**Linguagem:** Java  
**Data de Entrega:** 11/11/2025

## Objetivo

Sistema completo de gestão de biblioteca digital implementando os principais conceitos de Programação Orientada a Objetos (POO), incluindo CRUD completo, herança, polimorfismo e tratamento de exceções.

## Requisitos Atendidos

| Requisito | Status |
|-----------|--------|
| Mínimo 8 Classes | ✅ |
| Encapsulamento de todas as entidades | ✅ |
| Mínimo 2 Heranças (Classes Bases Diferentes) | ✅ |
| Mínimo 2 Polimorfismo (Classes Bases Diferentes) | ✅ |
| CRUD completo (6 entidades) | ✅ |
| Tratamento de exceções personalizadas | ✅ |
| Diagrama de Classes UML | ✅ |

## Estrutura do Projeto

```
biblioteca-lp2/
├── src/
│   ├── model/              # Classes de modelo (entidades)
│   │   ├── Usuario.java
│   │   ├── Estudante.java
│   │   ├── Professor.java
│   │   ├── Livro.java
│   │   ├── Autor.java
│   │   ├── Categoria.java
│   │   ├── OperacaoBiblioteca.java
│   │   ├── Emprestimo.java
│   │   └── Reserva.java
│   ├── enums/              # Enumeradores
│   │   ├── StatusEmprestimo.java
│   │   └── StatusReserva.java
│   ├── controller/         # Gerenciadores (CRUD)
│   │   ├── GerenciadorUsuarios.java
│   │   ├── GerenciadorLivros.java
│   │   ├── GerenciadorEmprestimos.java
│   │   ├── GerenciadorReservas.java
│   │   ├── GerenciadorAutores.java
│   │   └── GerenciadorCategorias.java
│   ├── exception/          # Exceções personalizadas
│   │   ├── UsuarioNaoEncontradoException.java
│   │   ├── UsuarioJaExisteException.java
│   │   ├── LivroNaoDisponivelException.java
│   │   ├── LivroJaExisteException.java
│   │   └── EmprestimoInvalidoException.java
│   ├── util/               # Utilitários
│   │   ├── CpfUtils.java
│   │   ├── EmailUtils.java
│   │   └── DateUtils.java
│   └── SistemaBiblioteca.java
├── docs/                   # Documentação
│   └── diagrama.md
├── compile.sh              # Script de compilação
├── run.sh                  # Script de execução
└── README.md               # Este arquivo
```

## Conceitos de POO Implementados

### 1. Encapsulamento
- Todos os atributos são privados ou protected
- Acesso controlado através de getters e setters
- Validação de dados nos métodos setter e construtores

### 2. Herança
- **Hierarquia 1:** `Usuario` (classe abstrata base)
  - `Estudante extends Usuario`
  - `Professor extends Usuario`
- **Hierarquia 2:** `OperacaoBiblioteca` (classe abstrata base)
  - `Emprestimo extends OperacaoBiblioteca`
  - `Reserva extends OperacaoBiblioteca`

### 3. Polimorfismo
- **Polimorfismo 1:** Métodos abstratos em `Usuario` implementados nas classes filhas
  - `getTipoUsuario()` e `getInformacoesEspecificas()` em `Estudante` e `Professor`
- **Polimorfismo 2:** Métodos abstratos em `OperacaoBiblioteca` implementados nas classes filhas
  - `getTipoOperacao()`, `getStatusOperacao()`, `podeFinalizar()` e `finalizarOperacao()` em `Emprestimo` e `Reserva`
- Enums com comportamentos diferentes

### 4. Abstração
- Classe `Usuario` abstrata não pode ser instanciada
- Define contrato que as classes filhas devem seguir

## Funcionalidades CRUD

### Usuários
- ✅ Criar (Estudante/Professor)
- ✅ Ler (buscar por CPF, nome)
- ✅ Atualizar (editar dados)
- ✅ Remover (excluir usuário)

### Livros
- ✅ Criar (cadastrar livro)
- ✅ Ler (buscar por ISBN, título, autor)
- ✅ Atualizar (editar informações)
- ✅ Remover (excluir livro)

### Empréstimos
- ✅ Criar (realizar empréstimo)
- ✅ Ler (consultar empréstimos ativos/histórico)
- ✅ Atualizar (devolver livro, atualizar status)
- ✅ Remover (cancelar empréstimo)

### Reservas
- ✅ Criar (fazer reserva)
- ✅ Ler (consultar reservas ativas)
- ✅ Atualizar (atualizar status)
- ✅ Remover (cancelar reserva)

### Autores
- ✅ Criar (cadastrar autor)
- ✅ Ler (buscar por nome)
- ✅ Atualizar (editar dados)
- ✅ Remover (excluir autor)

### Categorias
- ✅ Criar (cadastrar categoria)
- ✅ Ler (listar todas)
- ✅ Atualizar (editar categoria)
- ✅ Remover (excluir categoria)

## Como Executar

### Pré-requisitos
- Java 8 ou superior
- Terminal/Command Prompt

### Compilação

```bash
chmod +x compile.sh
./compile.sh
```

Ou manualmente:
```bash
javac -d bin -sourcepath src src/**/*.java src/SistemaBiblioteca.java
```

### Execução

```bash
chmod +x run.sh
./run.sh
```

Ou manualmente:
```bash
java -cp bin SistemaBiblioteca
```

## Validações Implementadas

- **CPF:** Validação completa com dígitos verificadores
- **Email:** Validação com regex
- **Datas:** Uso de LocalDate para type safety
- **Campos obrigatórios:** Validação em todos os construtores
- **Transições de estado:** Validação de mudanças de status nos enums

## Tratamento de Exceções

O sistema utiliza exceções personalizadas para tratamento de erros:
- `UsuarioNaoEncontradoException`
- `UsuarioJaExisteException`
- `LivroNaoDisponivelException`
- `LivroJaExisteException`
- `EmprestimoInvalidoException`

## Diagrama UML

O diagrama de classes está disponível em `docs/diagrama.md`.

## Autores

- Hugo José - Modelo e Entidades
- Arthur - Controle e Gerenciadores
- Leandro - Sistema Principal

## Licença

Este projeto foi desenvolvido para fins acadêmicos.
