package com.katalon.automation.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static Properties properties;

    public static String getBrowserProperty(String key) {
        return getProperties().getProperty(key);
    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            FileInputStream browserProperty;
            try {
                browserProperty = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
                properties.load(browserProperty);
            } catch (IOException e) {
                //ignore
            }
        }
        return properties;
    }
}
