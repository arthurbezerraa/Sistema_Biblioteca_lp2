# Diagrama de Classes UML

## Sistema de Gestão de Biblioteca Digital

```mermaid
classDiagram
    class Usuario {
        <<abstract>>
        #String nome
        #String email
        #String cpf
        +getTipoUsuario()* String
        +getInformacoesEspecificas()* String
    }
    
    class Estudante {
        -String matricula
        -String curso
        +getTipoUsuario() String
        +getInformacoesEspecificas() String
    }
    
    class Professor {
        -String departamento
        -String titulacao
        +getTipoUsuario() String
        +getInformacoesEspecificas() String
    }
    
    class Livro {
        -String isbn
        -String titulo
        -Autor autor
        -Categoria categoria
        -boolean disponivel
        -int anoPublicacao
        +emprestar()
        +devolver()
    }
    
    class Autor {
        -String nome
        -String nacionalidade
        -LocalDate dataNascimento
    }
    
    class Categoria {
        -String nome
        -String descricao
    }
    
    class OperacaoBiblioteca {
        <<abstract>>
        #Usuario usuario
        #Livro livro
        #LocalDate dataOperacao
        +getTipoOperacao()* String
        +getStatusOperacao()* String
        +podeFinalizar()* boolean
        +finalizarOperacao()* void
    }
    
    class Emprestimo {
        -LocalDate dataEmprestimo
        -LocalDate dataDevolucao
        -LocalDate dataLimite
        -StatusEmprestimo status
        +getTipoOperacao() String
        +getStatusOperacao() String
        +podeFinalizar() boolean
        +finalizarOperacao() void
        +devolverLivro()
        +isAtrasado() boolean
    }
    
    class Reserva {
        -LocalDate dataReserva
        -StatusReserva status
        +getTipoOperacao() String
        +getStatusOperacao() String
        +podeFinalizar() boolean
        +finalizarOperacao() void
        +cancelar()
        +concluir()
    }
    
    class StatusEmprestimo {
        <<enumeration>>
        ATIVO
        DEVOLVIDO
        ATRASADO
        +podeTransitarPara() boolean
    }
    
    class StatusReserva {
        <<enumeration>>
        ATIVA
        CANCELADA
        CONCLUIDA
        +podeTransitarPara() boolean
    }
    
    class GerenciadorUsuarios {
        -List~Usuario~ usuarios
        +adicionarUsuario()
        +buscarPorCpf()
        +listarUsuarios()
        +removerUsuario()
    }
    
    class GerenciadorLivros {
        -List~Livro~ livros
        +adicionarLivro()
        +buscarPorIsbn()
        +listarLivros()
    }
    
    class GerenciadorEmprestimos {
        -List~Emprestimo~ emprestimos
        +realizarEmprestimo()
        +devolverLivro()
        +listarEmprestimos()
    }
    
    class GerenciadorReservas {
        -List~Reserva~ reservas
        +fazerReserva()
        +listarReservas()
    }
    
    class GerenciadorAutores {
        -List~Autor~ autores
        +adicionarAutor()
        +listarAutores()
    }
    
    class GerenciadorCategorias {
        -List~Categoria~ categorias
        +adicionarCategoria()
        +listarCategorias()
    }
    
    Usuario <|-- Estudante
    Usuario <|-- Professor
    OperacaoBiblioteca <|-- Emprestimo
    OperacaoBiblioteca <|-- Reserva
    Livro --> Autor
    Livro --> Categoria
    OperacaoBiblioteca --> Usuario
    OperacaoBiblioteca --> Livro
    Emprestimo --> StatusEmprestimo
    Reserva --> StatusReserva
    GerenciadorUsuarios --> Usuario
    GerenciadorLivros --> Livro
    GerenciadorEmprestimos --> Emprestimo
    GerenciadorReservas --> Reserva
    GerenciadorAutores --> Autor
    GerenciadorCategorias --> Categoria
```

## Legenda

- **Herança 1:** `Usuario` é classe abstrata base para `Estudante` e `Professor`
- **Herança 2:** `OperacaoBiblioteca` é classe abstrata base para `Emprestimo` e `Reserva`
- **Polimorfismo 1:** `getTipoUsuario()` e `getInformacoesEspecificas()` em `Estudante` e `Professor`
- **Polimorfismo 2:** `getTipoOperacao()`, `getStatusOperacao()`, `podeFinalizar()` e `finalizarOperacao()` em `Emprestimo` e `Reserva`
- **Composição:** `Livro` contém `Autor` e `Categoria`
- **Associação:** `OperacaoBiblioteca` (e suas subclasses) associam `Usuario` e `Livro`
- **Enum:** `StatusEmprestimo` e `StatusReserva` definem estados
- **Controller:** Gerenciadores implementam operações CRUD

