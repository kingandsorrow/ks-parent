package top.ks.yonyou.test.iuap;

import cn.hutool.core.io.file.FileWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.yonyou.bootstrap.KsYonyouWebApplication;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsYonyouWebApplication.class)
public class TestDb {
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
            log.error(String.format("birjc RedisHandleController.repeatBp:: %s, %s", "system error::" + e.getMessage(), e));
        }
        return;
    }
}
