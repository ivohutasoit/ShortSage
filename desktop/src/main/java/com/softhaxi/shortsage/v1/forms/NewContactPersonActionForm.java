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
    
    private JComboBox<String> cbPrefix;
    private JTextField txPrefix;

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
        GroupLayout layout = new GroupLayout(pForm);
        pForm.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPrefix)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPrefix)
                    .addComponent(cbPrefix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        add(pForm, BorderLayout.CENTER);
    }
    
    @Override
    public void initData() {
        
    }
}
