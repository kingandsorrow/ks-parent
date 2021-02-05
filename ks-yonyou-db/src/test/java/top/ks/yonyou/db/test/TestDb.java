package top.ks.yonyou.db.test;

import cn.hutool.core.io.file.FileWriter;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;
import top.ks.yonyou.db.bootstrap.KsYonyouDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsYonyouDb.class)
public class TestDb {

    @Autowired
    @Qualifier("jdbcTemplateOne")
    private JdbcTemplate jdbcTemplateOne;

    @Autowired
    @Qualifier("jdbcTemplateTwo")
    private JdbcTemplate jdbcTemplateTwo;

    Map<String, String> test = ImmutableMap.<String, String>builder()
            .put("cmdAdd", "新增")
            .put("cmdEnableAll", "全部启用")
            .put("cmdOrgCite", "全部启用")
            .build();

    @Test
    public void test0() {
        RowMapper<Billitems> rowMapper =
                new BeanPropertyRowMapper<Billitems>(Billitems.class);
        String billNum = "org_center_card";
        List<Billitems> oneBillitems = this.jdbcTemplateOne.query("select cName,cCaption_resid from billitem_base where tenant_id = '0' and iBillId in (select id from bill_base where cBillNo = '" + billNum + "' order by cName);", rowMapper);
        List<Billitems> twoBillitems = this.jdbcTemplateTwo.query("select cName,cCaption_resid from billitem_base where tenant_id = '0' and iBillId in (select id from bill_base where cBillNo = '" + billNum + "' order by cName);", rowMapper);
        StringBuilder stringBuilder = new StringBuilder();
        for (Billitems onebillitem : oneBillitems) {
            if (StringUtils.isEmpty(onebillitem.getcCaption_resid())) {
                continue;
            }
            for (Billitems twoBillitem : twoBillitems) {
                if (onebillitem.getcName().equalsIgnoreCase(twoBillitem.getcName()) && (!onebillitem.getcCaption_resid().equalsIgnoreCase(twoBillitem.getcCaption_resid()) || StringUtils.isEmpty(twoBillitem.getcCaption_resid()))) {
                    stringBuilder.append("update billitem_base set cShowCaption_resid='" + onebillitem.getcCaption_resid() + "',cCaption_resid='" + onebillitem.getcCaption_resid() + "' where cName='" + twoBillitem.getcName() + "' and iBillId in (select id from bill_base where cBillNo = '" + billNum + "');\n");
                }
            }
        }
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202102011145_birjc_billitembase" + billNum + "_mul.sql");
        fileWriter.write(stringBuilder.toString());
    }

    @Test
    public void test01() {
        RowMapper<Billitems> rowMapper =
                new BeanPropertyRowMapper<Billitems>(Billitems.class);
        String billNum = "org_center_card";
        List<Billitems> oneBillitems = this.jdbcTemplateOne.query("select cCode,cName_resid  from billtplgroup_base where tenant_id = '0' and iBillId in (select id from bill_base where cBillNo = '" + billNum + "' order by cCode);", rowMapper);
        List<Billitems> twoBillitems = this.jdbcTemplateTwo.query("select cCode,cName_resid  from billtplgroup_base where tenant_id = '0' and iBillId in (select id from bill_base where cBillNo = '" + billNum + "' order by cCode);", rowMapper);
        StringBuilder stringBuilder = new StringBuilder();
        for (Billitems onebillitem : oneBillitems) {
            if (StringUtils.isEmpty(onebillitem.getcName_resid())) {
                continue;
            }
            for (Billitems twoBillitem : twoBillitems) {
                if (onebillitem.getcCode().equalsIgnoreCase(twoBillitem.getcCode()) && (!onebillitem.getcName_resid().equalsIgnoreCase(twoBillitem.getcName_resid()) || StringUtils.isEmpty(twoBillitem.getcName_resid()))) {
                    stringBuilder.append("update billtplgroup_base set cName_resid='" + onebillitem.getcCaption_resid() + " where cCode='" + twoBillitem.getcCode() + "' and iBillId in (select id from bill_base where cBillNo = '" + billNum + "');\n");
                }
            }
        }
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202102011145_birjc_billtplgroup" + billNum + "_mul.sql");
        fileWriter.write(stringBuilder.toString());
    }

