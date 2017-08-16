public class DoublyLinkedListTester {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        System.out.println(list.isEmpty());
        list.addToBack("Buga Luga");
        System.out.println(list.isEmpty());
        list.addToFront("first");
        list.addToFront("second");
        list.addToFront("third");
        list.addToFront("ugggg");
        list.addToFront("fourth");
        list.addToFront("third");
        System.out.println(list.size());
      

        System.out.println(list.size());
    list.addToFront("steven");
        list.addToFront("fourth");
        list.removeAtIndex(5);
        list.removeFromFront();
        list.removeFromBack();
        System.out.println(list.get(4));
        list.removeAllOccurrences("fourth");
        for (LinkedListNode n = list.getHead(); n != null; n = n.getNext()) {
            System.out.println(n.getData());
        }
        Object[] array = list.toArray();
        for (int x = 0; x < array.length; x++) {
            System.out.println(array[x]);
     }
    }
}