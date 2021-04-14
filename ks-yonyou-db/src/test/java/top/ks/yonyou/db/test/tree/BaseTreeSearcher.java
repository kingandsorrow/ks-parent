package top.ks.yonyou.db.test.tree;

public interface BaseTreeSearcher<N> {
    /**
     * 当该方法返回true时,表示匹配到该节点
     *
     * @param node 当前树节点
     * @return
     */
    boolean search(N node);
}
