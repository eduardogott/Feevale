import java.io.IOException;
import java.util.SortedMap; 
import java.util.TreeMap;

public class Fibonacci {

    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long timeFunction(int n, String function) {
        long startTime = System.nanoTime();
        if (function.equals("recursive")) {
            fibonacciRecursive(n);
        } else if (function.equals("iterative")) {
            fibonacciIterative(n);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static long fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        long a = 0;
        long b = 1;
        long fib = 0;

        for (int i = 2; i <= n; i++) {
            fib = a + b;
            a = b;
            b = fib;
        }

        return fib;
    }

    public static void main(String[] args) {
        int[] numbersToSearch = {5, 10, 20, 30, 40, 45, 50, 55};

        SortedMap<Integer, Long> recursiveFib = new TreeMap<Integer, Long>();
        SortedMap<Integer, Long> iterativeFib = new TreeMap<Integer, Long>();

        for (int number : numbersToSearch) {
            clear();
            for (int _number : numbersToSearch) {
                if (_number != number) {
                    System.out.println(" " + _number);
                } else {
                    System.out.println("> " + _number);
                }
            }
            System.out.println("\nCalculando Fibonacci de " + number + "...");
            long timeForRecursive = timeFunction(number, "recursive");
            long timeForIterative = timeFunction(number, "iterative");

            recursiveFib.put(number, timeForRecursive);
            iterativeFib.put(number, timeForIterative);

        }

        clear();
        System.out.printf("%6s | %12s | %10s | %13s | %13s\n", "n° Fib", "Rec. (ms)", "Iter. (ms)", "Diff. (%)", "Diff. (x)");
        System.out.printf("%6s | %12s | %10s | %13s | %13s \n", "", "", "", "", "");
        for (int key : recursiveFib.keySet()) {
            System.out.printf("%6d | %12.4f | %10.4f | %13.2f | %13.2f\n", key, ((double) recursiveFib.get(key)) / 1000000, ((double)iterativeFib.get(key)) / 1000000, ((double) recursiveFib.get(key) / iterativeFib.get(key)) * 100, (double) recursiveFib.get(key) / iterativeFib.get(key));
        }
        System.out.println("\n");
    }
}