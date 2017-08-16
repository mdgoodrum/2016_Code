import java.util.Collection;
import java.util.List;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Michael Goodrum
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot initialize with "
                + "null collection.");
        }
        java.util.Iterator<T> iter = data.iterator();
        while (iter.hasNext()) {
            add(iter.next());
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        } else {
            root = add(data, root);
        }
    }

    
    /**
     * private helper method for adding in data
     * @param  data [data to be added]
     * @param  node [current subtree being traversed]
     * @return      [root of each new subtree being traversed]
     */
    private AVLNode<T> add(T data, AVLNode<T> node) {
         if (node == null) {
             node = new AVLNode<T>(data);
             setBalanceFactor(node);
             node.setHeight(0);
             size++;
         } else if (data.compareTo((T) node.getData()) < 0) {
             node.setLeft(add(data, node.getLeft()));
             setBalanceFactor(node);
             if (node.getBalanceFactor() < - 1 || node.getBalanceFactor() > 1) {
                if(data.compareTo((T) node.getLeft().getData()) < 0) {
                     node = leftRotate(node);
                 }
                 else {
                     node = leftRightRotation(node);
                 }
             }
         } else if (data.compareTo((T) node.getData()) > 0) {
             node.setRight(add(data, node.getRight()));
             setBalanceFactor(node);
             if (node.getBalanceFactor() < - 1 || node.getBalanceFactor() > 1) {
                 if(data.compareTo((T) node.getRight().getData()) > 0) {
                     node = rightRotate(node);
                 } else {
                     node = rightLeftRotation(node);
                 }
             }
         }
         node.setHeight(height(node));
         return node;
     }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data.");
        }
        if (root.getData().equals(data)) {
            AVLNode<T> temp = root;
            AVLNode dummy = new AVLNode(0);
            dummy.setLeft(root);
            remove(dummy, data);
            root = dummy.getLeft();
            return temp.getData();
        } else {
            return remove(root, data).getData();
        }
    }

    /**
     * private helper method for recursion
     * @param  parent ["root" of recursed tree]
     * @param  data   [data to be removed]
     * @return        [data removed]
     */
    private AVLNode<T> remove(AVLNode<T> parent, T data) {
        if (parent == null) {
            throw new java.util.NoSuchElementException("Cannot remove data not "
                + "present in BST.");
        }
        AVLNode<T> temp = null;
        if (parent.getLeft() != null 
            && data.compareTo(parent.getLeft().getData()) == 0) {
            temp = parent.getLeft();
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
                AVLNode<T> predecessor = new AVLNode(
                    predecessor(parent.getLeft().getLeft()));
                predecessor.setLeft(parent.getLeft().getLeft());
                predecessor.setRight(parent.getLeft().getRight());
                parent.setLeft(predecessor);
                size++;
                remove(parent.getLeft(), parent.getLeft().getData());
            }
        } else if (parent.getRight() != null 
            && data.compareTo(parent.getRight().getData()) == 0) {
            temp = parent.getRight();
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
                AVLNode<T> predecessor = new AVLNode(predecessor(
                    parent.getRight().getLeft()));
                predecessor.setLeft(parent.getRight().getLeft());
                predecessor.setRight(parent.getRight().getRight());
                parent.setRight(predecessor);
                size++;
                remove(parent.getRight(), parent.getRight().getData());  
            }
            
        } else if (data.compareTo(parent.getData()) > 0) {
            return remove(parent.getRight(), data);
        } else {
            return remove(parent.getLeft(), data);
        }
        size--;
        setBalanceFactor(parent);
        return temp;
    }


    /**
     * Private helper method to determine predecessor
     * @param  leftChild [root of tree to find largest value of]
     * @return           [predecessor of wanted node]
     */
    private T predecessor(AVLNode<T> leftChild) {
        if (leftChild.getRight() == null) {
            return leftChild.getData();
        } else {
            return predecessor(leftChild.getRight());
        }
    }



    /* Rotate binary tree node with left child */     
     private AVLNode<T> leftRotate(AVLNode<T> node2) {
         AVLNode<T> node1 = node2.getLeft();
         node2.setLeft(node1.getRight());
         node1.setRight(node2);

         node2.setHeight(height(node2));
         node1.setHeight(height(node1));

         setBalanceFactor(node2);
         setBalanceFactor(node1);
         return node1;
     }
 
     /* Rotate binary tree node with right child */
     private AVLNode<T> rightRotate(AVLNode<T> node1) {
         AVLNode<T> node2 = node1.getRight();
         node1.setRight(node2.getLeft());
         node2.setLeft(node1);

         node1.setHeight(height(node1));
         node2.setHeight(height(node2));

         setBalanceFactor(node2);
         setBalanceFactor(node1);
         return node2;
     }
     /**
      * Double rotate binary tree node: first left child
      * with its right child; then node k3 with new left child */
     private AVLNode<T> leftRightRotation(AVLNode<T> node3) {
         node3.setLeft(rightRotate(node3.getLeft()));
         setBalanceFactor(node3);
         return leftRotate(node3);
     }
     /**
      * Double rotate binary tree node: first right child
      * with its left child; then node k1 with new right child */      
     private AVLNode<T> rightLeftRotation(AVLNode<T> node1) {
         node1.setRight(leftRotate(node1.getRight()));
         setBalanceFactor(node1);
         return rightRotate(node1);
     }    

    private void setBalanceFactor(AVLNode parent) {
        int leftHeight;
        int rightHeight;
        if (parent.getLeft() == null) {
            leftHeight = -1;
        } else {
            leftHeight = height(parent.getLeft());
        }
        if (parent.getRight() == null) {
            rightHeight = -1;
        } else {
            rightHeight = height(parent.getRight());
        }
        parent.setBalanceFactor(leftHeight - rightHeight);
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data.");
        }
        if (root != null) {
            return get(data, root);
        } else {
            return get(data, null);
        }
    }

    /**
     * private recursive helper method for retreiving an element
     * feed in a node and traverse down till you run into the element or you are at a leaf
     * @param  data [data for retreival]
     * @param  node ["root" of tree searchign is occuring in]
     * @return      [value your looking for]
     */
    private T get(T data, AVLNode<T> node) {
        if (node == null) {
            throw new java.util.NoSuchElementException(
                "Cannot get data not present in AVL tree.");
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
        List<T> list = new java.util.ArrayList<>();
        preorder(root, list);
        return list;
    }

    /**
     * recursive helper method to return preorder traversal
     * @param node [node currently at]
     * @param list [preorder representation]
     */
    private void preorder(AVLNode<T> node, List<T> list) {
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
        List<T> list = new java.util.ArrayList<>();
        postorder(root, list);
        return list;
    }

    /**
     * recursive helper method to return postorder traversal
     * @param node [node currently at]
     * @param list [postorder representation]
     */
    private void postorder(AVLNode<T> node, List<T> list) {
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
        List<T> list = new java.util.ArrayList<>();
        inorder(root, list);
        return list;
    } 

    /**
     * recursive helper method to return inorder traversal
     * @param node [node currently at]
     * @param list [inorder representation]
     */
    private void inorder(AVLNode<T> node, List<T> list) {
        if (node != null) {
            inorder(node.getLeft(), list);
            list.add((T) node.getData());
            inorder(node.getRight(), list);
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> list = new java.util.ArrayList<>();
        java.util.Queue<AVLNode> queue = new java.util.LinkedList<AVLNode>();
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
    private void levelorder(AVLNode<T> node, List<T> list, 
        java.util.Queue<AVLNode> queue, int queueSize) {
        if (node != null) {
            queue.add(node);
            queueSize++;
        }
        while (queueSize != 0) {
            if (queue.peek() != null) {
                AVLNode<T> temp = queue.peek();
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
    private int height(AVLNode<T> node) {
        if (node != null) {
            int height = (Integer) java.lang.Math.max(height(node.getLeft()), height(node.getRight())) + 1;
            return height;
        }
        return -1;
    }
    
    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}