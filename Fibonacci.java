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
        int[] numbersToSearch = {5, 10, 20, 30, 40};

        SortedMap<Integer, Long> recursiveFib = new TreeMap<Integer, Long>();
        SortedMap<Integer, Long> iterativeFib = new TreeMap<Integer, Long>();

        for (int number : numbersToSearch) {
            long timeForRecursive = timeFunction(number, "recursive");
            long timeForIterative = timeFunction(number, "iterative");

            recursiveFib.put(number, timeForRecursive);
            iterativeFib.put(number, timeForIterative);

        }

        for (int key : recursiveFib.keySet()) {
            System.out.println(key);
            System.out.println(recursiveFib.get(key));
            System.out.println(iterativeFib.get(key));
        }
    }
}