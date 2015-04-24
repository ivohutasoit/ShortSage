package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.desktop.HLookupField;
import com.softhaxi.shortsage.v1.desktop.HWaitDialog;
import com.softhaxi.shortsage.v1.dto.MessageTemplate;
import com.softhaxi.shortsage.v1.dto.OutboxMessage;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.impl.ShowHideGroupPanel;
import com.softhaxi.shortsage.v1.lookup.MessageTemplateSearch;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.RowGroup;
import org.hibernate.Session;
import org.jdesktop.swingx.JXDatePicker;
import org.smslib.Service;

public class MessageActionForm extends JPanel
        implements ActionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private OutboxMessage object;
    private ActionState state;

    /**
     * Tool bar items
     */
    private JButton bNew, bEdit, bDelete, bReply;
    private JButton bSave, bSaveNew, bCancel;

    /**
     * Fields
     */
    private JCheckBox cfDate;
    private HLookupField lfContact, lfTemplate;
    private JXDatePicker dfDate;
    private JTextArea tfText;
    private JComboBox cfStatus, cfHandler;

    /**
     * Message References
     */
    private HashMap<String, Contact> contacts;
    private MessageTemplate template;

    /**
     * Database and Modem
     */
    private Service mService;
    private List<OutboundMessage> mMessages;
    private Session hSession;
    private List<OutboxMessages> dMessages;

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
        contacts = new HashMap<String, Contact>();

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

        cfDate = new JCheckBox(RES_GLOBAL.getString("label.message.scheduler"));
        cfDate.setForeground(Color.blue);
        dfDate = new JXDatePicker();

        DesignGridLayout layout = new DesignGridLayout(pForm);
        RowGroup rgDate = new RowGroup();
        cfDate.setSelected(true);
        cfDate.addItemListener(new ShowHideGroupPanel(rgDate));

        layout.row().left().add(cfDate, new JSeparator()).fill();
        layout.row().group(rgDate).grid(new JLabel(RES_GLOBAL.getString("label.message.date"))).add(dfDate).empty(2);

        JLabel llMessage = new JLabel("Message Detail");
        llMessage.setForeground(Color.blue);
        lfContact = new HLookupField("Lookup or Typed Contact") {
            @Override
            public void lookupPerformed() {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setTitle("Lookup Contact - Dialog Selection");
                
                final ContactSearch panel = new MessageTemplateSearch();
                panel.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("select".equalsIgnoreCase(evt.getPropertyName())
                                && ((boolean) evt.getNewValue()) == true) {
                            List<Contact> lContacts = panel.getUserData();
                            if(lContact != null ||
                                lContact.size() != 0) {
                                
                                for(Contact contact : lContacts) {
                                  if(contacts.containsKey(contact.getName())) {
                                    contacts.remove(contact.getName());
                                    contacts.put(contact.getName(), contact)
                                  }  
                                }      
                            }
                            
                            StringBuilder sb = new StringBuilder();
                            for (Map.Entry<String, String> entry : map.entrySet()) {
                              sb.append(entry.getKey()).append("; ");
                          	}
                          	tfContact.setTexx(sb);
                            
                            dialog.setVisible(false);
                            dialog.dispose();
                        } else if ("cancel".equalsIgnoreCase(evt.getPropertyName())
                                && ((boolean) evt.getNewValue()) == true) {
                            dialog.setVisible(false);
                            dialog.dispose();
                        } else if ("clear".equalsIgnoreCase(evt.getPropertyName())
                                && ((boolean) evt.getNewValue()) == true) {
                            contacts.clear();
                            tfContact.setText("");
                            dialog.setVisible(false);
                            dialog.dispose();
                        }

                    }
                });
                dialog.add(panel);
                panel.setVisible(true);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        };
        tfText = new JTextArea();
        tfText.setRows(3);
        tfText.setFont(lfContact.getFont());
        tfText.setLineWrap(true);
        lfTemplate = new HLookupField("Lookup Template") {
            @Override
            public void lookupPerformed() {
                final JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.setTitle("Lookup Template - Dialog Selection");

                final MessageTemplateSearch panel = new MessageTemplateSearch();
                panel.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("select".equalsIgnoreCase(evt.getPropertyName())
                                && ((boolean) evt.getNewValue()) == true) {
                            template = panel.getUserData();
                            if (template != null) {
                                lfTemplate.setText(template.getName());
                                tfText.setText(template.getText());
                            }
                            dialog.setVisible(false);
                            dialog.dispose();
                        } else if ("cancel".equalsIgnoreCase(evt.getPropertyName())
                                && ((boolean) evt.getNewValue()) == true) {
                            dialog.setVisible(false);
                            dialog.dispose();
                        } else if ("clear".equalsIgnoreCase(evt.getPropertyName())
                                && ((boolean) evt.getNewValue()) == true) {
                            template = null;
                            lfTemplate.setText("");
                            tfText.setText("");
                            dialog.setVisible(false);
                            dialog.dispose();
                        }

                    }
                });
                dialog.add(panel);
                panel.setVisible(true);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        };
        lfTemplate.setEditable(false);
        cfStatus = new JComboBox();
        cfStatus.setEnabled(false);
        cfHandler = new JComboBox();

        layout.row().left().add(llMessage, new JSeparator()).fill();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.name") + " :")).add(lfContact);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.template.name") + " :")).add(lfTemplate, 2).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.message.text") + " :")).add(new JScrollPane(tfText));
