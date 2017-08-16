/**
 * Your implementation of a min heap.
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial size of {@code STARTING_SIZE} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (size == backingArray.length - 1) {
            T[] temp = (T[]) new Comparable[backingArray.length 
            + (backingArray.length / 2)];
            for (int x = 1; x < backingArray.length; x++) {
                temp[x] = backingArray[x];
            }
            backingArray = temp;
        }
        size++;
        backingArray[size] = item;
        heapify(size);
    }

    /**
     * private helper to heapify
     * @param start [root]
     */
    private void heapify(int start) {
        if (start > 1) {
            while (start > 1 
                && backingArray[start].compareTo(backingArray[start / 2]) 
                < 0) {
                T temp = backingArray[start];
                backingArray[start] = backingArray[start / 2];
                backingArray[start / 2] = temp;
                start = start / 2;
            }
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Cannot remove from "
                + "empty heap.");
        } else {
            T temp = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            if (size > 1) {
                checkDown(1);
            }
            return temp;
        }
    }

    /**
     * private helper method to remove
     * @param index [index node currently checking]
     */
    private void checkDown(int index) {
        if (index * 2 <= size) {
            int min = index;
            int leftChild = index * 2;
            int rightChild = (index * 2) + 1;
            if (backingArray[rightChild] == null) {
                if (backingArray[leftChild] != null) {
                    min = leftChild;
                }
            } else {
                if (backingArray[leftChild].compareTo(backingArray[rightChild]) 
                    < 0) {
                    min = leftChild;
                } else {
                    min = rightChild;
                }
            }
            if (backingArray[index].compareTo(backingArray[min]) > 0) {
                T temp = backingArray[min];
                backingArray[min] = backingArray[index];
                backingArray[index] = temp;
                checkDown(min);
            } 
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}