package top.ks.client.provider.schedule;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.util.RegexRule;
import org.jsoup.nodes.Element;

public class SaoDongCrawler extends BreadthCrawler {

    private final static String crawlPath = "www.famulei.com/data/ranking";

    private final static String seed = "https://www.famulei.com/data/ranking";

    RegexRule regexRule = new RegexRule();

    public SaoDongCrawler() {
        super(crawlPath, false);

        //添加爬取种子,也就是需要爬取的网站地址,以及爬取深度
        CrawlDatum datum = new CrawlDatum(seed)
                .meta("depth", "1");
        addSeed(datum);

        //设置线程数,根据自己的需求来搞
        setThreads(2);

        //添加正则表达式规则
        regexRule.addRule("http://.*");
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        Element nameElement = page.select("table[class=sTable]").first();
        String name = nameElement.text();
        System.out.println(name);
    }

    public static void main(String[] args) throws Exception {

        //测试
        SaoDongCrawler crawler = new SaoDongCrawler();
        crawler.start(2);
    }
}
