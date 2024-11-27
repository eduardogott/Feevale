public class Processo {
    public int pid;
    public int tempoChegada;
    public int burst;
    public int prioridade;
    public int tempoRestante;
    public int tempoEspera;

    public Processo(int pid, int tempoChegada, int burst, int prioridade) {
        this.pid = pid;
        this.tempoChegada = tempoChegada;
        this.burst = burst;
        this.prioridade = prioridade;
        this.tempoRestante = burst;
        this.tempoEspera = 0;
    }
}