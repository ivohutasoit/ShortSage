package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.enums.ActionState;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GatewayActionForm extends CActionForm<Gateway>
        implements ActionListener {

    private static String[] STATUS_LIST = {
        "CREATED",
        "ATTACHED",
        "DEATTACHED"
    };

    private static String[] HANDLE_LIST = {
        "CREATED",
        "ATTACH",
        "DEATTACH",
        "DELETE"
    };

    private JPanel pDetail;
    private JTextField tName;
    private JTextField tPort;
    private JTextField tRate;
    private JTextField tManufactur;
    private JTextField tModel;
    private JComboBox<String> cStatus;
    private JComboBox<String> cHandler;
    private JTextArea tRemark;

    public GatewayActionForm() {
        super();
    }

    private GatewayActionForm(Gateway gateway) {
        super(gateway);
    }

    private GatewayActionForm(ActionState state, Gateway gateway) {
        super(state, gateway);
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setPreferredSize(new Dimension(400, 300));
        
        bSave.addActionListener(this);
        bSaveNew.addActionListener(this);
        bCancel.addActionListener(this);

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
        
        JLabel lHandler = new JLabel("Handler:");
        cHandler = new JComboBox(HANDLE_LIST);

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
                        .addComponent(lHandler)
                        .addComponent(lRemark))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tName)
                        .addComponent(tPort)
                        .addComponent(tRate)
                        .addComponent(tManufactur)
                        .addComponent(tModel)
                        .addComponent(cStatus)
                        .addComponent(cHandler)
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
                        .addComponent(lHandler)
                        .addComponent(cHandler))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lRemark)
                        .addComponent(tRemark))
        );
        add(pForm, BorderLayout.CENTER);
    }

    @Override
    public void initState() {
        super.initState();
        if (state == ActionState.CREATE) {
            cStatus.setEnabled(false);
            cHandler.setEnabled(false);
        }
    }

    @Override
    public void initData() {
        if(object != null) {
            tName.setText(object.getName());
        }
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
                   } else if(bb == bSaveNew) {
                        
                   } 
                   
                   dialog.dispose();
                   dialog.setVisible(false);
                } 
        }
    }
}
