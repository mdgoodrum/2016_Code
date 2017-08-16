public class HeapTester {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        heap.add(10);
        heap.add(20);
        heap.add(5);
        heap.add(15);
        heap.add(2);
        heap.add(76);
        heap.add(0);
        heap.add(12);
        heap.add(6);
        heap.add(1);
        heap.add(4);
        heap.add(3);

         
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.size());
       
    }
}