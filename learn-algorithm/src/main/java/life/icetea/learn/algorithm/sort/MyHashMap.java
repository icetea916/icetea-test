package life.icetea.learn.algorithm.sort;

public class MyHashMap<K, V> {

    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();

        myHashMap.put("icetea", 19);
        myHashMap.put("icetea2", 20);
        myHashMap.put("icetea3", 31);

        Integer i = myHashMap.get("icetea");
        System.out.println(i);

        Integer i1 = myHashMap.get("ice");
        System.out.println(i1);

        myHashMap.put("ice", 17);
        Integer i2 = myHashMap.get("ice");
        System.out.println(i2);

    }

    private EntryNode<K, V>[] hashArr;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int capacity) {
        this.hashArr = new EntryNode[capacity];
    }

    class EntryNode<K, V> {
        K key;
        V value;
        EntryNode next;
    }

    public V get(K k) {
        EntryNode<K, V> node = getNode(k);
        return node == null ? null : node.value;
    }

    private EntryNode<K, V> getNode(K k) {
        int index = getIndex(k);
        EntryNode<K, V> node = hashArr[index];
        if (node == null) {
            return node;
        }
        while (node != null && !node.key.equals(k)) {
            node = node.next;
        }
        return node;
    }

    public void put(K k, V v) {
        EntryNode<K, V> node = getNode(k);
        if (node == null) {
            int index = getIndex(k);
            node = hashArr[index];
            if (node == null) {
                node = new EntryNode<>();
                node.key = k;
                node.value = v;
                hashArr[index] = node;
            } else {
                while (node.next != null) {
                    node = node.next;
                }
                node.next = new EntryNode<>();
                node.next.key = k;
                node.next.value = v;
            }
        } else {
            node.value = v;
        }
    }


    private int getIndex(K k) {
        long abs = Math.abs((long) k.hashCode());
        long index = abs % hashArr.length;
        return (int) index;
    }

}
