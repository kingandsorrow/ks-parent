package top.ks.common.algorithm.subject2;

/**
 * <b>类名称:</b>Subject$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/9/3<br/>
 * <b>修改备注:</b><br/>
 * <p>
 * Copyright KS 2018/9/3
 */
public class Solution {

    /**
     * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */

    public static void main(String[] args) {
        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(9);
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;
        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);
        ListNode l24 = new ListNode(8);
        l21.next = l22;
        l22.next = l23;
        l23.next = l24;
        System.out.println(getSumNode(l11, l21).toString());
    }


    public static ListNode getSumNode(ListNode l1, ListNode l2) {
        ListNode headNode = new ListNode(0);
        int carr = 0;
        ListNode n1 = l1;
        ListNode n2 = l2;
        int p1 = 0;
        int p2 = 0;
        ListNode handNode = headNode;
        while (n1 != null || n2 != null) {
            p1 = n1 == null ? 0 : n1.val;
            p2 = n2 == null ? 0 : n2.val;
            int sum = p1 + p2 + carr;
            carr = sum / 10;
            handNode.next = new ListNode(sum % 10);
            handNode = handNode.next;
            if (l1 != null) {
                n1 = n1.next;
            }
            if (l2 != null) {
                n2 = n2.next;
            }
        }
        if (carr > 0) {
            handNode.next = new ListNode(carr);
        }
        return headNode.next;
    }


}
