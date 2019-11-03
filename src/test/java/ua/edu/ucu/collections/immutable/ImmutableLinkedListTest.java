package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableLinkedListTest {

    private ImmutableLinkedList empty_list, list, one_list;

    @Before
    public void setUp() throws Exception {
        empty_list = new ImmutableLinkedList();
        list = new ImmutableLinkedList(new Object[]{1, "abc", 2.5, 'd'});
        one_list = new ImmutableLinkedList(new Object[]{3});
    }

    @Test
    public void addFirst() {
        assertArrayEquals(new Object[]{'5', 1, "abc", 2.5, 'd'}, list.addFirst('5').toArray());
        assertArrayEquals(new Object[]{"fff"}, empty_list.addFirst("fff").toArray());
    }

    @Test
    public void addLast() {
        assertArrayEquals(new Object[]{1, "abc", 2.5, 'd', 5}, list.addLast(5).toArray());
        assertArrayEquals(new Object[]{"fff"}, empty_list.addLast("fff").toArray());
    }

    @Test
    public void getFirst1() {
        assertEquals(1, list.getFirst());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getFirst2() {
        empty_list.getFirst();
    }

    @Test
    public void getLast1() {
        assertEquals('d', list.getLast());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLast2() {
        empty_list.getLast();
    }

    @Test
    public void removeFirst1() {
        assertArrayEquals(new Object[]{"abc", 2.5, 'd'}, list.removeFirst().toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeFirst2() {
        empty_list.removeFirst();
    }

    @Test
    public void removeFirst3() {
        assertArrayEquals(new Object[]{}, one_list.removeFirst().toArray());
    }

    @Test
    public void removeLast() {
        assertArrayEquals(new Object[]{1, "abc", 2.5}, list.removeLast().toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeLast2() {
        empty_list.removeLast();
    }

    @Test
    public void removeLast3() {
        assertArrayEquals(new Object[]{}, one_list.removeLast().toArray());
    }

    @Test
    public void add1() {
        assertArrayEquals(new Object[]{1, "abc", 2.5, 'd', 5}, list.add(5).toArray());
        assertArrayEquals(new Object[]{"fff"}, empty_list.add("fff").toArray());
    }

    @Test
    public void add2() {
        assertArrayEquals(new Object[]{1, "abc", "2", 2.5, 'd'}, list.add(2,"2").toArray());
        assertArrayEquals(new Object[]{5.3}, empty_list.add(0, 5.3).toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add3() {
        list.add(-3,"2");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void add4() {
        empty_list.add(2, 5.3);
    }

    @Test
    public void addAll1() {
        assertArrayEquals(new Object[]{1, "abc", 2.5, 'd', 5, "f", 3.14}, list.addAll(new Object[]{5, "f", 3.14}).toArray());
        assertArrayEquals(new Object[]{"abc", '4'}, empty_list.addAll(new Object[]{"abc", '4'}).toArray());
    }

    @Test
    public void AddAll2() {
        assertArrayEquals(new Object[]{1, "abc", 2.5, 'd'}, list.addAll(2, new Object[]{}).toArray());
        assertArrayEquals(new Object[]{1, "1..0"}, empty_list.addAll(0, new Object[]{1, "1..0"}).toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void AddAll3() {
        list.addAll(-3, new Object[]{"abc", '4'});
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void AddAll4() {
        empty_list.addAll(1, new Object[]{});
    }

    @Test
    public void get1() {
        assertEquals("abc", list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get2() {
        assertEquals("abc", empty_list.get(1) );
    }

    @Test
    public void remove1() {
        assertArrayEquals(new Object[]{1, "abc", 'd'}, list.remove(2).toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove2() {
        list.remove(5);
    }

    @Test
    public void set1() {
        assertArrayEquals(new Object[]{1, "c", 2.5, 'd'}, list.set(1, "c").toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set2() {
        list.set(6, "q");
    }

    @Test
    public void indexOf() {
        assertEquals(1, list.indexOf("abc"));
        assertEquals(-1, list.indexOf('1'));
        assertEquals(-1, empty_list.indexOf(3));
    }

    @Test
    public void size() {
        assertEquals(4, list.size());
        assertEquals(0, empty_list.size());
    }

    @Test
    public void clear() {
        assertArrayEquals(new Object[]{}, list.clear().toArray());
        assertArrayEquals(new Object[]{}, empty_list.clear().toArray());
    }

    @Test
    public void isEmpty() {
        assertFalse(list.isEmpty());
        assertTrue(empty_list.isEmpty());
    }

    @Test
    public void toArray() {
        assertArrayEquals(new Object[]{1, "abc", 2.5, 'd'}, list.toArray());
        assertArrayEquals(new Object[]{}, empty_list.toArray());
    }

    @Test
    public void ToString() {
        assertEquals("[1, abc, 2.5, d]", list.toString());
        assertEquals("[]", empty_list.toString());
    }

}
