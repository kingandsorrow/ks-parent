package top.ks.client.provider.schedule;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import cn.hutool.http.HttpConnection;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.http.client.HttpClient;
import org.apache.juli.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LolRankHandler {

    public static void main(String[] args) throws Exception {
        String url = "https://www.op.gg/ranking/ladder/";
        Document doc = Jsoup.connect(url).get();
        getRankInfos(doc);
    }

    private static List<String> getRankInfos(Document doc) {
        Elements elements = doc.select("li[class=ranking-highest__item]");
        List<String> strs = new ArrayList<String>();
        for (Element element : elements) {
            Elements eles = element.select("a[ranking-highest__name]");
            for (Element ele : eles) {
                /*Elements es = ele.select("td[class=rank]");
                strs.add(es.get(0).select("span").get(0).text());*/
                System.out.println(ele.text());
                strs.add(ele.text());
            }
        }
        System.out.println(JSON.toJSONString(strs));
        return strs;
    }
}
