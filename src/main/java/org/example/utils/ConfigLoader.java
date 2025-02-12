package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The config loader class uses the "Properties" class to read the config files for the application
 * It presents different method for each of the config properties loaded into the application.
 */
public class ConfigLoader {
    // The properties object
    private static final Properties properties = new Properties();

    /**
     * static block defined to load the config variables at the start of the application,
     * since it is only required at the time of initialization.
     */
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

    /**
     * Retrieves the administrator password from the configuration file.
     * @return the admin password as a String
     */
    public static String getAdminPassword() {
        return properties.getProperty("admin.password");
    }

    /**
     * Retrieves the default hospital name from the configuration file.
     * @return the hospital name as a String
     */
    public static String getDefaultHospitalName(){
        return properties.getProperty("hospital.name");
    }

    /**
     * Retrieves the default hospital address from the configuration file.
     * @return the hospital address as a String
     */
    public static String getDefaultHospitalAddress(){
        return properties.getProperty("hospital.address");
    }

    /**
     * Checks whether the application should load sample data based on the configuration.
     * @return true if sample data should be loaded, false otherwise
     */
    public static boolean loadSampleData(){
        return properties.getProperty("load.sample.data").equals("true");
    }
}
