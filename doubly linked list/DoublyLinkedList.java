/**
 * My implementation of a DoublyLinkedList
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index cannot be less than 0 "
                + "or value outside array.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (size == 0) {
            head = new LinkedListNode(data);
            tail = new LinkedListNode(data);
            size++;
        }
        if (index == size) {
            addToBack(data);
        }
        if (index == 0) {
            addToFront(data);
        } else {
            int token = 0;
            for (LinkedListNode n = head; n != null; n.getNext()) {
                token++;
                if (index == token) {
                    LinkedListNode<T> node = new LinkedListNode(data);
                    node.setPrevious(n.getPrevious());
                    node.setNext(n);
                    n.getPrevious().setNext(node);
                    n.setPrevious(node);
                    size++;
                }
            }
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        LinkedListNode<T> node = new LinkedListNode(data);
        node.setPrevious(null);
        if (size == 0) {
            node.setNext(null);
            head = node;
            tail = node;
            size++;
        } else {
            node.setNext(head);
            head.setPrevious(node);
            head = node;
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        LinkedListNode<T> node = new LinkedListNode(data);
        node.setPrevious(tail);
        if (size == 0) {
            node.setNext(null);
            node.setPrevious(null);
            head = node;
            tail = node;
            size++;
        } else {
            node.setNext(null);
            tail.setNext(node);
            tail = node;
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index cannot be less than 0 "
                + "or value outside array.");
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        }
        int token = 0;
        T temp = null;
        for (LinkedListNode n = head; n != null; n = n.getNext()) {
            if (token == index) {
                temp = (T) n.getData();
                n.getPrevious().setNext(n.getNext());
                n.getNext().setPrevious(n.getPrevious());
                size--; 
            }
            token++;
        }
        return temp;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return  null;
        }
        T temp = head.getData();
        head = head.getNext();
        head.setPrevious(null);
        size--;
        return temp;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T temp = tail.getData();
        tail = tail.getPrevious();
        tail.setNext(null);
        size--;
        return temp;
    }

    @Override
    public boolean removeAllOccurrences(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data.");
        }
        boolean removed = false;
        for (LinkedListNode n = head; n != null; n = n.getNext()) {
            if (n.getData().equals(data)) {
                if (n.equals(head)) {
                    removeFromFront();
                    removed = true;
                } else if (n.equals(tail)) {
                    removeFromBack();
                    removed = true;
                } else {
                    n.getPrevious().setNext(n.getNext());
                    n.getNext().setPrevious(n.getPrevious());
                    size--;
                    removed = true;
                }
            }
        }
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index cannot be less than 0"
            + " or value outside array.");
        }
        T get = null;
        if (index == 0) {
            get = head.getData();
        } else if (index == size - 1) {
            get = tail.getData();
        } else {
            int token = 0;
            for (LinkedListNode n = head; n != null; n = n.getNext()) {
                if (token == index) {
                    get = (T) n.getData();
                }
                token++;
            }
        }
        return get;
    }

    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[size];
        int token = 0;
        for (LinkedListNode n = head; n != null; n = n.getNext()) {
            array[token] = (T) n.getData();
            token++;
        }
        return array;
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
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}