package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties file was not found");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read config.properties", e);
        }
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        return systemValue != null ? systemValue : properties.getProperty(key, "");
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}
