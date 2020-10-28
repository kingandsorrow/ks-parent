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
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.Map;

@Slf4j
public class EmotionImgSpider implements PageProcessor {

    private String preFixImg = null;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public EmotionImgSpider(String preFixImg) {
        this.preFixImg = preFixImg;
    }

    public EmotionImgSpider() {

    }

    @Override
    public void process(Page page) {
        SpidersImageMapperImpl spidersImgMapper = new SpidersImageMapperImpl();
        Selectable selectable = page.getHtml().xpath("img[@class='ui image lazy']");
        //1、存储页面找到的url
        for (Selectable table : selectable.nodes()) {
            String imgUrl = table.$(".image", "data-original").toString();
            if (StringUtils.isEmpty(imgUrl)) {
                continue;
            }
            SpidersImage spidersImageDb = spidersImgMapper.selectBySourceUrl(imgUrl);
            if (spidersImageDb != null) {
                continue;
            }
            String imgtitle = table.$(".image", "title").toString();
            // /Users/birongjun/Downloads
            Map<String, String> map = DownloadUtils.generFilePath(this.preFixImg);
            long id = new SnowFlakeUtil(0, 1).nextId();
            String imgCompleteUrl = map.get(DownloadUtils.COMPLETE_PATH);
            String imgReleateUrl = map.get(DownloadUtils.RELATIVE_PATH);
            String imgLast = imgUrl.substring(imgUrl.lastIndexOf("."));
            DownloadUtils.dUrlImg(imgUrl, imgCompleteUrl + id + imgLast);
            SpidersImage spidersImage = new SpidersImage();
            spidersImage.setCreateTime(new Date());
            spidersImage.setFileUrl(imgReleateUrl + id + imgLast);
            spidersImage.setId(id);
            spidersImage.setName(imgtitle);
            spidersImage.setSourceUrl(imgUrl);
            spidersImage.setReadCount(0);
            spidersImage.setIpCount(0);
            spidersImage.setCreateTime(new Date());
            spidersImage.setUpdateTime(new Date());
            spidersImgMapper.insertSelective(spidersImage);
            try {
                ImgEsService imgEsService = new ImgEsServiceImpl();
                UpdateResponse updateResponse = imgEsService.upsertData(spidersImage);
                log.info(String.format("birjc EmotionImgSpider.process:: %s, %s", "updateResponse is.." + JSON.toJSONString(updateResponse), ""));
            } catch (Exception e) {
                log.error(String.format("birjc EmotionImgSpider.process:: %s, %s", "system error::" + e.getMessage(), e));
            }
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
