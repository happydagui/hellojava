package hello.datastruc;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by min on 17-2-14.
 * vmargs: -ea
 */
public class CollectionDemo {
    public static void main(String[] args) {
        testStack();
        testLinkedList();


    }

    /**
     * About Stack
     * in JDK:
     * `class Stack<E> extends Vector<E>`
     * Thread safe, fast-fail
     */
    static void testStack() {
        Stack<String> stack = new Stack<String>();
        stack.push("hello");
        stack.push("world");

        assert "world".equals(stack.peek());
        assert "world".equals(stack.pop()); // FILO
        assert "hello".equals(stack.pop());
    }

    /**
     * About Queue, Deque, LinkedList
     * in JDK:
     * `class LinkedList<E> implements List<E>, Deque<E>`
     * `Deque<E> extends Queue`
     */
    static void testLinkedList() {
        LinkedList<String> linkedList = new LinkedList<String>();
        // as a queue
        linkedList.add("hello");
        linkedList.add("world");
        assert "hello".equals(linkedList.peek()); // head of the queue
        assert "hello".equals(linkedList.remove()); // FIFO
        assert "world".equals(linkedList.remove());
    }

    /**
     * PriorityQueue
     */
    static void testQueue2() {

    }
}
