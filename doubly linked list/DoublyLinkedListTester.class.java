public class DoublyLinkedListTester {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
    //    LinkedListNode first = new LinkedListNode("first");
    //    LinkedListNode second = new LinkedListNode("second");
    //    LinkedListNode third = new LinkedListNode("third");
    //    LinkedListNode fourth = new LinkedListNode("fourth");
    //    LinkedListNode fifth = new LinkedListNode("fifth");
    //    LinkedListNode sixth = new LinkedListNode("sixth");
    //    LinkedListNode seventh = new LinkedListNode("seventh");
    //    LinkedListNode eighth = new LinkedListNode("eighth");
    //    LinkedListNode nineth = new LinkedListNode("nineth");
        list.addToBack("first");
        list.addToBack("second");
        list.addToBack("third");
        list.addToBack("fourth");
        for (LinkedListNode n = head; n != null; n.getNext()) {
            System.out.println(n);
        }

    }
}