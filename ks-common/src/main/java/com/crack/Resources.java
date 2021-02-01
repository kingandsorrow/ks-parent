package com.crack;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Resources {
    private static ClassLoader DEFAULT_CLASSLOADER = null;

    public Resources() {
    }

    public static Properties readAsProperties(String name) {
        InputStream in = null;
        Properties prop = null;

        try {
            in = getResourceAsStream(name);
            prop = new Properties();
            prop.load(in);
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }

        }

        return prop;
    }

    public static InputStream getResourceAsStream(String name) {
        return getClassLoader().getResourceAsStream(name);
    }

    public static URL getResource(String name) {
        return getClassLoader().getResource(name);
    }

    public static ClassLoader getClassLoader() {
        return DEFAULT_CLASSLOADER != null ? DEFAULT_CLASSLOADER : Thread.currentThread().getContextClassLoader();
    }

    public static void setDEFAULT_CLASSLOADER(ClassLoader default_classloader) {
        DEFAULT_CLASSLOADER = default_classloader;
    }
}
