package top.ks.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @描述:
 * @文件名: cn.brj.demo.util
 * @创建人: 毕荣君
 * @创建时间: 2017/4/13 / 21:32
 * @修改人:
 * @修改备注: Copyright brj 2017/4/13
 */
@Component
public class SpringHelper implements ApplicationContextAware, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(SpringHelper.class);

    private ApplicationContext applicationContext;

    private static SpringHelper instance;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.init();
    }

    private void init() {
        instance = this;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> String[] getBeanNames(Class<T> clazz) {
        try {
            return instance.applicationContext.getBeanNamesForType(clazz);
        } catch (BeansException var2) {
            logger.warn("Bean is not exist for class:{}", clazz);
            return null;
        }
    }

    public static <T> Map<String, T> getBeansForType(Class<T> clazz) {
        try {
            return instance.applicationContext.getBeansOfType(clazz);
        } catch (BeansException var2) {
            logger.warn("Bean is not exist for class:{}", clazz);
            return null;
        }
    }

    public static Object getBean(String beanName) {
        try {
            return null == instance ? null : instance.applicationContext.getBean(beanName);
        } catch (BeansException var2) {
            return null;
        }
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        try {
            return null == instance ? null : instance.applicationContext.getBean(beanName, clazz);
        } catch (BeansException var3) {
            return null;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        try {
            return null == instance ? null : instance.applicationContext.getBean(clazz);
        } catch (BeansException var2) {
            return null;
        }
    }
}
