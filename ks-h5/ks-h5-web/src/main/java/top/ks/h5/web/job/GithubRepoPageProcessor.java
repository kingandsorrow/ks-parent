package top.ks.h5.web.job;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.update.UpdateResponse;
import top.ks.common.util.DownloadUtils;
import top.ks.common.util.SnowFlakeUtil;
import top.ks.h5.web.mapper.impl.SpidersImageMapperImpl;
import top.ks.h5.web.mapper.impl.SpidersUrlMapperImpl;
import top.ks.h5.web.model.SpidersImage;
import top.ks.h5.web.service.ImgEsService;
import top.ks.h5.web.service.ImgEsServiceImpl;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        SpidersUrlMapperImpl spidersUrlMapper = new SpidersUrlMapperImpl();
        SpidersImageMapperImpl spidersImgMapper = new SpidersImageMapperImpl();
        //List<String> urls = page.getHtml().xpath("img[@class='ui image lazy']").$(".image","data-original").all();
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
            Map<String, String> map = DownloadUtils.generFilePath("/Users/birongjun/Downloads");
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
                log.info(String.format("birjc GithubRepoPageProcessor.process:: %s, %s", "updateResponse is.." + JSON.toJSONString(updateResponse), ""));
            } catch (Exception e) {
                log.error(String.format("birjc GithubRepoPageProcessor.process:: %s, %s", "system error::" + e.getMessage(), e));
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        String url = "https://fabiaoqing.com/biaoqing/detail/id/655001.html";
        Spider.create(new GithubRepoPageProcessor()).addUrl(url).thread(5).run();
        /*Settings settings = Settings.builder().put("cluster.name", "esCluster").build();
        Client client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.20.48.13"), 30021));*/
        /*IndexResponse response = client.
                prepareIndex("spiders_image_es", "spiders_image", "478562288496934912").setSource(jsonBuilder()
                .startObject()
                .field("file_url", "/20201021/0002/07/478562288496934912.jpg")
                .field("source_url", "http://wx1.sinaimg.cn/bmiddle/ab4cb34agy1g9s0vel79fj20tz0thwn0.jpg")
                .field("name", "以前的我 VS 现在的我_斗图表情包")
                .field("name1", "")
                .field("read_count", 0)
                .field("ip_count", 0)
                .endObject())
                .execute()
                .actionGet();*/
       /* IndexRequest indexRequest = new IndexRequest("spiders_image_es", "spiders_image", "478562288496934912")
                .source(jsonBuilder()
                        .startObject()
                        .field("file_url", "/20201021/0002/07/478562288496934912.jpg")
                        .field("source_url", "http://wx1.sinaimg.cn/bmiddle/ab4cb34agy1g9s0vel79fj20tz0thwn0.jpg")
                        .field("name", "以前的我 VS 现在的我_斗图表情包")
                        .field("name1", "")
                        .field("read_count", 0)
                        .field("ip_count", 0)
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest("spiders_image_es", "spiders_image", "478562296545804288")
                .doc(jsonBuilder()
                        .startObject()
                        .field("file_url", "/20201021/0002/01/478562296545804288.gif")
                        .field("source_url", "http://wx3.sinaimg.cn/bmiddle/005J4OU5ly1gd31w1p5qgg309u09kqrd.gif")
                        .field("name", "萌娃罗熙哭唧唧表情包动图吵架生气必备表情包")
                        .field("name1", "")
                        .field("read_count", 0)
                        .field("ip_count", 0)
                        .endObject())
                .upsert(indexRequest);
        UpdateResponse updateResponse = client.update(updateRequest).get();*/

        // 删除索引
        /*DeleteIndexResponse dResponse = client.admin().indices().prepareDelete("spiders_image_es").execute().actionGet();
        System.out.println(dResponse);*/

    }
}
