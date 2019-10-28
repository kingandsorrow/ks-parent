package top.ks.oss.web.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @描述:
 * @文件名: cn.brj.demo.util
 * @创建人: 毕荣君
 * @创建时间: 2017/4/13 / 21:32
 * @修改人:
 * @修改备注: Copyright brj 2017/4/13
 */
public class SpringHelper {
    private static Log logger = LogFactory.getLog(SpringHelper.class);
    private static ApplicationContext cx = null;

    public SpringHelper() {
    }

    public static <T> T getBean(String beanId) {
        if (cx == null) {
            cx = new ClassPathXmlApplicationContext("classpath*:spring/*.xml");
        }

        return (T) cx.getBean(beanId);
    }

    public static String[] getBeanDefinitionNames() {
        return cx == null ? null : cx.getBeanDefinitionNames();
    }

    public static boolean containsBean(String name) {
        return cx != null && cx.containsBean(name);
    }

    public static synchronized void init(ApplicationContext ctx) {
        if (cx == null) {
            cx = ctx;
            logger.info("**********Spring config success!,init from application ctx.**********");
        }

    }

    public static synchronized void init() {
        if (cx == null) {
            cx = new ClassPathXmlApplicationContext("classpath:spring/*.xml");
            logger.info("Spring config success!,ApplicationContext set a object");
        }

    }

    public static synchronized void init(String[] paths) {
        if (cx == null) {
            cx = new ClassPathXmlApplicationContext(paths);
            logger.info("Spring config success!,ApplicationContext set a object");
        }

    }

    public static synchronized void init(String path) {
        init(new String[]{path});
    }
}
