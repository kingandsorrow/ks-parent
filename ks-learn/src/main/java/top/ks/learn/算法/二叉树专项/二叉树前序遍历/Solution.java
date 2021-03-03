package top.ks.learn.算法.二叉树专项.二叉树前序遍历;

import top.ks.learn.算法.二叉树专项.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
 * Copyright KS 2021-02-26
 */
public class Solution {
    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 栈空间 后进先出
     * @author : birjc
     * @CreateDate : 2021-02-26 10:05
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return res;
    }

}
