package com.example.breakout_clone.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Class to handle all the resource requests from the game
 * The class behaves as a static class thus the initialisation of fields is
 * done in the static initialisation block.
 * @author Praket Chavan
 */
public class ResourceHandler {
    /**
     * Constant URL for the sprite resource directory
     */
    private static final URL RESOURCE_URL = ResourceHandler.class.
            getResource("/sprite");

    /**
     * Constant Properties object that is used to read the theme of the game
     */
    private static final Properties properties = new Properties();

    /**
     * Constant String that defines the path to the theme style sheet in the
     * resource folder
     */
    public static final String THEME_CSS_PATH =
            ResourceHandler.class.getResource("/css/theme.css")
                                 .toExternalForm();

    /**
     * Constant File object that opens the theme.properties file
     */
    private static final File FILE = new File(
            ResourceHandler.class
                    .getResource("/properties/theme.properties").getFile());

    /**
     * FileInputStream that will be used to read the theme.properties file
     */
    private static FileInputStream RESOURSE;

    /**
     * Long value that stores when the theme.properties file was last modified.
     */
    private static long m_LastModified = FILE.lastModified();

    static {
        try {
            //Create the FileInputStream for the theme.properties file and
            // load it.
            RESOURSE = new FileInputStream(
                    ResourceHandler.class
                            .getResource("/properties/theme.properties").getFile());
            properties.load(RESOURSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the sprite resource from the resource folder depending on the
     * current theme of the game
     * @param resource the name of the sprite resource that is needed
     * @return the Path of the resource as a String
     */
    public static String getResource(String resource) {
        try {
            /*Check to see if the theme.properties file has been modified
             after it was previously opened if it has then recreated the
             FileInputStream to get the updated file and reload using the
             Properties obejct
               */
            if (m_LastModified != FILE.lastModified()) {
                RESOURSE = new FileInputStream(
                        ResourceHandler.class
                                .getResource("/properties/theme.properties").getFile());
                m_LastModified = FILE.lastModified();
            }
            properties.load(RESOURSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Get the current theme from the properties file
        String theme = properties.getProperty("theme");
        //Fetch the resource of that is required
        return String.format("%s%s/%s.png", RESOURCE_URL.toString(),
                             theme, resource);
    }

    /**
     * Handles the request for the audio resources that are used in the game
     * @param resource the name of the audio resource that is needed
     * @return the path to the audio resource as a String
     */
    public static String getSoundResource(String resource) {
        return ResourceHandler.class.getResource("/audio/" + resource)
                                    .toExternalForm();
    }
}
