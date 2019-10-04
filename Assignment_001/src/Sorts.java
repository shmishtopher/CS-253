import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Sorts {
  public static void main(String[] args) {
    /**
     * Empirically verify the efficency of various sorting methods
     * using toy data for the best case, worst case, and average
     * case.
     */
    Record bubbleSortBestCase = bubbleSort(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    Record bubbleSortWorstCase = bubbleSort(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
    List<Record> bubbleSortAverageCase = Arrays.asList(
      bubbleSort(Arrays.asList(2, 4, 5, 1, 8, 0, 7, 6, 3, 9)),
      bubbleSort(Arrays.asList(0, 6, 4, 7, 9, 8, 3, 2, 1, 5)),
      bubbleSort(Arrays.asList(9, 0, 3, 6, 4, 7, 8, 2, 1, 5)),
      bubbleSort(Arrays.asList(0, 2, 7, 9, 3, 6, 5, 4, 1, 8)),
      bubbleSort(Arrays.asList(3, 8, 2, 4, 7, 5, 0, 6, 1, 9))
    );

    Record selectionSortBestCase = selectionSort(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    Record selectionSortWorstCase = selectionSort(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
    List<Record> selectionSortAverageCase = Arrays.asList(
      selectionSort(Arrays.asList(2, 4, 5, 1, 8, 0, 7, 6, 3, 9)),
      selectionSort(Arrays.asList(0, 6, 4, 7, 9, 8, 3, 2, 1, 5)),
      selectionSort(Arrays.asList(9, 0, 3, 6, 4, 7, 8, 2, 1, 5)),
      selectionSort(Arrays.asList(0, 2, 7, 9, 3, 6, 5, 4, 1, 8)),
      selectionSort(Arrays.asList(3, 8, 2, 4, 7, 5, 0, 6, 1, 9))
    );

    Record insertionSortBestCase = insertionSort(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    Record insertionSortWorstCase = insertionSort(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
    List<Record> insertionSortAverageCase = Arrays.asList(
      insertionSort(Arrays.asList(2, 4, 5, 1, 8, 0, 7, 6, 3, 9)),
      insertionSort(Arrays.asList(0, 6, 4, 7, 9, 8, 3, 2, 1, 5)),
      insertionSort(Arrays.asList(9, 0, 3, 6, 4, 7, 8, 2, 1, 5)),
      insertionSort(Arrays.asList(0, 2, 7, 9, 3, 6, 5, 4, 1, 8)),
      insertionSort(Arrays.asList(3, 8, 2, 4, 7, 5, 0, 6, 1, 9))
    );

    System.out.println("\n\nToy Data: n = 10");
    System.out.println("\t\t\tBest Case\t\tWorst Case\t\tAverage Case");
    System.out.println("Algorithm\t| exchanges comparisons\t| exchanges comparisons\t| exchanges comparisons");
    
    System.out.print("BubbleSort\t");
    System.out.print(String.format("| %d\t    %d\t\t", bubbleSortBestCase.exchanges, bubbleSortBestCase.comparisons));
    System.out.print(String.format("| %d\t    %d\t\t", bubbleSortWorstCase.exchanges, bubbleSortWorstCase.comparisons));
    System.out.print(String.format("| %d\t    %d\t\t\n",
      bubbleSortAverageCase.stream().map(x -> x.exchanges).reduce(0, Integer::sum) / 5,
      bubbleSortAverageCase.stream().map(x -> x.comparisons).reduce(0, Integer::sum) / 5
    ));

    System.out.print("SelectionSort\t");
    System.out.print(String.format("| %d\t    %d\t\t", selectionSortBestCase.exchanges, selectionSortBestCase.comparisons));
    System.out.print(String.format("| %d\t    %d\t\t", selectionSortWorstCase.exchanges, selectionSortWorstCase.comparisons));
    System.out.print(String.format("| %d\t    %d\t\t\n",
      selectionSortAverageCase.stream().map(x -> x.exchanges).reduce(0, Integer::sum) / 5,
      selectionSortAverageCase.stream().map(x -> x.comparisons).reduce(0, Integer::sum) / 5
    ));

    System.out.print("InsertionSort\t");
    System.out.print(String.format("| %d\t    %d\t\t", insertionSortBestCase.exchanges, insertionSortBestCase.comparisons));
    System.out.print(String.format("| %d\t    %d\t\t", insertionSortWorstCase.exchanges, insertionSortWorstCase.comparisons));
    System.out.print(String.format("| %d\t    %d\t\t\n",
      insertionSortAverageCase.stream().map(x -> x.exchanges).reduce(0, Integer::sum) / 5,
      insertionSortAverageCase.stream().map(x -> x.comparisons).reduce(0, Integer::sum) / 5
    ));


    /**
     * Empirically verify the efficency of various sorting methods
     * using test data for the best case, worst case, and average
     * case.  This block utilizes Java streams to collect files
     * into lists containing integers.
     */
    try {
      System.out.println("\n\nTest Data: n = 2000");
      bubbleSortBestCase = bubbleSort(Files.readAllLines(Paths.get("testData", "bestCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      bubbleSortWorstCase = bubbleSort(Files.readAllLines(Paths.get("testData", "worstCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      bubbleSortAverageCase = Arrays.asList(
        bubbleSort(Files.readAllLines(Paths.get("testData", "averageCase_1.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        bubbleSort(Files.readAllLines(Paths.get("testData", "averageCase_2.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        bubbleSort(Files.readAllLines(Paths.get("testData", "averageCase_3.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        bubbleSort(Files.readAllLines(Paths.get("testData", "averageCase_4.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        bubbleSort(Files.readAllLines(Paths.get("testData", "averageCase_5.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()))
      );

      selectionSortBestCase = selectionSort(Files.readAllLines(Paths.get("testData", "bestCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      selectionSortWorstCase = selectionSort(Files.readAllLines(Paths.get("testData", "worstCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      selectionSortAverageCase = Arrays.asList(
        selectionSort(Files.readAllLines(Paths.get("testData", "averageCase_1.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        selectionSort(Files.readAllLines(Paths.get("testData", "averageCase_2.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        selectionSort(Files.readAllLines(Paths.get("testData", "averageCase_3.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        selectionSort(Files.readAllLines(Paths.get("testData", "averageCase_4.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        selectionSort(Files.readAllLines(Paths.get("testData", "averageCase_5.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()))
      );

      insertionSortBestCase = insertionSort(Files.readAllLines(Paths.get("testData", "bestCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      insertionSortWorstCase = insertionSort(Files.readAllLines(Paths.get("testData", "worstCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      insertionSortAverageCase = Arrays.asList(
        insertionSort(Files.readAllLines(Paths.get("testData", "averageCase_1.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        insertionSort(Files.readAllLines(Paths.get("testData", "averageCase_2.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        insertionSort(Files.readAllLines(Paths.get("testData", "averageCase_3.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        insertionSort(Files.readAllLines(Paths.get("testData", "averageCase_4.txt")).stream().map(Integer::parseInt).collect(Collectors.toList())),
        insertionSort(Files.readAllLines(Paths.get("testData", "averageCase_5.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()))
      );

      System.out.println("\t\t\tBest Case\t\tWorst Case\t\tAverage Case");
      System.out.println("Algorithm\t| exchanges comparisons\t| exchanges comparisons\t| exchanges comparisons");
      
      System.out.print("BubbleSort\t");
      System.out.print(String.format("| %d\t    %d\t", bubbleSortBestCase.exchanges, bubbleSortBestCase.comparisons));
      System.out.print(String.format("| %d\t%d\t", bubbleSortWorstCase.exchanges, bubbleSortWorstCase.comparisons));
      System.out.print(String.format("| %d\t%d\t\t\n",
        bubbleSortAverageCase.stream().map(x -> x.exchanges).reduce(0, Integer::sum) / 5,
        bubbleSortAverageCase.stream().map(x -> x.comparisons).reduce(0, Integer::sum) / 5
      ));

      System.out.print("SelectionSort\t");
      System.out.print(String.format("| %d\t    %d\t", selectionSortBestCase.exchanges, selectionSortBestCase.comparisons));
      System.out.print(String.format("| %d\t\t%d\t", selectionSortWorstCase.exchanges, selectionSortWorstCase.comparisons));
      System.out.print(String.format("| %d\t\t%d\t\t\n",
        selectionSortAverageCase.stream().map(x -> x.exchanges).reduce(0, Integer::sum) / 5,
        selectionSortAverageCase.stream().map(x -> x.comparisons).reduce(0, Integer::sum) / 5
      ));

      System.out.print("InsertionSort\t");
      System.out.print(String.format("| %d\t    %d\t", insertionSortBestCase.exchanges, insertionSortBestCase.comparisons));
      System.out.print(String.format("| %d\t%d\t", insertionSortWorstCase.exchanges, insertionSortWorstCase.comparisons));
      System.out.print(String.format("| %d\t%d\t\t\n",
        insertionSortAverageCase.stream().map(x -> x.exchanges).reduce(0, Integer::sum) / 5,
        insertionSortAverageCase.stream().map(x -> x.comparisons).reduce(0, Integer::sum) / 5
      ));
    }
    catch (Exception error) {
      System.out.println("Could not stream file(s): ");
      System.out.println(error);
    }
  }


  /**
   * BubbleSort - In bubble sort, unsorted elements in a list gradually bubble
   * up into their place.  Bubble sort repeatly compares ajacent elements,
   * swapping them if they are out of order.  This process continues until no
   * more swaps are nessasary.
   */
  private static <T extends Comparable<T>> Record bubbleSort(List<T> list) {
    Record record = new Record();

    boolean shouldContinue = false;
    for (int i = 0; i < list.size() - 1; i += 1) {
      shouldContinue = false;
      for (int j = 0; j < list.size() - i - 1; j += 1) {
        record.comparisons += 1;
        if (list.get(j).compareTo(list.get(j + 1)) > 0) {
          record.exchanges += 1;
          shouldContinue = true;
          Collections.swap(list, j, j + 1);
        }
      }

      if (!shouldContinue) {
        break;
      }
    }

    return record;
  }


  /**
   * SelectionSort - In selection sort, the list is partitioned in two groups,
   * with the sorted elements on the left and unsorted elements of the right.
   * items from the unsorted sublit are placed in order into the sorted sublist,
   * growing it to the right until the entire list is sorted.
   */
  private static <T extends Comparable<T>> Record selectionSort(List<T> list) {
    Record record = new Record();

    for (int i = 0; i < list.size() - 1; i += 1) {
      int minimumIndex = i;
      for (int j = i + 1; j < list.size(); j += 1) {
        record.comparisons += 1;
        if (list.get(j).compareTo(list.get(minimumIndex)) < 0) {
          minimumIndex = j;
        }
      }
      record.exchanges += 1;
      Collections.swap(list, i, minimumIndex);
    }

    return record;
  }


  /**
   * InsertionSort - In insertion sort, We start with an empty left side and
   * the elements in the list. We then remove one element at a time from the 
   * list and insert it into the correct position in the left side. To find
   * the correct position for a new element, we compare it with the already
   * sorted set of elements in the list, from right to left.
   */
  private static <T extends Comparable<T>> Record insertionSort(List<T> list) {
    Record record = new Record();

    for (int i = 0; i < list.size(); i += 1) {
      int j = i;
      while (++record.comparisons > 0 && j > 0 && list.get(j - 1).compareTo(list.get(j)) > 0) {
        record.exchanges += 1;
        Collections.swap(list, j, j - 1);
        j -= 1;
      }
    }

    return record;
  }


  /**
   * Record - The recored class represents the count of the number swaps and
   * the number of comparisons performed by any particular sort.
   */
  private static  final class Record {
    public Integer comparisons = 0;
    public Integer exchanges = 0;
  }
}