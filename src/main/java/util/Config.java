package util;

import java.util.Properties;

public class Config {

    private Properties configFile;

    private static Config instance;

    private Config() {
        configFile = new java.util.Properties();
        try {
            configFile.load(this.getClass().getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (Exception eta) {
            eta.printStackTrace();
        }
    }

    private static String getProperty(String key) {
        if (instance == null) {
            instance = new Config();
        }
        return instance.configFile.getProperty(key);
    }

    public static Long getTimeoutInSeconds() {
        return Long.parseLong(getProperty("timeoutinseconds"));
    }

    public static Long getSleepInMillis() {
        return Long.parseLong(getProperty("sleepinmillis"));
    }
}