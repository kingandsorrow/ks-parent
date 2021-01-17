package top.ks.yonyou.db.test;

import cn.hutool.core.io.file.FileWriter;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.yonyou.db.bootstrap.KsYonyouDb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsYonyouDb.class)
public class TestDb {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Map<String, String> test = ImmutableMap.<String, String>builder()
            .put("cmdAdd", "新增")
            .put("cmdEnableAll", "全部启用")
            .put("cmdOrgCite", "全部启用")
            .build();

    @Test
    public void test1() {
        try {
            RowMapper<Columns> rowMapper =
                    new BeanPropertyRowMapper<Columns>(Columns.class);
            List<String> ids = this.jdbcTemplate.queryForList("select min(id) from org_bp_tenant_conf group by type_code,tenantid having count(id)>1", String.class);
            List<Columns> cs = this.jdbcTemplate.query("select type_code,tenantid from org_bp_tenant_conf group by type_code,tenantid having count(id)>1", rowMapper);
            List<String> setIds = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;
            for (Columns c : cs) {
                List<String> columnsList = this.jdbcTemplate.queryForList("select id from org_bp_tenant_conf where tenantid='" + c.getTenantid() + "' and type_code='" + c.getType_code() + "'", String.class);
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
    public void test2() {
        // 单据billnum
        String billNum = "org_structure_tree_list";
        // 当前单据的servicecode
        String parent_id = "zzjgt";
        String sub_id = "GZTORG";
        Integer sortNum = 20;
        RowMapper<CommandVo> rowMapper =
                new BeanPropertyRowMapper<CommandVo>(CommandVo.class);
        RowMapper<ToolbarItem> rowMapper1 =
                new BeanPropertyRowMapper<ToolbarItem>(ToolbarItem.class);
        List<CommandVo> cs = this.jdbcTemplate.query("select name,action,billnumber from bill_command where tenant_id='0' and  billnumber ='" + billNum + "'", rowMapper);
        List<ToolbarItem> items = this.jdbcTemplate.query("select command,text,text_resid from bill_toolbaritem where tenant_id='0' and  billnumber ='" + billNum + "'", rowMapper1);
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
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202101121454_birjc_"+billNum+"command.sql");
        fileWriter.write(updateSql.toString());
        FileWriter fileWriter1 = new FileWriter("/Users/birongjun/Downloads/202101121455_birjc_"+billNum+"auth.sql");
        fileWriter1.write(insertSql.toString());
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
