package life.icetea.learn.algorithm.sort;

import java.util.HashMap;

public class LRUCache {

    private HashMap<Integer, LRUCacheNode> cacheMap = new HashMap<>();
    private LRUCacheNode headNode, tailNode;

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        headNode = new LRUCacheNode();
        tailNode = new LRUCacheNode();
        headNode.next = tailNode;
        tailNode.prev = headNode;
    }

    public int get(int key) {
        LRUCacheNode node = cacheMap.get(key);
        if (node == null) {
            return -1;
        }
        remove(node);
        addToHead(node);
        return node.value;
    }


    public void put(int key, int value) {
        LRUCacheNode lruCacheNode = cacheMap.get(key);
        if (lruCacheNode == null) {
            if (cacheMap.size() >= capacity) {
                LRUCacheNode removeNode = removeTail();
                cacheMap.remove(removeNode.key);
            }
            lruCacheNode = new LRUCacheNode(key, value);
            addToHead(lruCacheNode);
            cacheMap.put(key, lruCacheNode);
        } else {
            lruCacheNode.value = value;
            remove(lruCacheNode);
            addToHead(lruCacheNode);
        }
    }

    private void addToHead(LRUCacheNode node) {
        LRUCacheNode next = headNode.next;
        node.prev = headNode;
        node.next = next;
        headNode.next = node;
        next.prev = node;
    }

    private LRUCacheNode removeTail() {
        LRUCacheNode res = tailNode.prev;
        remove(res);

        return res;
    }

    private void remove(LRUCacheNode removeNode) {
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
    }

    class LRUCacheNode {
        int key;
        int value;
        LRUCacheNode prev;
        LRUCacheNode next;

        public LRUCacheNode() {
        }

        public LRUCacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int i = lRUCache.get(1);// 返回 1
        System.out.println(i);
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        int i1 = lRUCache.get(2);// 返回 -1 (未找到)
        System.out.println(i1);
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        int i2 = lRUCache.get(1);// 返回 -1 (未找到)
        System.out.println(i2);
        int i3 = lRUCache.get(3);// 返回 3
        System.out.println(i3);
        int i4 = lRUCache.get(4);// 返回 4
        System.out.println(i4);

//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.get(2); // -1
//        lRUCache.put(2, 6);
//        lRUCache.get(1); // -1
//        lRUCache.put(1, 5);
//        lRUCache.put(1, 2);
//        lRUCache.get(1);
//        int i = lRUCache.get(2);
//        System.out.println(i);


    }

}
