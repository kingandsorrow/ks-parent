package top.ks.common;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ks.common.entity.SimpleBean;

public class Application {
    public static void main(String[] args) {
        System.setProperty("spring", "classpath");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("${spring}:spring.xml");
        SimpleBean bean = context.getBean(SimpleBean.class);
        bean.send();
        context.close();

        System.out.println(-3|9);
    }
}
