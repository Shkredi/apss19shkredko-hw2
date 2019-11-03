package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {

    private Stack empty_stack, stack;

    @Before
    public void setUp() throws Exception {
        empty_stack = new Stack();
        stack = new Stack();
        Object[] arr = new Object[]{1, "abc", 2.5, 'd'};
        for (Object e: arr) {
            stack.push(e);
        }
    }

    @Test
    public void peek1() {
        assertEquals('d', stack.peek());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void peek2() {
        empty_stack.peek();
    }

    @Test
    public void pop1() {
        assertEquals('d', stack.pop());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void pop2() {
        empty_stack.pop();
    }

    @Test
    public void push() {
        stack.push("q");
        assertEquals("q", stack.peek());
        empty_stack.push("q");
        assertEquals("q", empty_stack.peek());
    }

    @Test
    public void isEmpty() {
        assertFalse(stack.isEmpty());
        assertTrue(empty_stack.isEmpty());
    }
}
