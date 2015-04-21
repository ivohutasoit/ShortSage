package com.softhaxi.shortsage.v1.worker;

import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
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
  private JProgressBar progress;
  private JDialog dialog;
  
  private ActionState state;
  private Session session;
  private T t;
  
  public SavingDataWorker(T t, ActionState state, boolean showDialog) {
    this.state = state;
    this.t = t;
    
    if(showDialog) {
      if (dialog == null) {
          dialog = new JDialog();
          dialog.setTitle("Saving Data");
          dialog.setPreferredSize(new Dimension(300, 250));
          dialog.setLayout(new GridBagLayout());
          dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
          GridBagConstraints gbc = new GridBagConstraints();
          gbc.insets = new Insets(2, 2, 2, 2);
          gbc.weightx = 1;
          gbc.gridy = 0;
          dialog.add(new JLabel("Please wait..."), gbc);
          progress = new JProgressBar();
          progress.setIndeterminate(true);
          gbc.gridy = 1;
          dialog.add(progress, gbc);
          dialog.pack();
          dialog.setLocationRelativeTo(null);
          dialog.setVisible(true);
      }
    }
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
  
  @Override
  protected void done() {
      if (dialog != null) {
          dialog.dispose();
      }
  }
}
