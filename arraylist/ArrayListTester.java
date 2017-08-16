public class ArrayListTester {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.addToFront(11);
		list.addToFront(10);
        list.addToFront(9);
        list.addToFront(8);
        list.addToFront(7);
        list.addToFront(6);
        list.addToFront(5);
        list.addToFront(4);
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        System.out.println(list.size());
        list.addAtIndex(0, 0);
        System.out.println(list.size());
        for (int x = 0; x < list.size(); x++) {
            System.out.print(list.get(x) + " ");
        }
	}
}