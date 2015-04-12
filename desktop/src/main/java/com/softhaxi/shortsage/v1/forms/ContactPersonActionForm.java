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
 */
public class ContactPersonActionForm extends HActionForm<ContactPerson> {

    private JComboBox cmPrefix, cmCountry, cmStatus, cmHandler;
    private JTextField fFName, fMName, fLName, fEmail, fCompany;
    private JTextField fAddr1, fAddr2, fAddr3, fCity, fZip;
    private JTextField fPhone, fHome, fWork, fCustom;
    private JTextArea fRemark;
    private JCheckBox chAddress, chPreference;

    /**
     *
     */
    public ContactPersonActionForm() {
        super();
    }

    /**
     *
     * @param person
     */
    public ContactPersonActionForm(ContactPerson person) {
        super(person);
    }

    /**
     *
     * @param state
     * @param person
     */
    public ContactPersonActionForm(ActionState state, ContactPerson person) {
        super(state, person);
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBorder(new EmptyBorder(0, 2, 2, 0));
        setPreferredSize(new Dimension(500, 600));

        cmPrefix = new JComboBox(new String[]{
            "Mr.",
            "Mrs.",
            "Ms."
        });

        cmCountry = new JComboBox(new String[]{
            "IDN"
        });

        cmStatus = new JComboBox(new String[]{
            "Active",
            "Deactive"
        });
        cmStatus.setEnabled(false);

        cmHandler = new JComboBox(new String[]{
            "No Action",
            "Activated",
            "Deactivated",
            "Deleted"
        });

        fFName = new JTextField();
        fMName = new JTextField();
        fLName = new JTextField();
        fEmail = new JTextField();
        fCompany = new JTextField();
        fAddr1 = new JTextField();
        fAddr2 = new JTextField();
        fAddr3 = new JTextField();
        fCity = new JTextField();
        fZip = new JTextField();
        fPhone = new JTextField();
        fRemark = new JTextArea();
        fRemark.setRows(3);
        fRemark.setLineWrap(true);

        JPanel pForm = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel("Prefix :")).add(cmPrefix).empty(5);
        layout.row().grid(new JLabel("Name :")).add(fFName).add(fMName).add(fLName);
        layout.row().grid(new JLabel("Phone Number :")).add(cmCountry).add(fPhone, 3).empty(2);
        layout.row().grid(new JLabel("Email :")).add(fEmail).empty();
        layout.row().grid(new JLabel("Company :")).add(fCompany).empty();
        layout.emptyRow();
        layout.row().grid(new JLabel("Address :")).add(fAddr1);
        layout.row().grid().add(fAddr2);
        layout.row().grid().add(fAddr3);
        layout.row().grid(new JLabel("City :")).add(fCity).empty();
        layout.row().grid(new JLabel("Zip Code :")).add(fZip).empty(5);
        //layout.emptyRow();
        //layout.row().grid(new JLabel("Description :")).add(new JScrollPane(fRemark));
        layout.row().grid(new JLabel("Status :")).add(cmStatus).empty(2);
        layout.row().grid(new JLabel("Handler :")).add(cmHandler).empty(2);

        add(pForm, BorderLayout.CENTER);
        
        bSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     *
     */
    @Override
    public void initState() {
//        CDialog dialog = null;
//
//        if (getRootPane().getParent() instanceof CDialog) {
//            dialog = (CDialog) getRootPane().getParent();
//        }
//
//        if (dialog != null) {
//            bSave.setVisible(false);
//            bSaveNew.setVisible(false);
//            bCancel.setVisible(false);
//        } else {
//            if (state == ActionState.CREATE || state == ActionState.EDIT) {
//                
//            }
//        }
        
        if(state == ActionState.CREATE) {
            cmHandler.setEnabled(false);
        }
        
        bNew.setVisible(false);
        bEdit.setVisible(false);
        bDelete.setVisible(false);
    }

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
