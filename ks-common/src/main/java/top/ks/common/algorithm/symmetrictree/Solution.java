package top.ks.common.algorithm.symmetrictree;

import java.util.Stack;

/**
 * <b>类名称:</b>Solution<br/>
 * <b>类注释:</b>给定一个二叉树，检查它是否是镜像对称的。<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>birjc<br/>
 * <b>修改人:</b>birjc<br/>
 * <b>修改时间:</b><br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br />
 * <p>
 * Copyright KS 2020-01-28
 */
public class Solution {
    //递归
    public boolean isSymmetricD(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    public boolean dfs(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            return (p == null) && (q == null);
        if (p.val != q.val)
            return false;
        return dfs(p.left, q.right) && dfs(p.right, q.left);
    }

    //迭代(中序遍历迭代方法)
    public boolean isSymmetricX(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> sleft = new Stack<>();
        Stack<TreeNode> sright = new Stack<>();
        TreeNode l = root.left, r = root.right;
        while (l != null || r != null || sleft.size() != 0 || sright.size() != 0) {
            while (l != null && r != null) {
                sleft.push(l);
                l = l.left;
                sright.push(r);
                r = r.right;
            }
            if (l != null || r != null) return false;
            l = sleft.peek();
            sleft.pop();
            r = sright.peek();
            sright.pop();
            if (l.val != r.val) return false;
            l = l.right;
            r = r.left;
        }
        return true;
    }


}
