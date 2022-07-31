package practice.test2;

import java.util.LinkedList;
import java.util.*;

/**
 * Created by wenqi on 2022/1/6.
 */
public class LRUCache {
    public static void main(String[] args) {

    }

    class ListNode {
        //key用于删除的时候用
        public String key;
        public String value;
        public ListNode parent;
        public ListNode next;

        public ListNode() {

        }

        public ListNode(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<String, ListNode> map;
    private int maxSize;
    private ListNode head;
    private ListNode rear;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        head = new ListNode();
        rear = new ListNode();
        head.next = rear;
        rear.parent = head;
        map = new HashMap<String, ListNode>();
    }

    public String get(String key) throws Exception {
        if (map.containsKey(key)) {
            //move the Node to head first
            ListNode targetNode = map.get(key);
            MoveNodeToHead(targetNode);
            return targetNode.value;
        } else {
            throw new Exception();
        }
    }

    public boolean put(String key, String value) throws Exception {
        ListNode newNode = new ListNode(key, value);
        if (map.containsKey(key)) {
            ListNode targetNode = map.get(key);
            MoveNodeToHead(targetNode);
            return true;
        } else {
            if (map.size() == maxSize) {
                //remove the node in rear
                ListNode rearParentNode = rear.parent;
                removeNode(rearParentNode);
                map.remove(rearParentNode.key);
                map.put(key, newNode);
                addNodeToHead(newNode);
            } else {
                map.put(key, newNode);
                addNodeToHead(newNode);
            }
            return true;
        }
    }

    public void MoveNodeToHead(ListNode targetNode) {
        removeNode(targetNode);
        addNodeToHead(targetNode);
    }

    public void removeNode(ListNode targetNode) {
        ListNode parentNode = targetNode.parent;
        ListNode nextNode = targetNode.next;
        parentNode.next = targetNode.next;
        nextNode.parent = parentNode;
    }

    public void addNodeToHead(ListNode targetNode) {
        ListNode headNextNode = head.next;
        head.next = targetNode;
        targetNode.parent = head;
        targetNode.next = headNextNode;
        headNextNode.parent = targetNode;
    }
}
