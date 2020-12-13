package uk.co.opses.hello;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FibonacciTest {

    @Test
    public void getFibonacciSequence_countIs0() {
        List<Integer> result = Fibonacci.getFibonacciSequence(0);
        assertTrue(result.isEmpty());
    }

    @Test
    public void getFibonacciSequence_countIs1() {
        List<Integer> result = Fibonacci.getFibonacciSequence(1);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).intValue(), 0);
    }

    @Test
    public void getFibonacciSequence_countIs5() {
        List<Integer> result = Fibonacci.getFibonacciSequence(7);

        assertEquals(result.size(), 7);
        assertEquals(result.get(0).intValue(), 0);
        assertEquals(result.get(1).intValue(), 1);
        assertEquals(result.get(2).intValue(), 1);
        assertEquals(result.get(3).intValue(), 2);
        assertEquals(result.get(4).intValue(), 3);
        assertEquals(result.get(5).intValue(), 5);
        assertEquals(result.get(6).intValue(), 8);
    }

}