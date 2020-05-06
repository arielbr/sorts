package hw3;


/**
 * The Insertion Sort algorithm, with minimizing swaps optimization.
 *
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {


  /**
   * method to sort the array given.
   * @param array Array to sort.
   */
  @Override
  public void sort(Array<T> array) {
    int len = array.length();
    T last;
    T cur;
    T temp;
    // loop from second to last element
    for (int j = 1; j < len; j++) {
      // examine each current element with one element before till at first elem
      int i = j;
      last = array.get(i - 1);
      cur = array.get(i);
      while (cur.compareTo(last) < 0 && i > 0) {
        temp = last;
        array.put(i - 1, cur);
        array.put(i, temp);
        i--;
        if (i > 0) {
          last = array.get(i - 1);
        }
      }
    }
  }

  @Override
  public String name() {
    return "Insertion Sort";
  }
}
