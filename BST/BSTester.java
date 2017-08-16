import java.util.List;

public class BSTester {
    public static void main(String[] args) {
        BST tree = new BST();
        tree.add(40);
        tree.add(30);
        tree.add(50);
        tree.add(60);
        tree.add(70);
        tree.add(65);
        tree.add(67);
        tree.remove(67);
        tree.remove(65);
        tree.remove(70);
        //System.out.println(tree.height()); //works
        //System.out.println(tree.size());  // works
        //System.out.println(tree.contains(17)); //works
        //System.out.println(tree.contains(32)); //works
        //System.out.println(tree.get(17)); //works
        //System.out.println(tree.get(32)); //works
        //tree.remove(40);
        //System.out.println(tree.size());
        System.out.println(tree.size());
        System.out.println(tree.height());
        //List<BSTNode> list = tree.inorder(); // works
        //List<BSTNode> list = tree.preorder(); // works
        //List<BSTNode> list = tree.levelorder(); // works
        //List<BSTNode> list = tree.postorder(); // works
        //for (int x = 0; x < list.size(); x++) {
        //    System.out.println(list.get(x));
       // }
        //System.out.println(tree.height());

    }
}