package top.ks.learn.算法.链表专项题.链表两数之和;

public class Solution {

    class ListNode {
        ListNode next;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;
        ListNode l3 = new ListNode(0);
        // 没想出的点 构造结果，用一个指针指着。
        ListNode p = l3;
        while (l1 != null || l2 != null || temp != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + temp;
            temp = sumVal / 10;
            p.next = new ListNode(sumVal % 10);
            p = p.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return l3.next;
    }

    public static void main(String[] args) {
        System.out.println(1/2);
    }
}
