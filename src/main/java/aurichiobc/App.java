package aurichiobc;
import java.util.List;
import java.util.Scanner;

import aurichiobc.model.Aluno;
import aurichiobc.model.Escola; 

public class App {
    public static void main(String[] args) {
        Escola escola = new Escola("Escola Aurichio", "1473-2589");
        Scanner scanner = new Scanner(System.in);

        while (true) {
           
            System.out.println("\n === Menu Escola Aurichio ===");
            System.out.println("1) Adicionar Aluno");
            System.out.println("2) Buscar Aluno por Nome");
            System.out.println("3) Buscar Aluno por CPF");
            System.out.println("4) Remover Aluno");
            System.out.println("5) Listar Alunos");
            System.out.println("6) Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("\nNome do aluno: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Ano de nascimento: ");
                    int anoNascimento = scanner.nextInt();
                    scanner.nextLine(); 

                    Aluno aluno = new Aluno(nome, cpf, email, anoNascimento);
                    if (escola.adicionarAluno(aluno)) {
                        System.out.println("\nAluno adicionado com sucesso!!");
                        scanner.nextLine(); 
                    } else {
                        System.out.println("\nErro: Já existe um aluno com esse CPF.");
                        scanner.nextLine(); 
                    }
                    break;
                    
                    case 2:
                    System.out.print("\nDigite o nome do aluno: ");
                    String nomeBusca = scanner.nextLine();
                    List<Aluno> alunosEncontrados = escola.buscarAlunosNome(nomeBusca);
                    if (!alunosEncontrados.isEmpty()) {
                        System.out.println("\nAlunos encontrados:");
                        for (Aluno alunoEncontrado : alunosEncontrados) {
                            System.out.println(alunoEncontrado);  
                            System.out.println(); 
                        }
                    } else {
                        System.out.println("\nNenhum aluno encontrado com esse nome.");
                    }
                    break;


                case 3:
                    System.out.print("\nDigite o CPF do aluno: ");
                    String cpfBusca = scanner.nextLine();
                    Aluno alunoCpf = escola.buscarAlunoCpf(cpfBusca);
                    if (alunoCpf != null) {
                        System.out.println("\nAluno encontrado: " + alunoCpf);
                        scanner.nextLine(); 
                    } else {
                        System.out.println("\nAluno não encontrado...");
                        scanner.nextLine(); 
                    }
                    break;

                case 4:
                    System.out.print("\nDigite o CPF do aluno a ser removido: ");
                    String cpfRemover = scanner.nextLine();
                    if (escola.removerAluno(cpfRemover)) {
                        System.out.println("\nAluno removido com sucesso!");
                        scanner.nextLine(); 
                    } else {
                        System.out.println("\nAluno não encontrado...");
                        scanner.nextLine(); 
                    }
                    break;

                case 5:
                    System.out.println("\nLista de Alunos:");
                    System.out.println(escola.listar());
                    break;

                case 6:
                    System.out.println("\nSaindo do sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
                    scanner.nextLine(); 
            }
        }
    }
}