package helpers;

import java.util.Random;

/**
 * A class that prints a performance table for various sorting algorithms.
 * The table shows the number of comparisons and the sorting time (in
 * milliseconds) for different array sizes.
 *
 * @author JaniOC
 */
public class PerformanceTable {
    // Array of array sizes to test
    private final int[] arraySizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

    // Array of algorithm names to test
    private final String[] algorithms = {"bubbleSort", "insertionSort", "mergeSort", "quickSort", "selectionSort"};

    /**
     * Constructor that prints the performance table when the object is created.
     */
    public PerformanceTable() {
        printTable();
    }

    /**
     * Prints the performance table, showing the number of comparisons and
     * the time in milliseconds for each sorting algorithm.
     */
    public void printTable() {
        // Calculate the width of the first column
        int firstColumnWidth = calculateFirstColumnWidth();

        // Print the header
        System.out.printf(" %-" + firstColumnWidth + "s ", ""); // First header cell empty
        for (int size : arraySizes) {
            System.out.printf(" %-10d ", size); // Print array sizes left-aligned
        }
        System.out.println();

        // Print performance data for each algorithm
        for (String algo : algorithms) {
            printAlgorithmPerformance(algo, firstColumnWidth);
        }
        System.out.println();
    }

    /**
     * Prints the performance data (comparisons and time) for a specific
     * algorithm.
     *
     * @param algorithm the sorting algorithm to test
     * @param firstColumnWidth the width for the first column in the table
     */
    private void printAlgorithmPerformance(String algorithm, int firstColumnWidth) {
        System.out.printf(" %-" + firstColumnWidth + "s ", algorithm + "." + "random.comparisons");
        for (int size : arraySizes) {
            Results<Long, Long> results = calculatePerformance(algorithm, size);
            System.out.printf(" %-10d ", results.getCount()); // Print comparisons left-aligned
        }
        System.out.println();

        System.out.printf(" %-" + firstColumnWidth + "s ", algorithm + "." + "random" + ".ms");
        for (int size : arraySizes) {
            Results<Long, Long> results = calculatePerformance(algorithm, size);
            System.out.printf(" %-10d ", results.getTime()); // Print milliseconds left-aligned
        }
        System.out.println();
    }

    /**
     * Calculates the width required for the first column in the table based on
     * the longest label for comparisons and milliseconds.
     *
     * @return the width of the first column
     */
    private int calculateFirstColumnWidth() {
        int maxLength = 0;
        for (String algo : algorithms) {
            String comparisonsLabel = algo + ".random.comparisons";

            // Determine the longest string
            maxLength = Math.max(maxLength, comparisonsLabel.length());
        }
        return maxLength;
    }

    /**
     * Calculates the performance of a specific algorithm on an array of a given size.
     * This includes the number of comparisons and the time taken in milliseconds.
     *
     * @param algorithm the sorting algorithm to test
     * @param size the size of the array to sort
     * @return a Results object containing the number of comparisons and time in ms
     */
    private Results<Long, Long> calculatePerformance(String algorithm, int size) {
        // Generate a random array of the specified size
        Integer[] array = generateRandomArray(size);

        // Reset the shared comparison counter before sorting
        MonitoredComparator.resetComparisonCount();

        // Measure sorting time and count comparisons
        long startTime = System.currentTimeMillis();

        // Perform the sorting based on the selected algorithm
        switch (algorithm) {
            case "bubbleSort":
                Sorting.bubbleSort(array);
                break;
            case "insertionSort":
                Sorting.insertionSort(array);
                break;
            case "mergeSort":
                Sorting.mergeSort(array);
                break;
            case "quickSort":
                Sorting.quickSort(array);
                break;
            case "selectionSort":
                Sorting.selectionSort(array);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }

        long endTime = System.currentTimeMillis();

        // Retrieve the comparison count and elapsed time
        long comparisons = MonitoredComparator.getComparisonCount();
        long milliseconds = endTime - startTime;

        return new Results<>(comparisons, milliseconds);
    }

    /**
     * Generates a random array of integers with values between 0 and the specified
     * size.
     *
     * @param size the size of the array to generate
     * @return a randomly generated array of integers
     */
    private Integer[] generateRandomArray(int size) {
        Integer[] array = new Integer[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(size);
        }
        return array;
    }
}
