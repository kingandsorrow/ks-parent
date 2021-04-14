package top.ks.yonyou.db.test.event;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.yonyou.db.bootstrap.KsYonyouDb;
import top.ks.yonyou.db.test.AdminVO;
import top.ks.yonyou.db.test.basetree.BaseTreeService;
import top.ks.yonyou.db.test.basetree.ServiceResult;
import top.ks.yonyou.db.test.tree.Tree;

import java.util.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsYonyouDb.class)
public class TestEvent {
    @Autowired
    @Qualifier("jdbcTemplateOne")
    private JdbcTemplate jdbcTemplateOne;

    @Test
    public void test() {
        String tenantid = "x0y0m8rn";
        RowMapper<AdminVO> rowMapper = new BeanPropertyRowMapper<AdminVO>(AdminVO.class);
        List<AdminVO> adminVOS = this.jdbcTemplateOne.query("select * from org_orgs where tenantid ='" + tenantid + "' and dr=0 and orgtype", rowMapper);
        Tree<AdminVO, String> tree = new Tree<AdminVO, String>(adminVOS) {
            @Override
            protected String getKey(AdminVO node) {
                return node.getId();
            }

            @Override
            protected String getParentKey(AdminVO node) {
                return node.getParentid();
            }
        };
        Map<String, List<AdminVO>> map = tree.getChildMap();
        Map<String, AdminVO> trrMap = tree.getSelfMap();
        AdminVO rootMap = tree.getRoot();
        tree.getAllNodes();
        System.out.println(rootMap);
    }

    @Test
    public void test2() {
        String tenantid = "x0y0m8rn";
        RowMapper<AdminAO> rowMapper = new BeanPropertyRowMapper<AdminAO>(AdminAO.class);
        List<AdminAO> adminVOS = this.jdbcTemplateOne.query("select UNIX_TIMESTAMP(synchts)*1000 as synchts,objid,isdefault,UNIX_TIMESTAMP(pubts)*1000 as pubts,tenantid as tenant,sourceid,level,isEnd,companytype,FLOOR((length(innercode)/4)) as depth,code,name,id,parentid,parentorgid,orgtype,is_biz_unit,orgtype,innercode,tenantid,UNIX_TIMESTAMP(creationtime)*1000 as creationtime,creator,displayorder,dr,UNIX_TIMESTAMP(effectivedate)*1000 as effectivedate,enable,exchangerate,UNIX_TIMESTAMP(expirationdate)*1000 as expirationdate,external_org as externalOrg,is_biz_unit as isbizunit,UNIX_TIMESTAMP(modifiedtime)*1000 as modifiedtime,modifier,name2,name3,orgid,sysid,UNIX_TIMESTAMP(ts)*1000 as ts,innercode,shortname,region,countryzone,timezone,principal,path,currency,exchangerate from org_orgs where tenantid ='" + tenantid + "' and dr=0 and id!=666666", rowMapper);
        Map<String, AdminEO> map = new HashMap<>();
        for (AdminAO adminVO : adminVOS) {
            AdminEO adminEO = new AdminEO();
            BeanUtil.copyProperties(adminVO, adminEO);
            Map<String, Object> mul = new HashMap<>();
            if (!StringUtils.isEmpty(adminVO.getName())) {
                mul.put("zh_CN", adminVO.getName());
            }
            if (!StringUtils.isEmpty(adminVO.getName2())) {
                mul.put("en_US", adminVO.getName2());
            }
            if (!StringUtils.isEmpty(adminVO.getName3())) {
                mul.put("zh_TW", adminVO.getName3());
            }
            if (StringUtils.isEmpty(adminVO.getAncestorpath())) {
                adminEO.setAncestorpath("");
            }
            adminEO.setMultiLangName(mul);
            map.put(adminVO.getId(), adminEO);
        }
        BaseTreeService baseTreeService = new BaseTreeService();
        ServiceResult serviceResult = baseTreeService.listTreeNodes(adminVOS);
        if (!serviceResult.isSucceed()) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        List<AdminAO> adminAOS = (List<AdminAO>) serviceResult.getData();
        forEachEventData(stringBuilder, adminAOS, map);
        System.out.println(JSON.toJSONString(stringBuilder));
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/" + DateUtil.format(new Date(), "yyyyMMdd") + "_event_" + tenantid + "_orgadmin.sql");
        fileWriter.write(stringBuilder.toString());
    }

