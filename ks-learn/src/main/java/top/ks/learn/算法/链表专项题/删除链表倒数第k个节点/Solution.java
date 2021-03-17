package top.ks.learn.算法.链表专项题.删除链表倒数第k个节点;

import top.ks.learn.算法.链表专项题.ListNode;

public class Solution {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-03-10 00:18
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode slow = head;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            pre = pre.next;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        System.out.println(removeNthFromEnd(node1, 2));

    }
}
