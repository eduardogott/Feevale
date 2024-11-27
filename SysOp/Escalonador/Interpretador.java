import java.io.*;
import java.util.*;

public class Interpretador {
    public List<Processo> carregarProcessos(String diretorio) {
        List<Processo> processos = new ArrayList<>();
        File pasta = new File(diretorio);
        
        if (!pasta.exists() || !pasta.isDirectory()) {
            System.out.println("Diretório inválido.");
            return processos;
        }
        
        File[] arquivos = pasta.listFiles((dir, nome) -> nome.startsWith("processo_") && nome.endsWith(".txt"));
        if (arquivos == null) return processos;

        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                int pid = Integer.parseInt(arquivo.getName().replaceAll("[^0-9]", ""));
                int tempoChegada = 0, burst = 0, prioridade = 0;

                String linha;
                while ((linha = br.readLine()) != null) {
                    String[] partes = linha.split("=");
                    switch (partes[0].trim()) {
                        case "TEMPO_CHEGADA":
                            tempoChegada = Integer.parseInt(partes[1].trim());
                            break;
                        case "BURST":
                            burst = Integer.parseInt(partes[1].trim());
                            break;
                        case "PRIORIDADE":
                            prioridade = Integer.parseInt(partes[1].trim());
                            break;
                    }
                }
                processos.add(new Processo(pid, tempoChegada, burst, prioridade));
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
            }
        }
        return processos;
    }
}