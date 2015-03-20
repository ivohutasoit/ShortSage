package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.hibernate.Session;

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
    
    private static Integer[] BAUD_RATES = {
        115200
    };

    private JPanel pDetail;
    private JTextField tName;
    private JTextField tPort;
    private JComboBox<Integer> cRate;
    private JTextField tManufacture;
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
        cRate = new JComboBox<>(BAUD_RATES);

        JLabel lManufacture = new JLabel("Manufacture:");
        tManufacture = new JTextField();

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
                        .addComponent(lManufacture)
                        .addComponent(lModel)
                        .addComponent(lStatus)
                        .addComponent(lHandler)
                        .addComponent(lRemark))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tName)
                        .addComponent(tPort)
                        .addComponent(cRate)
                        .addComponent(tManufacture)
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
                        .addComponent(cRate))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lManufacture)
                        .addComponent(tManufacture))
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
                   if(bb == bSave || bb == bSaveNew) {
                        save();
                   } 
                   
                   dialog.dispose();
                   dialog.setVisible(false);
                } 
        }
    }
    
    private void save() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        session.beginTransaction();
        Gateway gateway = new Gateway();
        gateway.setName(tName.getText().trim());
        gateway.setPort(tPort.getText().trim());
        gateway.setBaudRate(Integer.parseInt(cRate.getSelectedItem().toString().trim()));
        gateway.setStatus(1);
        gateway.setManufacture(tManufacture.getText().trim());
        gateway.setModel(tModel.getText().trim());
        gateway.setCreatedBy("SYSTEM");
        gateway.setCreatedOn(new Date());
        session.save(gateway);
        session.getTransaction().commit();
        session.close();
    }
}
