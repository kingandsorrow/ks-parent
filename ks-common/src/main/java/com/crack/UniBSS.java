package com.crack;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * <b>类名称:</b>UniBSS$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/6<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 * <p>
 * Copyright 西安创意 2018/8/6
 */
public class UniBSS implements Serializable {
    @JSONField(name = "OrigDomain")
    private String origDomain;
    @JSONField(name = "HomeDomain")
    private String homeDomain;
    @JSONField(name = "BIPCode")
    private String bIPCode;
    @JSONField(name = "BIPVer")
    private String bIPVer;
    @JSONField(name = "ActivityCode")
    private String activityCode;
    @JSONField(name = "ActionCode")
    private String actionCode;
    @JSONField(name = "ActionRelation")
    private String actionRelation;
    @JSONField(name = "Routing")
    private Routing routing;
    @JSONField(name = "ProcID")
    private String procID;
    @JSONField(name = "TransIDO")
    private String transIDO;
    @JSONField(name = "ProcessTime")
    private String processTime;
    @JSONField(name = "TestFlag")
    private String testFlag;
    @JSONField(name = "MsgSender")
    private String msgSender;
    @JSONField(name = "MsgReceiver")
    private String msgReceiver;
    @JSONField(name = "SvcContVer")
    private String svcContVer;
    @JSONField(name = "SvcCont")
    private SvcCont svcCont;

    public String getOrigDomain() {
        return origDomain;
    }

    public void setOrigDomain(String origDomain) {
        this.origDomain = origDomain;
    }

    public String getHomeDomain() {
        return homeDomain;
    }

    public void setHomeDomain(String homeDomain) {
        this.homeDomain = homeDomain;
    }

    public String getbIPCode() {
        return bIPCode;
    }

    public void setbIPCode(String bIPCode) {
        this.bIPCode = bIPCode;
    }

    public String getbIPVer() {
        return bIPVer;
    }

    public void setbIPVer(String bIPVer) {
        this.bIPVer = bIPVer;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionRelation() {
        return actionRelation;
    }

    public void setActionRelation(String actionRelation) {
        this.actionRelation = actionRelation;
    }

    public Routing getRouting() {
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    public String getProcID() {
        return procID;
    }

    public void setProcID(String procID) {
        this.procID = procID;
    }

    public String getTransIDO() {
        return transIDO;
    }

    public void setTransIDO(String transIDO) {
        this.transIDO = transIDO;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(String testFlag) {
        this.testFlag = testFlag;
    }

    public String getMsgSender() {
        return msgSender;
    }

    public void setMsgSender(String msgSender) {
        this.msgSender = msgSender;
    }

    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver;
    }

    public String getSvcContVer() {
        return svcContVer;
    }

    public void setSvcContVer(String svcContVer) {
        this.svcContVer = svcContVer;
    }

    public SvcCont getSvcCont() {
        return svcCont;
    }

    public void setSvcCont(SvcCont svcCont) {
        this.svcCont = svcCont;
    }
}
