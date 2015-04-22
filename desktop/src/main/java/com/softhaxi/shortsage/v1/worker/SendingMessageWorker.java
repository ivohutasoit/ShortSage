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
  private OutboundMessage message;
  private Date date;
  
  /**
   * 
   */
  public SendingMessageWorker() {
  }
  
  public void setMessage(OutboundMessage message) {
      this.message = message;
  }
  
  public void setDate(Date date) {
      this.date = date;
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
}
