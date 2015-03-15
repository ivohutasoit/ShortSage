package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.model.ContactPerson;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ActionContactPersonForm extends JPanel {
    
    private ActionState state;
    private ContactPerson object;
    
    
    public ActionContactPersonForm() {
        this(ActionState.CREATE, null);  
    }
    
    public ActionContactPersonForm(ActionState state, ContactPerson object) {
        this.state = state;
        this.object = object;
        
        initComponents();
        initState();
        initData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        JToolBar ftBar = new JToolBar();
        ftBar.add(new JButton("New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png"))));
        ftBar.add(new JButton("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png"))));
        
        add(ftBar, BorderLayout.NORTH);
        
        JPanel pForm = new JPanel();
        GroupLayout layout = new GroupLayout(pForm);
        pForm.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        JLabel lFirst = new JLabel("First Label: ");
        JTextField tFirst = new JTextField(16);
        
        JLabel lSecond = new JLabel("Second Code: ");
        JTextField tSecond = new JTextField(16);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(lFirst)
                .addComponent(lSecond))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(tFirst)
                .addComponent(tSecond))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lFirst)
                .addComponent(tFirst))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lSecond)
                .addComponent(tSecond))
        );
        add(pForm, BorderLayout.CENTER);
    }
    
    private void initState() {
    
    }
    
    private void initData() {
      
    }
}
