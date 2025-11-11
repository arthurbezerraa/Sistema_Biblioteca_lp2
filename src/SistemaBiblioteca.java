import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import controller.GerenciadorAutores;
import controller.GerenciadorCategorias;
import controller.GerenciadorEmprestimos;
import controller.GerenciadorLivros;
import controller.GerenciadorReservas;
import controller.GerenciadorUsuarios;
import enums.StatusEmprestimo;
import exception.UsuarioNaoEncontradoException;
import model.Autor;
import model.Categoria;
import model.Emprestimo;
import model.Estudante;
import model.Livro;
import model.Professor;
import model.Reserva;
import model.Usuario;

public class SistemaBiblioteca {
    private GerenciadorUsuarios gerenciadorUsuarios;
    private GerenciadorLivros gerenciadorLivros;
    private GerenciadorEmprestimos gerenciadorEmprestimos;
    private GerenciadorReservas gerenciadorReservas;
    private GerenciadorAutores gerenciadorAutores;
    private GerenciadorCategorias gerenciadorCategorias;
    private Scanner scanner;

    public SistemaBiblioteca() {
        this.gerenciadorUsuarios = new GerenciadorUsuarios();
        this.gerenciadorLivros = new GerenciadorLivros();
        this.gerenciadorEmprestimos = new GerenciadorEmprestimos();
        this.gerenciadorReservas = new GerenciadorReservas();
        this.gerenciadorAutores = new GerenciadorAutores();
        this.gerenciadorCategorias = new GerenciadorCategorias();
        this.scanner = new Scanner(System.in);

        inicializarDadosExemplo();
    }

    public void executar() {
        System.out.println("=== SISTEMA DE GESTÃO DE BIBLIOTECA DIGITAL ===");
        System.out.println("Bem-vindo ao sistema de biblioteca!");
        
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        
        System.out.println("Obrigado por usar o Sistema de Biblioteca Digital!");
        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Gerenciar Usuários");
        System.out.println("2. Gerenciar Livros");
        System.out.println("3. Gerenciar Empréstimos");
        System.out.println("4. Gerenciar Reservas");
        System.out.println("5. Gerenciar Autores");
        System.out.println("6. Gerenciar Categorias");
        System.out.println("7. Relatórios");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                gerenciarUsuarios();
                break;
            case 2:
                gerenciarLivros();
                break;
            case 3:
                gerenciarEmprestimos();
                break;
            case 4:
                gerenciarReservas();
                break;
            case 5:
                gerenciarAutores();
                break;
            case 6:
                gerenciarCategorias();
                break;
            case 7:
                exibirRelatorios();
                break;
            case 0:
                System.out.println("Encerrando sistema...");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private void gerenciarUsuarios() {
        System.out.println("\n=== GERENCIAR USUÁRIOS ===");
        System.out.println("1. Cadastrar Estudante");
        System.out.println("2. Cadastrar Professor");
        System.out.println("3. Buscar Usuário");
        System.out.println("4. Listar Usuários");
        System.out.println("5. Atualizar Usuário");
        System.out.println("6. Remover Usuário");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                cadastrarEstudante();
                break;
            case 2:
                cadastrarProfessor();
                break;
            case 3:
                buscarUsuario();
                break;
            case 4:
                listarUsuarios();
                break;
            case 5:
                atualizarUsuario();
                break;
            case 6:
                removerUsuario();
                break;
        }
    }

    private void cadastrarEstudante() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Matrícula: ");
            String matricula = scanner.nextLine();
            System.out.print("Curso: ");
            String curso = scanner.nextLine();
            
