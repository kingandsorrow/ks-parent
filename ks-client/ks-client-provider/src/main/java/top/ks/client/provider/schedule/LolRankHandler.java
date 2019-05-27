package top.ks.client.provider.schedule;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.juli.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LolRankHandler {

    public static void main(String[] args) throws Exception {
        String url = "https://www.famulei.com/data/ranking";
        Document doc = Jsoup.connect(url).get();
        /**HtmlUnit请求web页面*/
        WebClient wc = new WebClient();
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false); //禁用css支持
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        wc.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待
        HtmlPage page = wc.getPage(url);
        String pageXml = page.asXml(); //以xml的形式获取响应文本

        /**jsoup解析文档*/
        Element pv = doc.select("#feed_content span").get(1);
        System.out.println(pv.text());
        Assert.assertTrue(pv.text().contains("浏览"));

        System.out.println("Thank God!");
    }

    private static List<String> getRankInfos(Document doc) {
        Elements elements = doc.select("table[class=sTable]");
        List<String> strs = new ArrayList<String>();
        for (Element element : elements) {
            Elements eles = element.select("tr");
            for (Element ele : eles) {
                Elements es = ele.select("td[class=rank]");
                strs.add(es.get(0).select("span").get(0).text());
            }
        }
        System.out.println(JSON.toJSONString(strs));
        return strs;
    }
}
