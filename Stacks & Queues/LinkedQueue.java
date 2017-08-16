/**
 * Your implementation of a linked queue.
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot dequeue "
                + "from empty queue.");
        }
        T temp = head.getData();
        head = head.getNext();
        size--;
        return temp;
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot enqueue null data.");
        }
        LinkedNode node = new LinkedNode(data);
        if (size == 0) {
            head = node;
            tail = node;
            head.setNext(null);
            tail.setNext(null);
            size++;    
        } else if (size == 1) {
            head.setNext(node);
            tail = node;
            node.setNext(null);
            size++;
        } else {
            node.setNext(null);
            tail.setNext(node);
            tail = node;
            size++;
        }
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}