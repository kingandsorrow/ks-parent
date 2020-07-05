package top.ks.oss.provider.util.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tree {
    private Map<String, Object> root;

    public Map<String, Object> root() {
        return root;
    }

    public void root(Map<String, Object> root) {
        this.root = root;
    }

    public void walk(TreeNodeWalker walker) {
        if (root != null) {
            root.values().forEach(root1 -> {
                if (root1 instanceof Map<?, ?>) {
                    walk((Map<String, Object>) root1, walker);
                }
            });
        }
    }

    public <T extends Map<String, Object>> List<T> toList() {
        if (root != null) {
            List<T> list = new ArrayList();
            root.values().forEach(v -> list.add((T) v));
            return list;
        } else {
            return null;
        }

    }

    private void walk(Map<String, Object> parent, TreeNodeWalker walker) {
        if (parent == null) {
            return;
        }
        walker.walk(parent);
        List<Map<String, Object>> children = (List<Map<String, Object>>) parent.get("children");
        if (children != null) {
            for (Map<String, Object> child : children) {
                walk(child, walker);
            }
        }
    }
}
