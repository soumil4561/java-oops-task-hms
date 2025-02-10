package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Configuration file 'application.properties' not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static String getAdminPassword() {
        return properties.getProperty("admin.password");
    }

    public static String getDefaultHospitalName(){
        return properties.getProperty("hospital.name");
    }

    public static String getDefaultHospitalAddress(){
        return properties.getProperty("hospital.address");
    }

    public static boolean loadSampleData(){
        return properties.getProperty("load.sample.data").equals("true");
    }
}
