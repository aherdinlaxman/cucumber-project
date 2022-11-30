package org.configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public String getData(String property) throws IOException {
        Properties prop=new Properties();
        String path = System.getProperty("user.dir");
        FileInputStream ip= new FileInputStream(path + "\\src/test/resources/config.properties");
        prop.load(ip);
       String value = prop.getProperty(property);
        return value;
    }
}