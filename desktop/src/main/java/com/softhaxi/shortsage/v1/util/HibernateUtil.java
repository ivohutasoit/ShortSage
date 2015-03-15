package com.softhaxi.shortsage.v1.util;

import com.softhaxi.shortsage.v1.dto.Gateway;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * Reference
 * <a href="http://www.concretepage.com/hibernate/configure_hibernate_without_hibernate_cfg_xml">Hibernate</a>
 *
 * @author Hutasoit
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            AnnotationConfiguration conf = new AnnotationConfiguration()
                    .addPackage("com.softhaxi.shortsage.v1.dto")
                    .addAnnotatedClass(Gateway.class)
                    //.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                    //.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                    //.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/isftdb?zeroDateTimeBehavior=convertToNull")
                    .setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:shsg01")
                    .setProperty("hibernate.connection.username", "admin")
                    .setProperty("hibernate.connection.password", "admin")
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hbm2ddl.auto", "create-drop");
            sessionFactory = conf.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