//        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status") + " :")).add(cfStatus).empty(2);
//        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler") + " :")).add(cfHandler).empty(2);

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
        if (cfDate.isSelected()) {
            if (dfDate.getDate().toString().equals("")) {
                dfDate.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                return false;
            }
        }

        if (lfContact.getText().equals("")) {
            lfContact.setBorder(BorderFactory.createLineBorder(Color.red, 1));
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
        lfContact.setText(contact);
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
                final String[] names = tfContact.getText().split(";");
                final OutbounMessage temp = new OutboundMessage(tfText.getText().trim(), tfContact.getText().trim());
                final Date date = dfDate.getDate();
                final boolean isScheduler = cfDate.isSelected();
                
                SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {
                  @Override
                  protected void doInBackground() {
                    OutboundMessage mMsg = null;
                    OutboxMessage dMsg = null;
                    
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    mService = Service.getInstance();
                    hSession.getTransaction().begin();
                    for(String name : names) {
                      if(contacts.containsKey(name)) {
                        Contact cc = contacts.get(name);
                        if(cc instanceof ContactPerson) {
                          temp.clone(mMsg);
                          mMsg.setRecipient(cc.getNumber());
                          
                          if(isScheduler) {
                            mService.queueMessageAt(mMsg, date);
                          } else {
                            mService.queueMessage(mMsg);
                          }
                        } else if(cc instanceof ContactGroup) {
                          
                        }
                      } else {
                        // Only Number
                        Query query = hSession.getNamedQuery("Contact.ByNameOrNumber");
                        query.setParameter("name", name);
                        query.setParameter("number", name);
                        
                        Contact cc = (contact) query.uniqueResult();
                        if(cc == null) {
                          temp.clone(mMsg);
                          mMsg.setRecipient(name);
                          
                          if(isScheduler) {
                            mService.queueMessageAt(mMsg, date);
                          } else {
                            mService.queueMessage(mMsg);
                          }
                        } else if(cc instanceof ContactPerson) {
                          temp.clone(mMsg);
                          mMsg.setRecipient(cc.getNumber());
                          
                          if(isScheduler) {
                            mService.queueMessageAt(mMsg, date);
                          } else {
                            mService.queueMessage(mMsg);
                          }
                        } else if(cc instanceof ContactGroup) {
                          
                        }
                      }
                      dMsg = new OutboxMessage();
                      dMsg.setRefId(mMsg.getUuid());
                      dMsg.setGatewayId(mMsg.getGatewayId());
                      dMsg.setContact(mMsg.getRecipient());
                      dMsg.setText(mMsg.getText());
                      dMsg.setDate(mMsg.getDate());
                      dMsg.setFailureCause(msg.getFailureCause().toString());
                      dMsg.setRetryCount(msg.getRetryCount());
                      dMsg.setErrorMessage(msg.getErrorMessage());
                      dMsg.setStatus(mMsg.getMessageStatus() == OutboundMessage.MessageStatuses.UNSENT ? 1
                                  : mMsg.getMessageStatus() == OutboundMessage.MessageStatuses.SENT ? 2 : 3);
                                  
                      
                      hSession.saveOrUpdate(message);
                    }
                    hSession.getTransaction().commit();
                    return true;
                  }
                };

                dialog.setVisible(true);
            } else if (bb == bCancel) {
                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
            }
        }
    }
    // </editor-fold>
}