            Estudante estudante = new Estudante(nome, email, cpf, matricula, curso);
            gerenciadorUsuarios.adicionarUsuario(estudante);
            System.out.println("Estudante cadastrado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar estudante: " + e.getMessage());
        }
    }

    private void cadastrarProfessor() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Departamento: ");
            String departamento = scanner.nextLine();
            System.out.print("Titulação: ");
            String titulacao = scanner.nextLine();
            
            Professor professor = new Professor(nome, email, cpf, departamento, titulacao);
            gerenciadorUsuarios.adicionarUsuario(professor);
            System.out.println("Professor cadastrado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }

    private void buscarUsuario() {
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();
        
        Usuario usuario = gerenciadorUsuarios.buscarPorCpf(cpf);
        if (usuario != null) {
            System.out.println("Usuário encontrado:");
            System.out.println(usuario);
            System.out.println("Tipo: " + usuario.getTipoUsuario());
            System.out.println("Informações específicas: " + usuario.getInformacoesEspecificas());
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }

    private void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        System.out.println("Total de usuários: " + gerenciadorUsuarios.getQuantidadeUsuarios());
        System.out.println("Estudantes: " + gerenciadorUsuarios.getQuantidadeEstudantes());
        System.out.println("Professores: " + gerenciadorUsuarios.getQuantidadeProfessores());
        
        for (Usuario usuario : gerenciadorUsuarios.listarUsuarios()) {
            System.out.println("- " + usuario);
        }
    }

    private void atualizarUsuario() {
        System.out.print("Digite o CPF do usuário a ser atualizado: ");
        String cpf = scanner.nextLine();
        
        Usuario usuario = gerenciadorUsuarios.buscarPorCpf(cpf);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        try {
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo email: ");
            String email = scanner.nextLine();
            
            usuario.setNome(nome);
            usuario.setEmail(email);
            
            System.out.println("Usuário atualizado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private void removerUsuario() {
        System.out.print("Digite o CPF do usuário a ser removido: ");
        String cpf = scanner.nextLine();
        
        try {
            gerenciadorUsuarios.removerUsuario(cpf);
            System.out.println("Usuário removido com sucesso!");
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void gerenciarLivros() {
        System.out.println("\n=== GERENCIAR LIVROS ===");
        System.out.println("1. Cadastrar Livro");
        System.out.println("2. Buscar Livro");
        System.out.println("3. Listar Livros");
        System.out.println("4. Listar Livros Disponíveis");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                cadastrarLivro();
                break;
            case 2:
                buscarLivro();
                break;
            case 3:
                listarLivros();
                break;
            case 4:
                listarLivrosDisponiveis();
                break;
        }
    }

    private void cadastrarLivro() {
        try {
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Ano de publicação: ");
            int anoPublicacao = Integer.parseInt(scanner.nextLine());

            System.out.print("Nome do autor: ");
            String nomeAutor = scanner.nextLine();
            Autor autor = gerenciadorAutores.buscarPorNome(nomeAutor);
            if (autor == null) {
                System.out.print("Nacionalidade do autor: ");
                String nacionalidade = scanner.nextLine();
                System.out.print("Data de nascimento do autor (DD/MM/AAAA): ");
                String dataNascimento = scanner.nextLine();
                autor = new Autor(nomeAutor, nacionalidade, dataNascimento);
                gerenciadorAutores.adicionarAutor(autor);
            }

            System.out.print("Nome da categoria: ");
            String nomeCategoria = scanner.nextLine();
            Categoria categoria = gerenciadorCategorias.buscarPorNome(nomeCategoria);
            if (categoria == null) {
                System.out.print("Descrição da categoria: ");
                String descricao = scanner.nextLine();
                categoria = new Categoria(nomeCategoria, descricao);
                gerenciadorCategorias.adicionarCategoria(categoria);
            }
            
            Livro livro = new Livro(isbn, titulo, autor, categoria, anoPublicacao);
            gerenciadorLivros.adicionarLivro(livro);
            System.out.println("Livro cadastrado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    private void buscarLivro() {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        
        Livro livro = gerenciadorLivros.buscarPorIsbn(isbn);
        if (livro != null) {
            System.out.println("Livro encontrado:");
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private void listarLivros() {
        System.out.println("\n=== LISTA DE LIVROS ===");
        System.out.println("Total de livros: " + gerenciadorLivros.getQuantidadeLivros());
        System.out.println("Disponíveis: " + gerenciadorLivros.getQuantidadeLivrosDisponiveis());
        System.out.println("Indisponíveis: " + gerenciadorLivros.getQuantidadeLivrosIndisponiveis());
        
        for (Livro livro : gerenciadorLivros.listarLivros()) {
            System.out.println("- " + livro);
        }
    }

    private void listarLivrosDisponiveis() {
        System.out.println("\n=== LIVROS DISPONÍVEIS ===");
        for (Livro livro : gerenciadorLivros.listarLivrosDisponiveis()) {
            System.out.println("- " + livro);
        }
    }

    private void gerenciarEmprestimos() {
        System.out.println("\n=== GERENCIAR EMPRÉSTIMOS ===");
        System.out.println("1. Realizar Empréstimo");
        System.out.println("2. Devolver Livro");
        System.out.println("3. Listar Empréstimos");
        System.out.println("4. Listar Empréstimos Ativos");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                realizarEmprestimo();
                break;
            case 2:
                devolverLivro();
                break;
            case 3:
                listarEmprestimos();
                break;
            case 4:
                listarEmprestimosAtivos();
                break;
        }
    }

    private void realizarEmprestimo() {
        try {
            System.out.print("CPF do usuário: ");
            String cpf = scanner.nextLine();
            Usuario usuario = gerenciadorUsuarios.buscarPorCpf(cpf);
            if (usuario == null) {
                System.out.println("Usuário não encontrado!");
                return;
            }
            
            System.out.print("ISBN do livro: ");
            String isbn = scanner.nextLine();
            Livro livro = gerenciadorLivros.buscarPorIsbn(isbn);
            if (livro == null) {
                System.out.println("Livro não encontrado!");
                return;
            }
            
            String dataEmprestimo = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String dataLimite = LocalDate.now().plusDays(14).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
            gerenciadorEmprestimos.realizarEmprestimo(usuario, livro, dataEmprestimo, dataLimite);
            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Data limite para devolução: " + dataLimite);
            
        } catch (Exception e) {
            System.out.println("Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    private void devolverLivro() {
        System.out.print("CPF do usuário: ");
        String cpf = scanner.nextLine();
        
        Usuario usuario = gerenciadorUsuarios.buscarPorCpf(cpf);
        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }
        
        System.out.println("Empréstimos ativos do usuário:");
        int i = 1;
        for (Emprestimo emprestimo : gerenciadorEmprestimos.buscarPorUsuario(usuario)) {
            if (emprestimo.getStatus() == StatusEmprestimo.ATIVO) {
                System.out.println(i + ". " + emprestimo);
                i++;
            }
        }
        
        if (i == 1) {
            System.out.println("Nenhum empréstimo ativo encontrado!");
            return;
        }
        
        System.out.print("Digite o número do empréstimo a ser devolvido: ");
        try {
            int numero = Integer.parseInt(scanner.nextLine()) - 1;
            List<Emprestimo> emprestimosAtivos = gerenciadorEmprestimos.buscarPorUsuario(usuario)
                    .stream()
                    .filter(e -> e.getStatus() == StatusEmprestimo.ATIVO)
                    .collect(java.util.stream.Collectors.toList());
            
            if (numero >= 0 && numero < emprestimosAtivos.size()) {
                Emprestimo emprestimo = emprestimosAtivos.get(numero);
                String dataDevolucao = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                gerenciadorEmprestimos.devolverLivro(emprestimo, dataDevolucao);
                System.out.println("Livro devolvido com sucesso!");
            } else {
                System.out.println("Número inválido!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Número inválido!");
        }
    }

    private void listarEmprestimos() {
        System.out.println("\n=== LISTA DE EMPRÉSTIMOS ===");
        System.out.println("Total de empréstimos: " + gerenciadorEmprestimos.getQuantidadeEmprestimos());
        
        for (Emprestimo emprestimo : gerenciadorEmprestimos.listarEmprestimos()) {
            System.out.println("- " + emprestimo);
        }
    }

    private void listarEmprestimosAtivos() {
        System.out.println("\n=== EMPRÉSTIMOS ATIVOS ===");
        for (Emprestimo emprestimo : gerenciadorEmprestimos.listarEmprestimosAtivos()) {
            System.out.println("- " + emprestimo);
        }
    }

    private void gerenciarReservas() {
        System.out.println("\n=== GERENCIAR RESERVAS ===");
        System.out.println("1. Fazer Reserva");
        System.out.println("2. Listar Reservas");
        System.out.println("3. Listar Reservas Ativas");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                fazerReserva();
                break;
            case 2:
                listarReservas();
                break;
            case 3:
                listarReservasAtivas();
                break;
        }
    }

    private void fazerReserva() {
        try {
            System.out.print("CPF do usuário: ");
            String cpf = scanner.nextLine();
            Usuario usuario = gerenciadorUsuarios.buscarPorCpf(cpf);
            if (usuario == null) {
                System.out.println("Usuário não encontrado!");
                return;
            }
            
            System.out.print("ISBN do livro: ");
            String isbn = scanner.nextLine();
            Livro livro = gerenciadorLivros.buscarPorIsbn(isbn);
            if (livro == null) {
                System.out.println("Livro não encontrado!");
                return;
            }
            
            String dataReserva = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            gerenciadorReservas.fazerReserva(usuario, livro, dataReserva);
            System.out.println("Reserva realizada com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao fazer reserva: " + e.getMessage());
        }
    }

    private void listarReservas() {
        System.out.println("\n=== LISTA DE RESERVAS ===");
        System.out.println("Total de reservas: " + gerenciadorReservas.getQuantidadeReservas());
        
        for (Reserva reserva : gerenciadorReservas.listarReservas()) {
            System.out.println("- " + reserva);
        }
    }

    private void listarReservasAtivas() {
        System.out.println("\n=== RESERVAS ATIVAS ===");
        for (Reserva reserva : gerenciadorReservas.listarReservasAtivas()) {
            System.out.println("- " + reserva);
        }
    }

    private void gerenciarAutores() {
        System.out.println("\n=== GERENCIAR AUTORES ===");
        System.out.println("1. Cadastrar Autor");
        System.out.println("2. Listar Autores");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                cadastrarAutor();
                break;
            case 2:
                listarAutores();
                break;
        }
    }

    private void cadastrarAutor() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Nacionalidade: ");
            String nacionalidade = scanner.nextLine();
            System.out.print("Data de nascimento (DD/MM/AAAA): ");
            String dataNascimento = scanner.nextLine();
            
            Autor autor = new Autor(nome, nacionalidade, dataNascimento);
            gerenciadorAutores.adicionarAutor(autor);
            System.out.println("Autor cadastrado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar autor: " + e.getMessage());
        }
    }

    private void listarAutores() {
        System.out.println("\n=== LISTA DE AUTORES ===");
        System.out.println("Total de autores: " + gerenciadorAutores.getQuantidadeAutores());
        
        for (Autor autor : gerenciadorAutores.listarAutores()) {
            System.out.println("- " + autor);
        }
    }

    private void gerenciarCategorias() {
        System.out.println("\n=== GERENCIAR CATEGORIAS ===");
        System.out.println("1. Cadastrar Categoria");
        System.out.println("2. Listar Categorias");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                cadastrarCategoria();
                break;
            case 2:
                listarCategorias();
                break;
        }
    }

    private void cadastrarCategoria() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            
            Categoria categoria = new Categoria(nome, descricao);
            gerenciadorCategorias.adicionarCategoria(categoria);
            System.out.println("Categoria cadastrada com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar categoria: " + e.getMessage());
        }
    }

    private void listarCategorias() {
        System.out.println("\n=== LISTA DE CATEGORIAS ===");
        System.out.println("Total de categorias: " + gerenciadorCategorias.getQuantidadeCategorias());
        
        for (Categoria categoria : gerenciadorCategorias.listarCategorias()) {
            System.out.println("- " + categoria);
        }
    }

    private void exibirRelatorios() {
        System.out.println("\n=== RELATÓRIOS DO SISTEMA ===");
        System.out.println("Usuários cadastrados: " + gerenciadorUsuarios.getQuantidadeUsuarios());
        System.out.println("  - Estudantes: " + gerenciadorUsuarios.getQuantidadeEstudantes());
        System.out.println("  - Professores: " + gerenciadorUsuarios.getQuantidadeProfessores());
        System.out.println("Livros cadastrados: " + gerenciadorLivros.getQuantidadeLivros());
        System.out.println("  - Disponíveis: " + gerenciadorLivros.getQuantidadeLivrosDisponiveis());
        System.out.println("  - Indisponíveis: " + gerenciadorLivros.getQuantidadeLivrosIndisponiveis());
        System.out.println("Empréstimos realizados: " + gerenciadorEmprestimos.getQuantidadeEmprestimos());
        System.out.println("Reservas realizadas: " + gerenciadorReservas.getQuantidadeReservas());
        System.out.println("Autores cadastrados: " + gerenciadorAutores.getQuantidadeAutores());
        System.out.println("Categorias cadastradas: " + gerenciadorCategorias.getQuantidadeCategorias());
    }

    private void inicializarDadosExemplo() {
        try {

            Categoria categoria1 = new Categoria("Ficção", "Livros de ficção científica e fantasia");
            Categoria categoria2 = new Categoria("Técnico", "Livros técnicos e acadêmicos");
            gerenciadorCategorias.adicionarCategoria(categoria1);
            gerenciadorCategorias.adicionarCategoria(categoria2);

            Autor autor1 = new Autor("Jorge Amado", "Brasileira", "10/08/1912");
            Autor autor2 = new Autor("Isaac Asimov", "Americana", "02/01/1920");
            gerenciadorAutores.adicionarAutor(autor1);
            gerenciadorAutores.adicionarAutor(autor2);

            Livro livro1 = new Livro("978-85-359-0277-5", "Capitães da Areia", autor1, categoria1, 1937);
            Livro livro2 = new Livro("978-85-359-0278-2", "Fundação", autor2, categoria1, 1951);
            gerenciadorLivros.adicionarLivro(livro1);
            gerenciadorLivros.adicionarLivro(livro2);

            Estudante estudante1 = new Estudante("João Silva", "joao@email.com", "11144477735", "2023001", "Ciência da Computação");
            Professor professor1 = new Professor("Maria Santos", "maria@email.com", "12345678909", "Informática", "Doutora");
            gerenciadorUsuarios.adicionarUsuario(estudante1);
            gerenciadorUsuarios.adicionarUsuario(professor1);
            
        } catch (Exception e) {
            System.out.println("Erro ao inicializar dados de exemplo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SistemaBiblioteca sistema = new SistemaBiblioteca();
        sistema.executar();
    }
}
