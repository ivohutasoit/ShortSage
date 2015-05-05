package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.desktop.HWaitDialog;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.hibernate.Session;
import org.jdesktop.swingx.JXTextArea;
import org.jdesktop.swingx.JXTextField;

/**
 * Referencese
 * <ul>
 * <li><a href="https://designgridlayout.java.net/">Design Grid Layout</a></li>
 * </ul>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ContactPersonActionForm extends JPanel
        implements ActionListener {

    private final static ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private ContactPerson object;
    private ActionState state;

    /**
     * Tool bar items
     */
    private JButton bNew, bEdit, bDelete;
    private JButton bSave, bSaveNew, bCancel;

    /**
     * Fields
     */
    private JComboBox cfPrefix, cfCountry, cfStatus, cfHandler;
    private JXTextField tfFName, tfMName, tfLName, tfEmail, tfCompany;
    private JXTextField tfAddr1, tfAddr2, tfAddr3, tfCity, tfZip;
    private JXTextField tfPhone, tfHome, tfWork, tfCustom;
    private JXTextArea tfRemark;
    private JCheckBox cfAddress, cfPreference;

    /**
     * Database
     */
    private Session hSession;

    /**
     *
     */
    public ContactPersonActionForm() {
        this(null, ActionState.CREATE);
    }

    /**
     *
     * @param object
     */
    public ContactPersonActionForm(ContactPerson object) {
        this(object, ActionState.SHOW);
    }

    /**
     *
     * @param object
     * @param state
     */
    public ContactPersonActionForm(ContactPerson object, ActionState state) {
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
        setPreferredSize(new Dimension(500, 600));

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
        cfPrefix = new JComboBox(new String[]{
            "Mr.",
            "Mrs.",
            "Ms."
        });

        cfCountry = new JComboBox(new String[]{
            "IDN (+62)"
        });

        cfStatus = new JComboBox();
        cfStatus.setEnabled(false);
        cfHandler = new JComboBox();
        tfFName = new JXTextField(RES_GLOBAL.getString("label.contact.firstname"));
        tfMName = new JXTextField(RES_GLOBAL.getString("label.contact.midname"));
        tfLName = new JXTextField(RES_GLOBAL.getString("label.contact.lastname"));
        tfEmail = new JXTextField(RES_GLOBAL.getString("label.contact.email"));
        tfCompany = new JXTextField(RES_GLOBAL.getString("label.contact.company"));
        tfAddr1 = new JXTextField(RES_GLOBAL.getString("label.contact.fulladdress"));
        tfAddr2 = new JXTextField();
        tfAddr3 = new JXTextField();
        tfCity = new JXTextField(RES_GLOBAL.getString("label.contact.city"));
        tfZip = new JXTextField(RES_GLOBAL.getString("label.contact.zip"));
        tfPhone = new JXTextField(RES_GLOBAL.getString("label.contact.phone") + " " + 
                RES_GLOBAL.getString("label.contact.phone.exp"));
        tfRemark = new JXTextArea();
        tfRemark.setRows(3);
        tfRemark.setFont(tfPhone.getFont());
        tfRemark.setLineWrap(true);
        tfRemark.setWrapStyleWord(true);

        JPanel pForm = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.prefix") + " :")).add(cfPrefix).empty(5);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.name"))).add(tfFName)
                .add(tfMName)
                .add(tfLName);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.phone") + " :")).add(cfCountry)
                .add(tfPhone, 3)
                .empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.email") + " :")).add(tfEmail).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.company") + " :")).add(tfCompany).empty();
        layout.emptyRow();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.address") + " :")).add(tfAddr1);
        layout.row().grid().add(tfAddr2);
        layout.row().grid().add(tfAddr3);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.city") + " :")).add(tfCity).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.contact.zip") + " :")).add(tfZip).empty(5);
        //layout.emptyRow();
        //layout.row().grid(new JLabel("Description :")).add(new JScrollPane(fRemark));
        //layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status") + " :")).add(cfStatus).empty(2);
        //layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler") + " :")).add(cfHandler).empty(2);

        add(pForm, BorderLayout.CENTER);
    }

    /**
     *
     */
    private void initListeners() {
      bSave.addActionListener(this);
    }

    /**
     *
     */
    private void initState() {
        if (state == ActionState.CREATE) {
            cfStatus.removeAllItems();
            cfStatus.addItem("Create");

            cfHandler.removeAllItems();
            cfHandler.addItem("Create");
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

    /**
     * 
     */ 
    private boolean isModelValid() {
      if(tfFName.getText().trim().equal("")) {
        tfFName.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        return false;
      }
      return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bb = (JButton) e.getSource();
            if (bb == bSave) {
                if (!isModelValid()) {
                    return;
                }

                firePropertyChange(PropertyChangeField.SAVING.toString(), false, true);
                final HWaitDialog dialog = new HWaitDialog("Save Data");

                object = new ContactPerson();
                object.setPrefix(cfPrefix.getSelectedItem().toString());
                object.setFirstName(tfFName.getText().trim());
                object.setMidName(tfMName.getText().trim());
                object.setLastName(tfLName.getText().trim());
                object.setPhone(tfPhone.getText().trim());

                final SwingWorker<Boolean, Void> td = new SwingWorker<Boolean, Void>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        boolean saved = false;
                        try {
                            hSession = HibernateUtil.getSessionFactory().openSession();
                            hSession.getTransaction().begin();

                            if (state == ActionState.CREATE) {
                                hSession.save(object);
                                saved = true;
                            } else if (state == ActionState.UPDATE) {
                                hSession.update(object);
                                saved = true;
                            }

                            if (saved == true) {
                                hSession.getTransaction().commit();
                                saved = true;
                            } else {
                                hSession.getTransaction().rollback();
                                saved = false;
                            }

                        } catch (Exception ex) {
                            hSession.getTransaction().rollback();
                            saved = false;
                            Logger.getLogger(ContactPersonActionForm.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            hSession.close();
                        }
                        return saved;
                    }
                };
                td.addPropertyChangeListener(new PropertyChangeListener() {

                    /**
                     *
                     * @param evt
                     */
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if ("state".equals(evt.getPropertyName())
                                && SwingWorker.StateValue.DONE == evt.getNewValue()) {
                            try {
                                if (td.get() == true) {
                                    dialog.setVisible(false);
                                    dialog.dispose();
                                    JOptionPane.showMessageDialog(null, "Saving new contact successfull",
                                            "New Contact Person", JOptionPane.INFORMATION_MESSAGE);
                                }
                                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
                            } catch (InterruptedException | ExecutionException ex) {
                                Logger.getLogger(ContactPersonActionForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                td.execute();
                dialog.setVisible(true);
            }
        }
    }
}
