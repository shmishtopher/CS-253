import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sorts {
  private static volatile int comparisons;
  private static volatile int exchanges;

  public static void main(String[] args) {
    comparisons = 0;
    exchanges = 0;
    mergeSort(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    System.out.println(String.format("Merge Sort best case: %d %d", exchanges, comparisons));

    comparisons = 0;
    exchanges = 0;
    mergeSort(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
    System.out.println(String.format("Merge Sort worse case: %d %d", exchanges, comparisons));

    comparisons = 0;
    exchanges = 0;
    mergeSort(Arrays.asList(2, 4, 5, 1, 8, 0, 7, 6, 3, 9));
    mergeSort(Arrays.asList(0, 6, 4, 7, 9, 8, 3, 2, 1, 5));
    mergeSort(Arrays.asList(9, 0, 3, 6, 4, 7, 8, 2, 1, 5));
    mergeSort(Arrays.asList(0, 2, 7, 9, 3, 6, 5, 4, 1, 8));
    mergeSort(Arrays.asList(3, 8, 2, 4, 7, 5, 0, 6, 1, 9));
    System.out.println(String.format("Merge Sort average case: %d %d", exchanges / 5, comparisons / 5));

    comparisons = 0;
    exchanges = 0;
    radixSort(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    System.out.println(String.format("Radix Sort best case: %d %d", exchanges, comparisons));

    comparisons = 0;
    exchanges = 0;
    radixSort(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1, 0));
    System.out.println(String.format("Radix Sort worst case: %d %d", exchanges, comparisons));

    comparisons = 0;
    exchanges = 0;
    radixSort(Arrays.asList(2, 4, 5, 1, 8, 0, 7, 6, 3, 9));
    radixSort(Arrays.asList(0, 6, 4, 7, 9, 8, 3, 2, 1, 5));
    radixSort(Arrays.asList(9, 0, 3, 6, 4, 7, 8, 2, 1, 5));
    radixSort(Arrays.asList(0, 2, 7, 9, 3, 6, 5, 4, 1, 8));
    radixSort(Arrays.asList(3, 8, 2, 4, 7, 5, 0, 6, 1, 9));
    System.out.println(String.format("Radix Sort average case: %d %d", exchanges / 5, comparisons / 5));

    try {
      comparisons = 0;
      exchanges = 0;
      mergeSort(Files.readAllLines(Paths.get("testData", "bestCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Merge Sort best case: %d %d", exchanges, comparisons));

      comparisons = 0;
      exchanges = 0;
      mergeSort(Files.readAllLines(Paths.get("testData", "worstCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Merge Sort worst case: %d %d", exchanges, comparisons));

      comparisons = 0;
      exchanges = 0;
      mergeSort(Files.readAllLines(Paths.get("testData", "averageCase_1.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      mergeSort(Files.readAllLines(Paths.get("testData", "averageCase_2.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      mergeSort(Files.readAllLines(Paths.get("testData", "averageCase_3.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      mergeSort(Files.readAllLines(Paths.get("testData", "averageCase_4.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      mergeSort(Files.readAllLines(Paths.get("testData", "averageCase_5.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Merge Sort average case: %d %d", exchanges / 5, comparisons / 5));

      comparisons = 0;
      exchanges = 0;
      radixSort(Files.readAllLines(Paths.get("testData", "bestCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Radix Sort best case: %d %d", exchanges, comparisons));

      comparisons = 0;
      exchanges = 0;
      radixSort(Files.readAllLines(Paths.get("testData", "worstCase.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Radix Sort worst case: %d %d", exchanges, comparisons));

      comparisons = 0;
      exchanges = 0;
      radixSort(Files.readAllLines(Paths.get("testData", "averageCase_1.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      radixSort(Files.readAllLines(Paths.get("testData", "averageCase_2.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      radixSort(Files.readAllLines(Paths.get("testData", "averageCase_3.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      radixSort(Files.readAllLines(Paths.get("testData", "averageCase_4.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      radixSort(Files.readAllLines(Paths.get("testData", "averageCase_5.txt")).stream().map(Integer::parseInt).collect(Collectors.toList()));
      System.out.println(String.format("Radix Sort average case: %d %d", exchanges / 5, comparisons / 5));
    }
    catch (Exception error) {
      System.out.println("Could not stream file(s): ");
      System.out.println(error);
    }
  }


  public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
    if (list.size() <= 1 && comparisons++ > -1) {
      return list;
    }

    else {
      int middle = list.size() / 2;
      List<T> left = list.subList(0, middle);
      List<T> right = list.subList(middle, list.size());

      return merge(
        mergeSort(left),
        mergeSort(right)
      );
    }
  }


  public static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right) {
    List<T> mergedList = new ArrayList<T>();
    int leftPointer = 0;
    int rightPointer = 0;

    while (leftPointer < left.size() && rightPointer < right.size() && (comparisons += 2) > -1) {
      if (left.get(leftPointer).compareTo(right.get(rightPointer)) < 0) {
        mergedList.add(left.get(leftPointer));
        leftPointer += 1;
        exchanges += 1;
      }
      else {
        mergedList.add(right.get(rightPointer));
        rightPointer += 1;
        exchanges += 1;
      }
    }

    if (mergedList.addAll(left.subList(leftPointer, left.size()))) exchanges += 1;
    if (mergedList.addAll(right.subList(rightPointer, right.size()))) exchanges += 1;
    return mergedList;
  }


  public static void radixSort(List<Integer> list) {
    int max = list.get(0);

    for (Integer i : list) {
      if (i > max) {
        max = i;
      }
    }

    for (int i = 0; i < Math.log10(max) + 1; i += 1) {
      countingSort(list, i);
    }
  }


  public static void countingSort(List<Integer> list, int place) {
    List<List<Integer>> buckets = new ArrayList<List<Integer>>();
    
    for (int i = 0; i < 10; i += 1) {
      buckets.add(new ArrayList<Integer>());
    }

    for (Integer i : list) {
      int index = (int) (i % Math.pow(10, place + 1) / Math.pow(10, place));
      buckets.get(index).add(i);
      exchanges += 1;
    }

    int pointer = 0;
    for (List<Integer> bucket : buckets) {
      for (Integer i : bucket) {
        list.set(pointer, i);
        pointer += 1;
        exchanges += 1;
      }
    }
  }
}