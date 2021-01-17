package top.ks.common.algorithm.链表专项题.合并两个有序链表;

public class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 类似归并排序中的合并过程
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }

    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        // 类似归并排序中的合并过程
        if (l1 == null || l2 == null) {
            return null;
        }
        return null;
    }


    public static void main(String[] args) {

        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode nodea4 = new ListNode(5);
        ListNode nodea3 = new ListNode(2, nodea4);
        ListNode nodea2 = new ListNode(2, nodea3);
        ListNode nodea1 = new ListNode(1, nodea2);

        mergeTwoLists(node1, nodea1);
    }
}
