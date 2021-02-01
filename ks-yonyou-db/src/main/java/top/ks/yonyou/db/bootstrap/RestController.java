package top.ks.yonyou.db.bootstrap;

import cn.hutool.core.io.file.FileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    @Qualifier("jdbcTemplateOne")
    private JdbcTemplate jdbcTemplateOne;

    @Autowired
    @Qualifier("jdbcTemplateTwo")
    private JdbcTemplate jdbcTemplateTwo;

    @RequestMapping("/test")
    public String test() {
        return "test success";
    }
    @RequestMapping("/test01")
    public String test01() {
        RowMapper<Billitems> rowMapper =
                new BeanPropertyRowMapper<Billitems>(Billitems.class);
        String billNum = "org_center_card";
        List<Billitems> oneBillitems = this.jdbcTemplateOne.query("select name,text_resid  from bill_toolbaritem where tenant_id = '0' and  billnumber = '" + billNum + "' order by name;", rowMapper);
        List<Billitems> twoBillitems = this.jdbcTemplateTwo.query("select name,text_resid  from bill_toolbaritem where tenant_id = '0' and billnumber = '" + billNum + "' order by name;", rowMapper);
        StringBuilder stringBuilder = new StringBuilder();
        for (Billitems onebillitem : oneBillitems) {
            if (StringUtils.isEmpty(onebillitem.getText_resid())) {
                continue;
            }
            for (Billitems twoBillitem : twoBillitems) {
                if (onebillitem.getName().equalsIgnoreCase(twoBillitem.getName()) && (!onebillitem.getText_resid().equalsIgnoreCase(twoBillitem.getText_resid()) || StringUtils.isEmpty(twoBillitem.getText_resid()))) {
                    stringBuilder.append("update bill_toolbaritem set text_resid='" + onebillitem.getText_resid() + "' where name='" + twoBillitem.getName() + "' and billnumber = '" + billNum + "';\n");
                }
            }
        }
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202102011146_birjc_bill_toolbaritem_" + billNum + "_mul.sql");
        fileWriter.write(stringBuilder.toString());
        return "success";
    }

    @RequestMapping("/test02")
    public String test02() {
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
                    stringBuilder.append("update billtplgroup_base set cName_resid='" + onebillitem.getcName_resid() + "' where cCode='" + twoBillitem.getcCode() + "' and iBillId in (select id from bill_base where cBillNo = '" + billNum + "');\n");
                }
            }
        }
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202102011146_birjc_billtplgroup" + billNum + "_mul.sql");
        fileWriter.write(stringBuilder.toString());
        return "success";
    }

    @RequestMapping("/test03")
    public String test03() {
        RowMapper<Billitems> rowMapper =
                new BeanPropertyRowMapper<Billitems>(Billitems.class);
        String billNum = "org_unit_tree_list";
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
        FileWriter fileWriter = new FileWriter("/Users/birongjun/Downloads/202102011146_birjc_billitembase" + billNum + "_mul.sql");
        fileWriter.write(stringBuilder.toString());
        return "success";
    }
}
