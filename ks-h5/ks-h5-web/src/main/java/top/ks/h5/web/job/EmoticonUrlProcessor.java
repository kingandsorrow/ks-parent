package top.ks.h5.web.job;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import top.ks.h5.web.constanst.H5Const;
import top.ks.h5.web.mapper.SpidersUrlMapper;
import top.ks.h5.web.mapper.impl.SpidersUrlMapperImpl;
import top.ks.h5.web.model.SpidersUrl;
import top.ks.h5.web.spiders.EmotionUrlSpider;
import us.codecraft.webmagic.Spider;

@Component
public class EmoticonUrlProcessor {


    @XxlJob("emoticonUrlHandler")
    public ReturnT<String> emoticonHandler(String param) throws Exception {
        long startTime = System.currentTimeMillis();
        SpidersUrl spidersUrl = null;
        try {
            //1.查询待生成的url
            SpidersUrlMapper spidersUrlMapper = new SpidersUrlMapperImpl();
            spidersUrl = spidersUrlMapper.selectByStatus(0, null);
            XxlJobLogger.log(String.format("birjc EmoticonUrlProcessor.emoticonUrlHandler:: %s, %s", "spidersUrl is.." + JSON.toJSONString(spidersUrl), ""));
            if (spidersUrl == null || StringUtils.isEmpty(spidersUrl.getUrl())) {
                return ReturnT.SUCCESS;
            }
            //2.更新下这条数据为处理中
            spidersUrl.setUrlStatus(H5Const.STATUS_ONE);
            int row1 = spidersUrlMapper.updateByPrimaryKeySelective(spidersUrl);
            //3.开始爬虫链接
            Spider.create(new EmotionUrlSpider()).addUrl(spidersUrl.getUrl()).thread(5).run();
            //4.更新为已经处理完成
            spidersUrl.setUrlStatus(H5Const.STATUS_TWO);
            int row2 = spidersUrlMapper.updateByPrimaryKeySelective(spidersUrl);
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            XxlJobLogger.log(String.format("birjc EmoticonUrlProcessor.emoticonUrlHandler:: %s, %s", "system error::" + JSON.toJSONString(spidersUrl) + e.getMessage(), e));
            throw new Exception(e);
        } finally {
            long endTime = System.currentTimeMillis();
            XxlJobLogger.log(String.format("birjc EmoticonImgProcessor.emoticonUrlHandler:: %s", "spidersUrl is.." + JSON.toJSONString(spidersUrl) + " end ..spend time .." + (endTime - startTime)));
        }
    }

    public static void main(String[] args) throws Exception {
        EmoticonUrlProcessor emoticonUrlProcessor = new EmoticonUrlProcessor();
        emoticonUrlProcessor.emoticonHandler(null);
    }

}
