package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {

    private Queue empty_queue, queue;

    @Before
    public void setUp() throws Exception {
        empty_queue = new Queue();
        queue = new Queue();
        Object[] arr = new Object[]{1, "abc", 2.5, 'd'};
        for (Object e: arr) {
            queue.enqueue(e);
        }
    }

    @Test
    public void peek1() {
        assertEquals(1, queue.peek());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void peek2() {
        empty_queue.peek();
    }

    @Test
    public void dequeue1() {
        assertEquals(1, queue.dequeue());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void dequeue2() {
        empty_queue.dequeue();
    }

    @Test
    public void enqueue() {
        queue.enqueue('s');
        Object e = null;
        while (!queue.isEmpty()){
            e = queue.dequeue();
        }
        assertEquals('s', e);
        empty_queue.enqueue('s');
        assertEquals('s', empty_queue.peek());
    }

    @Test
    public void isEmpty() {
        assertFalse(queue.isEmpty());
        assertTrue(empty_queue.isEmpty());
    }
}
