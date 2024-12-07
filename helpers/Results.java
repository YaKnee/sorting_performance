package helpers;

/**
 * A class that holds the results of performance measurements, including
 * the number of comparisons and the time taken for an operation.
 *
 * @author JaniOC
 */
public class Results<N, S> {
    private final N count; // Number of comparisons
    private final S time;  // Time taken in milliseconds

    /**
     * Constructs a new Results object to store the number of comparisons
     * and the time taken for an operation.
     *
     * @param comparisons the number of comparisons
     * @param milliseconds the time taken in milliseconds
     */
    public Results(N comparisons, S milliseconds) {
        this.count = comparisons;
        this.time = milliseconds;
    }

    /**
     * Gets the number of comparisons.
     *
     * @return the number of comparisons
     */
    public N getCount() {
        return count;
    }

    /**
     * Gets the time taken in milliseconds.
     *
     * @return the time in milliseconds
     */
    public S getTime() {
        return time;
    }
}
