package top.ks.common.algorithm.链表专项题.链表倒数第k个节点;

/**
 * <b>类名称:</b>Solution<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2021-01-02
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int kthToLast(ListNode head, int k) {
        ListNode kuai = head;
        ListNode man = head;

        while (kuai != null) {
            while (k > 0) {
                kuai = kuai.next;
                k--;
            }
            man = man.next;
            kuai = kuai.next;
        }

        return man.next.val;
    }
}
