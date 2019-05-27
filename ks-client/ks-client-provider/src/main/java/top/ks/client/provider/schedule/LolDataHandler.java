package top.ks.client.provider.schedule;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.ks.client.api.FileUploadServiceI;
import top.ks.client.api.req.UploadImgReq;
import top.ks.client.api.resp.FilepathRes;
import top.ks.client.provider.database.mapper.LolHeroAbilityMapper;
import top.ks.client.provider.database.mapper.LolHeroMapper;
import top.ks.client.provider.database.model.LolHero;
import top.ks.client.provider.database.model.LolHeroAbility;
import top.ks.client.provider.database.service.LolHeroService;
import top.ks.common.util.LogFormat;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

import org.jsoup.nodes.Document;
import top.ks.common.util.SequenceHelper;
import top.ks.common.util.ToolUtil;

import javax.annotation.Resource;

@Component
public class LolDataHandler {
    @Resource
    private LolHeroMapper lolHeroMapper;
    @Resource
    private LolHeroAbilityMapper lolHeroAbilityMapper;
    @Resource
    private FileUploadServiceI fileUploadServiceI;
    @Resource
    private LolHeroService lolHeroService;

    public static final Log log = LogFactory.getLog(LolDataHandler.class);

    @Scheduled(cron = "*/5 * * * * *")
    private void handle() {
        log.info(LogFormat.formatMsg("LolDataHandler.handle", "current thread is.." + Thread.currentThread().getId() + " name is.." + Thread.currentThread().getName() + "current times .." + ToolUtil.getDateStr(new Date()), "11111111111111111111"));
        List<String> heroEnNames = getHeroEnNames();
        for (String heroEnName : heroEnNames) {
            try {
                LolHero lolHero = lolHeroMapper.selectByHeroEngName(heroEnName);
                if (lolHero != null) {
                    log.info(LogFormat.formatMsg("TestTreasureJob.processJob", "database hero is not null.." + heroEnName, ""));
                    continue;
                }
                log.info(LogFormat.formatMsg("TestTreasureJob.processJob", "current handle hero eng name is.." + heroEnName, ""));
                Map<String, Object> map = crawlData(heroEnName);

                int row1 = lolHeroService.insertHero((LolHero) map.get("LolHero"), (List<LolHeroAbility>) map.get("lolHeroAbilities"));
                log.info(LogFormat.formatMsg("TestTreasureJob.processJob", "row1::" + row1, ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getHeroEnNames() {
        String url = "http://lol.duowan.com/";
        List<String> strs = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element element = doc.select("ul[id=champion_list]").get(0);
            Elements elements = element.select("a");
            for (Element e : elements) {
                strs.add(e.attr("href").split("/")[3]);
            }
            System.out.println(JSON.toJSONString(strs));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strs;
    }

    //计算
    private Map<String, Object> crawlData(String heroEnName) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String url = "http://lol.duowan.com/" + heroEnName + "/";
        Document doc = Jsoup.connect(url).get();
        LolHero lolHero = new LolHero();
        lolHero.setHeroId(SequenceHelper.getNextSequence());
        lolHero.setHeroName(doc.select("h2[class=hero-title]").get(0).text());
        lolHero.setHeroShortName(doc.select("h1[class=hero-name]").get(0).text());
        lolHero.setHeroPosition("");
        lolHero.setHeroSmallImg(getHeroSmallImg(heroEnName, doc));
        lolHero.setHeroBigImg(getHeroBigImg(heroEnName, doc));
        lolHero.setHeroAttribute(getHeroAttribute(doc));
        lolHero.setViabilityValue(BigDecimal.valueOf(getViabilityValue(doc)));
        lolHero.setPhysicalAttack(BigDecimal.valueOf(getPhysicalAttack(doc)));
        lolHero.setMagicAttack(BigDecimal.valueOf(getMagicAttack(doc)));
        lolHero.setOperationDifficulty(BigDecimal.valueOf(getOperationDifficulty(doc)));
        lolHero.setHeroBackStory(getHeroBackStory(doc));
        lolHero.setHeroEngName(heroEnName);
        lolHero.setCreateTime(new Date());
        map.put("LolHero", lolHero);
        List<String> abilityImgs = getAbilityImgs(doc);
        List<Map<String, Object>> abilityInfos = getAbilityInfos(doc);
        List<LolHeroAbility> lolHeroAbilities = new ArrayList<LolHeroAbility>();
        if (abilityImgs.size() > 0 && abilityInfos.size() > 0 && abilityInfos.size() == abilityImgs.size()) {
            for (int i = 0; i < abilityImgs.size(); i++) {
                LolHeroAbility lolHeroAbility = new LolHeroAbility();
                lolHeroAbility.setAbilityId(SequenceHelper.getNextSequence());
                lolHeroAbility.setHeroId(lolHero.getHeroId());
                lolHeroAbility.setAbilityImg(fileUpload(heroEnName, abilityImgs.get(i)));
                lolHeroAbility.setAbilityName((String) abilityInfos.get(i).get("name"));
                lolHeroAbility.setAbilityHotKey((String) abilityInfos.get(i).get("hotKey"));
                lolHeroAbility.setAbilityDesc((String) abilityInfos.get(i).get("ability-desc"));
                lolHeroAbility.setCreateTime(new Date());
                lolHeroAbilities.add(lolHeroAbility);
            }
        } else {
            log.info(LogFormat.formatMsg("TestTreasureJob.crawlData", "heroEnName get faill.." + heroEnName, ""));
        }
        map.put("lolHeroAbilities", lolHeroAbilities);
        return map;
    }

    private String fileUpload(String heroEnName, String s) throws Exception {
        UploadImgReq uploadImgReq = new UploadImgReq();
        uploadImgReq.setImgUrl(s);
        uploadImgReq.setImgType(".jpg");
        uploadImgReq.setImgPrePath("/lol/" + heroEnName + "/");
        FilepathRes filepathRes = fileUploadServiceI.imgStrUrlStream(uploadImgReq);
        return (filepathRes == null || !filepathRes.respSuc()) ? "" : filepathRes.getFilePath();
    }

    private static String getHeroBackStory(Document doc) {
        Elements newsHeadlines = doc.select("p[class=hero-popup__txt]");
        String backStory = "";
        for (Element element : newsHeadlines) {
            backStory += element.text();
        }
        return backStory;
    }

    private static List<String> getAbilityImgs(Document doc) {
        List<String> strings = new ArrayList<>();
        Elements lines = doc.select("li[class=skill-item active]");
        if (lines != null && lines.size() > 0) {
            strings.add(lines.get(0).select("img").get(0).attr("src"));
        }
        Elements newsHeadlines = doc.select("li[class=skill-item]");
        for (Element e : newsHeadlines) {
            Elements elements = e.select("img");
            strings.add(elements.get(0).attr("src"));
        }
        return strings;
    }

    //查询
    private static List<Map<String, Object>> getAbilityInfos(Document doc) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>();
        Elements lines = doc.select("div[class=skill-content active]");
        if (lines != null && lines.size() > 0) {
            params.put("name", lines.get(0).select("span[class=name]").get(0).text());
            params.put("hotKey", lines.get(0).select("span[class=hot-key]").get(0).text());
            params.put("ability-desc", getAbilityDesc(lines.get(0)));
            list.add(params);
        }
        Elements elements = doc.select("div[class=skill-content]");
        for (Element element : elements) {
            Map<String, Object> p = new HashMap<String, Object>();
            p.put("name", element.select("span[class=name]").get(0).text());
            p.put("hotKey", element.select("span[class=hot-key]").get(0).text());
            p.put("ability-desc", getAbilityDesc(element));
            list.add(p);
        }
        System.out.println(JSON.toJSONString(list));
        return list;
    }

    private static String getAbilityDesc(Element element) {
        Elements elements = element.select("p[class=desc]");
        String desc1 = "";
        for (Element element1 : elements) {
            desc1 += element1.text() + "<br>";
        }
        Elements es = element.select("p[class=quality]");
        for (Element e : es) {
            desc1 += e.text() + "<br>";
        }
        return desc1;
    }

    private static Double getOperationDifficulty(Document doc) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        Elements newsHeadlines = doc.select("span[class=hero-attribute is-difficulty]");
        Number n1 = numberFormat.parse(newsHeadlines.get(0).select("i").attr("style").split(":")[1]);
        return n1.doubleValue();
    }

    private static Double getMagicAttack(Document doc) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        Elements newsHeadlines = doc.select("span[class=hero-attribute is-magic]");
        Number n1 = numberFormat.parse(newsHeadlines.get(0).select("i").attr("style").split(":")[1]);
        return n1.doubleValue();
    }

    private static Double getPhysicalAttack(Document doc) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        Elements newsHeadlines = doc.select("span[class=hero-attribute is-physical]");
        Number n1 = numberFormat.parse(newsHeadlines.get(0).select("i").attr("style").split(":")[1]);
        return n1.doubleValue();
    }

