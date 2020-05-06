package hw3;

import exceptions.IndexException;


/**
 * An Array that is able to report the number of accesses and mutations,
 * as well as reset those statistics.
 *
 * @param <T> The type of the array.
 */
public class MeasuredArray<T> extends SimpleArray<T> implements Measured<T> {

  private int numMutation;
  private int numAccess;
  //private T[] data;

  /**
   * Constructor for a MeasuredArray that calls the SimpleArray constructor.
   *
   * @param n The size of the array.
   * @param t The initial value to set every object to in the array..
   */
  public MeasuredArray(int n, T t) {
    super(n, t);
    numAccess = 0;
    numMutation = 0;
  }

  /**
   * returns length of the array.
   * @return length of array
   */
  @Override
  public int length() {
    return super.length();
  }

  /**
   * gets the element at the given index.
   * @param i index to search.
   * @return value at index i.
   * @throws IndexException when i out of bound.
   */
  @Override
  public T get(int i) throws IndexException {
    try {
      T target = super.get(i);
      numAccess++;
      return target;
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IndexException();
    }
  }

  /**
   * changes the element at given index i to t.
   * @param i index to put new value in.
   * @param t new value to be written.
   * @throws IndexException when i out of bound
   */
  @Override
  public void put(int i, T t) throws IndexException {
    super.put(i, t);
    numMutation++;
  }

  /**
   * resets numAccess and numMutation to 0.
   */
  @Override
  public void reset() {
    numAccess = 0;
    numMutation = 0;
  }

  /**
   * return the number of times get() is called.
   * @return number of accesses.
   */
  @Override
  public int accesses() {
    return numAccess;
  }

  /**
   * return the number of times put() is called.
   * @return number of mutations.
   */
  @Override
  public int mutations() {
    return this.numMutation;
  }

  /**
   * counts the number of appearances of an element t in teh array.
   * @param t The value to count.
   * @return number of appearances.
   */
  @Override
  public int count(T t) {
    int count = 0;
    for (int i = 0; i < length(); i++) {
      if (this.get(i) == t) {
        count++;
      }
    }
    return count;
  }
}
