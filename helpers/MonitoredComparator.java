package helpers;

/**
 * A comparator that tracks the number of comparisons made during the comparison
 * process. It increments a shared static counter each time a comparison is made.
 *
 * @author JaniOC
 */
public class MonitoredComparator<T extends Comparable<T>> {
    // Shared counter across all instances
    private static long comparisonCount = 0;

    /**
     * Compares two objects and increments the comparison count.
     *
     * @param o1 the first object to compare
     * @param o2 the second object to compare
     * @return a negative integer, zero, or a positive integer as the first object
     *         is less than, equal to, or greater than the second object
     */
    public int compare(T o1, T o2) {
        comparisonCount++; // Increment the shared counter
        return o1.compareTo(o2);
    }

    /**
     * Returns the total number of comparisons made since the last reset.
     *
     * @return the number of comparisons made
     */
    public static long getComparisonCount() {
        return comparisonCount;
    }

    /**
     * Resets the comparison count to zero.
     */
    public static void resetComparisonCount() {
        comparisonCount = 0;
    }
}
