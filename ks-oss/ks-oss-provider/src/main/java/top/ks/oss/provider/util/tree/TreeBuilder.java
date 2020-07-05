package top.ks.oss.provider.util.tree;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TreeBuilder {
    public static <T extends Map<String, Object>> Tree build(List<T> data) {
        return build(data, "id", "parent", false);
    }

    public static <T extends Map<String, Object>> Tree build(List<T> data, String pkField, String parentField) {
        return build(data, pkField, parentField, true);
    }

    public static <T extends Map<String, Object>> Tree build(List<T> data, String pkField, String parentField, boolean isStrict) {
        if (data == null || data.size() == 0) {
            return null;
        }
        Tree tree = new Tree();
        Map<String, Object> root = getRoot(data, pkField, parentField, isStrict);
        tree.root(root);
        return tree;
    }

    private static <T extends Map<String, Object>> Map<String, Object> getRoot(List<T> data, String pkField, String parentField, boolean isStrict) {
        LinkedHashMap<String, Map<String, Object>> map = new LinkedHashMap<>((int) (data.size() / 0.7));
        for (Map<String, Object> obj : data) {
            map.put(String.valueOf(obj.get(pkField)), obj);
        }
        map.put("-1", new LinkedHashMap<>());//虚拟根
        LinkedHashMap<String, Object> root = (LinkedHashMap<String, Object>) map.get("-1");
        for (Map<String, Object> obj : data) {
            Object parentKey = obj.get(parentField);
            if (parentKey == null) {
                root.put(String.valueOf(obj.get(pkField)), obj);
            } else {
                String parentId = String.valueOf(parentKey);
                if (parentId.length() == 0 || "0".equals(parentId)) { //父id为0也为根
                    root.put(String.valueOf(obj.get(pkField)), obj);
                    continue;
                }
                Map<String, Object> parentObj = map.get(parentId);
                if (parentObj == null) {//找不到父,忽略
                    //throw new Exception("parent not exists:"+parentId);
                    if (!isStrict)
                        root.put(String.valueOf(obj.get(pkField)), obj);
                    continue;
                }
                List<Map<String, Object>> children = (List<Map<String, Object>>) parentObj.get("children");
                if (children == null) {
                    children = new ArrayList<>();
                    parentObj.put("children", children);
                }
                children.add(obj);
            }
        }
        return root;
    }

}
