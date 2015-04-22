package com.softhaxi.shortsage.v1.modem;

import com.softhaxi.shortsage.v1.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ModemCallback {
    protected Session session;
    
    protected void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }
    
    protected void closeSession() {
        session.close();
    }
}
