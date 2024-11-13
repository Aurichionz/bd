package aurichiobc.model;
public class Aluno {
    private String nome;
    private String cpf;
    private String email;
    private int anoNascimento;
    
        public Aluno (String nome, String cpf, String email, int anoNascimento){
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
            this.anoNascimento = anoNascimento;
        }
    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getAnoNascimento(){
        return anoNascimento;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setAnoNascimento(int anoNascimento){
        this.anoNascimento = anoNascimento;
    }
    
    
    @Override
    public String toString() {
        return "" + nome + "\nCPF: " + cpf + "\nEmail: " + email + "\nAno de Nascimento: " + anoNascimento;
    }

    public String toFileFormat() {
        return nome + ":" + cpf + ":" + email + ":" + anoNascimento;
    }
}


 