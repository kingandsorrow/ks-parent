package top.ks.cy.web.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.ks.common.util.LogFormat;
import top.ks.common.util.Strings;
import top.ks.cy.web.database.mapper.UserAwardMapper;
import top.ks.cy.web.database.mapper.UserJoinMapper;
import top.ks.cy.web.database.mapper.UserNotWonMapper;
import top.ks.cy.web.database.model.UserAward;
import top.ks.cy.web.database.model.UserJoin;
import top.ks.cy.web.database.model.UserNotWon;
import top.ks.cy.web.database.service.UserAwardService;
import top.ks.cy.web.util.Const;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * <b>类名称:</b>HandlerData$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/11/15<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0
 * Copyright KS 2018/11/15
 */
@Component
public class HandlerData {

    private static final Log log = LogFactory.getLog(HandlerData.class);

    @Value("${source.file}")
    private String sourceFilePath;
    @Value("${win.file}")
    private String winFilePath;
    @Value("${result.file}")
    private String resultFilePath;
    @Value("${h5.file}")
    private String h5FilePath;
    @Resource
    private UserAwardMapper userAwardMapper;
    @Resource
    private UserNotWonMapper userNotWonMapper;
    @Resource
    private UserAwardService userAwardService;
    @Value("${noWin.file}")
    private String noWinFilePath;
    @Value("${all.file}")
    private String allFilePath;
    @Resource
    private UserJoinMapper userJoinMapper;


