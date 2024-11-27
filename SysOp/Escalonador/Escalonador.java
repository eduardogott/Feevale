import java.util.*;

public class Escalonador {
    public List<Processo> processos;

    public Escalonador(List<Processo> processos) {
        this.processos = processos;
        processos.sort(Comparator.comparingInt(p -> p.tempoChegada));
    }

    public void executar() {
        PriorityQueue<Processo> fila = new PriorityQueue<>(
            Comparator.comparingInt((Processo p) -> p.prioridade).thenComparingInt(p -> p.tempoChegada)
        );

        int tempoAtual = 0;
        int totalEspera = 0;
        int numProcessos = processos.size();
        Processo processoAtual = null;

        while (!processos.isEmpty() || !fila.isEmpty() || processoAtual != null) {
            while (!processos.isEmpty() && processos.get(0).tempoChegada <= tempoAtual) {
                Processo p = processos.remove(0);
                fila.add(p);
                System.out.printf("\u001B[33m%-3s | Processo %d chegou à fila (Prioridade: %d, Burst: %d)\u001B[37m\n\n", tempoAtual, p.pid, p.prioridade, p.burst);
            }

            if (processoAtual != null && !fila.isEmpty() && fila.peek().prioridade < processoAtual.prioridade) {
                System.out.printf("\u001B[41m%-3s | Processo %d foi preemptado (Burst restante: %d)\u001B[0m\n\n", tempoAtual, processoAtual.pid, processoAtual.tempoRestante);
                fila.add(processoAtual);
                processoAtual = fila.poll();
                System.out.printf("\u001B[42m%-3s | Processo %d assumiu a CPU (Prioridade: %d, Burst restante: %d)\u001B[0m\n\n", tempoAtual, processoAtual.pid, processoAtual.prioridade, processoAtual.tempoRestante);
            }

            if (processoAtual == null && !fila.isEmpty()) {
                processoAtual = fila.poll();
                System.out.printf("\u001B[32m%-3s | Processo %d iniciou na CPU (Prioridade: %d, Burst: %d)\u001B[37m\n\n", tempoAtual, processoAtual.pid, processoAtual.prioridade, processoAtual.burst);
            }

            if (processoAtual != null) {
                processoAtual.tempoRestante--;
                if (processoAtual.tempoRestante == 0) {
                    tempoAtual++;
                    System.out.printf("\u001B[36m%-3s | Processo %d terminou (Tempo de Espera: %d)\u001B[37m\n\n", tempoAtual, processoAtual.pid, processoAtual.tempoEspera);
                    totalEspera += processoAtual.tempoEspera;
                    processoAtual = null;
                } else {
                    for (Processo p : fila) {
                        p.tempoEspera++;
                    }
                }
            }
            tempoAtual++;
        }

        double tme = (double) totalEspera / numProcessos;
        System.out.printf("\u001B[35mTempo Médio de Espera (TME): %.2f\u001B[37m\n\n", tme);
    }
}