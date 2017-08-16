/**
 * Your implementation of an array-backed stack.
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot pop from "
                + "empty stack.");
        }
        T temp = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return temp;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary.
     *
     * @see StackInterface#push()
     */
    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot push null data.");
        }
        if (size == backingArray.length) {
            T[] temp = (T[]) new Object[size + (size / 2)];
            for (int x = 0; x < backingArray.length; x++) {
                temp[x] = backingArray[x];
            }
            temp[size] = data;
            backingArray = temp;
            size++;
        } else {
            backingArray[size] = data;
            size++;
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the backing array of this stack.
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