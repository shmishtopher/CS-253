import java.util.Arrays;
import java.util.List;


public class Recursive {
  public static void main(String[] args) {
    System.out.println("Testing binary search");
    System.out.println(binarySearch(0, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 9));
    System.out.println(binarySearch(3, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 9));
    System.out.println(binarySearch(9, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 9));
    System.out.println(binarySearch(11, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 9));
    System.out.println(binarySearch(13, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 9));
    System.out.println(binarySearch(64, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), 0, 9));
    
    System.out.println("Testing factorial");
    System.out.println(factorial(0));
    System.out.println(factorial(1));
    System.out.println(factorial(2));
    System.out.println(factorial(3));
    System.out.println(factorial(4));
    System.out.println(factorial(5));

    System.out.println("Testing fibonacci");
    System.out.println(fibonacci(1));
    System.out.println(fibonacci(2));
    System.out.println(fibonacci(3));
    System.out.println(fibonacci(4));
    System.out.println(fibonacci(5));
    System.out.println(fibonacci(6));

  }


  /**
   * Binary search determines weather a given element exists whithin
   * a sorted list or not.
   */
  public static <T extends Comparable<T>> Boolean binarySearch(T object, List<T> list, Integer start, Integer end) {
    int middle = (start + end) / 2;

    if (end < start) return false;
    if (object.compareTo(list.get(middle)) < 0) return binarySearch(object, list, start, middle - 1);
    if (object.compareTo(list.get(middle)) > 0) return binarySearch(object, list, middle + 1, end);
    if (object.compareTo(list.get(middle)) == 0) return true;

    return false;
  }


  /**
   * Compute the factorial of n by using the recursive definition.
   */
  public static Integer factorial(Integer n) {
    return n == 0 ? 1 : n * factorial(n - 1);
  }


  /**
   * Compute the nth fibonacci number by using the recusive defintion.
   */
  public static Integer fibonacci(Integer n) {
    return n <= 1 ? n : fibonacci(n - 1) + fibonacci(n - 2);
  }
}