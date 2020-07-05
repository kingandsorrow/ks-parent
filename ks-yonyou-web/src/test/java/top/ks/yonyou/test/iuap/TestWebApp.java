package top.ks.yonyou.test.iuap;

import cn.hutool.core.lang.Func;
import com.alibaba.fastjson.JSON;
import com.yonyou.diwork.service.auth.IServiceIsolateService;
import com.yonyou.iuap.admin.entity.bo.AdminOrg;
import com.yonyou.iuap.bd.common.exception.BaseDocException;
import com.yonyou.iuap.bd.pub.param.ConditionVO;
import com.yonyou.iuap.bd.pub.param.Order;
import com.yonyou.iuap.context.InvocationInfoProxy;
import com.yonyou.iuap.data.entity.dto.FuncOrg;
import com.yonyou.iuap.data.entity.dto.OrgPermissionDTO;
import com.yonyou.iuap.data.service.itf.BizDelegateApi;
import com.yonyou.iuap.data.service.itf.FuncOrgDataQryService;
import com.yonyou.iuap.data.service.itf.TenantStatusApi;
import com.yonyou.iuap.enumeration.org.OrgFunc;
import com.yonyou.iuap.org.dto.TenantMultiOrgInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import top.ks.yonyou.bootstrap.KsYonyouWebApplication;

import java.util.List;
import java.util.concurrent.locks.Condition;

@RunWith(SpringRunner.class)
/*@ContextConfiguration
@SpringBootTest(classes = KsYonyouWebApplication.class)*/
public class TestWebApp {