    /**
     * @param :
     * @return :
     * @Method :
     * @Description :当天处理昨天发过来的中奖文件
     * @author : brj
     * @CreateDate : 2018/11/15 19:26
     */
    @Scheduled(cron = "${award.cron}")
    public void handleAward() throws Exception {
        //1.获取昨天的时间
        String yesterDay = DateUtil.format(DateUtil.offsetDay(new Date(), -1), "MMdd");
        String sourceFile = this.getSourceFilePath() + yesterDay + ".txt";
        log.info(LogFormat.formatMsg("HandlerData.handleAward", "get source file is::" + sourceFile, ""));
        //2.读取文件
        if (!FileUtil.exist(sourceFile)) {
            log.info(LogFormat.formatMsg("HandlerData.handleAward", "file is not exist.." + sourceFile, ""));
            return;
        }
        FileReader fileReader = new FileReader(sourceFile, Const.CHAR_SET);
        List<String> lineStrings = fileReader.readLines();
        if (CollUtil.isEmpty(lineStrings)) {
            log.info(LogFormat.formatMsg("HandlerData.handleAward", "read file is empty.." + sourceFile, ""));
            return;
        }
        //3.处理每一行数据
        for (String line : lineStrings) {
            String result = handleAwardLine(line);
            if (Strings.isEmpty(result)) {
                log.info(LogFormat.formatMsg("HandlerData.handleAward", "handle award line is null" + line, ""));
                continue;
            }
        }
        //4.查询今天全部生成的奖励数据生成文件
        List<UserAward> userAwards = userAwardMapper.selectListByTime(DateUtil.format(new Date(), "YYYY-MM-dd") + " 00:00:00", DateUtil.format(new Date(), "YYYY-MM-dd") + " 23:59:59");
        addAwardFile(yesterDay, this.getWinFilePath(), userAwards);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :处理结果文件
     * @author : brj
     * @CreateDate : 2018/11/16 15:54
     */
    @Scheduled(cron = "${result.cron}")
    public void handleResult() {
        //1.获取昨天的时间
        String beforeYesterDay = DateUtil.format(DateUtil.offsetDay(new Date(), -2), "MMdd");
        String resultFile = this.getResultFilePath() + beforeYesterDay + ".txt";
        log.info(LogFormat.formatMsg("HandlerData.handleResult", "get result file is::" + this.getResultFilePath(), ""));
        //2.读取文件
        if (!FileUtil.exist(resultFile)) {
            log.info(LogFormat.formatMsg("HandlerData.handleResult", "result file is not exist.." + this.getResultFilePath(), ""));
            return;
        }
        FileReader fileReader = new FileReader(resultFile, Const.CHAR_SET);
        List<String> lineStrings = fileReader.readLines();
        if (CollUtil.isEmpty(lineStrings)) {
            log.info(LogFormat.formatMsg("HandlerData.handleResult", "read result file is empty.." + this.getResultFilePath(), ""));
            return;
        }
        //3.处理每一行数据
        for (String line : lineStrings) {
            String result = handleResultLine(line);
            if (Strings.isEmpty(result)) {
                log.info(LogFormat.formatMsg("HandlerData.handleResult", "handle result line is null" + line, ""));
                continue;
            }
        }
        //4.查询今天全部生成的奖励数据生成文件
        List<UserNotWon> userNotWons = userNotWonMapper.selectListByGetTime(DateUtil.format(DateUtil.offsetDay(new Date(), -2), "YYYY-MM-dd") + " 00:00:00", DateUtil.format(DateUtil.offsetDay(new Date(), -2), "YYYY-MM-dd") + " 23:59:59");
        addResultFile(beforeYesterDay, userNotWons);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :处理结果文件
     * @author : brj
     * @CreateDate : 2018/11/16 15:53
     */
    @Scheduled(cron = "${all.cron}")
    public void handleAllReward() {
        //1.获取昨天的时间
        String nowDay = DateUtil.format(new Date(), "MMdd");
        //2.查询目前为止全部生成的奖励数据生成文件
        List<UserAward> allUserAwards = userAwardMapper.selectListByTime(null, null);
        addAwardFile(nowDay, this.getAllFilePath(), allUserAwards);
    }

    @Scheduled(cron = "${h5.cron}")
    public void handleH5Data() {
        log.info(LogFormat.formatMsg("HandlerData.handleH5Data", "h5 data is start...", ""));
        Date yesterDay = DateUtil.offsetDay(new Date(), -1);
        String yesDay = DateUtil.format(yesterDay, "MMdd");
        List<UserJoin> userJoins = userJoinMapper.selectListByTime(DateUtil.format(yesterDay, "YYYY-MM-dd") + " 00:00:00", DateUtil.format(yesterDay, "YYYY-MM-dd") + " 23:59:59");
        log.info(LogFormat.formatMsg("HandlerData.handleH5Data", "get h5 user joins .. is::" + userJoins.size(), ""));
        addH5File(yesDay, this.getH5FilePath(), userJoins);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :把h5 数据插入到文件
     * @author : brj
     * @CreateDate : 2018/11/20 5:52 PM
     */
    private void addH5File(String yesterDay, String h5FilePath, List<UserJoin> userJoins) {
        if (CollUtil.isEmpty(userJoins)) {
            log.info(LogFormat.formatMsg("HandlerData.addH5File", "userJoins is empty.." + yesterDay, ""));
            return;
        }
        // 中奖路径,存在删除再生成
        String h5File = h5FilePath + yesterDay + ".txt";
        if (FileUtil.exist(h5File)) {
            log.info(LogFormat.formatMsg("HandlerData.addH5File", "winPath is exist del it.." + h5File, ""));
            FileUtil.del(h5File);
        }
        FileUtil.appendString("user_id    phone_num   qq \r\n", h5File, Const.CHAR_SET);
        for (UserJoin userJoin : userJoins) {
            FileUtil.appendString(userJoin.getIptvNum() + " " + userJoin.getPhoneNum() + " " + userJoin.getQqNum() +" \r\n", h5File, Const.CHAR_SET);
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :往结果文件放数据
     * @author : brj
     * @CreateDate : 2018/11/16 15:52
     */
    private void addResultFile(String beforeYesterDay, List<UserNotWon> userNotWons) {
        if (CollUtil.isEmpty(userNotWons)) {
            log.info(LogFormat.formatMsg("HandlerData.addAwardFile", "userAwards is empty", ""));
            return;
        }
        // 中奖路径,存在删除再生成
        String noWinPath = this.getNoWinFilePath() + beforeYesterDay + ".txt";
        if (FileUtil.exist(noWinPath)) {
            log.info(LogFormat.formatMsg("HandlerData.addAwardFile", "winPath is exist del it..", ""));
            FileUtil.del(noWinPath);
        }
        FileUtil.appendString("user_id    phone_num   qq  grade   send_time   get_time    1_or_0 \r\n", noWinPath, Const.CHAR_SET);
        for (UserNotWon userNotWon : userNotWons) {
            FileUtil.appendString(userNotWon.getUserId() + " " + userNotWon.getPhoneNum() + " " + userNotWon.getQqNum() + " " + userNotWon.getGrade() + " " + userNotWon.getSendTime() + " " + DateUtil.format(userNotWon.getGetTime(), "YYYY-MM-dd") + " " + userNotWon.getStatus() + " \r\n", noWinPath, Const.CHAR_SET);
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :
     * @author : brj
     * @CreateDate : 2018/11/16 11:31
     */
    private String handleResultLine(String line) {
        if (Strings.isEmpty(line)) {
            log.info(LogFormat.formatMsg("HandlerData.handleResultLine", "result line is null..", ""));
            return null;
        }
        String[] lineArray = line.split("\\s+");
        UserNotWon userNotWon = initNotWon(lineArray);
        //如果发奖已经完成,不做操作
        if (Const.STATUS_ONE.equals(userNotWon.getStatus())) {
            log.info(LogFormat.formatMsg("HandlerData.handleAwardLine", "this user not rewarded..", ""));
            return null;
        }
        //如果不为数字，返回
        if (!NumberUtil.isNumber(userNotWon.getGrade() + "")) {
            log.info(LogFormat.formatMsg("HandlerData.handleAwardLine", "this line is head line..", ""));
            return null;
        }
        //如果发送失败，则从发送成功表中删掉，并往发送失败表中增加一条记录
        UserNotWon notWon = userNotWonMapper.selectByPrimaryKey(userNotWon.getUserId());
        int row = userAwardService.handleSendFail(userNotWon, notWon == null ? true : false);
        return JSON.toJSONString(userNotWon);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :初始化未中奖记录
     * @author : brj
     * @CreateDate : 2018/11/16 11:43
     */
    private UserNotWon initNotWon(String[] lineArray) {
        UserNotWon userNotWon = new UserNotWon();
        userNotWon.setUserId(lineArray[0]);
        userNotWon.setPhoneNum(lineArray[1]);
        userNotWon.setQqNum(lineArray[2]);
        userNotWon.setGrade(lineArray[3]);
        userNotWon.setSendTime(lineArray[4]);
        userNotWon.setGetTime(DateUtil.offsetDay(new Date(), -2));
        userNotWon.setStatus(Const.STATUS_ZERO);
        userNotWon.setCreateTime(new Date());
        return userNotWon;
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :往奖励文件放记录
     * @author : brj
     * @CreateDate : 2018/11/16 9:33
     */
    private void addAwardFile(String day, String path, List<UserAward> userAwards) {
        if (CollUtil.isEmpty(userAwards)) {
            log.info(LogFormat.formatMsg("HandlerData.addAwardFile", "userAwards is empty", ""));
            return;
        }
        // 中奖路径,存在删除再生成
        String filePath = path + day + ".txt";
        if (FileUtil.exist(filePath)) {
            log.info(LogFormat.formatMsg("HandlerData.addAwardFile", "winPath is exist del it..", ""));
            Boolean flag = FileUtil.del(filePath);
            log.info(LogFormat.formatMsg("HandlerData.addAwardFile", "del path result is::" + flag, ""));

        }
        FileUtil.appendString("user_id    phone_num   qq  grade   send_time   get_time    1_or_0 \r\n", filePath, Const.CHAR_SET);
        for (UserAward userAward : userAwards) {
            FileUtil.appendString(userAward.getUserId() + " " + userAward.getPhoneNum() + " " + userAward.getQqNum() + " " + userAward.getGrade() + " " + userAward.getSendTime() + " \r\n", filePath, Const.CHAR_SET);
        }
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :处理一行
     * @author : brj
     * @CreateDate : 2018/11/15 20:07
     */
    private String handleAwardLine(String line) {
        if (Strings.isEmpty(line)) {
            return null;
        }
        String[] lineArray = line.split("\\s+");
        UserAward userAward = initUserAward(lineArray);
        //查询未中奖表是否有数据
        UserNotWon userNotWon = userNotWonMapper.selectByPrimaryKey(userAward.getUserId());
        // 如果不为数字，返回
        if (!NumberUtil.isNumber(userAward.getGrade())) {
            log.info(LogFormat.formatMsg("HandlerData.handleAwardLine", "this line is not data..", ""));
            return null;
        }
        //如果没有中奖,往未中奖表放数据
        if (Const.NO_REWARD.equals(userAward.getGrade())) {
            log.info(LogFormat.formatMsg("HandlerData.handleAwardLine", "this user not rewarded..", ""));
            //如果没中奖往未中奖记录放
            if (userNotWon == null) {
                UserNotWon notWon = initNotWon(lineArray);
                notWon.setGetTime(DateUtil.offsetDay(new Date(), -1));
                userNotWonMapper.insertSelective(notWon);
            }
            return null;
        }
        userAward.setStatus(Const.Y_REWARD);
        //查询是否是已中奖用户
        UserAward award = userAwardMapper.selectByPrimaryKey(userAward.getUserId());
        if (award != null) {
            log.info(LogFormat.formatMsg("HandlerData.handleAwardLine", "this user has awarded..", ""));
            return null;
        }
        // 处理往中奖表放数据并且把未中奖表
        int row = userAwardService.handleAward(userAward, userNotWon);
        return JSON.toJSONString(userAward);
    }

    /**
     * @param :
     * @return :
     * @Method :
     * @Description :转换userAward
     * @author : brj
     * @CreateDate : 2018/11/15 20:20
     */
    private UserAward initUserAward(String[] lineArray) {
        UserAward userAward = new UserAward();
        userAward.setUserId(lineArray[0]);
        userAward.setCreateTime(new Date());
        userAward.setPhoneNum(lineArray[1]);
        userAward.setQqNum(lineArray[2]);
        userAward.setGrade(lineArray[3]);
        userAward.setSendTime(lineArray[4]);
        log.info(LogFormat.formatMsg("HandlerData.initUserAward", "initAward is::" + JSON.toJSONString(userAward), ""));
        return userAward;
    }


    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getWinFilePath() {
        return winFilePath;
    }

    public void setWinFilePath(String winFilePath) {
        this.winFilePath = winFilePath;
    }

    public String getResultFilePath() {
        return resultFilePath;
    }

    public void setResultFilePath(String resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    public String getNoWinFilePath() {
        return noWinFilePath;
    }

    public void setNoWinFilePath(String noWinFilePath) {
        this.noWinFilePath = noWinFilePath;
    }

    public String getAllFilePath() {
        return allFilePath;
    }

    public void setAllFilePath(String allFilePath) {
        this.allFilePath = allFilePath;
    }

    public String getH5FilePath() {
        return h5FilePath;
    }

    public void setH5FilePath(String h5FilePath) {
        this.h5FilePath = h5FilePath;
    }
}
