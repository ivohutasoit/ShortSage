package com.softhaxi.shortsage.v1.worker;

import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.hibernate.Session;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 * @param <T>
 */
public class SavingDataWorker<T> extends SwingWorker<Boolean, Void> {
  private ActionState state;
  private Session session;
  private T t;
  
  public SavingDataWorker(T t, ActionState state) {
    this.state = state;
    this.t = t;
  }
  
  @Override
  protected Boolean doInBackground() throws Exception {
      boolean saved = false;
      try {
          session = HibernateUtil.getSessionFactory().openSession();
          session.getTransaction().begin();
          
          if(state == ActionState.CREATE) {
              session.save(t);
              saved = true;
          }else if (state == ActionState.UPDATE) {
              session.update(t);
              saved = true;
          } else if(state == ActionState.DELETE) {
              session.delete(t);
              saved = true;
          }
          
          if(saved == true) { 
            session.getTransaction().commit();
            saved = true;
          } else {
            session.getTransaction().rollback();
            saved = false;
          }
          
      } catch (Exception ex) {
          session.getTransaction().rollback();
          saved  = false;
          Logger.getLogger(SavingDataWorker.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        session.close();
      }
      return saved;
  }
}
