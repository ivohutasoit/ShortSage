package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.pages.GatewaySetupPage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ActionGatewaySetupForm extends JPanel
        implements ActionListener {

    private static String[] STATUS_LIST = {
        "CREATED",
        "ATTACHED",
        "DEATTACHED"
    };

    private ActionState state;
    private Gateway object;

    private JButton bNew;
    private JButton bEdit;
    private JButton bDelete;
    private JButton bSave;
    private JButton bSaveNew;
    private JButton bCancel;

    private JTextField tName;
    private JTextField tPort;
    private JTextField tRate;
    private JTextField tManufactur;
    private JTextField tModel;
    private JComboBox<String> cStatus;
    private JComboBox<String> cHandle;
    private JTextArea tRemark;

    public ActionGatewaySetupForm() {
        this(null, ActionState.CREATE, null);
    }
    
    public ActionGatewaySetupForm(Gateway object) {
        this(null, ActionState.SHOW, object);
    }
    
    public ActionGatewaySetupForm(GatewaySetupPage page) {
        this(page, ActionState.CREATE, null);
    }
    
    public ActionGatewaySetupForm(GatewaySetupPage page, Gateway object) {
        this(page, ActionState.SHOW, object);
    }

    public ActionGatewaySetupForm(GatewaySetupPage page, ActionState state, Gateway object) {
        this.state = state;
        this.object = object;
        
        initComponents();
        initState();
        initData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(2, 2, 2, 2));
        setPreferredSize(new Dimension(400, 300));

        JToolBar ftBar = new JToolBar();
        ftBar.setFloatable(false);
        ftBar.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));

        bNew = new JButton("New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png")));
        bNew.addActionListener(this);
        ftBar.add(bNew);

        bEdit = new JButton("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png")));
        ftBar.add(bEdit);

        bSave = new JButton("Save", new ImageIcon(getClass().getClassLoader().getResource("images/ic_save.png")));
        ftBar.add(bSave);

        bSaveNew = new JButton("Save and New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_save_as.png")));
        ftBar.add(bSaveNew);
        ftBar.add(new JToolBar.Separator());

        bDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png")));
        ftBar.add(bDelete);

        bCancel = new JButton("Cancel", new ImageIcon(getClass().getClassLoader().getResource("images/ic_cancel.png")));
        bCancel.addActionListener(this);
        ftBar.add(bCancel);

        add(ftBar, BorderLayout.NORTH);

        JPanel pForm = new JPanel();
        GroupLayout layout = new GroupLayout(pForm);
        pForm.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lName = new JLabel("Gateway Name:");
        tName = new JTextField();

        JLabel lPort = new JLabel("Port:");
        tPort = new JTextField();
        
        JLabel lRate = new JLabel("Baud Rate:");
        tRate = new JTextField();
        
        JLabel lManufactur = new JLabel("Manufacture:");
        tManufactur = new JTextField();
        
        JLabel lModel = new JLabel("Model:");
        tModel = new JTextField();
        
        JLabel lStatus = new JLabel("Status:");
        cStatus = new JComboBox(STATUS_LIST);
        
        JLabel lRemark = new JLabel("Remark:");
        tRemark = new JTextArea(5, 5);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lName)
                        .addComponent(lPort)
                        .addComponent(lRate)
                        .addComponent(lManufactur)
                        .addComponent(lModel)
                        .addComponent(lStatus)
                        .addComponent(lRemark))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tName)
                        .addComponent(tPort)
                        .addComponent(tRate)
                        .addComponent(tManufactur)
                        .addComponent(tModel)
                        .addComponent(cStatus)
                        .addComponent(tRemark))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lName)
                        .addComponent(tName))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lPort)
                        .addComponent(tPort))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lRate)
                        .addComponent(tRate))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lManufactur)
                        .addComponent(tManufactur))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lModel)
                        .addComponent(tModel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lStatus)
                        .addComponent(cStatus))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lRemark)
                        .addComponent(tRemark))
        );
        add(pForm, BorderLayout.CENTER);
    }

    private void initState() {
        if (state == ActionState.CREATE) {
            bNew.setVisible(false);
            bEdit.setVisible(false);
            bDelete.setVisible(false);
            cStatus.setEnabled(false);
        }
    }

    private void initData() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        JDialog dialog = null;
        if(getRootPane().getParent() instanceof JDialog) {
            dialog = (JDialog) getRootPane().getParent();
        }
        if(e.getSource() instanceof JButton) {
                JButton bb = (JButton) e.getSource();
                
                if(dialog != null) {
                   if(bb == bSave) {
                        if(!save()) {
                        }
                   } else if(bb == bSaveNew) {
                        if(!save()) {
                        }
                   } 
                   
                   dialog.dispose();
                   dialog.setVisible(false);
                } 
        }
    }
    
    private boolean save() {
        if(state == ActionState.CREATE) {
           return true;
        }
        return false;
    }
}
