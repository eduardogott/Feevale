// [ATIVIDADE]

public class Funcionario {
    private String nome;
    private int idade;
    private String setor;
    private String cargo;
    private String endereco;

    public Funcionario(String nome, int idade, String setor, String cargo, String endereco) {
        this.nome = nome;
        this.idade = idade;
        this.setor = setor;
        this.cargo = cargo;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String imprimeDados() {
        String dados = String.format("Funcionario[Nome: %s, Idade: %d, Setor: %s, Cargo: %s, Endereco: %s]", this.getNome(), this.getIdade(), this.getSetor(), this.getCargo(), this.getEndereco());
        return dados;
    }
}
