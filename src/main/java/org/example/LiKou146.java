package org.example;

import java.util.HashMap;
import java.util.Map;

/*
    * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
    实现 LRUCache 类：
    LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
    int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
    void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
    函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。



    示例：

    输入
    ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    输出
    [null, null, null, 1, null, -1, null, -1, 3, 4]

    解释
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // 缓存是 {1=1}
    lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
    lRUCache.get(1);    // 返回 1
    lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    lRUCache.get(2);    // 返回 -1 (未找到)
    lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    lRUCache.get(1);    // 返回 -1 (未找到)
    lRUCache.get(3);    // 返回 3
    lRUCache.get(4);    // 返回 4*/
public class LiKou146 {
    public class Node{
        int key;
        int val;
        Node next;
        Node pre;
        public Node(int val, int key){
            this.val = val;
            this.key = key;
        }
        public Node(int key, int val, Node next, Node pre){
            this.val = val;
            this.key = key;
            this.next = next;
            this.pre = pre;
        }
    }
    Map<Integer, Node> map = new HashMap<Integer, Node>();
    int capacity;
    Node dummy = new Node(-1, -1);
    public LiKou146(int capacity) {
        this.capacity = capacity;
        dummy.next = dummy;
        dummy.pre = dummy;
    }

    public int get(int key) {
        Node node = getNode(key);
        return node == null ? -1 : node.val;
    }

    public void put(int key, int value) {
        Node node = getNode(key);
        if(node != null){
            node.val = value;
            return;
        }
        Node node1 = new Node(key, value);
        map.put(key, node1);
        putFirst(node1);
        if(map.size() > capacity) {
            map.remove(dummy.pre.key);
            remove(dummy.pre);
        }
    }
    Node getNode(int key) {
        if(!map.containsKey(key)){
            return null;
        }
        Node node = map.get(key);
        remove(node);
        putFirst(node);
        return node;
    }
    void remove(Node node) {
        node.pre.next = dummy;
        dummy.pre = node.pre;
    }
    void putFirst(Node node) {
        node.next = dummy.next;
        node.pre = dummy;
        dummy.next = node;
        node.next.pre = node;
    }
}
