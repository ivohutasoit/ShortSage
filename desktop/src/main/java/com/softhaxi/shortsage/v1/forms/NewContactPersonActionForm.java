package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.enums.ActionState;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Referencese 
 * <ul>
 * <li><a href="https://designgridlayout.java.net/">Design Grid Layout</a></li>
 * </ul>
 * @author Ivo Hutasoit
 */
public class NewContactPersonActionForm extends CActionForm<ContactPerson> {
    
    private JComboBox<String> cmPrefix;
    private JTextField txFName, txMName, txLName, txEmail, txCompany;
    private JTextField txAddr1, txAddr2, txAddr3, txCity, txZip;
    private JTextField txPhone, txHome, txWork, txCustom;
    private JTextArea txDescription;
    private JCheckBox chAddress, chPreference;
    /**
     *
     */
    public NewContactPersonActionForm() {
        super();
    }

    /**
     *
     * @param person
     */
    public NewContactPersonActionForm(ContactPerson person) {
        super(person);
    }

    /**
     *
     * @param state
     * @param person
     */
    public NewContactPersonActionForm(ActionState state, ContactPerson person) {
        super(state, person);
    }
    
    @Override
    public void initComponents() {
        super.initComponents();
        setBorder(new EmptyBorder(0, 4, 4, 0));
        setPreferredSize(new Dimension(500, 600));
        
        
        JLabel lbPrefix = new JLabel("Prefix :");
        cbPrefix = new JComboBox<String>(new String[] {
            "Mr.",
            "Mrs.",
            "Ms."
        });
        txPrefix = new JTextField();
        
        JPanel pForm = new JPanel();
        
        
        add(pForm, BorderLayout.CENTER);
    }
    
    @Override
    public void initData() {
        
    }
    
    private class RowShowHideAction implements ItemListener {
        private final RowGroup group;
        
        public ShowHideAction(RowGroup group) {
                this.group = group;
        }
        
        @Override public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                        group.show();
                } else {
                        group.hide();
                }
        }
    }
}
