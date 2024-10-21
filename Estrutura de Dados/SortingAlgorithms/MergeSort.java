public class MergeSort {

    // Main method to sort an array using merge sort
    public static void mergeSort(int[] array) {
        // If the array is empty or has a single element, it's already sorted
        if (array.length <= 1) {
            return;
        }

        // Find the middle index to split the array
        int mid = array.length / 2;

        // Create left and right subarrays
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        // Copy elements into the left and right subarrays
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        // Recursively sort both subarrays
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted subarrays back into the original array
        merge(array, left, right);
    }

    // Method to merge two sorted subarrays into one sorted array
    public static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Compare elements from both subarrays and merge them in sorted order
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++]; // Add the smaller element to the original array
            } else {
                array[k++] = right[j++]; // Add the smaller element to the original array
            }
        }

        // If there are remaining elements in the left subarray, add them
        while (i < left.length) {
            array[k++] = left[i++];
        }

        // If there are remaining elements in the right subarray, add them
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    // Main method to test the merge sort implementation
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10}; // Sample array to be sorted

        System.out.println("Unsorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        mergeSort(array); // Sort the array using merge sort

        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}