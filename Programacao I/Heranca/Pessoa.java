public class Pessoa {
    private String nome;
    private String endereco;
    private String nacionalidade;

    public Pessoa(String nome, String endereco, String nacionalidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.nacionalidade = nacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String imprimeDados() {
        String dados = String.format("Pessoa[Nome: %s, Endereco: %s, Nacionalidade: %s]", this.getNome(), this.getEndereco(), this.getNacionalidade());
        return dados;
    }
}