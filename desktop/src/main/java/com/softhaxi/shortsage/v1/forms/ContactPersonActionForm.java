package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.desktop.HActionForm;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.UUID;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import net.java.dev.designgridlayout.RowGroup;
import org.hibernate.Session;

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
public class ContactPersonActionForm extends JPanel {
    
    private final static ResourceBundle RES_GLOBAL = ResourceBundle.load("global");
    
    private ContactPerson object;
    private ActionState state;

    /**
     * Fields
     */
    private JComboBox cfPrefix, cfCountry, cfStatus, cfHandler;
    private JTextField tfFName, tfMName, tfLName, tfEmail, tfCompany;
    private JTextField tfAddr1, tfAddr2, tfAddr3, tfCity, tfZip;
    private JTextField tfPhone, tfHome, tfWork, tfCustom;
    private JTextArea tfRemark;
    private JCheckBox cfAddress, cfPreference;

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
        super(state, person);
        
        initComponents();
        initListeners();
        initState();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Region Initialization">
    public void initComponents() {
        setBorder(new EmptyBorder(0, 2, 2, 0));
        setPreferredSize(new Dimension(500, 600));
        
        initToolbar();
        initFormPanel();
    }
    
    private void initToolbar() {
        
    }
    
    private void initFormPanel() {
        cfPrefix = new JComboBox(new String[]{
            "Mr.",
            "Mrs.",
            "Ms."
        });

        cfCountry = new JComboBox(new String[]{
            "IDN"
        });

        cfStatus = new JComboBox();
        cfStatus.setEnabled(false);
        cfHandler = new JComboBox();
        tfFName = new JTextField();
        tfMName = new JTextField();
        tfLName = new JTextField();
        tfEmail = new JTextField();
        tfCompany = new JTextField();
        tfAddr1 = new JTextField();
        tfAddr2 = new JTextField();
        tfAddr3 = new JTextField();
        tfCity = new JTextField();
        tfZip = new JTextField();
        tfPhone = new JTextField();
        tfRemark = new JTextArea();
        tfRemark.setRows(3);
        tfRemark.setLineWrap(true);

        JPanel pForm = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.person.prefix") + " :")).add(cfPrefix).empty(5);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.person.name"))).add(tfFName)
            .add(tfMName)
            .add(tfLName);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.person.phone") + " :")).add(cfCountry)
            .add(tfPhone, 3)
            .empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.person.email") + " :")).add(tfEmail).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.person.company") + " :")).add(tfCompany).empty();
        layout.emptyRow();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.person.address") + " :")).add(tfAddr1);
        layout.row().grid().add(tfAddr2);
        layout.row().grid().add(tfAddr3);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.person.city") + " :")).add(tfCity).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.person.zip") + " :")).add(tfZip).empty(5);
        //layout.emptyRow();
        //layout.row().grid(new JLabel("Description :")).add(new JScrollPane(fRemark));
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status") + " :")).add(cfStatus).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler") + " :")).add(cfHandler).empty(2);

        add(pForm, BorderLayout.CENTER);
    }
    
    private void initListeners() {
        
    }

    /**
     *
     */
    public void initState() {
    }
    
    public void initData() {

    }
    
    // </editor-fold>

    public void save() {
        if (state == ActionState.CREATE) {
            object = new ContactPerson();
            object.setId(UUID.randomUUID().toString());
            object.setFirstName(fFName.getText().trim());
            object.setMidName(fMName.getText().trim());
            object.setLastName(fLName.getText().trim());
            object.setName(String.format("%s %s %s",
                    object.getFirstName(), object.getMidName(), object.getLastName()));
            object.setCountry(cmCountry.getSelectedItem().toString());
            object.setPhone(fPhone.getText().trim());
            object.setStatus(1);
            object.setCreatedBy("SYSTEM");
            object.setCreatedOn(new Date());
            object.setModifiedBy(object.getCreatedBy());
            object.setModifiedOn(object.getCreatedOn());
            object.setDeletedState(0);

            Session session = HibernateUtil.getSessionFactory().openSession();

            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
            session.close();
         
        }
    }

    private class RowShowHideAction implements ItemListener {

        private RowGroup group;

        public RowShowHideAction(RowGroup group) {
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
