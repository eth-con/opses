package uk.co.opses.hello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Returns a given number of elements of the Fibonacci sequence
 */

public class Fibonacci {

    public static List<Integer> getFibonacciSequence(Integer count) {

        if (count == null || count < 0 || count > 99) {
            throw new IllegalArgumentException("Count must be an integer between 0 and 99");
        }

        if (count == 0) {
            return Collections.emptyList();
        } else if (count == 1) {
            return Collections.singletonList(0);
        }

        // Add the 2 default integers to the list which don't follow the pattern
        List<Integer> fibonacciSequence = new ArrayList<Integer>();
        fibonacciSequence.add(0);
        fibonacciSequence.add(1);

        // Must subtract 2 from count due to adding in the 2 default elements above
        for (int i = 1; i <= count -2; i++) {
            fibonacciSequence.add(fibonacciSequence.get(i-1) + fibonacciSequence.get(i));
        }

        return fibonacciSequence;
    }
}