    @Test
    public void test02() {
        RowMapper<Billitems> rowMapper =
                new BeanPropertyRowMapper<Billitems>(Billitems.class);
        String billNum = "org_center_card";
        List<Billitems> oneBillitems = this.jdbcTemplateOne.query("select name,text_resid  from bill_toolbaritem where tenant_id = '0' and toolbar ='org_admin_toolbar' and  billnumber = '" + billNum + "' order by name;", rowMapper);
        List<Billitems> twoBillitems = this.jdbcTemplateTwo.query("select name,text_resid  from bill_toolbaritem where tenant_id = '0' and toolbar ='org_admin_toolbar'  and billnumber = '" + billNum + "' order by name;", rowMapper);
        StringBuilder stringBuilder = new StringBuilder();
        for (Billitems onebillitem : oneBillitems) {
            if (StringUtils.isEmpty(onebillitem.getText_resid())) {
                continue;
            }
            for (Billitems twoBillitem : twoBillitems) {
                if (onebillitem.getName().equalsIgnoreCase(twoBillitem.getName()) && (!onebillitem.getText_resid().equalsIgnoreCase(twoBillitem.getText_resid()) || StringUtils.isEmpty(twoBillitem.getText_resid()))) {
                    stringBuilder.append("update bill_toolbaritem set text_resid='" + onebillitem.getText_resid() + " where name='" + twoBillitem.getName() + "' and billnumber = '" + billNum + "';\n");
                }
            }
        }
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202102011145_birjc_bill_toolbaritem_" + billNum + "_mul.sql");
        fileWriter.write(stringBuilder.toString());
    }

