package top.ks.oss.provider.util.tree;

import java.util.Map;

/**
 * Created by yanfengzhao on 17/5/16.
 */
public interface TreeNodeWalker {
    void walk(Map<String, Object> node);
}
