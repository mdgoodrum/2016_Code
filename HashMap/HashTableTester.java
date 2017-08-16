public class HashTableTester {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(1, "steve");
        map.put(3, "rachel");
        map.put(15, "michael");
        map.put(4, "sarah");
        map.put(5, "weed");
        map.put(6, "Drugs");
        map.put(0, "steven");
        map.put("bananas", "bananas");
        map.put(2, "S");
        map.put(35, "simba");
        map.put(58, "roumba");
        map.put(12, "a");
        System.out.println(map.size());
        java.util.List list = map.values();
        for (int x = 0; x < list.size(); x++) {
            System.out.print(list.get(x) + ", ");
        }
    }
}