public class SelectionSort {

    // This method performs selection sort on the input array and returns the sorted array.
    public static int[] selectionSort(int[] arr) {
        int n = arr.length;  // Get the length of the array.

        // Outer loop to iterate over the unsorted portion of the array.
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;  // Assume the current element (arr[i]) is the minimum.

            // Inner loop to find the smallest element in the unsorted portion of the array.
            for (int j = i + 1; j < n; j++) {
                // If a smaller element is found, update minIndex.
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // If a new minimum is found, swap it with the current element at index 'i'.
            if (minIndex != i) {
                int temp = arr[i];         // Store the current element in a temporary variable.
                arr[i] = arr[minIndex];    // Replace the current element with the minimum.
                arr[minIndex] = temp;      // Place the original element at the minIndex.
            }
        }

        return arr;  // Return the sorted array.
    }
}