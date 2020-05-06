package hw3;


/**
 * The Bubble Sort algorithm with the optimized "quick" break to exit
 * if the array is sorted.
 *
 * @param <T> The type being sorted.
 */
public final class BubbleSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

  /**
   * method to sort the given array.
   * @param array Array to sort.
   */
  @Override
  public void sort(Array<T> array) {
    T temp;
    for (int i = 0; i < array.length() - 1; i++) {
      int swap = 0;
      for (int j = 0; j < array.length() - 1; j++) {
        T next = array.get(j + 1);
        T cur = array.get(j);
        if (next.compareTo(cur) < 0) {
          temp = cur;
          array.put(j, next);
          array.put(j + 1, temp);
          swap++;
        }
      }
      // OPTIMIZATION: terminate early if no swaps
      if (swap == 0) {
        break;
      }
    }
  }

  @Override
  public String name() {
    return "Bubble Sort";
  }
}
