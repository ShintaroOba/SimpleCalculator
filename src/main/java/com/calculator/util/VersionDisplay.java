package com.calculator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for displaying version information.
 */
public class VersionDisplay {
    
    private static final String DEFAULT_VERSION = "1.0.0";
    private static final String VERSION_FILE = "/version.properties";
    
    /**
     * Returns the version message.
     * 
     * @return formatted version message
     */
    public String getVersionMessage() {
        String version = getVersion();
        return "Calculator version " + version + "\n" +
               "A simple command-line calculator for basic arithmetic operations\n" +
               "Built with Java 25";
    }
    
    /**
     * Gets the version from the properties file or returns default.
     * 
     * @return version string
     */
    private String getVersion() {
        try (InputStream inputStream = getClass().getResourceAsStream(VERSION_FILE)) {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties.getProperty("version", DEFAULT_VERSION);
            }
        } catch (IOException e) {
            // If we can't load the version file, use default
        }
        return DEFAULT_VERSION;
    }
}
