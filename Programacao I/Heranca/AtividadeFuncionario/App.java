// [ATIVIDADE]

import java.util.SortedMap;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) {
        SortedMap<String, Integer> linguagens = new TreeMap<String, Integer>(); 
        linguagens.put("Python", 9);
        linguagens.put("Java", 7);
        linguagens.put("C++", 2);
        Programador p1 = new Programador("Eduardo", 17, "TI", "Senior", "Campo Bom", linguagens);

        String dados = p1.imprimeDados();
        String linguagensToPrint = p1.imprimeLinguagens();

        System.out.println(dados);
        System.out.println(linguagensToPrint);
    }
}
