import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Heapsort {
  private static volatile int comparisons;
  private static volatile int exchanges;

  /**
   * Empirically verify the efficency of various sorting methods
   * using toy data for the best case, worst case, and average
   * case.
   */
  public static void main(String[] args) {
    comparisons = 0;
    exchanges = 0;
    heapSort(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    System.out.println(String.format("Heap Sort best case: %d %d", exchanges, comparisons));

    comparisons = 0;
    exchanges = 0;
    heapSort(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
    System.out.println(String.format("Heap Sort worst case: %d %d", exchanges, comparisons));

    comparisons = 0;
    exchanges = 0;
    heapSort(Arrays.asList(2, 4, 5, 1, 8, 0, 7, 6, 3, 9));
    heapSort(Arrays.asList(0, 6, 4, 7, 9, 8, 3, 2, 1, 5));
    heapSort(Arrays.asList(9, 0, 3, 6, 4, 7, 8, 2, 1, 5));
    heapSort(Arrays.asList(0, 2, 7, 9, 3, 6, 5, 4, 1, 8));
    heapSort(Arrays.asList(3, 8, 2, 4, 7, 5, 0, 6, 1, 9));
    System.out.println(String.format("Heap Sort average case: %d %d", exchanges / 5, comparisons / 5));

    try {
      comparisons = 0;
      exchanges = 0;
      heapSort(Files.readAllLines(Paths.get("testData", "bestCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Heap Sort best case: %d %d", exchanges, comparisons));

      comparisons = 0;
      exchanges = 0;
      heapSort(Files.readAllLines(Paths.get("testData", "worstCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Heap Sort worst case: %d %d", exchanges, comparisons));

      comparisons = 0;
      exchanges = 0;
      heapSort(Files.readAllLines(Paths.get("testData", "averageCase_1.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      heapSort(Files.readAllLines(Paths.get("testData", "averageCase_2.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      heapSort(Files.readAllLines(Paths.get("testData", "averageCase_3.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      heapSort(Files.readAllLines(Paths.get("testData", "averageCase_4.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      heapSort(Files.readAllLines(Paths.get("testData", "averageCase_5.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Heap Sort average case: %d %d", exchanges / 5, comparisons / 5));
    }
    catch (Exception error) {
      System.out.println("Could not stream file(s): ");
      System.out.println(error);
    }
  }


  /**
   * toHeap takes a list of elements and orders them such they they obey the
   * heap property, where each nodes children are at root * 2 + 1 and
   * root * 2 + 2, and both of these children are less than the root.
   * 
   * @param <T> Comparable - any type implementing comparable
   * @param arr - The list to turn into a heap
   * @param size - the size of the sub-heap to process
   * @param root - the index of the root of the sub-heap to process
   */
  private static <T extends Comparable<T>> void toHeap(List<T> arr, int size, int root) {
    int leftIndex = 2 * root + 1;
    int rightIndex = 2 * root + 2;
    int maximum = root;

    if (leftIndex < size && arr.get(leftIndex).compareTo(arr.get(maximum)) > 0) maximum = leftIndex;
    if (rightIndex < size && arr.get(rightIndex).compareTo(arr.get(maximum)) > 0) maximum = rightIndex;
    
    if (root != maximum) {
      exchanges += 1;
      Collections.swap(arr, root, maximum);
      toHeap(arr, size, maximum);
    }
  }


  /**
   * heapsort constructs a heap out of a provided list of comparable items
   * and then pops the root of the heap off.  This item is then attached
   * to the end of the array in a similar manner to selection sort.  This
   * process contines on the remaining portion of the list untill it is ordered.
   * 
   * @param <T> Comparable - any type implementing comparable
   * @param arr - the list to sort using a heapsort
   */
  private static <T extends Comparable<T>> void heapSort(List<T> arr) {
    for (int i = arr.size() / 2 - 1; i >= 0; i -= 1) {
      toHeap(arr, arr.size(), i);
    }

    for (int i = arr.size() - 1; i >= 0; i -= 1) {
      comparisons += 3;
      Collections.swap(arr, 0, i);
      toHeap(arr, i, 0);
    }
  }
}