    @Autowired
    private IServiceIsolateService serviceIsolateService;
    @Autowired
    private FuncOrgDataQryService funcOrgDataQryService;
    @Autowired
    private BizDelegateApi bizDelegateApi;
    @Autowired
    private TenantStatusApi tenantStatusApi;

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    public void testIris() {
        FuncOrg funcOrg = null;
        try {
            funcOrg = funcOrgDataQryService.getById("1603357683749120", "orq5s8og", "diwork", OrgFunc.ADMIN_ORG.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(funcOrg));
    }



    @Test
    public void testTemplateOnline() throws BaseDocException {
        String userId = "0b32d89f-3b0b-4008-9c4c-6145422af156";
        String serviceCode = "GZTTMP040";
        String token = "Hm_lvt_diwork=1589177669; YKJ_IS_DIWORK=1; YKJ_DIWORK_DATA=%7B%22data%22%3A%7B%22is_diwork%22%3A1%2C%22cur_qzid%22%3A%2218915%22%7D%2C%22key%22%3A%221df53490e4e8cbe1a9dcbc29e4943f7d%22%7D; ck_safe_chaoke_csrf_token=8bc0694d479e883c01bd7a1d0c5b0c4b; at=9f932b7b-ce04-4676-bc7e-5b5f234b329b; yonyou_uid=0b32d89f-3b0b-4008-9c4c-6145422af156; yonyou_uname=YS%25E6%25B5%258B%25E8%25AF%2595%2B1; wb_at=LMjqmqsqvjbIofvFZns4NirgMpxrmvjdtbkxnmxntbkntckbnl; PHPSESSID=8ee6ke304oqvgf5ivm0v3b0jd3; yht_access_token=bttSHJZRkc3U21nNXI1WE1acld4RVBDSTViN204VjhYS01mbGNrdGxlY1lqRUduM1VNVVhiTFZsb2UxRVpaemhVVU43YmgyOHRvVmNHTEZHaXhCcmFXajdNYURZL2I0clFIT3N4VSsyQlJ0WGFUelRKQTNZQ1gzYWtpZkZiei8yT2lfX2V1Yy55b255b3VjbG91ZC5jb20.__01b534d57fcd4c849d3900596f6a28a5_1589853500540; eudiqz=j8ah38g50b32d89f-3b0b-4008-9c4c-6145422af156+82526235; locale=en_US; acw_tc=1bdd784815898579162993303e1ccd2ead3440e477e66158ddfbe1073c; jwt_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODk4NTkwMDYsInNlc3Npb24iOiJ7XCJjbGllbnRJcFwiOlwiMTIzLjEwMy45LjhcIixcImNyZWF0ZURhdGVcIjoxNTg5ODUzNTAwLFwiZGF0YWZvcm1hdFwiOlwie1xcXCJkYXRlVGltZUZvcm1hdFxcXCI6XFxcInl5eXktTU0tZGQgSEg6bW06c3NcXFwiLFxcXCJudW1iZXJGb3JtYXRcXFwiOlxcXCIrIywjIyMsIyMjLCMjIywjIyMsIyMjWy5dIyMjIyMjIyNcXFwiLFxcXCJkYXRlRm9ybWF0XFxcIjpcXFwieXl5eS1NTS1kZFxcXCIsXFxcInRpbWVGb3JtYXRcXFwiOlxcXCJISDptbTpzc1xcXCJ9XCIsXCJleHRcIjp7XCJvcmdTdGF0dXNcIjpcIm11bHRpXCIsXCJ5aHRfdXNlcm5hbWVcIjpcIlNULTQwNDY0OS1jUDJnOU1hb3RCVWpzaFRxeTVuOS1ldWMueW9ueW91Y2xvdWQuY29tX18wYjMyZDg5Zi0zYjBiLTQwMDgtOWM0Yy02MTQ1NDIyYWYxNTZcIixcImFkbWluXCI6dHJ1ZSxcInlodF91c2VydG9rZW5cIjpcIms0bHVvakZPSk9CSEpoRDk1WUdXdGZqbmlIMlJqcUZnWktDdm1DQ2Z1R0xUaUFuWnpha2Vmbnl1NEp0RUZuRVlkVFQ4QWNuczhQQ0dlTFBzaDNtVVZBPT1cIixcInlodF9hY2Nlc3NfdG9rZW5cIjpcImJ0dFNISlpSa2MzVTIxbk5YSTFXRTFhY2xkNFJWQkRTVFZpTjIwNFZqaFlTMDFtYkdOcmRHeGxZMWxxUlVkdU0xVk5WVmhpVEZac2IyVXhSVnBhZW1oVlZVNDNZbWd5T0hSdlZtTkhURVpIYVhoQ2NtRlhhamROWVVSWkwySTBjbEZJVDNONFZTc3lRbEowV0dGVWVsUktRVE5aUTFnellXdHBaa1ppZWk4eVQybGZYMlYxWXk1NWIyNTViM1ZqYkc5MVpDNWpiMjAuX18wMWI1MzRkNTdmY2Q0Yzg0OWQzOTAwNTk2ZjZhMjhhNV8xNTg5ODUzNTAwNTQwXCJ9LFwiand0RXhwU2VjXCI6NjAsXCJqd3RWYWxpZERhdGVcIjoxNTg5ODU3OTc1LFwibGFzdERhdGVcIjoxNTg5ODU4OTQ2LFwibG9jYWxlXCI6XCJlbl9VU1wiLFwicHJvZHVjdExpbmVcIjpcImRpd29ya1wiLFwic2Vzc2lvbkV4cE1pblwiOjIxNjAsXCJzZXNzaW9uSWRcIjpcIkxNanFtcXNxdmpiSW9mdkZabnM0TmlyZ01weHJtdmpkdGJreG5teG50YmtudGNrYm5sXCIsXCJzb3VyY2VJZFwiOlwiZGl3b3JrXCIsXCJ0ZW5hbnRJZFwiOlwiajhhaDM4ZzVcIixcInRpbWV6b25lXCI6XCJVVEMrMTA6MDBcIixcInVzZXJJZFwiOlwiMGIzMmQ4OWYtM2IwYi00MDA4LTljNGMtNjE0NTQyMmFmMTU2XCJ9Iiwic3ViIjoiZGl3b3JrIn0.6EHvBCjzIOI1PSwT58hRzJfE4h6Oiwu_N76U3XXadps; ARK_STARTUP=eyJTVEFSVFVQIjp0cnVlLCJTVEFSVFVQVElNRSI6IjIwMjAtMDUtMTkgMTE6Mjk6MjMuMjA4In0%3D; ARK_ID=JS428704a402dbefbe686a2d5ccc839c574287; FZ_STROAGE.diwork.com=eyJTRUVTSU9OSUQiOiJmM2U4Y2VjYmUxZGM3YWY5IiwiU0VFU0lPTkRBVEUiOjE1ODk4NTg5NjMyMjEsIkFOU0FQUElEIjoiYWQ5YjkxMGYwNzEwOTUyZiIsIkFOUyRERUJVRyI6MiwiQU5TVVBMT0FEVVJMIjoiaHR0cHM6Ly9hcnQuZGl3b3JrLmNvbS8iLCJGUklTVERBWSI6IjIwMjAwNTE5IiwiRlJJU1RJTUUiOnRydWUsIkFSS19JRCI6IkpTNDI4NzA0YTQwMmRiZWZiZTY4NmEyZDVjY2M4MzljNTc0Mjg3IiwiQVJLRlJJU1RQUk9GSUxFIjoiMjAyMC0wNS0xOSAxMToyOToyMy4yMTciLCJBTlNTRVJWRVJUSU1FIjotMjk3fQ%3D%3D";
        String tenantId = "j8ah38g5";
        String sysId = "diwork";
        String funcType = OrgFunc.ADMIN_ORG.getCode();
        InvocationInfoProxy.setLocale("en_US");
        List<ConditionVO> conditionVOS = null;
        List<Order> orders = null;
        OrgPermissionDTO orgPermissionDTO = funcOrgDataQryService.listByConditionWithOrgPermissionUnionParentOrg(userId, serviceCode, token, tenantId, sysId, funcType, conditionVOS, orders);
        List<FuncOrg> funcOrgs = orgPermissionDTO.getParentOrgList();
        for (FuncOrg funcOrg : funcOrgs) {
            AdminOrg adminOrg = (AdminOrg) funcOrg;
            System.err.println(adminOrg.getLocaleName());
        }
        System.err.println(funcOrgs.size());
    }

    @Test
    public void testTemplateDaily() throws BaseDocException {
        String userId = "0dc05721-d2b7-4acf-b779-daf20f341589";
        String serviceCode = "GZTTMP040";
        String token = "PHPSESSID=76uvmig6fdb2smigcl9q2har24; ck_safe_chaoke_csrf_token=cf1ae806c4c40db80f523bede1417613; YKJ_IS_DIWORK=1; YKJ_DIWORK_DATA=%7B%22data%22%3A%7B%22is_diwork%22%3A1%2C%22cur_qzid%22%3A%2222100%22%7D%2C%22key%22%3A%226e23d8a932b40215b0c18a2b1cd73848%22%7D; at=e1a9de8a-7543-402a-aced-98cb55e7a2ed; yonyou_uid=0dc05721-d2b7-4acf-b779-daf20f341589; yonyou_uname=%25E7%2599%25BD%25E5%25BB%25BA%25E5%25AE%2587; wb_at=LMjnntj8JHZmn8A5MeMi7rNqExSjtubjtrdqjcZhkxkxxtZokbnl; yht_access_token=bttYUwrZndUS3pVM1NudWxVSGNlNFluSUFQRDFQOHBUc09uNVhyMFM1OTdPM0xIb0JtSnZBdVdSTU52aWVVc0t1a2RuaUtEV1RtdFdpdXB5Vkx2ekNBMjlmVFJVZ251YllkNmtnTURTY1crZE9MenFFTGZBTStvajlVWHJkbG1hdldfX3U4Yy1zc28tZGFpbHkueXl1YXAuY29t__5d00204ca490f36cd42ff4ec9168c734_1589854021042; ARK_STARTUP=eyJTVEFSVFVQIjp0cnVlLCJTVEFSVFVQVElNRSI6IjIwMjAtMDUtMTkgMTA6MTA6NTUuOTk4In0%3D; locale=en_US; jwt_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODk4NTg1MjMsInNlc3Npb24iOiJ7XCJjbGllbnRJcFwiOlwiMTAuMy4wLjc0XCIsXCJjcmVhdGVEYXRlXCI6MTU4OTg1NDI1MixcImRhdGFmb3JtYXRcIjpcIntcXFwiZGF0ZVRpbWVGb3JtYXRcXFwiOlxcXCJ5eXl5LU1NLWRkIEhIOm1tOnNzXFxcIixcXFwibnVtYmVyRm9ybWF0XFxcIjpcXFwiKyMsIyMjLCMjIywjIyMsIyMjLCMjI1suXSMjIyMjIyMjXFxcIixcXFwiZGF0ZUZvcm1hdFxcXCI6XFxcInl5eXktTU0tZGRcXFwiLFxcXCJ0aW1lRm9ybWF0XFxcIjpcXFwiSEg6bW06c3NcXFwifVwiLFwiZXh0XCI6e1wib3JnU3RhdHVzXCI6XCJtdWx0aVwiLFwieWh0X3VzZXJuYW1lXCI6XCJTVC0xMTctRlFPYW5vRkhDVGZUakVzVTRMeVotdThjLXVzZXItZGFpbHkueXl1YXAuY29tX18wZGMwNTcyMS1kMmI3LTRhY2YtYjc3OS1kYWYyMGYzNDE1ODlcIixcImFkbWluXCI6dHJ1ZSxcInlodF91c2VydG9rZW5cIjpcInc4dmFzS1k4WGVnNXAzb1h6ZUUvdkNpa2lIOEh2V2k0QnV3am10NndDY1hjY2ZWQStxbjd3VEtkcHZLM3ZkbW9nSCtLRncrN2RuU0RPZ3huR2VUVEt3PT1cIixcInlodF9hY2Nlc3NfdG9rZW5cIjpcImJ0dFlVd3JabmRVUzNwVk0xTnVkV3hWU0dObE5GbHVTVUZRUkRGUU9IQlVjMDl1TlZoeU1GTTFPVGRQTTB4SWIwSnRTblpCZFZkU1RVNTJhV1ZWYzB0MWEyUnVhVXRFVjFSdGRGZHBkWEI1Vmt4MmVrTkJNamxtVkZKVloyNTFZbGxrTm10blRVUlRZMWNyWkU5TWVuRkZUR1pCVFN0dmFqbFZXSEprYkcxaGRsZGZYM1U0WXkxemMyOHRaR0ZwYkhrdWVYbDFZWEF1WTI5dF9fNWQwMDIwNGNhNDkwZjM2Y2Q0MmZmNGVjOTE2OGM3MzRfMTU4OTg1NDI1Mjk5MVwifSxcImp3dEV4cFNlY1wiOjYwLFwiand0VmFsaWREYXRlXCI6MTU4OTg1NzkxNSxcImxhc3REYXRlXCI6MTU4OTg1ODQ2MyxcImxvY2FsZVwiOlwiZW5fVVNcIixcInByb2R1Y3RMaW5lXCI6XCJkaXdvcmtcIixcInNlc3Npb25FeHBNaW5cIjoyMTYwLFwic2Vzc2lvbklkXCI6XCJMTWpubnRqOEpIWm1uOEE1TWVNaTdyTnFFeFNqdHVianRyZHFqY1poa3hreHh0Wm9rYm5sXCIsXCJzb3VyY2VJZFwiOlwiZGl3b3JrXCIsXCJ0ZW5hbnRJZFwiOlwibDBmd3Nqc2ZcIixcInRpbWV6b25lXCI6XCJVVEMrMDg6MDBcIixcInVzZXJJZFwiOlwiMGRjMDU3MjEtZDJiNy00YWNmLWI3NzktZGFmMjBmMzQxNTg5XCJ9Iiwic3ViIjoiZGl3b3JrIn0.RemySh8gzZFi3qzp7wq-dKHqpIwqFKJP6v4tlnSvDeg; ARK_ID=JSfd7737073093c4e3135c247ffed33757fd77; FZ_STROAGE.yyuap.com=eyJTRUVTSU9OSUQiOiIwZDE1OTViMzMwMWM5OWYzIiwiU0VFU0lPTkRBVEUiOjE1ODk4NTg0NjUwNTIsIkFOU0FQUElEIjoiYWQ5YjkxMGYwNzEwOTUyZiIsIkFOUyRERUJVRyI6MiwiQU5TVVBMT0FEVVJMIjoiaHR0cHM6Ly9hcnQuZGl3b3JrLmNvbS8iLCJGUklTVERBWSI6IjIwMjAwNTE5IiwiRlJJU1RJTUUiOmZhbHNlLCJBUktfTE9HSU5JRCI6IjBkYzA1NzIxLWQyYjctNGFjZi1iNzc5LWRhZjIwZjM0MTU4OSIsIkFSS19JRCI6IkpTZmQ3NzM3MDczMDkzYzRlMzEzNWMyNDdmZmVkMzM3NTdmZDc3IiwiQVJLRlJJU1RQUk9GSUxFIjoiMjAyMC0wNS0xOSAxMDowNDo1Ny41MTEiLCJBTlNTRVJWRVJUSU1FIjotMjA5fQ%3D%3D";
        String tenantId = "l0fwsjsf";
        String sysId = "diwork";
        String funcType = OrgFunc.ADMIN_ORG.getCode();
        List<ConditionVO> conditionVOS = null;
        List<Order> orders = null;
        InvocationInfoProxy.setLocale("en_US");
        OrgPermissionDTO orgPermissionDTO = funcOrgDataQryService.listByConditionWithOrgPermissionUnionParentOrg(userId, serviceCode, token, tenantId, sysId, funcType, conditionVOS, orders);
        List<FuncOrg> funcOrgs = orgPermissionDTO.getParentOrgList();
        for (FuncOrg funcOrg : funcOrgs) {
            AdminOrg adminOrg = (AdminOrg) funcOrg;
            System.err.println(adminOrg.getLocaleName());
        }
        System.err.println(funcOrgs.size());
    }

}
