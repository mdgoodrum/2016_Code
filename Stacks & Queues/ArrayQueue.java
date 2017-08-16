/**
 * Your implementation of an array-backed queue.
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you <b>must not</b>
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Cannot dequeue from "
                + "empty queue.");
        }
        T temp = backingArray[front];
        backingArray[front] = null;
        if (size != 1) {
            front = (front + 1) % backingArray.length;
        }
        size--;
        return temp;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary. If a regrow is necessary, you should copy elements to the
     * front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (size == backingArray.length - 1) {
            T[] array = (T[]) new Object[backingArray.length
                + (backingArray.length / 2)];
            for (int x = 0; x < backingArray.length; x++) {
                array[x] = backingArray[x];
            }
            backingArray = array;
        }
        if (size != 0) {
            back = (back + 1) % backingArray.length;
        }
        backingArray[back] = data;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }
}