    private static double getViabilityValue(Document doc) throws Exception {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        Elements newsHeadlines = doc.select("span[class=hero-attribute is-life]");
        System.out.println();
        Number n1 = numberFormat.parse(newsHeadlines.get(0).select("i").attr("style").split(":")[1]);
        return n1.doubleValue();
    }

    private String getHeroBigImg(String heroEnName, Document doc) throws Exception {
        Elements newsHeadlines = doc.select("ul[class=ui-slide__content]");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.children().get(0).attr("src"));
        }
        String bigImg = "";
        Element element = newsHeadlines.get(0);
        Elements elements = element.select("img");
        for (Element e : elements) {
            UploadImgReq uploadImgReq = new UploadImgReq();
            uploadImgReq.setImgUrl(e.attr("src"));
            uploadImgReq.setImgType(".jpg");
            uploadImgReq.setImgPrePath("/lol/" + heroEnName + "/");
            FilepathRes filepathRes = fileUploadServiceI.imgStrUrlStream(uploadImgReq);
            if (filepathRes == null || !filepathRes.respSuc()) {
                bigImg += "";
            } else {
                bigImg += filepathRes.getFilePath() + ",";
            }
        }
        return bigImg;
    }

    private String getHeroSmallImg(String heroEnName, Document doc) throws Exception {
        Elements newsHeadlines = doc.select("img[class=hero-skin__img]");
        String heroSmallImgs = "";
        for (Element headline : newsHeadlines) {
            String imgUrl = headline.attr("src");
            UploadImgReq uploadImgReq = new UploadImgReq();
            uploadImgReq.setImgUrl(imgUrl);
            uploadImgReq.setImgType(".jpg");
            uploadImgReq.setImgPrePath("/lol/" + heroEnName + "/");
            FilepathRes filepathRes = fileUploadServiceI.imgStrUrlStream(uploadImgReq);
            if (filepathRes == null || !filepathRes.respSuc()) {
                log.info(LogFormat.formatMsg("TestTreasureJob.getHeroSmallImg", "filepathRes fail.." + JSON.toJSONString(filepathRes), ""));
                heroSmallImgs += "";
            } else {
                heroSmallImgs += filepathRes.getFilePath() + ",";
            }
        }
        return heroSmallImgs;
    }

    private String getHeroAttribute(Document doc) {
        String heroAttribute = "";
        Elements newsHeadlines = doc.select("span[class=hero-tag]");
        for (Element headline : newsHeadlines) {
            heroAttribute += headline.text();
        }
        return heroAttribute;
    }
}
