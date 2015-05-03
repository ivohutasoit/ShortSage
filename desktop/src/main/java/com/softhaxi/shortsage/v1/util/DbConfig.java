package com.softhaxi.shortsage.v1.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivo Hutasoit
 */
public class DbConfig {

    private static ResourceBundle bundle = ResourceBundle.getBundle("global");

    private static String dbPath;
    private static Properties dbProperties, sysDbProperties;

    static {
        dbPath = System.getProperty("user.dir")
                + File.separator + bundle.getString("app.dbconfig.file");
        dbProperties = new Properties();
    }
    
    /**
     * Checking file dbconfig.properties is exist or not
     *
     * @return false if file was not exist true if file was exist
     */
    public static boolean isExist() {
        return new File(dbPath).exists();
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public static Properties getDbProperties() throws Exception {
        Properties temp = new Properties();
        
        InputStream is = null;
        try {
            is = new FileInputStream(dbPath);
            
            temp.load(is);
            if(!temp.getProperty("connection.default").equals("")) {
                String db = temp.getProperty("connection.default");
                if(!temp.getProperty(String.format("%s.hibernate.dialect", db), null).equals("")) {
                    dbProperties.setProperty("hibernate.dialect", 
                            temp.getProperty(String.format("%s.hibernate.dialect", db), null));
                }
                
                if(!temp.getProperty(String.format("%s.hibernate.connection.driver_class", db), null).equals("")) {
                    dbProperties.setProperty("hibernate.connection.driver_class", 
                            temp.getProperty(String.format("%s.hibernate.connection.driver_class", db), null));
                }
                
                if(!temp.getProperty(String.format("%s.hibernate.connection.url", db), null).equals("")) {
                    dbProperties.setProperty("hibernate.connection.url", 
                            temp.getProperty(String.format("%s.hibernate.connection.url", db), null));
                }
                
                if(!temp.getProperty(String.format("%s.hibernate.connection.username", db), null).equals("")) {
                    dbProperties.setProperty("hibernate.connection.username", 
                            temp.getProperty(String.format("%s.hibernate.connection.username", db), null));
                }
                
                if(!temp.getProperty(String.format("%s.hibernate.connection.password", db), null).equals("")) {
                    dbProperties.setProperty("hibernate.connection.password", 
                            temp.getProperty(String.format("%s.hibernate.connection.password", db), null));
                }
                
                if(!temp.getProperty(String.format("%s.hibernate.show_sql", db), null).equals("")) {
                    dbProperties.setProperty("hibernate.show_sql", 
                            temp.getProperty(String.format("%s.hibernate.show_sql", db), null));
                }
                
                if(!temp.getProperty(String.format("%s.hibernate.hbm2ddl.auto", db), null).equals("")) {
                    dbProperties.setProperty("hibernate.hbm2ddl.auto", 
                            temp.getProperty(String.format("%s.hibernate.hbm2ddl.auto", db), null));
                }
            }
        } catch (IOException ioex) {
            Logger.getLogger(DbConfig.class.getName()).log(Level.SEVERE, null, ioex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    Logger.getLogger(DbConfig.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return dbProperties;
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public static Properties getSysDbProperties() throws Exception {
        return sysDbProperties;
    }
}
