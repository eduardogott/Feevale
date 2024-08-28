// [HOBBY]

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SearchingAlgorithms {
   
    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long timeFunction(int[] arr, int target, String function) {
        long startTime = System.nanoTime();
        if (function.equals("sequential")) {
            sequentialSearch(arr, target);
        } else if (function.equals("binary")) {
            binarySearch(arr, target);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long sequentialSearch(int[] arr, long target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static long binarySearch(int[] arr, long target) {
        long left = 0;
        long right = arr.length - 1;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (arr[(int) mid] == target) {
                return mid;
            } else if (arr[(int) mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int timesToRun = 100;
        int arraySize = 2_000_000_000;

        int[] arr = new int[arraySize];
        double startTime = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            if (i % 10_000_000 == 0) {
                clear();
                System.out.println("Gerando array (vetor)... " + (i) + "/" + arraySize);
            }
            arr[i] = i;
        }
        double endTime = System.nanoTime();
        long timeToInitializeArray = (long) (endTime - startTime);
        double timeToInitializeArrayMillis = timeToInitializeArray / 1_000_000.0;
        
        ArrayList<Long> sequentialSearchTimes = new ArrayList<>();
        ArrayList<Long> binarySearchTimes = new ArrayList<>();

        long largestNum = 0;
        long smallestNum = Long.MAX_VALUE;

        long seqTimeForLargestNum = 0;
        long seqTimeForSmallestNum = 0;

        long binTimeForLargestNum = 0;
        long binTimeForSmallestNum = 0;

        long estimatedTimeLeft = 0;

        for (int i = 0; i < timesToRun; i++) {
            clear();
            System.out.printf("\nTempo para inicializar array com %,d ítens: %,d ns (%,.2f ms)\n", arr.length, timeToInitializeArray, timeToInitializeArrayMillis);
            System.out.println("Executando... " + (i + 1) + "/" + timesToRun);
            System.out.println("Tempo restante estimado: " + estimatedTimeLeft/1_000_000 + " ms");
            
            Random rand = new Random();
            int target = rand.nextInt(arr.length);
            
            long sequential = timeFunction(arr, target, "sequential");
            long binary = timeFunction(arr, target, "binary");

            sequentialSearchTimes.add(sequential);
            binarySearchTimes.add(binary);

            if (target > largestNum) {
                largestNum = target;
                seqTimeForLargestNum = sequential;
                binTimeForLargestNum = binary;
            }
            if (target < smallestNum) {
                smallestNum = target;
                seqTimeForSmallestNum = sequential;
                binTimeForSmallestNum = binary;
            }

            estimatedTimeLeft = (long) ((timesToRun - i - 1) * (System.nanoTime() - startTime) / (i + 1));
        }

        double seqLargestMillis = seqTimeForLargestNum / 1_000_000.0;
        double seqSmallestMillis = seqTimeForSmallestNum / 1_000_000.0;
        double binLargestMillis = binTimeForLargestNum / 1_000_000.0;
        double binSmallestMillis = binTimeForSmallestNum / 1_000_000.0;

        long sequentialSearchSum = 0;
        long binarySearchSum = 0;
        for (int i = 0; i < timesToRun; i++) {
            sequentialSearchSum += sequentialSearchTimes.get(i);
            binarySearchSum += binarySearchTimes.get(i);
        }

        double averageSequentialSearchTime = sequentialSearchSum / timesToRun;
        double averageBinarySearchTime = binarySearchSum / timesToRun;
        double averageSequentialSearchTimeMillis = averageSequentialSearchTime / 1_000_000;
        double averageBinarySearchTimeMillis = averageBinarySearchTime / 1_000_000;
        double seqSearchSumMillis = averageSequentialSearchTimeMillis*timesToRun;
        double binSearchSumMillis = averageBinarySearchTimeMillis*timesToRun;

        long differenceNano = (long) ((averageSequentialSearchTime/averageBinarySearchTime - 1)*100);
        long differenceMillis = (long) ((averageSequentialSearchTimeMillis/averageBinarySearchTimeMillis - 1)*100);
        long differenceSmallest = (long) ((seqSmallestMillis/binSmallestMillis - 1)*100);
        long differenceLargest = (long) ((seqLargestMillis/binLargestMillis - 1)*100);
        long differenceTotalSum = (long) ((seqSearchSumMillis/binSearchSumMillis - 1)*100);
        
        System.out.printf("\nFinalizado! Foram buscados %,d ítens. Maior: %,d - Menor: %,d\n\n", timesToRun, largestNum, smallestNum);

        System.out.printf("%11s | %10s | %10s | %10s | %10s | %8s |\n", "Tipo", "Média (ns)", "Média (ms)", "Menor (ms)", "Maior (ms)", "Total (ms)");
        System.out.printf("%11s | %10d | %10.4f | %10.4f | %10.4f | %10.3f |\n", "Sequencial", (long) averageSequentialSearchTime, averageSequentialSearchTimeMillis, seqSmallestMillis, seqLargestMillis, seqSearchSumMillis);
        System.out.printf("%11s | %10d | %10.4f | %10.4f | %10.4f | %10.3f |\n", "Binária", (long) averageBinarySearchTime, averageBinarySearchTimeMillis, binSmallestMillis, binLargestMillis, binSearchSumMillis);
        System.out.printf("%11s | %10s | %10s | %10s | %10s | %10s |\n", "", "", "", "", "", "");
        System.out.printf("%11s | %9d%% | %9d%% | %9d%% | %9d%% | %9d%% |\n", "%Diferença", differenceNano, differenceMillis, differenceSmallest, differenceLargest, differenceTotalSum);
        System.out.printf("%11s | %9dx | %9dx | %9dx | %9dx | %9dx |\n\n", "*Diferença", differenceNano/100, differenceMillis/100, differenceSmallest/100, differenceLargest/100, differenceTotalSum/100);
        System.out.println("A diferença denota quantas vezes o algoritmo binário é em relação ao sequencial, percentualmente e em vezes.\n");
    }
}