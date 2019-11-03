package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList{

    private final class Node{
        private Object val;
        private Node prev;
        private Node next;

        private Node(Object val, Node prev, Node next){
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

        private Node() {
            this(null, null, null);
        }

        private Node(Object val){
            this(val, null, null);
        }

    }

    private int size;
    private Node head;
    private Node tail;

    public ImmutableLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private ImmutableLinkedList(Node head, Node tail, int size){
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public ImmutableLinkedList(Object[] arr){
        this(null, null, arr.length);
        if (arr.length >= 1){
            Node node = new Node(arr[0]);
            this.head = node;

            Node cur_node = node;
            for (int i = 1; i < arr.length; i++) {
                cur_node = new Node(arr[i], node, null);
                node.next = cur_node;
                node = cur_node;
            }

            this.tail = cur_node;
        }
    }

    // робить копію зв'язного списку
    private static ImmutableLinkedList CopyOf(ImmutableLinkedList list){
        return ImmutableLinkedList.CopyOf(list, 0, list.size);
    }

    // робить копію зв'язного списку
    private static ImmutableLinkedList CopyOf(ImmutableLinkedList list, int index1, int index2){
        return new ImmutableLinkedList(list.toArray(index1, index2));
    }

    //додає елемент у початок зв'язаного списку
    public ImmutableLinkedList addFirst(Object e){
        if (this.size == 0){
            Node node = new Node(e);
            return new ImmutableLinkedList(node, node, 1);
        }
        else {
            ImmutableLinkedList new_list = ImmutableLinkedList.CopyOf(this);
            new_list.head = new Node(e, null, new_list.head);
            new_list.head.next.prev = new_list.head;
            new_list.size += 1;
            return new_list;
        }
    }

    // додає елемент у кінець зв'язаного списку
    public ImmutableLinkedList addLast(Object e){
        if (this.size == 0){
            Node node = new Node(e);
            return new ImmutableLinkedList(node, node, 1);
        }
        else {
            ImmutableLinkedList new_list = ImmutableLinkedList.CopyOf(this);
            new_list.tail = new Node(e, new_list.tail, null);
            new_list.tail.prev.next = new_list.tail;
            new_list.size += 1;
            return new_list;
        }
    }

    // повератє перший елемнт зв'язного списку
    public Object getFirst(){
        if (this.size == 0){
            throw new IndexOutOfBoundsException();
        }
        return  this.head.val;
    }

    // повертає останній елемент зв'язного списку
    public Object getLast(){
        if (this.size == 0){
            throw new IndexOutOfBoundsException();
        }
        return  this.tail.val;
    }

    //видаляє перший елемент
    public ImmutableLinkedList removeFirst(){
        if (this.size == 0){
            throw new IndexOutOfBoundsException();
        }
        if  (this.size == 1){
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList new_list = ImmutableLinkedList.CopyOf(this);
        new_list.head = new_list.head.next;
        new_list.size -= 1;
        return new_list;
    }

    //видаляє останній елемент
    public ImmutableLinkedList removeLast(){
        if (this.size == 0){
            throw new IndexOutOfBoundsException();
        }
        if  (this.size == 1){
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList new_list = ImmutableLinkedList.CopyOf(this);
        new_list.size -= 1;
        new_list.tail = new_list.tail.prev;
        return new_list;
    }

    //додає елемент у кінець колекції
    @Override
    public ImmutableList add(Object e) {
        return this.add(this.size, e);
    }

    //додає елемент до колекції за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    @Override
    public ImmutableList add(int index, Object e) {;
        return this.addAll(index, new Object[]{e});
    }

    //додає масив елементів у кінець колекції
    @Override
    public ImmutableList addAll(Object[] c) {
        return this.addAll(this.size, c);
    }

    // додає масив елементів починаючи з зазначеного індекса, та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        Object[] arr = this.toArray();
        Object[] new_arr = new Object[this.size + c.length];
        System.arraycopy(arr, 0, new_arr, 0, index);
        System.arraycopy(c, 0, new_arr, index, c.length);
        if (index < this.size) {
            System.arraycopy(arr, index, new_arr, index  + c.length, this.size - index);
        }
        return new ImmutableLinkedList(new_arr);
    }

    //повертає елемент за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    @Override
    public Object get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.val;
    }

    //видаляє елемент за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    @Override
    public ImmutableList remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Object[] arr = this.toArray();
        Object[] new_arr = new Object[this.size-1];
        System.arraycopy(arr, 0, new_arr, 0, index);
        System.arraycopy(arr, index + 1, new_arr, index, this.size - index - 1);
        return new ImmutableLinkedList(new_arr);
    }

    //змінює значення елементу за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції
    @Override
    public ImmutableList set(int index, Object e) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Object[] arr = this.toArray();
        Object[] new_arr = new Object[this.size];
        System.arraycopy(arr, 0, new_arr, 0, index);
        new_arr[index] = e;
        System.arraycopy(arr, index + 1, new_arr, index + 1, this.size - index - 1);
        return new ImmutableLinkedList(new_arr);
    }

    //шукає індекс елемента (повертає індекс першого який знайшов, або -1 у випадку відсутності)
    @Override
    public int indexOf(Object e) {
        Node node = this.head;
        for (int i = 0; i < this.size; i++) {
            if (node.val == e){
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    //розмір колекції
    @Override
    public int size() {
        return this.size;
    }

    //очищує вміст колекції
    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    //якщо у колеції нема елементів то повертає true
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    //перетворює колекцію до масиву обєктів
    @Override
    public Object[] toArray() {
        return this.toArray(0, this.size);
    }

    //перетворює колекцію до масиву обєктів
    private Object[] toArray(int index1, int index2) {
        if (index1 < 0 || index2 > this.size || index1 > index2){
            throw new IndexOutOfBoundsException();
        }
        Object[] arr = new Object[index2 - index1];
        Node node = this.head;
        for (int i = index1; i < index2; i++) {
            arr[i] = node.val;
            node = node.next;
        }
        return arr;
    }

    //повертає рядок, де через кому відображаютсься елементи колекції
    @Override
    public String toString(){
        return Arrays.toString(this.toArray());
    }
}
