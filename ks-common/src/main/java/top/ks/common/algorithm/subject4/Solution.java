package top.ks.common.algorithm.subject4;


import com.alibaba.fastjson.JSON;
import top.ks.common.algorithm.subject2.ListNode;

/**
 * <b>类名称:</b>Solution$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b> 两两交换链表中的节点;给定 1->2->3->4, 你应该返回 2->1->4->3.<br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2019/4/15<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2019/4/15
 */
public class Solution {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }


    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        /*ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(4);*/
        l11.next = l12;
        /*l12.next = l13;
        l13.next = l14;*/
        ListNode listNode = swapPairs(l11);
        System.out.println(listNode.toString());
    }


}
