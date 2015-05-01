package com.softhaxi.shortsage.v1.util;

import com.softhaxi.shortsage.v1.dto.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
            Configuration config = new Configuration()
                    .addAnnotatedClass(Message.class)
                    .addAnnotatedClass(InboxMessage.class)
                    .addAnnotatedClass(OutboxMessage.class)
                    .addAnnotatedClass(Contact.class)
                    .addAnnotatedClass(ContactPerson.class)
                    .addAnnotatedClass(ContactNumber.class)
                    .addAnnotatedClass(ContactGroup.class)
                    .addAnnotatedClass(ContactGroupLine.class)
                    .addAnnotatedClass(Gateway.class)
                    .addAnnotatedClass(MessageTemplate.class)
                    //.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    //.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect")
                    //.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                    //.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
                    .setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver")
                    //.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/isftdb?zeroDateTimeBehavior=convertToNull")
                    //.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:shsg01")
                    .setProperty("hibernate.connection.url", "jdbc:derby:shsg01;create=true")
                    .setProperty("hibernate.connection.username", "admin")
                    .setProperty("hibernate.connection.password", "admin")
                    .setProperty("hibernate.show_sql", "true");
//                    .setProperty("hibernate.hbm2ddl.auto", "create-drop");
            sessionFactory = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

//    static {
//        try {
//            // Create the SessionFactory from standard (hibernate.cfg.xml) 
//            // config file.
//            AnnotationConfiguration conf = new AnnotationConfiguration()
//                    .addPackage("com.softhaxi.shortsage.v1.dto")
//                    .addAnnotatedClass(Gateway.class)
//                    .addAnnotatedClass(Message.class)
//                    .addAnnotatedClass(InboxMessage.class)
//                    .addAnnotatedClass(OutboxMessage.class)
//                    .addAnnotatedClass(Contact.class)
//                    .addAnnotatedClass(ContactPerson.class)
//                    .addAnnotatedClass(ContactGroup.class)
//                    .addAnnotatedClass(ContactGroupLine.class)
//                    //.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
//                    //.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
//                    .setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect")
//                    //.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
//                    //.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
//                    .setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver")
//                    //.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/isftdb?zeroDateTimeBehavior=convertToNull")
//                    //.setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:shsg01")
//                    .setProperty("hibernate.connection.url", "jdbc:derby:shsg01;create=true")
//                    .setProperty("hibernate.connection.username", "admin")
//                    .setProperty("hibernate.connection.password", "admin")
//                    .setProperty("hibernate.show_sql", "true");
////                    .setProperty("hibernate.hbm2ddl.auto", "create-drop");
//            sessionFactory = conf.buildSessionFactory();
//        } catch (Throwable ex) {
//            // Log the exception. 
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
