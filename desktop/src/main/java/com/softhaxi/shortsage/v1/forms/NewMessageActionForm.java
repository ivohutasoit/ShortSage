package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.dto.Message;
import com.softhaxi.shortsage.v1.enums.ActionState;
import static java.awt.AWTEventMulticaster.add;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.RowGroup;
import org.hibernate.Session;
import org.jdesktop.swingx.JXDatePicker;

public class NewMessageActionForm extends JPanel 
        implements ActionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private Message object;
    private ActionState state;

    /**
     * Tool bar items
     */
    private JButton bNew, bEdit, bDelete, bReply;
    private JButton bSave, bSaveNew, bCancel;

    /**
     * Fields
     */
    private JCheckBox cfScheduler;
    private JTextField tfContact;
    private JXDatePicker dfDate;
    private JTextArea tfText;
    private JComboBox cfTemplate, cfStatus, cfHandler;

    /**
     * Database connection
     */
    private Session hSession;

    /**
     *
     */
    public NewMessageActionForm() {
        this(null, ActionState.CREATE);
    }

    /**
     *
     * @param object
     * @param state
     */
    public NewMessageActionForm(Message object, ActionState state) {
        this.object = object;
        this.state = state;

        initComponents();
        initListeners();
        initState();
    }

    // <editor-fold defaultstate="collapsed" desc="Region Initialization">
    /**
     *
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 300));

        initToolbar();
        initFormPanel();
    }

    /**
     *
     */
    private void initToolbar() {
        JToolBar pToolbar = new JToolBar();
        pToolbar.setFloatable(false);

        bNew = new JButton(RES_GLOBAL.getString("label.new"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        pToolbar.add(bNew);

        bEdit = new JButton(RES_GLOBAL.getString("label.edit"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png")));
        pToolbar.add(bEdit);

        bSave = new JButton(RES_GLOBAL.getString("label.save"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_save.png")));
        pToolbar.add(bSave);

        bSaveNew = new JButton(RES_GLOBAL.getString("label.save.new"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_save_as.png")));
        pToolbar.add(bSaveNew);
        pToolbar.addSeparator();

        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        pToolbar.add(bDelete);

        bCancel = new JButton(RES_GLOBAL.getString("label.cancel"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_cancel.png")));
        pToolbar.add(bCancel);

        add(pToolbar, BorderLayout.NORTH);
    }

    /**
     *
     */
    private void initFormPanel() {
        JPanel pForm = new JPanel();

        cfScheduler = new JCheckBox(RES_GLOBAL.getString("label.message.scheduler"));
        cfScheduler.setForeground(Color.blue);
        tfContact = new JTextField();
        dfDate = new JXDatePicker();
        tfText = new JTextArea();
        tfText.setRows(3);
        tfText.setFont(tfContact.getFont());
        cfTemplate = new JComboBox();
        cfStatus = new JComboBox();
        cfStatus.setEnabled(false);
        cfHandler = new JComboBox();

        DesignGridLayout layout = new DesignGridLayout(pForm);
        RowGroup rgScheduler = new RowGroup();
        cfScheduler.setSelected(true);
        cfScheduler.addItemListener(new ShowHideAction(rgScheduler));

        layout.row().left().add(cfScheduler, new JSeparator()).fill();
        layout.row().group(rgScheduler).grid(new JLabel(RES_GLOBAL.getString("label.message.date"))).add(dfDate).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.name") + " :")).add(tfContact, 2).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.template.name") + " :")).add(cfTemplate, 2).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.message.text") + " :")).add(new JScrollPane(tfText));
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status") + " :")).add(cfStatus).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler") + " :")).add(cfHandler).empty(2);

        add(pForm, BorderLayout.CENTER);
    }

    /**
     *
     */
    private void initListeners() {
    }

    /**
     *
     */
    private void initState() {
        if(state == ActionState.CREATE) {
            cfStatus.removeAllItems();
            cfStatus.addItem("Create");
            
            cfHandler.removeAllItems();
            cfHandler.addItem("Created");
            cfHandler.setEnabled(false);
            
            bNew.setVisible(false);
            bEdit.setVisible(false);
            bDelete.setVisible(false);
            bSaveNew.setVisible(false);
        }
    }

    /**
     *
     */
    private void initData() {
    }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="Private Methods">
  private boolean isValid() {
      boolean valid = false;
      
      if(cfScheduler.isSelected()) {
          if(!cfDate.getText().equals("")) {
              continue;
          } else {
              cfDate.setBorder(BorderFactory.createLineBorder(Color.red, 5));
          }
      }
      
      if(!tfContact.getText().equals("")) {
          continue;
      } else {
          tfContact.setBorder(BorderFactory.createLineBorder(Color.red, 5));
      }
      
      if(!tfText.getText().equals("")) {
          valid = true;
      } else {
          tfText.setBorder(BorderFactory.createLineBorder(Color.red, 5));
      }
      
      return valid;
  }
  
  /**
   * Send message or scheduler sending message at date given.
   */ 
  private synchronized void sendMessage() {
      if(!isValid()) 
        return;
        
      firePropertyChange(PropertyChangeField.SENDING.toString(), false, true);
      if(cfSheduler.isSelected()) {
          // Queue Message At
      } else {
          // Send Message
      }
  }
  
  private synchronized void saveMessage() {
      if(isValid()) 
        return;
        
      firePropertyChange(PropertyChangeField.SAVING.toString(), false, true);
      // Save Message to database
  }
  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="ActionListener Implementation">
  @Override
  public void actionPerformed(ActionEvent e) {
      
  }
  // </editor-fold>

    class ShowHideAction implements ItemListener {

        private RowGroup group;

        public ShowHideAction(RowGroup group) {
            this.group = group;
        }

        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                group.show();
            } else {
                group.hide();
            }
        }
    }
}
