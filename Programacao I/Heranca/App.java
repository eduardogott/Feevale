// [AULA]

public class App {
    public static void main(String[] args) throws Exception {
        Pessoa p1 = new Pessoa("Rodrigo", "NH", "BR");
        p1.imprimeDados();

        Aluno a1 = new Aluno("Rodrigo", "Campo Bom", "BR",
                             "412951", "Ciencia da Computacao");

        
        System.out.println(a1.imprimeDados());
        System.out.println(a1.imprimirCurso());
        
    }
}