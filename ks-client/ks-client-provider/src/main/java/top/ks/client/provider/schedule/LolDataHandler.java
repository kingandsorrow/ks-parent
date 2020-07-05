package top.ks.client.provider.schedule;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
}
