package top.ks.h5.web.spiders;

import org.apache.commons.lang3.StringUtils;
import top.ks.common.util.SnowFlakeUtil;
import top.ks.h5.web.constanst.H5Const;
import top.ks.h5.web.mapper.impl.SpidersUrlMapperImpl;
import top.ks.h5.web.model.SpidersUrl;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;

public class EmotionUrlSpider implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        SpidersUrlMapperImpl spidersUrlMapper = new SpidersUrlMapperImpl();
        //List<String> urls = page.getHtml().xpath("img[@class='ui image lazy']").$(".image","data-original").all();
        Selectable selectable = page.getHtml().xpath("img[@class='ui image lazy']");
        //1、存储页面找到的url
        List<String> urls = page.getHtml().links().all();
        /**/
        for (String url : urls) {
            if (StringUtils.isEmpty(url)) {
                continue;
            }
            SpidersUrl spidersUrlDb = spidersUrlMapper.selectByUrl(url);
            if (spidersUrlDb != null) {
                continue;
            }
            SpidersUrl spidersUrl = new SpidersUrl();
            spidersUrl.setId(new SnowFlakeUtil(0, 1).nextId());
            spidersUrl.setCreateTime(new Date());
            spidersUrl.setSourceUrl(page.getUrl().toString());
            spidersUrl.setUrl(url);
            spidersUrl.setUrlStatus(H5Const.STATUS_ZERO);
            spidersUrl.setImgStatus(H5Const.STATUS_ZERO);
            spidersUrlMapper.insertSelective(spidersUrl);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
