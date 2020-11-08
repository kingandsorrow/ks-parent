package top.ks.h5.web.job;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ks.h5.web.config.XxlJobConfig;
import top.ks.h5.web.constanst.H5Const;
import top.ks.h5.web.mapper.SpidersImageMapper;
import top.ks.h5.web.mapper.SpidersUrlMapper;
import top.ks.h5.web.mapper.impl.SpidersImageMapperImpl;
import top.ks.h5.web.mapper.impl.SpidersUrlMapperImpl;
import top.ks.h5.web.model.SpidersUrl;
import top.ks.h5.web.spiders.EmotionImgSpider;
import top.ks.h5.web.spiders.EmotionUrlSpider;
import us.codecraft.webmagic.Spider;

@Component
public class EmoticonImgProcessor {
    @Autowired
    private XxlJobConfig xxlJobConfig;

    @XxlJob("emoticonImgHandler")
    public ReturnT<String> emoticonHandler(String param) throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            //1.查询待生成的url
            SpidersUrlMapper spidersUrlMapper = new SpidersUrlMapperImpl();
            SpidersUrl spidersUrl = spidersUrlMapper.selectByStatus(null, 0);
            XxlJobLogger.log("emoticonImgHandler running", "spidersUrl.." + JSON.toJSONString(spidersUrl));
            if (spidersUrl == null || StringUtils.isEmpty(spidersUrl.getUrl())) {
                return ReturnT.SUCCESS;
            }
            //2.更新下这条数据为处理中
            spidersUrl.setImgStatus(H5Const.STATUS_ONE);
            int row1 = spidersUrlMapper.updateByPrimaryKeySelective(spidersUrl);
            //3.开始爬虫链接
            Spider.create(new EmotionImgSpider(xxlJobConfig.getPreFixImg())).addUrl(spidersUrl.getUrl()).thread(5).run();
            //4.更新为已经处理完成
            spidersUrl.setImgStatus(H5Const.STATUS_TWO);
            int row2 = spidersUrlMapper.updateByPrimaryKeySelective(spidersUrl);
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(String.format("birjc EmoticonImgProcessor.emoticonHandler:: %s, %s", "system error::" + e.getMessage(), e));
            throw new Exception(e);
        } finally {
            long endTime = System.currentTimeMillis();
            XxlJobLogger.log(String.format("birjc EmoticonImgProcessor.emoticonHandler:: %s", "end ..spend time .." + (endTime - startTime)));
        }
    }

    public static void main(String[] args) throws Exception {
        EmoticonImgProcessor emoticonImgProcessor = new EmoticonImgProcessor();
        emoticonImgProcessor.emoticonHandler(null);
    }

}
