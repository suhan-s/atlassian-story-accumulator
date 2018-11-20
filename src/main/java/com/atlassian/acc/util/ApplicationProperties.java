package com.atlassian.acc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created by suhan.s on 11/20/2018.
 */
public class ApplicationProperties {
    private static final Logger log = LogManager.getLogger(ApplicationProperties.class);
    private static ApplicationProperties applicationProperties = null;
    private static Properties properties = new Properties();
    final private static String PROPERTY_FILE = "/application.properties";

    static {
        getInstance();
    }

    private ApplicationProperties() {
        loadProperties();
    }

    public static synchronized ApplicationProperties getInstance() {
        if (applicationProperties == null) {
            applicationProperties = new ApplicationProperties();
            return applicationProperties;
        } else {
            return applicationProperties;
        }
    }

    private void loadProperties() {
        log.info("Inside loadProperties");
        try {
            properties.load(this.getClass().getResourceAsStream(PROPERTY_FILE));
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new RuntimeException(exception.toString());
        }

        log.info(properties.size() + " Application Properties Loaded!!!");
    }

    public static String getProperty(String key) {
        return (String) properties.get(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static void mapFileToClass(Class configClass, String configFile) throws IllegalAccessException, IOException {
        Properties properties;
        properties = new Properties();
        properties.load(configClass.getResourceAsStream(configFile));
        for (Field field : configClass.getFields()) {
            field.set(null, Integer.parseInt(properties.getProperty(field.getName())));
        }
    }

    public static String[] getPropertyList(String name) {
        return getPropertyList(name, ",");
    }

    public static String[] getPropertyList(String name, String separator) {
        return getProperty(name).split(separator);
    }
}