    @Test
    public void test1() {
        try {
            RowMapper<Columns> rowMapper =
                    new BeanPropertyRowMapper<Columns>(Columns.class);
            List<String> ids = this.jdbcTemplateOne.queryForList("select min(id) from org_bp_tenant_conf group by type_code,tenantid having count(id)>1", String.class);
            List<Columns> cs = this.jdbcTemplateOne.query("select type_code,tenantid from org_bp_tenant_conf group by type_code,tenantid having count(id)>1", rowMapper);
            List<String> setIds = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;
            for (Columns c : cs) {
                List<String> columnsList = this.jdbcTemplateOne.queryForList("select id from org_bp_tenant_conf where tenantid='" + c.getTenantid() + "' and type_code='" + c.getType_code() + "'", String.class);
                count += columnsList.size();
                for (String cl : columnsList) {
                    if (!ids.contains(cl)) {
                        setIds.add(cl);
                    }

                }
            }
            System.out.println(count);
            for (String remId : setIds) {
                stringBuilder.append("delete from org_bp_tenant_conf where id ='" + remId + "';\n");
            }

            FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/delete1.sql");
            fileWriter.write(stringBuilder.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return;
    }


    @Test
    public void testrepeat() {
        try {
            String dbTable = "bd_grade";
            String uniqueKey = "code";
            String tenantId = " where tenantid='ru0dsil8'";
            RowMapper<CommonVO> rowMapper =
                    new BeanPropertyRowMapper<CommonVO>(CommonVO.class);
            List<String> ids = this.jdbcTemplateOne.queryForList("select min(id) from " + dbTable + tenantId + "  group by " + uniqueKey + ",tenantid having count(id)>1", String.class);
            List<CommonVO> cs = this.jdbcTemplateOne.query("select " + uniqueKey + ",tenantid from " + dbTable + tenantId + " group by " + uniqueKey + ",tenantid having count(id)>1", rowMapper);
            List<String> setIds = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;
            for (CommonVO c : cs) {
                List<String> columnsList = this.jdbcTemplateOne.queryForList("select id from " + dbTable + " where tenantid='" + c.getTenantid() + "' and " + uniqueKey + "='" + c.getCode() + "'", String.class);
                count += columnsList.size();
                for (String cl : columnsList) {
                    if (!ids.contains(cl)) {
                        setIds.add(cl);
                    }

                }
            }
            System.out.println(count);
            for (String remId : setIds) {
                stringBuilder.append("delete from " + dbTable + " where id ='" + remId + "';\n");
            }

            FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/delete" + dbTable + ".sql");
            fileWriter.write(stringBuilder.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return;
    }

    @Test
    public void test2() {
        // 单据billnum
        String billNum = "org_registergrouplist";
        // 当前单据的servicecode
        String parent_id = "znzc";
        String sub_id = "GZTORG";
        Integer sortNum = 20;
        RowMapper<CommandVo> rowMapper =
                new BeanPropertyRowMapper<CommandVo>(CommandVo.class);
        RowMapper<ToolbarItem> rowMapper1 =
                new BeanPropertyRowMapper<ToolbarItem>(ToolbarItem.class);
        List<CommandVo> cs = this.jdbcTemplateOne.query("select name,action,billnumber from bill_command where tenant_id='0' and  billnumber ='" + billNum + "'", rowMapper);
        List<ToolbarItem> items = this.jdbcTemplateOne.query("select command,text,text_resid from bill_toolbaritem where tenant_id='0' and  billnumber ='" + billNum + "'", rowMapper1);
        StringBuilder updateSql = new StringBuilder();
        StringBuilder insertSql = new StringBuilder();
        for (CommandVo commandVo : cs) {
            String authid = billNum + (commandVo.getName().replace("cmd", ""));
            String sqlU = " update bill_command set authid ='" + authid + "' where name='" + commandVo.getName() + "' and action='" + commandVo.getAction() + "' and billnumber='" + commandVo.getBillnumber() + "';\n";
            updateSql.append(sqlU);
            String iName = checkName(commandVo.getName(), items);
            String iResid = checkResid(commandVo.getName(), items);
            sortNum += 10;
            String isEnd = (iName.equals("启用") || iName.equals("停用")) ? "true" : "false";
            String delSql = "delete from auth where code ='" + authid + "' and parent_id='" + parent_id + "' ; \n";
            String SqlI = "INSERT INTO auth (code, name, parent_id, level, path, sort_num, isEnd, pubts, auth_level, note, alias_code, subId, name_resid, resid) VALUES ('" + authid + "', '" + iName + "', '" + parent_id + "', 3, null, " + sortNum + ", " + isEnd + ", now(), 1, null, null, '" + sub_id + "', '" + iResid + "', null);\n";
            insertSql.append(delSql);
            insertSql.append(SqlI);
        }
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202101121454_birjc_" + billNum + "command.sql");
        fileWriter.write(updateSql.toString());
        FileWriter fileWriter1 = new FileWriter("/Users/birongjun/Downloads/202101121455_birjc_" + billNum + "auth.sql");
        fileWriter1.write(insertSql.toString());
    }

    @Test
    public void test3() {
        // 单据billnum
        String billNum = "org_func_sharing_setting_list";
        RowMapper<CommandVo> rowMapper =
                new BeanPropertyRowMapper<CommandVo>(CommandVo.class);
        RowMapper<ToolbarItem> rowMapper1 =
                new BeanPropertyRowMapper<ToolbarItem>(ToolbarItem.class);
        List<CommandVo> cs = this.jdbcTemplateOne.query("select name,action,billnumber,authid from bill_command where tenant_id='0' and  billnumber ='" + billNum + "'", rowMapper);
        List<ToolbarItem> items = this.jdbcTemplateOne.query("select command,text,text_resid,name,billnumber from bill_toolbaritem where tenant_id='0' and  billnumber ='" + billNum + "'", rowMapper1);
        StringBuilder updateSql = new StringBuilder();
        for (ToolbarItem toolbarItem : items) {
            String authId = getItemAuthId(toolbarItem, cs);
            if (StringUtils.isEmpty(authId)) {
                continue;
            }
            String sqlU = " update bill_toolbaritem set authid ='" + authId + "' where name='" + toolbarItem.getName() + "' and billnumber='" + toolbarItem.getBillnumber() + "';\n";
            updateSql.append(sqlU);
        }
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202101181454_birjc_" + billNum + "toolbaritem.sql");
        fileWriter.write(updateSql.toString());
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-01-18 11:41
     */
    private String getItemAuthId(ToolbarItem toolbarItem, List<CommandVo> cs) {
        for (CommandVo commandVo : cs) {
            if (commandVo.getName().equalsIgnoreCase(toolbarItem.getCommand())) {
                return commandVo.getAuthid();
            }
        }
        return "";
    }


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-01-11 14:42
     */
    private String checkResid(String name, List<ToolbarItem> items) {
        for (ToolbarItem toolbarItem : items) {
            if (name.equalsIgnoreCase(toolbarItem.getCommand())) {
                return toolbarItem.getText_resid();
            }
        }
        return "";
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : birjc
     * @CreateDate : 2021-01-11 14:40
     */
    private String checkName(String name, List<ToolbarItem> items) {
        for (ToolbarItem toolbarItem : items) {
            if (name.equalsIgnoreCase(toolbarItem.getCommand())) {
                return toolbarItem.getText();
            }
        }
        return "";
    }
}