    @Test
    public void test3() {
        String tenantid = "x0y0m8rn";
        RowMapper<AdminAO> rowMapper = new BeanPropertyRowMapper<AdminAO>(AdminAO.class);
        List<AdminAO> adminVOS = this.jdbcTemplateOne.query("select UNIX_TIMESTAMP(synchts)*1000 as synchts,objid,isdefault,UNIX_TIMESTAMP(pubts)*1000 as pubts,tenantid as tenant,sourceid,level,isEnd,companytype,FLOOR((length(innercode)/4)) as depth,code,name,id,parentid,parentorgid,orgtype,is_biz_unit,orgtype,innercode,tenantid,UNIX_TIMESTAMP(creationtime)*1000 as creationtime,creator,displayorder,dr,UNIX_TIMESTAMP(effectivedate)*1000 as effectivedate,enable,exchangerate,UNIX_TIMESTAMP(expirationdate)*1000 as expirationdate,external_org as externalOrg,is_biz_unit as isbizunit,UNIX_TIMESTAMP(modifiedtime)*1000 as modifiedtime,modifier,name2,name3,orgid,sysid,UNIX_TIMESTAMP(ts)*1000 as ts,innercode,shortname,region,countryzone,timezone,principal,path,currency,exchangerate from org_admin where tenantid ='" + tenantid + "' and dr=0 and id!=666666", rowMapper);
        Map<String, AdminEO> map = new HashMap<>();
        for (AdminAO adminVO : adminVOS) {
            AdminEO adminEO = new AdminEO();
            BeanUtil.copyProperties(adminVO, adminEO);
            Map<String, Object> mul = new HashMap<>();
            if (!StringUtils.isEmpty(adminVO.getName())) {
                mul.put("zh_CN", adminVO.getName());
            }
            if (!StringUtils.isEmpty(adminVO.getName2())) {
                mul.put("en_US", adminVO.getName2());
            }
            if (!StringUtils.isEmpty(adminVO.getName3())) {
                mul.put("zh_TW", adminVO.getName3());
            }
            if (StringUtils.isEmpty(adminVO.getAncestorpath())) {
                adminEO.setAncestorpath("");
            }
            adminEO.setMultiLangName(mul);
            map.put(adminVO.getId(), adminEO);
        }
        BaseTreeService baseTreeService = new BaseTreeService();
        ServiceResult serviceResult = baseTreeService.listTreeNodes(adminVOS);
        if (!serviceResult.isSucceed()) {
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        List<AdminAO> adminAOS = (List<AdminAO>) serviceResult.getData();
        forEachEventData(stringBuilder, adminAOS, map);
        System.out.println(JSON.toJSONString(stringBuilder));
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/" + DateUtil.format(new Date(), "yyyyMMdd") + "_event_" + tenantid + "_hrorgadmin.sql");
        fileWriter.write(stringBuilder.toString());
    }

    private void forEachEventData(StringBuilder stringBuilder, List<AdminAO> adminAOS, Map<String, AdminEO> map) {
        for (AdminAO adminAO : adminAOS) {
            AdminEO adminEO = map.get(adminAO.getId());
            adminEO.setAncestorpath(adminAO.getAncestorpath());
            adminEO.setTs(new Date().getTime());
            //adminEO.setDepth(adminAO.getDepth()+1000);
            String source = adminAO.getOrgtype().equals(1) ? "synorg" : "syndept";
            Map<String, Object> orgEventObject = getEventObject(adminEO, adminAO.getTenantid());
            String date = DateUtil.format(new Date(), "yyyyMMdd");
            String insertSql = "INSERT INTO iuap_cloud_basedoc.eos_mqsend_success_" + date + " (id, txid, ptxid, gtxid, srcqueue, destqueue, content," + "createtime, updatetime, status, source, deleted, extone," + "exttwo, extthree, extfour, extfive)" +
                    "VALUES ('" + date + RandomUtil.randomNumbers(2) + IdUtil.randomUUID() + "','" + date + RandomUtil.randomNumbers(2) + IdUtil.randomUUID() + "', null," +
                    "'" + date + IdUtil.randomUUID() + "', null, null," +
                    "'" + JSON.toJSONString(orgEventObject, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue) + "'," +
                    "" + System.currentTimeMillis() + ", " + System.currentTimeMillis() + ", 'waitsend', " + "'" + source + "'" + ", 0, null, null, null, null, null);\n";
            stringBuilder.append(insertSql);
            List<AdminAO> adminAOChildrens = adminAO.getChildren();
            if (CollectionUtil.isNotEmpty(adminAOChildrens)) {
                forEachEventData(stringBuilder, adminAOChildrens, map);
            }
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-04-10 15:13
     */
    private EventObject getDeptEventObject(AdminAO adminAO, String tenantid) {
        Map<String, Object> userObject = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> extMap = new HashMap<>();
        extMap.put("oldModel", adminAO);
        dataMap.put("tenantId", tenantid);
        dataMap.put("deptId", adminAO.getId());
        dataMap.put("deptType", adminAO.getOrgtype());
        userObject.put("operator", "UPDATE_AFTER");
        userObject.put("doctype", "adminorg");
        userObject.put("option", "updateDept");
        userObject.put("data", dataMap);
        userObject.put("model", adminAO);
        if (MapUtil.isNotEmpty(extMap)) {
            Map<String, Object> extObj = new HashMap();
            Iterator iterator = extMap.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Object> map = (Map.Entry) iterator.next();
                extObj.put(map.getKey(), map.getValue());
            }

            userObject.put("ext", extObj);
        }
        EventObject eventObject = new EventObject();
        eventObject.setEventType("UPDATE_AFTER");
        eventObject.setSendTime(System.currentTimeMillis() + "");
        eventObject.setSourceID("BASE_ADMIN_EVENT");
        eventObject.setTenantCode(tenantid);
        eventObject.setUserObject(JSON.toJSONString(userObject));
        eventObject.setInvocationInfo(new HashMap<>());
        eventObject.setUuid(IdUtil.randomUUID());
        return eventObject;
    }

    private Map<String, Object> getEventObject(AdminEO adminEO, String tenantId) {
        Map<String, Object> userObject = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> extMap = new HashMap<>();
        extMap.put("oldModel", adminEO);
        dataMap.put("tenantId", tenantId);
        dataMap.put("deptId", adminEO.getId());
        dataMap.put("deptType", adminEO.getOrgtype());
        userObject.put("operator", "UPDATE_AFTER");
        userObject.put("doctype", "adminorg");
        userObject.put("option", "updateDept");
        userObject.put("data", dataMap);
        userObject.put("model", adminEO);
        if (MapUtil.isNotEmpty(extMap)) {
            Map<String, Object> extObj = new HashMap();
            Iterator iterator = extMap.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, Object> map = (Map.Entry) iterator.next();
                extObj.put(map.getKey(), map.getValue());
            }

            userObject.put("ext", extObj);
        }
        Map<String, Object> eventObject = new HashMap<>();
        eventObject.put("eventType", "UPDATE_AFTER");
        eventObject.put("invocationInfo", new HashMap<>());
        eventObject.put("sendTime", System.currentTimeMillis() + "");
        eventObject.put("userObject", userObject);
        eventObject.put("tenantCode", tenantId);
        eventObject.put("uuid", IdUtil.randomUUID());
        if (adminEO.getOrgtype().equals(1)) {
            eventObject.put("sourceID", "BASE_ORG_EVENT");
        } else {
            eventObject.put("sourceID", "BASE_ADMIN_EVENT");
        }
        return eventObject;
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return nowTimeStamp;
    }
}
