package top.ks.yonyou.db.test.basetree;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseTreeService {

    /**
     * 获取树结构数据
     *
     * @return
     */
    public <T extends BaseTreeAO> ServiceResult<List<T>> listTreeNodes(List<T> allList) {
        ServiceResult<List<T>> ret = new ServiceResult<>();
        List<T> parentList = new ArrayList<>();//根节点
        List<T> classifyList = null;
        if (!CollectionUtils.isEmpty(allList)) {
            classifyList = allList;
            for (T resource : allList) {
                if (StringUtils.isEmpty(resource.getParentid())) {
                    parentList.add(resource);
                }
            }
        }
        //返回的树形节点数据
        List<T> treeNodeList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(parentList)) {
            for (T parent : parentList) {
                //递归查询所有子节点
                treeNodeList.add(recursiveTree(parent, classifyList));
            }
        }
        ret.setData(treeNodeList);
        ret.setSucceed(true);
        return ret;
    }


    /**
     * 递归算法解析成树形结构
     */
    public <T extends BaseTreeAO> T recursiveTree(T parentNode, List<T> classifyList) {
        List<T> childTreeNodes = getChildTree(parentNode, classifyList);
        if (!CollectionUtils.isEmpty(childTreeNodes)) {
            for (T child : childTreeNodes) {
                T n = recursiveTree(child, classifyList);
                parentNode.getChildren().add(n);
            }
        }
        return parentNode;
    }

    /**
     * 根据父节点ID获取所有子节点
     */
    public <T extends BaseTreeAO> List<T> getChildTree(T parentNode, List<T> classifyList) {
        String parentId = parentNode.getId();
        String parentAncestorpath = parentNode.getAncestorpath();
        List<T> childNodes = new ArrayList<>();
        if (!CollectionUtils.isEmpty(classifyList)) {
            for (T resource : classifyList) {
                if (parentId.equals(resource.getParentid())) {
                    resource.setAncestorpath((StringUtils.isEmpty(parentAncestorpath) ? "" : parentAncestorpath + ",") + parentId);
                    childNodes.add(resource);
                }
            }
        }
        return childNodes;
    }

}
