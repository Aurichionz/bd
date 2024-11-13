package aurichiobc.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Escola {
    private String nome;
    private String codigo; 
    private List<Aluno> alunos;  
    private static final String PASTA_DADOS = "dados";
    private static final String ARQUIVO_ALUNOS = PASTA_DADOS + File.separator + "Aluno.txt"; 

    public Escola(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;   
        this.alunos = new ArrayList<>();
        criarPastaSeNaoExistir(); 
        carregarAlunosDoArquivo(); 
    }

    private void criarPastaSeNaoExistir() {
        File pasta = new File(PASTA_DADOS);
        if (!pasta.exists()) {
            pasta.mkdirs(); 
        }
    }

    public boolean adicionarAluno(Aluno a) {
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(a.getCpf())) {
                return false; 
            }
        }
        alunos.add(a); 
        salvarAlunosNoArquivo(); 
        return true;
    }

    public List<Aluno> buscarAlunosNome(String nome) {
        List<Aluno> encontrados = new ArrayList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getNome().toLowerCase().contains(nome.toLowerCase())) { 
                encontrados.add(aluno);
                System.out.println("\n");
            }
        }
        return encontrados;
    }
    
    public Aluno buscarAlunoCpf(String cpf) {
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                return aluno;
            }
        }
        return null; 
    }

    public boolean removerAluno(String cpf) {
        for (Aluno aluno : alunos) {
            if (aluno.getCpf().equals(cpf)) {
                alunos.remove(aluno);  
                salvarAlunosNoArquivo();  
                return true;  
            }
        }
        return false; 
    }

    public String listar() {
        if (alunos.isEmpty()) {
            return "Nenhum aluno cadastrado.";
        }
        StringBuilder sb = new StringBuilder();
        for (Aluno aluno : alunos) {
            sb.append(aluno.toString()).append("\n\n");
        }
        return sb.toString();
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    private void carregarAlunosDoArquivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ALUNOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(":");
                if (dados.length == 4) {
                    String nome = dados[0];
                    String cpf = dados[1];
                    String email = dados[2];
                    int anoNascimento = Integer.parseInt(dados[3]);
                    Aluno aluno = new Aluno(nome, cpf, email, anoNascimento);
                    alunos.add(aluno);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados dos alunos: " + e.getMessage());
        }
    }

    private void salvarAlunosNoArquivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_ALUNOS))) {
            for (Aluno aluno : alunos) {
                bw.write(aluno.getNome() + ":" + aluno.getCpf() + ":" + aluno.getEmail() + ":" + aluno.getAnoNascimento());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
