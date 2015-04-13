package com.softhaxi.shortsage.v1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * References 
 * <ol>
 * <li><a href="http://blog.stchedroff.com/2012/12/09/a-generic-dao-using-jpa-and-hibernate-4/">Generic Dao hibernate 4</a>
 * </ol>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class GenericDao<T> {
  private Session session;
  private SessionFactory sessionFactory;
  private Class type;
  
  public GenericDao(SessionFactory sessionFactory, Class type) {
    this.sessionFactory = sessionFactory;
    this.type = type;
  }
}
