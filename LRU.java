import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LRU {
    public static void main(String[] args) {
        
        // Test case - 1
        int[] arr = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int frames = 4;
        
        // Test case - 2
        // int[] arr = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        // int frames = 3;
        
        // Test case - 3
        // int[] arr = {3, 1, 2, 3, 4, 2, 3, 0, 3, 1, 3};
        // int frames = 3;
        
        // Test case - 4
        // int[] arr = {3, 2, 1, 3, 4, 1, 6, 2, 4, 3, 4, 2, 1, 4, 5, 2, 1, 3, 4};
        // int frames = 3;

        int hitCount = 0;
        int missCount = 0;

        Helper helper = new Helper(frames);

        ArrayList<String> hitOrMissList = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {
            if(helper.get(arr[i]) == -1) {
                hitOrMissList.add("Miss");
                missCount++;
                helper.put(arr[i], arr[i]);
                System.out.println(helper.printList() + " Miss");
            } else {
                hitOrMissList.add("Hit");
                hitCount++;
                System.out.println(helper.printList() + " Hit");
            }
        }
                
        System.out.println(hitOrMissList);

        double hitRatio = ((double)hitCount / arr.length) * 100;
        double missRatio = ((double)missCount / arr.length) * 100;

        System.out.println("Hit %: " + hitRatio + "%");
        System.out.println("Miss %: " + missRatio + "%");
    }
}

class Helper {
    Map<Integer, Node> map;
    int frames;

    Node head = null;
    Node tail = null;

    public Helper(int frames) {
        this.map = new HashMap<>();
        this.frames = frames;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        deleteFromList(node);
        setListHead(node);

        return node.getValue();
    }

    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.setValue(value);

            deleteFromList(node);
            setListHead(node);
        } else {
            if(map.size() >= frames) {
                map.remove(tail.getKey());
                deleteFromList(tail);
            }

            Node node = new Node(key, value);

            map.put(key, node);
            setListHead(node);
        }
    }

    public ArrayList<String> printList() {
        Node node = head;
        ArrayList<String> list = new ArrayList<>();
        while(node != null) {
            list.add(node.getKey() + "");
            node = node.getNext();
        }
        return list;
    }

    private void deleteFromList(Node node) {
        if(node.getPrev() != null && node.getNext() != null) {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        } else if(node.getPrev() == null && node.getNext() != null) {
            head = node.getNext();
            head.setPrev(null);
        } else if(node.getPrev() != null && node.getNext() == null) {
            tail = node.getPrev();
            tail.setNext(null);
        } else {
            head = null;
            tail = null;
        }
    }

    private void setListHead(Node node) {
        if(head == null) {
            head = node;
            tail = node;
        } else {
            head.setPrev(node);
            node.setNext(head);
            node.setPrev(null);
            head = node;
        }
    }
}

class Node {
    private int key;
    private int value;

    private Node prev;
    private Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
}
