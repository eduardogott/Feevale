// [AULA]

public class Aluno extends Pessoa {
    private int matricula;
    private String curso;

    public Aluno(String nome, String endereco, String nacionalidade,
                 int matricula, String curso) {
        super(nome, endereco, nacionalidade);
        this.matricula = matricula;
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public String getMatricula() {
        return matricula;
    }

    public String imprimirCurso() {
        String curso = String.format("Aluno[Curso: %s, Matricula: %d]", this.getCurso(), this.getMatricula());
        return curso;
    }
}