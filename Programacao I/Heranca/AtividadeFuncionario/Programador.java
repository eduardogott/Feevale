// [ATIVIDADE]

import java.util.SortedMap;

public class Programador extends Funcionario {
    private SortedMap<String, Integer> linguagens;

    public Programador(String nome, int idade, String setor, String cargo, String endereco,
            SortedMap<String, Integer> linguagens) {
        super(nome, idade, setor, cargo, endereco);
        this.linguagens = linguagens;
    }

    public SortedMap<String, Integer> getLinguagens() {
        return linguagens;
    }

    public void setLinguagens(SortedMap<String, Integer> linguagens) {
        this.linguagens = linguagens;
    }

    public String imprimeLinguagens() {
        String linguagensParaRetorno = "Linguagens[";
        for (String linguagem : linguagens.keySet()) {
            linguagensParaRetorno += String.format("%s: %d, ", linguagem, linguagens.get(linguagem));
        }
        linguagensParaRetorno = linguagensParaRetorno.replaceAll(", $", "");
        linguagensParaRetorno += "]";

        return linguagensParaRetorno;
    }
    
}
