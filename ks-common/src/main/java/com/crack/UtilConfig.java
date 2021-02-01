package com.crack;

import java.util.Properties;

public class UtilConfig {

    private static UtilConfig instance;

    private Properties props;

    private UtilConfig() {
    }

    private static void init() {

        if (instance != null) {

            return;
        }

        instance = new UtilConfig();

        instance.props = Resources.readAsProperties("util.properties");

        if (instance.props == null) {

            instance.props = new Properties();
        }

        return;
    }

    public synchronized static UtilConfig getInstance() {

        if (instance == null) {

            init();
        }

        return instance;
    }

    public String getProperty(String key) {

        return props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {

        return props.getProperty(key, defaultValue);
    }

    public static void main(String[] args) {

        String proxy = getInstance().getProperty("http.proxyhost");

        System.out.println(proxy);
    }

}
