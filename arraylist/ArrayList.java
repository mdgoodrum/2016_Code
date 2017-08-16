/**
 * Your implementation of an ArrayList.
 *
 * @author Michael Goodrum
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index value is either"
            + " negative or not present in the array.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot input null data.");
        }
        if (backingArray.length > size) {
            for (int x = backingArray.length - 1; x >= index; x--) {
                if (backingArray[x] != null) {
                    backingArray[x + 1] = backingArray[x];
                }
            }
            backingArray[index] = data;
        } else {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            for (int x = 0; x < index; x++) {
                temp[x] = backingArray[x];
            }
            temp[index] = data;
            for (int x = index + 1; x < backingArray.length; x++) {
                temp[x] = backingArray[x];
            }
            backingArray = temp;
        }
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot input null data.");
        }
        if (isEmpty()) {
            backingArray[0] = data;
            size++;
        } else if (backingArray.length == size() - 1) {
            T[] temp = (T[]) new Object[backingArray.length * 2];
            temp[0] = data;
            for (int x = 1; x < backingArray.length; x++) {
                temp[x] = backingArray[x - 1];
            }
            backingArray = temp;
            size++;
        } else {
            for (int x = backingArray.length - 1; x >= 0; x--) {
                if (backingArray[x] != null) {
                    backingArray[x + 1] = backingArray[x];
                }
            }
            backingArray[0] = data;
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        size++;
        if (data == null) {
            throw new IllegalArgumentException("Cannot input null data.");
        }
        if (backingArray[backingArray.length - 1] != null) {
            int y = backingArray.length;
            T[] temp = (T[]) new Object[y * 2];
            for (int x = 0; x < backingArray.length; x++) {
                temp[x] = backingArray[x];
            }
            temp[y] = data;
            backingArray = temp;
        } else {
            for (int x = 0; x < backingArray.length; x++) {
                if (backingArray[x] == null) {
                    backingArray[x] = data;
                    x = backingArray.length;
                }
            }
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index value is either"
                + " negative or not present in the array.");
        }
        if (isEmpty()) {
            return null;
        }
        size--;
        T temp = backingArray[index];
        if (index == size()) {
            backingArray[index] = null;
        } else {
            backingArray[index] = null;
            for (int x = index + 1; x < backingArray.length; x++) {
                if (backingArray[x] != null) {
                    backingArray[x - 1] = backingArray[x];
                } else {
                    backingArray[x - 1] = null;
                    x = backingArray.length;
                }
            }
        }
        return temp;
    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T temp = backingArray[0];
        backingArray[0] = null;
        for (int x = 0; x < backingArray.length - 1; x++) {
            backingArray[x] = backingArray[x + 1];
        }
        if (backingArray[backingArray.length - 1] != null) {
            backingArray[backingArray.length - 1] = null;
        }
        return temp;
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T temp;
        if (isEmpty()) {
            return null;
        } else {
            temp = backingArray[size() - 1];
            backingArray[size() - 1] = null;
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index value is either"
            + " negative or not present in the array.");
        } else {
            return backingArray[index];
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}