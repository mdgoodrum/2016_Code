import java.util.Collection;
import java.util.List;

/**
 * Your implementation of a binary search tree.
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot initialize with "
                + "null collection.");
        }
        java.util.Iterator<T> iter = data.iterator();
        while (iter.hasNext()) {
            if (iter.next() == null) {
                throw new IllegalArgumentException("Cannot add null data.");
            } else {
                add(iter.next());
            }
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (root == null) {
            root = new BSTNode(data);
            size++;   
        } else {
            add(root, data);
        }
    }

    /**
     * private helper method to recursively add to the tree
     * @param node [current node were checking]
     * @param data [data to be added]
     */
    private void add(BSTNode<T> node, T data) {
        if (node.getData().compareTo(data) > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode(data));
                size++;
            } else {
                add(node.getLeft(), data);
            }
        } else if (node.getData().compareTo(data) < 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode(data));
                size++;
            } else {
                add(node.getRight(), data);
            }
        }
    }


    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data.");
        }
        if (root.getData().equals(data)) {
            T temp = root.getData();
            BSTNode dummy = new BSTNode(0);
            dummy.setLeft(root);
            remove(dummy, data);
            root = dummy.getLeft();
            return temp;
        } else {
            return remove(root, data);
        }
    }

    /**
     * private helper method for recursion
     * @param  parent ["root" of recursed tree]
     * @param  data   [data to be removed]
     * @return        [data removed]
     */
    private T remove(BSTNode<T> parent, T data) {
        if (parent == null) {
            throw new java.util.NoSuchElementException("Cannot remove data not "
                + "present in BST.");
        }
        if (parent.getLeft() != null 
            && data.compareTo(parent.getLeft().getData()) == 0) {
            T temp = parent.getLeft().getData();
            if (parent.getLeft().getLeft() == null 
                && parent.getLeft().getRight() == null) {
                parent.setLeft(null);
            } else if (parent.getLeft().getLeft() != null 
                && parent.getLeft().getRight() == null) {
                parent.setLeft(parent.getLeft().getLeft());
            } else if (parent.getLeft().getRight() != null 
                && parent.getLeft().getLeft() == null) {
                parent.setLeft(parent.getLeft().getRight());
            } else if (parent.getLeft().getLeft() != null 
                && parent.getLeft().getRight() != null) {
                BSTNode predecessor = new BSTNode(
                    predecessor(parent.getLeft().getLeft()));
                predecessor.setLeft(parent.getLeft().getLeft());
                predecessor.setRight(parent.getLeft().getRight());
                parent.setLeft(predecessor);
                size++;
                remove(parent.getLeft(), parent.getLeft().getData());
            }
            size--;
            return temp;
        } else if (parent.getRight() != null 
            && data.compareTo(parent.getRight().getData()) == 0) {
            T temp = parent.getRight().getData();
            if (parent.getRight().getLeft() == null 
                && parent.getRight().getRight() == null) {
                parent.setRight(null);
            } else if (parent.getRight().getRight() != null 
                && parent.getRight().getLeft() == null) {
                parent.setRight(parent.getRight().getRight());
            } else if (parent.getRight().getLeft() != null 
                && parent.getRight().getRight() == null) {
                parent.setRight(parent.getRight().getLeft());
            } else if (parent.getRight().getLeft() != null 
                && parent.getRight().getRight() != null) {
                BSTNode predecessor = new BSTNode(predecessor(
                    parent.getRight().getLeft()));
                predecessor.setLeft(parent.getRight().getLeft());
                predecessor.setRight(parent.getRight().getRight());
                parent.setRight(predecessor);
                size++;
                remove(parent.getRight(), parent.getRight().getData());  
            }
            size--;
            return temp;
        } else if (data.compareTo(parent.getData()) > 0) {
            return remove(parent.getRight(), data);
        } else {
            return remove(parent.getLeft(), data);
        }
    }

    /**
     * Private helper method to determine predecessor
     * @param  leftChild [root of tree to find largest value of]
     * @return           [predecessor of wanted node]
     */
    private T predecessor(BSTNode<T> leftChild) {
        if (leftChild.getRight() == null) {
            return leftChild.getData();
        } else {
            return predecessor(leftChild.getRight());
        }
    }


    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data.");
        }
        if (root != null) {
            if (root.getData().compareTo(data) == 0) {
                return root.getData();
            } else if (root.getData().compareTo(data) > 0) {
                return get(data, root.getLeft());
            } else {
                return get(data, root.getRight());
            }
        } else {
            get(data, null);
        }
    }

    /**
     * private recursive helper method
     * @param  data [data for retreival]
     * @param  node ["root" of tree searchign is occuring in]
     * @return      [value your looking for]
     */
    private T get(T data, BSTNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException(
                "Cannot get data not present in BST.");
        }
        if (node.getData().compareTo(data) == 0) {
            return node.getData();
        } else if (node.getData().compareTo(data) > 0) {
            return get(data, node.getLeft());
        } else {
            return get(data, node.getRight());
        }
    }

    @Override
    public boolean contains(T data) {
        try {
            get(data);
            return true;
        } catch (java.util.NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> list = new java.util.ArrayList();
        preorder(root, list);
        return list;
    }

    /**
     * recursive helper method to return preorder traversal
     * @param node [node currently at]
     * @param list [preorder representation]
     */
    private void preorder(BSTNode node, List<T> list) {
        if (node != null) {
            list.add((T) node.getData());
            if (node.getLeft() != null) {
                preorder(node.getLeft(), list);
            }
            if (node.getRight() != null) {
                preorder(node.getRight(), list);
            }
        }
    }

    @Override
    public List<T> postorder() {
        List<T> list = new java.util.ArrayList();
        postorder(root, list);
        return list;
    }

    /**
     * recursive helper method to return postorder traversal
     * @param node [node currently at]
     * @param list [postorder representation]
     */
    private void postorder(BSTNode node, List<T> list) {
        if (node != null) {
            if (node.getLeft() != null) {
                postorder(node.getLeft(), list);
            }
            if (node.getRight() != null) {
                postorder(node.getRight(), list);
            }
            list.add((T) node.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> list = new java.util.ArrayList();
        inorder(root, list);
        return list;
    } 

    /**
     * recursive helper method to return inorder traversal
     * @param node [node currently at]
     * @param list [inorder representation]
     */
    private void inorder(BSTNode node, List<T> list) {
        if (node != null) {
            inorder(node.getLeft(), list);
            list.add((T) node.getData());
            inorder(node.getRight(), list);
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> list = new java.util.ArrayList();
        java.util.Queue<BSTNode> queue = new java.util.LinkedList<BSTNode>();
        int queueSize = 0;
        levelorder(root, list, queue, queueSize);
        return list;
    }

    /**
     * recursive helper method to return levelorder traversal
     * @param node      [node currently at]
     * @param list      [levelorder representation]
     * @param queue     [tool for getting each level]
     * @param queueSize [tool to make sure theres still more data]
     */
    private void levelorder(BSTNode node, List<T> list, 
        java.util.Queue<BSTNode> queue, int queueSize) {
        if (node != null) {
            queue.add(node);
            queueSize++;
        }
        while (queueSize != 0) {
            if (queue.peek() != null) {
                BSTNode temp = queue.peek();
                queue.remove();
                queueSize--;
                list.add((T) temp.getData());
                queue.add(temp.getLeft());
                queueSize++;
                queue.add(temp.getRight());
                queueSize++;
            } else {
                queue.remove();
                queueSize--;
            }
        }
    }

    @Override
    public void clear() {
        if (root != null) {
            root.setRight(null);
            root.setLeft(null);
            root = null;
            size = 0;
        }
    }

    @Override
    public int height() {
        if (size == 0) {
            return -1;
        } else {
            return height(root);
        }
    }

    /**
     * private helper method for height
     * @param  node [node your looking to see its depth]
     * @return      [height of tree]
     */
    private int height(BSTNode node) {
        if (node != null) {
            int height = (Integer) java.lang.Math.max(height(node.getLeft()),
                height(node.getRight())) + 1;
            return height;
        }
        return -1;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}