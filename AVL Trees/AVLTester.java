public class AVLTester {
    public static void main(String[] args) {
            AVL avl = new AVL();
            avl.add(2);
            avl.add(3);
            avl.add(4);
            avl.add(5);
            avl.remove(2);  
            java.util.List list = avl.levelorder();
            for (int x = 0; x < list.size(); x++) {
                System.out.println(list.get(x));
            }

    }
}