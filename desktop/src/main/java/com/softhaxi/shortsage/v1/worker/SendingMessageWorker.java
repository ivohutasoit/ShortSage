package com.softhaxi.shortsage.v1.worker;

import com.softhaxi.shortsage.v1.forms.MessageActionForm;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class SendingMessageWorker extends SwingWorker<OutboundMessage, Void> {
  private JProgressBar progress;
  private JDialog dialog;
  
  private OutboundMessage message;
  private Date date;
  
  /**
   * 
   * @param message
   * @param date
   * @param showDialog 
   */
  public SendingMessageWorker(OutboundMessage message, Date date, boolean showDialog) {
    this.message = message;
    this.date = date;
    
    if(showDialog) {
      if (dialog == null) {
          dialog = new JDialog();
          dialog.setTitle("Sending Message");
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
  protected OutboundMessage doInBackground() throws Exception {
      try {
          if (date != null) {
              Service.getInstance().queueMessageAt(message, date);
          } else {
              Service.getInstance().sendMessage(message);
          }
          return message;
      } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
          Logger.getLogger(SendingMessageWorker.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
  }
  
  @Override
  protected void done() {
      if (dialog != null) {
          dialog.dispose();
      }
  }
}
