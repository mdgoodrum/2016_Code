public class TestinQueues {
    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
        System.out.println(queue.size());
        queue.enqueue("Sally");
        System.out.println(queue.isEmpty());
        queue.enqueue("Sammy");
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("10");
        queue.enqueue("11");
        queue.enqueue("12");
        System.out.println(queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println("Size: " + queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.size());
    }
}