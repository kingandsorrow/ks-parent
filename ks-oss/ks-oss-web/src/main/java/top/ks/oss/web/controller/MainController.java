package top.ks.oss.web.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ks.oss.consumer.req.*;
import top.ks.oss.consumer.resp.*;
import top.ks.oss.web.util.HttpUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

//import com.google.code.kaptcha.Producer;

/**
 * <b>类名称:</b>MainController$<br/>
 * <b>类注释:</b><br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>brj<br/>
 * <b>修改人:</b>brj<br/>
 * <b>修改时间:</b>2018/8/1<br/>
 * <b>修改备注:</b><br/>
 *
 * <p>
 * Copyright KS 2018/8/1
 */
@RestController
public class MainController {
    private static final Log log = LogFactory.getLog(MainController.class);
    @Value("${project.id}")
    private String projectId;


    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        String code = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(code);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    public static void main(String[] args) {
        System.out.println("osskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskeyosskey".length());
    }

}
