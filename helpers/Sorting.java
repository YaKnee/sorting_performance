package helpers;

/**
 * Sorting demonstrates sorting and searching on an array
 * of objects. The methods utilize a MonitoredComparator
 * to track the number of comparisons during sorting
 *
 * @author Lewis and Chase
 * @version 4.0 (Edited by JaniOC, updated with MonitoredComparator support)
 */
public class Sorting
{
    /**
     * Sorts the specified array of integers using the selection
     * sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void selectionSort(T[] data) {
        MonitoredComparator<T> comparator = new MonitoredComparator<>();
        MonitoredComparator.resetComparisonCount();

        int min;
        T temp;

        for (int index = 0; index < data.length-1; index++)
        {
            min = index;
            for (int scan = index+1; scan < data.length; scan++){
                if (comparator.compare(data[scan], data[min]) < 0){
                    min = scan;
                }
            }

            swap(data, min, index);
        }
    }

    /**
     * Swaps to elements in an array. Used by various sorting algorithms.
     *
     * @param data   the array in which the elements are swapped
     * @param index1 the index of the first element to be swapped
     * @param index2 the index of the second element to be swapped
     */
    private static <T extends Comparable<T>>
    void swap(T[] data, int index1, int index2) {
        T temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    /**
     * Sorts the specified array of objects using an insertion
     * sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void insertionSort(T[] data) {
        MonitoredComparator<T> comparator = new MonitoredComparator<>();
        MonitoredComparator.resetComparisonCount();
        for (int index = 1; index < data.length; index++) {
            T key = data[index];
            int position = index;

            // shift larger values to the right
            while (position > 0 && comparator.compare(data[position - 1], key) > 0) {
                data[position] = data[position-1];
                position--;
            }

            data[position] = key;
        }
    }

    /**
     * Sorts the specified array of objects using a bubble sort
     * algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void bubbleSort(T[] data) {
        MonitoredComparator<T> comparator = new MonitoredComparator<>();
        MonitoredComparator.resetComparisonCount();

        int position, scan;
        T temp;

        for (position =  data.length - 1; position >= 0; position--) {
            for (scan = 0; scan <= position - 1; scan++) {
                if (comparator.compare(data[scan], data[scan + 1]) > 0) {
                    swap(data, scan, scan + 1);
                }
            }
        }
    }

    /**
     * Sorts the specified array of objects using the merge sort
     * algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void mergeSort(T[] data) {
        MonitoredComparator<T> comparator = new MonitoredComparator<>();
        MonitoredComparator.resetComparisonCount();
        mergeSort(data, 0, data.length - 1, comparator);
    }

    /**
     * Recursively sorts a range of objects in the specified array using the
     * merge sort algorithm.
     *
     * @param data the array to be sorted
     * @param min  the index of the first element
     * @param max  the index of the last element
     * @param comparator the MonitoredComparator tracking comparisons
     */
    private static <T extends Comparable<T>>
    void mergeSort(T[] data, int min, int max, MonitoredComparator<T> comparator) {
        if (min < max)
        {
            int mid = (min + max) / 2;
            mergeSort(data, min, mid, comparator);
            mergeSort(data, mid + 1, max, comparator);
            merge(data, min, mid, max, comparator);
        }
    }

    /**
     * Merges two sorted subarrays of the specified array.
     *
     * @param data the array to be sorted
     * @param first the beginning index of the first subarray
     * @param mid the ending index fo the first subarray
     * @param last the ending index of the second subarray
     * @param comparator the MonitoredComparator tracking comparisons
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>>
    void merge(T[] data, int first, int mid, int last, MonitoredComparator<T> comparator) {
        T[] temp = (T[])(new Comparable[data.length]);

        int first1 = first, last1 = mid;  // endpoints of first subarray
        int first2 = mid+1, last2 = last;  // endpoints of second subarray
        int index = first1;  // next index open in temp array

        //  Copy smaller item from each subarray into temp until one
        //  of the subarrays is exhausted
        while (first1 <= last1 && first2 <= last2) {
            if (comparator.compare(data[first1], data[first2]) < 0) {
                temp[index] = data[first1];
                first1++;
            } else {
                temp[index] = data[first2];
                first2++;
            }
            index++;
        }

        //  Copy remaining elements from first subarray, if any
        while (first1 <= last1) {
            temp[index] = data[first1];
            first1++;
            index++;
        }

        //  Copy remaining elements from second subarray, if any
        while (first2 <= last2) {
            temp[index] = data[first2];
            first2++;
            index++;
        }

        //  Copy merged data into original array
        for (index = first; index <= last; index++) {
            data[index] = temp[index];
        }
    }

    /**
     * Sorts the specified array of objects using the quick sort algorithm.
     *
     * @param data the array to be sorted
     */
    public static <T extends Comparable<T>>
    void quickSort(T[] data) {
        MonitoredComparator<T> comparator = new MonitoredComparator<>();
        MonitoredComparator.resetComparisonCount();
        quickSort(data, 0, data.length - 1, comparator);
    }

    /**
     * Recursively sorts a range of objects in the specified array using the
     * quick sort algorithm.
     *
     * @param data the array to be sorted
     * @param min  the minimum index in the range to be sorted
     * @param max  the maximum index in the range to be sorted
     * @param comparator the MonitoredComparator tracking comparisons
     */
    private static <T extends Comparable<T>>
    void quickSort(T[] data, int min, int max, MonitoredComparator<T> comparator) {
        if (min < max)
        {
            // create partitions
            int indexofpartition = partition(data, min, max, comparator);

            // sort the left partition (lower values)
            quickSort(data, min, indexofpartition - 1, comparator);

            // sort the right partition (higher values)
            quickSort(data, indexofpartition + 1, max, comparator);
        }
    }

    /**
     * Used by the quick sort algorithm to find the partition.
     *
     * @param data the array to be sorted
     * @param min  the minimum index in the range to be sorted
     * @param max  the maximum index in the range to be sorted
     * @param comparator the MonitoredComparator tracking comparisons
     */
    private static <T extends Comparable<T>>
    int partition(T[] data, int min, int max, MonitoredComparator<T> comparator) {

        T partitionelement;
        int left, right;
        int middle = (min + max) / 2;

        // use the middle data value as the partition element
        partitionelement = data[middle];
        // move it out of the way for now
        swap(data, middle, min);

        left = min;
        right = max;

        while (left < right)
        {
            // search for an element that is > the partition element
            while (left < right && comparator.compare(data[left], partitionelement) <= 0) {
                left++;
            }

            // search for an element that is < the partition element
            while (comparator.compare(data[right], partitionelement) > 0) {
                right--;
            }

            // swap the elements
            if (left < right) {
                swap(data, left, right);
            }
        }

        // move the partition element into place
        swap(data, min, right);

        return right;
    }
}

