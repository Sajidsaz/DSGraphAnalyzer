import java.util.Scanner;
 
/**
 * Searching algorithms: linear search and binary search.
 * Both count their steps (comparisons) and measure their running time.
 */
public class SearchOps {
 
    /** Checks elements one by one from the start. Time complexity O(n). */
    public static SearchResult linearSearch(int[] arr, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int foundIndex = -1;
 
        for (int i = 0; i < arr.length; i++) {
            steps++;
            if (arr[i] == target) {
                foundIndex = i;
                break;
            }
        }
        return new SearchResult(foundIndex, steps, System.nanoTime() - start);
    }
 
    /**
     * Repeatedly halves the search range. The array MUST be sorted.
     * Time complexity O(log n).
     */
    public static SearchResult binarySearch(int[] arr, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int foundIndex = -1;
        int low = 0;
        int high = arr.length - 1;
 
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                foundIndex = mid;
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(foundIndex, steps, System.nanoTime() - start);
    }
}