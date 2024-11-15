import java.util.HashMap;

class LFUCache {
    private class Node {
        int key, value, freq;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1; // Initial frequency is 1
        }
    }

    private class DoublyLinkedList {
        Node head, tail;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void add(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        boolean isEmpty() {
            return head.next == tail;
        }

        Node removeLast() {
            if (isEmpty()) return null;
            Node last = tail.prev;
            remove(last);
            return last;
        }
    }

    private final int capacity;
    private int size = 0;
    private int minFreq = 0;
    private final HashMap<Integer, Node> cache;
    private final HashMap<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    private void updateFrequency(Node node) {
        int freq = node.freq;
        freqMap.get(freq).remove(node);
        if (freq == minFreq && freqMap.get(freq).isEmpty()) {
            minFreq++;
        }
        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList()).add(node);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;
        Node node = cache.get(key);
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            if (size == capacity) {
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                Node lfuNode = minFreqList.removeLast();
                cache.remove(lfuNode.key);
                size--;
            }

            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            freqMap.computeIfAbsent(1, k -> new DoublyLinkedList()).add(newNode);
            minFreq = 1;
            size++;
        }
    }
    public static void main(String[] args){
        LFUCache lfu = new LFUCache(2);

        lfu.put(1, 1);         // cache is {1=1}
        lfu.put(2, 2);         // cache is {1=1, 2=2}
        System.out.println(lfu.get(1)); // return 1, cache is {2=2, 1=1}
        lfu.put(3, 3);         // invalidate  key 2, cache is {1=1, 3=3}
        System.out.println(lfu.get(2)); // return -1 (not found)
        System.out.println(lfu.get(3)); // return 3, cache is {1=1, 3=3}
        lfu.put(4, 4);         // invalidate  key 1, cache is {3=3, 4=4}
        System.out.println(lfu.get(1)); // return -1 (not found)
        System.out.println(lfu.get(3)); // return 3
        System.out.println(lfu.get(4)); // return 4
    }
}
