package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {

    private ImmutableLinkedList list;

    public Stack(){
        this.list = new ImmutableLinkedList();
    }

    // Returns the object from the top of the Stack without removing it
    public Object peek(){
        if (this.isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        return this.list.getFirst();
    }

    // Removes and returns the object from the top of the Stack
    public Object pop(){
        if (this.isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        Object e = this.list.getFirst();
        this.list = this.list.removeFirst();
        return e;
    }

    // Adds an object to the the top of the Stack.
    public void push(Object e){
        this.list = this.list.addFirst(e);
    }

    public boolean isEmpty(){
        return this.list.size() == 0;
    }

}
