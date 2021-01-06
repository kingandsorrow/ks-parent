package top.ks.h5.web.spiders;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.update.UpdateResponse;
import top.ks.common.util.DownloadUtils;
import top.ks.common.util.SnowFlakeUtil;
import top.ks.h5.web.mapper.impl.SpidersImageMapperImpl;
import top.ks.h5.web.model.SpidersImage;
import top.ks.h5.web.service.ImgEsService;
import top.ks.h5.web.service.ImgEsServiceImpl;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.Map;

@Slf4j
public class SportsSpider implements PageProcessor {


    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);


    public SportsSpider() {

    }

    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().xpath("div[@class='list flex flex-align-center']");
        System.out.println(selectable);
        //1、存储页面找到的url


    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {

        Spider.create(new SportsSpider()).addUrl("https://data.zhibo8.cc/pc_main_data/#/%E8%8B%B1%E8%B6%85").thread(5).run();
    }
}
