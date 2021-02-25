package top.ks.learn.算法.链表专项题.a反转链表;


import top.ks.learn.算法.链表专项题.ListNode;

public class Solution {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :迭代写法
     * @author : birjc
     * @CreateDate : 2021-01-17 23:36
     */
    public static ListNode reverseListDD(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :递归写法
     * @author : birjc
     * @CreateDate : 2021-01-17 23:36
     */
    public static ListNode reverseListDG(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode temp = cur.next;
        cur.next = pre;
        return reverseListDG(cur, temp);
    }

    private static ListNode reverseListDG1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseListDG1(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public static void main(String[] args) {
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode result = reverseListDG(null, node1);
        System.out.println(result);
    }
}
