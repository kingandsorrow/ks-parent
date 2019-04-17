package top.ks.cy.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ks.common.basic.StatusCodeConst;
import top.ks.cy.web.database.mapper.UserJoinMapper;
import top.ks.cy.web.database.model.UserJoin;
import top.ks.framework.base.entity.ResponseEntity;
import top.ks.framework.util.LogFormat;
import top.ks.framework.util.SequenceHelper;
import top.ks.framework.util.Strings;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <b>类名称:</b>MainController$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/1<br/>
 * <b>修改备注:</b><br/>
 *
 * <p>
 * Copyright 西安创意 2018/8/1
 */
@RestController
public class MainController {
    private static final Log log = LogFactory.getLog(MainController.class);

    @Resource
    private UserJoinMapper joinMapper;
    @Value("${phone.prefixs}")
    private String phonePrefixs;

    @RequestMapping("/doSubmit")
    public ResponseEntity doSubmit(@RequestParam("phoneNum") String phoneNum, @RequestParam("iptvNum") String iptvNum, @RequestParam("qqNum") String qqNum) {
        if (Strings.hasEmptyStr(phoneNum, iptvNum, qqNum)) {
            log.info(LogFormat.formatMsg("MainController.doSubmit", "params has null.." + phoneNum + "--" + iptvNum + "--" + qqNum, ""));
            return new ResponseEntity(StatusCodeConst.PARAMS_NULL, "请完善信息");
        }
        if (!isBJLTPhone(phoneNum)) {
            log.info(LogFormat.formatMsg("MainController.doSubmit", "this phoneNum is not bjlt ::" + phoneNum, ""));
            return new ResponseEntity(StatusCodeConst.PHONE_ERROR, "请输入北京联通手机号");
        }
        UserJoin userJoin = new UserJoin();
        userJoin.setPhoneNum(phoneNum);
        userJoin.setIptvNum(iptvNum);
        userJoin.setQqNum(qqNum);
        userJoin.setJoinId(SequenceHelper.getNextSequence());
        userJoin.setCreateTime(new Date());
        userJoin.setUpdateTime(new Date());
        userJoin.setQqNum(qqNum);
        ResponseEntity entity = new ResponseEntity();
        if (joinMapper.insertSelective(userJoin) < 1) {
            entity.setErrCode(StatusCodeConst.SYSTEM_ERROR);
            entity.setErrMsg("提交失败");
            return entity;
        }
        entity.setErrCode(StatusCodeConst.SUCCESS);
        entity.setErrMsg("提交成功");
        return entity;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :校验是否是北京联通手机号
     * @author : brj
     * @CreateDate : 2018/11/20 5:26 PM
     */
    private boolean isBJLTPhone(String phoneNum) {
        String[] phoneArr = this.getPhonePrefixs().split(",");
        log.info(LogFormat.formatMsg("MainController.isBJLTPhone", "this.phone num is::" + phoneNum, ""));
        return Arrays.asList(phoneArr).contains(phoneNum.substring(0, 7));
    }

    public String getPhonePrefixs() {
        return phonePrefixs;
    }

    public void setPhonePrefixs(String phonePrefixs) {
        this.phonePrefixs = phonePrefixs;
    }
}
