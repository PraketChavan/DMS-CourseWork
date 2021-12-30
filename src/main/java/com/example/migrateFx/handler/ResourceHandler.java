package com.example.migrateFx.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ResourceHandler {
    private static final URL RESOURCE_URL = ResourceHandler.class.
            getResource("/com/example/migrateFx/sprite");
    private static FileInputStream RESOURSE;
    private static Properties properties = new Properties();
    static  {
        try {
            RESOURSE = new FileInputStream(
                    ResourceHandler.class
                            .getResource("/theme.properties").getFile());
            properties.load(RESOURSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getResource(String resource) {
        String theme = properties.getProperty("theme");
        return String.format("%s%s/tile000/%s.png", RESOURCE_URL.toString(), theme, resource);
    }
    public static String getSoundResource(String resource) {
        return ResourceHandler.class.getResource("/" + resource).toExternalForm();
    }
}
