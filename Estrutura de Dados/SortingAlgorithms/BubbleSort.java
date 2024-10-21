public class BubbleSort {
    // Method to perform bubble sort on an array
    public static int[] bubbleSort(int[] arr) {
        int n = arr.length; // Get the length of the array
        boolean swapped; // Flag to track if a swap has occurred

        // Outer loop for each pass through the array
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Reset swapped flag for this pass

            // Inner loop for comparing adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                // If the current element is greater than the next, swap them
                if (arr[j] > arr[j + 1]) {
                    // Swap elements using a temporary variable
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // A swap occurred
                }
            }
            // If no elements were swapped, the array is sorted, so we can break early
            if (!swapped) break;
        }

        return arr; // Return the sorted array
    }
}