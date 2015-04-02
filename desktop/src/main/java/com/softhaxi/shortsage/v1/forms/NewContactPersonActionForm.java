package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import com.softhaxi.shortsage.v1.enums.ActionState;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

/**
 * Referencese 
 * <ul>
 * <li><a href="https://designgridlayout.java.net/">Design Grid Layout</a></li>
 * </ul>
 * @author Ivo Hutasoit
 */
public class NewContactPersonActionForm extends CActionForm<ContactPerson> {
    
    private JComboBox cmPrefix;
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
        setBorder(new EmptyBorder(0, 2, 2, 0));
        setPreferredSize(new Dimension(500, 600));
        
        
        JLabel lbPrefix = new JLabel("Prefix :");
        cmPrefix = new JComboBox(new String[] {
            "Mr.",
            "Mrs.",
            "Ms."
        });
        
        txFName = new JTextField();
        txMName = new JTextField();
        txLName = new JTextField();
        txEmail = new JTextField();
        txCompany = new JTextField();
        txAddr1 = new JTextField();
        txAddr2 = new JTextField();
        txAddr3 = new JTextField();
        txCity = new JTextField();
        txZip = new JTextField();
        
        JPanel pForm = new JPanel();
        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel("Prefix :")).add(cmPrefix).empty(5);
        layout.row().grid(new JLabel("Name :")).add(txFName).add(txMName).add(txLName);
        layout.row().grid(new JLabel("Email :")).add(txEmail).empty();
        layout.row().grid(new JLabel("Company :")).add(txCompany).empty();
        layout.emptyRow();
        layout.row().grid(new JLabel("Address :")).add(txAddr1);
        layout.row().grid().add(txAddr2);
        layout.row().grid().add(txAddr3);
        layout.row().grid(new JLabel("City :")).add(txCity).empty();
        layout.row().grid(new JLabel("Zip Code :")).add(txZip).empty(5);
        layout.emptyRow();
        layout.emptyRow();
        
        add(pForm, BorderLayout.CENTER);
    }
    
    @Override
    public void initData() {
        
    }
    
    private class RowShowHideAction implements ItemListener {
        private RowGroup group;
        
        public RowShowHideAction(RowGroup group) {
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
