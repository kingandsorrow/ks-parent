package top.ks.common.algorithm.链表专项题.扁平化多级双向链表;

import java.util.LinkedList;

class Solution {
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }

    ;

    /**
     * 借助栈
     */
    public static Node flatten_1(Node head) {
        if (head == null) {
            return null;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = head;
        while (true) {
            if (cur.child != null) {
                // 将 next 节点入栈
                if (cur.next != null) {
                    stack.push(cur.next);
                }
                // 将子链表扁平化
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            }
            // 遍历子链表的下一个节点或是从栈中弹出 next 节点
            if (cur.next != null) {
                cur = cur.next;
            } else if (!stack.isEmpty()) {
                Node next = stack.pop();
                cur.next = next;
                next.prev = cur;
                cur = next;
            } else {
                return head;
            }
        }
    }

    /**
     * 递归
     */
    public Node flatten(Node head) {
        if (head == null) return null;
        Node cur = head;
        // 向后遍历
        while (cur != null) {
            // 存在子链表，进行递归
            if (cur.child != null) {
                // 保留 next 节点
                Node next = cur.next;
                // 得到扁平化后的子链表，与之相连
                Node child = flatten(cur.child);
                cur.next = child;
                child.prev = cur;
                cur.child = null;
                // 连接原 next
                if (next != null) {
                    while (cur.next != null) {
                        cur = cur.next;
                    }
                    cur.next = next;
                    next.prev = cur;
                }
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        node1.val = 1;
        Node node2 = new Node();
        node2.val = 2;
        Node node3 = new Node();
        node3.val = 3;
        Node node4 = new Node();
        node4.val = 4;
        Node node7 = new Node();
        node7.val = 7;
        Node node8 = new Node();
        node8.val = 8;
        Node node11 = new Node();
        node11.val = 11;
        Node node12 = new Node();
        node12.val = 12;
        node1.next = node2;
        node2.next = node3;
        node2.prev = node1;
        node3.next = node4;
        node3.prev = node2;
        node3.child = node7;
        node7.next = node8;
        node8.prev = node7;
        node8.child = node11;
        node11.next = node12;
        node12.prev = node11;
        flatten_1(node1);
    }
}
