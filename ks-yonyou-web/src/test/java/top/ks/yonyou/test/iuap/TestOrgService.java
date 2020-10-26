package top.ks.yonyou.test.iuap;

import com.alibaba.fastjson.JSON;
import com.yonyou.iuap.bd.common.exception.BaseDocException;
import com.yonyou.iuap.bd.pub.param.ConditionVO;
import com.yonyou.iuap.bd.pub.param.Operator;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.entity.dto.OrgAgg;
import com.yonyou.iuap.data.service.itf.FuncOrgDataQryService;
import com.yonyou.iuap.data.service.itf.OrgUnitDataQryService;
import com.yonyou.iuap.enumeration.org.OrgFunc;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import top.ks.yonyou.bootstrap.KsYonyouWebApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes = KsYonyouWebApplication.class)
public class TestOrgService {

    @Autowired
    private FuncOrgDataQryService funcOrgDataQryService;
    @Autowired
    private OrgUnitDataQryService orgUnitDataQryService;

    @Test
    public void test1() throws BaseDocException {
        FuncOrg funcOrg = funcOrgDataQryService.getById("1900923657998592", "czqne4bp", "diwork", OrgFunc.ADMIN_ORG.getCode(), new String[]{FuncOrgDataQryService.SOURCE_HR_SIGN});
        System.out.println(JSON.toJSON(funcOrg));
    }

    @Test
    public void test3() throws BaseDocException {
        OrgAgg funcOrg = orgUnitDataQryService.getById("1971037920121344", "tsh7gkxd", "diwork", true);
        System.out.println(JSON.toJSON(funcOrg));
    }

    @Test
    public void test2() throws BaseDocException {
        List<ConditionVO> conditionVOS = new ArrayList<>();
        conditionVOS.add(getCodeConditionVO());
        conditionVOS.add(getDeptConditionVO());
        List<FuncOrg> funcOrgs = funcOrgDataQryService.listByCondition(conditionVOS, null, "en3ova8g", "diwork", OrgFunc.ADMIN_ORG.getCode());
        System.out.println(funcOrgs);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 组织启用条件 0未启用 1启用 2停用 in 条件
     * @author : birjc
     * @CreateDate : 2020-05-29 11:08
     */
    private ConditionVO getCodeConditionVO() {
        ConditionVO enableVO = new ConditionVO();
        enableVO.setField("code");
        enableVO.setOperator(Operator.IN);
        List<ConditionVO> values = new ArrayList<>();
        ConditionVO zero = new ConditionVO();
        zero.setValue("03847");
        values.add(zero);
        ConditionVO one = new ConditionVO();
        one.setValue("8785555");
        values.add(one);
        enableVO.setConditionList(values);
        return enableVO;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description : 部门条件
     * @author : birjc
     * @CreateDate : 2020-05-29 11:08
     */
    private ConditionVO getDeptConditionVO() {
        ConditionVO enableVO = new ConditionVO();
        enableVO.setField("orgtype");
        enableVO.setOperator(Operator.EQUAL);
        enableVO.setValue("2");
        return enableVO;
    }
}
