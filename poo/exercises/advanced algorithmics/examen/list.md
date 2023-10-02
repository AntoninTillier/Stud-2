**Advanced Algorithmics**

This activity focuses on the discovery and programming of classic sorting algorithms that efficiently organize and index collections of objects to facilitate later searching. In all exercises, we are interested in sorting algorithms in ascending order. It is evident that these algorithms can be used similarly for sorting in descending order.


**Questions:**

- Write a class for your sorting algorithms that implements the provided `Sort<T>` interface on Celene. Provide a constructor that takes two positive integers, n and m, and returns a linked list containing n integer values randomly chosen between 1 and m. This constructor will be used to generate lists to be sorted with different complexities and evaluate the performance of the sorting algorithms.
- Write the `triInsertion()` method that performs insertion sort and returns a list containing the values of the original list sorted in ascending order using the insertion sort method.
- Provide the `triBulles()` method that implements the bubble sort algorithm.
- Write the `triFusion()` method that performs a merge sort on your linked list.
- Finally, propose the `triRapide()` method that implements quicksort.
- For each of the previous algorithms, you will measure the computation time on lists of varying complexity: length, number of values, uniform list, already sorted list, sorted in reverse order. To obtain a more accurate estimate of the computation time, you will execute each test configuration multiple times (at least 10 times) and report the average computation time and the standard deviation around this average.