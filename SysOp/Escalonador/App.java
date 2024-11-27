//
// ESCALONADOR PARA CADEIRA DE SYSOP
// Eduardo Grohs Gottert, Vithor Oliveira Schuling e João Vitor Nunes da Silva
//

import java.util.List;

public class App {
    public static void main(String[] args) {
        String diretorioProcessos = "processos"; // Defina o diretório onde os arquivos de processos estão armazenados
        
        Interpretador interpretador = new Interpretador();
        List<Processo> processos = interpretador.carregarProcessos(diretorioProcessos);

        if (processos.isEmpty()) {
            System.out.println("Nenhum processo encontrado.");
            return;
        }

        System.out.printf("\n%-3s | %-8s | %-5s | %-10s\n", "PID", "Chegada", "Burst", "Prioridade");

        for (Processo p : processos) {
            System.out.printf("%-3d | %-8d | %-5d | %-10d\n", p.pid, p.tempoChegada, p.burst, p.prioridade);
        }

        System.out.println("\n\nCLK | Info\n");

        Escalonador escalonador = new Escalonador(processos);
        escalonador.executar();
    }
}