package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.desktop.HWaitDialog;
import com.softhaxi.shortsage.v1.dto.OutboxMessage;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.worker.SavingDataWorker;
import com.softhaxi.shortsage.v1.worker.SendingMessageWorker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.RowGroup;
import org.hibernate.Session;
import org.jdesktop.swingx.JXDatePicker;
import org.smslib.OutboundMessage;
import org.smslib.Service;

public class MessageActionForm extends JPanel
        implements ActionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private OutboxMessage object;
    private ActionState state;

    private boolean running = false;
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
     * Message
     */
    private OutboundMessage oMessage;

    /**
     * Database connection
     */
    private Session hSession;

    /**
     *
     */
    public MessageActionForm() {
        this(null, ActionState.CREATE);
    }

    /**
     *
     * @param object
     * @param state
     */
    public MessageActionForm(OutboxMessage object, ActionState state) {
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
        tfText.setLineWrap(true);
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
        bSave.addActionListener(this);
        bCancel.addActionListener(this);
    }

    /**
     *
     */
    private void initState() {
        if (state == ActionState.CREATE) {
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
    /**
     *
     */
    private void loadTemplateData() {

    }

    /**
     *
     * @return
     */
    private boolean isModelValid() {
        if (cfScheduler.isSelected()) {
            if (dfDate.getDate().toString().equals("")) {
                dfDate.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                return false;
            }
        }

        if (tfContact.getText().equals("")) {
            tfContact.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        }

        if (tfText.getText().equals("")) {
            tfText.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        }

        return true;
    }

    // </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        tfContact.setText(contact);
    }
  // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ActionListener Implementation">
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            if (bb == bSave) {
                if (!isModelValid()) {
                    return;
                }

                if (Service.getInstance().getServiceStatus() == Service.ServiceStatus.STOPPED) {
                    JOptionPane.showMessageDialog(null, "Modem Service does not running.",
                            "Message", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                final HWaitDialog dialog = new HWaitDialog("Send Message");
                
                oMessage = new OutboundMessage(tfContact.getText().trim(), tfText.getText().trim());      // Only one number 
                // not contact person or group
                object = new OutboxMessage();

                // http://stackoverflow.com/questions/8121621/progress-dialog-in-swingworker
                final SavingDataWorker td = new SavingDataWorker<OutboxMessage>(object, ActionState.CREATE);
                td.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("state".equals(evt.getPropertyName())
                                && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                            try {
                                if (Boolean.parseBoolean(td.get().toString()) == true) {
                                    dialog.setVisible(false);
                                    dialog.dispose();
                                    JOptionPane.showMessageDialog(null, "Sending and saving message successfull",
                                            "New Message", JOptionPane.INFORMATION_MESSAGE);
                                    firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
                                }
                            } catch (InterruptedException | ExecutionException ex) {
                                Logger.getLogger(MessageActionForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });

                final SendingMessageWorker tm = new SendingMessageWorker(oMessage, null);
                firePropertyChange(PropertyChangeField.SENDING.toString(), false, true);
                tm.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("state".equals(evt.getPropertyName())
                                && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                            try {
                                firePropertyChange(PropertyChangeField.SAVING.toString(), false, true);
                                if ((oMessage = tm.get()) != null) {
                                    object.setRefId(oMessage.getId());
                                    object.setContact(oMessage.getRecipient());
                                    object.setGatewayId(oMessage.getGatewayId());
                                    object.setText(oMessage.getText());
                                    object.setDate(oMessage.getDate());
                                    object.setFailureCause(oMessage.getFailureCause().toString());
                                    object.setRetryCount(oMessage.getRetryCount());
                                    object.setErrorMessage(oMessage.getErrorMessage());
                                    object.setStatus(oMessage.getMessageStatus() == OutboundMessage.MessageStatuses.UNSENT ? 1 :
                                            oMessage.getMessageStatus() == OutboundMessage.MessageStatuses.SENT ? 2 : 3);
                                    
                                    dialog.setVisible(false);
                                    td.execute();
                                    dialog.setTitle("Save Message");
                                    dialog.setVisible(true);
                                }
                            } catch (InterruptedException | ExecutionException ex) {
                                Logger.getLogger(MessageActionForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                tm.execute();
                dialog.setVisible(true);
            } else if(bb == bCancel) {
                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
            }
        }
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
