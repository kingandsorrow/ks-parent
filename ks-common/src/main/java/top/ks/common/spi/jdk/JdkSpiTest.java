package top.ks.common.spi.jdk;

import java.util.Iterator;
import java.util.ServiceLoader;

public class JdkSpiTest {

    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        Iterator iterator = serviceLoader.iterator();
        System.out.println("java sdk spi ");
        while (iterator.hasNext()) {
            Robot robot = (Robot) iterator.next();
            robot.syaHello();
        }
//        serviceLoader.forEach(Robot::syaHello);
    }
}
