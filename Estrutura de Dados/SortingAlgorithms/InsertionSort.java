public class InsertionSort {

    // This method performs an insertion sort on the input array and returns the sorted array.
    public static int[] insertionSort(int[] arr) {
        int n = arr.length; // Get the length of the array.

        // Loop through the array starting from the second element (index 1).
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // Store the current element in 'key'.
            int j = i - 1;    // Set 'j' to point to the element just before 'i'.

            // Shift elements of arr[0...i-1] that are greater than 'key' to one position ahead.
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Move the element one position to the right.
                j--;                 // Move 'j' one position to the left.
            }

            // Place the 'key' at the correct position in the sorted subarray.
            arr[j + 1] = key;
        }

        return arr; // Return the sorted array.
    }
}
