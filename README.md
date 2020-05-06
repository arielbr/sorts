# Discussion

## Part A
1. Discuss from a design perspective whether or not iterating over a MeasuredArray should affect the accesses and mutation counts. 

Iterating over a MeasuredArray should increment the total count of accesses
    when get() is called. This makes sense since the list itself is being accessed.
    However, iterating over a MeasuredArray should not affect the mutation counts,
    as the iterator should not be changing any elements within the list.
    
2. Can you inherit ArrayIterator from SimpleArray and override the relevant methods, or not? Explain.

No, ArrayIterator from SimpleArray cannot be inherited as it is a public class.
    As a result, its methods also cannot be overwritten. 

## Part B
Include the results of experiments (must have concrete time measurements and size of data set used).

Data file   |      Algorithm    |    Sorted? | Accesses |    Mutations  |  Seconds     

ascending.data  |  Null Sort    |    false  |  0     |      0        |    0.000009    
ascending.data  |  Gnome Sort     |  true   |  15,230,058  | 5,074,020  |  0.122102    
ascending.data  |  Selection Sort  | true  |   16,003,980 | 7,980      |  0.122488    
ascending.data  |  Bubble Sort    |  true  |   13,048,026  | 5,074,020  |  0.099694    
ascending.data  |  Insertion Sort |  true   |  2,545,008  |  5,074,020  |  0.088766    

descending.data |  Null Sort    |    false |   0         |   0          | 0.000001    
descending.data |  Gnome Sort   |    true  |   47,988,000|   15,996,000 |  0.304837    
descending.data |  Selection Sort |  true  |   16,000,000 |  4,000     |  0.081291    
descending.data |  Bubble Sort  |   true  |   47,980,002  | 15,996,000  | 0.192235    
descending.data |  Insertion Sort |  true |    8,001,999  |  15,996,000 |  0.152054    

random.data |      Null Sort    |   false  |  0          |  0         |   0.000001    
random.data |      Gnome Sort    |  true   |  24,145,478 |  8,045,828  |  0.148866    
random.data |      Selection Sort |  true  |   16,003,992 |  7,992      |  0.080989    
random.data |      Bubble Sort     | true  |   38,846,126 |  8,045,828 |   0.383106    
random.data |      Insertion Sort  | true   |  4,030,910  |  8,045,828 |   0.095585    

1. There is an intentional mistake in one of the provided data files. The goal of this assignment is to use the measurements to catch that mistake. 

The mistake is with the ascending.data, which writes the data in correct ascending order if they were treated as integers. However, in the SortingAlgorithmDriver.java file, the 
lines of the .data files are read, stored and compared as Strings. This causes different comparison results for their "magnitude" which is different from comparing integers.
For example, if 5 and 123 are both integers, 5.compareTo(123) < 0. But if they are both strings "5".compareTo("123") > 0 since Strings are compared char by char, and
the first character "5" is considered larger than "1".
This mistake is caught by looking at the mutations of ascending.data, which is expected to be 0, since all data are already put into their positions by
an ascending order. The mistake can be corrected by adding different numbers of zeros to each number before comparing them, such that the numbers will be of equal lengths.
For example, 98 becomes 00098, and 121 becomes 00121. Then, "00098".compareTo("00121") < 0, behaving as expected.

2. Does the actual running time correspond to the asymptotic complexity as you would expect?

The amount of accesses and mutations correspond to the expected amount relatively well, 
   as the worst case amount of accesses and runtime for bubble sort and insertion sort show 
   to be O(n^2). The best case runtime for the two should be O(n) if the mistake from part 1 
   is corrected, meaning that the array elements are each checked once, found current elements in correct position 
   (insertion sort) or no swap happened (bubble sort). n times => best case is in O(n).
   The actual runtime for random, however, appears to be not as expected, as 
   selection sort was the fastest, followed by insertion, and lastly bubble sort.

3. What explains the practical differences between these algorithms? (Theoretically, the algorithm runs in O(X) time, where X is a function of the input size, but in practice (i.e running it on datasets), you may observe that it is slower/faster compared to the other algorithms)

The processing power of the CPU running the program can influence the runtime
   of the sorting algorithm. The programs running in the background of the computer
   can skew the runtime slightly, even if the same program runs twice.
   
   Bubble Sort is also slower than insertion and selection sort as its inner loop is always run O(n^2) times
   or (n - i - 1) times (i being the current position index) when there is at least one swap. However, insertion breaks once the element to the left of
   the current element is less than itself in the inner loop. Also, to have an element bubbled to the right, it
   needs to call swap many times between adjacent elements until to the right, where as insertion and selection sorts just swap once.

4. Does it matter what kind of data (random, already sorted in ascending order, sorted in descending order) you are sorting? How should each algorithm behave (in terms of performance) based on the type of data it receives?

Yes, the order matters. Ascending data will result in best case runtime (assuming
    that the data is sorted correctly) for insertion and bubble sort. Descending data 
    will result in worst case runtime for insertion and bubble sort. Selection sort with
    have worst case and best case runtime regardless of order, as it is both O(N^2).
    Random data will have average around the same runtime for all three, but will
    vary slightly depending on how the data is randomized.

5. How do your implementations compare to the sorting done by Java Collections? Do you think that the standardized code is just better written than yours or is more asymptotically efficient? How would you go about determining which is the case?

Java Collections would likely run faster due to it being asymptotically efficient,
    rather than better written. The code itself would have very minor impacts on the runtime,
    as the difference in cycles taken per iteration grows linearly, rather than
    asymptoticly. Collections runs at O(n*log(n)), which is a better runtime that
    our O(n^2) runtime for all three sorting methods. We could determine the difference 
    by running a large sample list to be sorted.

## Part C
1. Determine exactly how many comparisons C(n) and assignments A(n) are performed by this implementation of selection sort in the worst case. Both of those should be polynomials of degree 2 since you know that the asymptotic complexity of selection sort is O(n^2).

If the array length is n. Line 3 executes (n-1) times. 
There is one assignment to i here. For each
execution of the outer loop, line 4 does assignment once, thus total (n-1) times, 
and line 5 executes (n-1-i) times, and j is assigned (n-1) times. 
The worst case thus assigns to max (n-1) + (n-2) + .. + 1 = n(n-1)/2 times in line 7. And line 5 and 
line 6 comparisons will be executed this many times too (each comparison updates and 
assigns to max). Then in line 10-12, there will be 2 assignments at each inner loop once, 
thus a total of 2*(n-1) assignments.
Thus, the worst case is for C(n) = n(n-1)/2 comparisons and A(n) = 1+(n-1)+(n-1)+n(n-1)/2+2*(n-1) = n^2/2+(7/2)n-3
 number of assignments. 
The desired arragement is from the largest to the smallest number,
but if each number is shifted by one position (eg smallest number, followed by the largest to the second smallest number), then each element needs to be shifted by one.
There will be n iterations through the array: from 1st to last, 2nd to last, 3rd to last, etc, as controlled by the outer loop (and j starts from i, i incrementing).
In each execution of the outer loop, the inner loop does (n-i-1) comparisons with the first number, thus total number of comparisons is sum(n-1-i) from i = 0 to i = n-1.
Comparison number is always fixed in this algorithm. 

In each iteration, there will be one pair swapped, since the largest in the remaining elements is put to position i, and the first (smallest) is shifted to the right by one position.
Thus there are in total n swaps/2*n assignments.
