package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.desktop.HMenuBar;
import com.softhaxi.shortsage.v1.desktop.HWaitDialog;
import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.page.DashboardPage;
import com.softhaxi.shortsage.v1.util.AppUtil;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingWorker;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.hibernate.Session;
import org.smslib.GatewayException;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class GatewayActionForm extends JPanel
        implements ActionListener {

    private static final ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private Gateway object;
    private ActionState state;

    /**
     * Tool bar items
     */
    private JButton bNew, bEdit, bDelete;
    private JButton bSave, bSaveNew, bCancel;
    private JButton bTest;

    /**
     * Fields
     */
    private JTextField tfName, tfPort, tfManufacture, tfModel, tfSerial, tfISMI, tfSignal, tfBattery;
    private JTextField tfProvider, tfCCenter, tfCBalance;
    private JComboBox cfRate, cfStatus, cfHandler;

    /**
     * Testing connection
     */
    private SerialModemGateway modem;

    /**
     * Database connection
     */
    private Session hSession;

    /**
     *
     */
    public GatewayActionForm() {
        this(null, ActionState.CREATE);
    }

    /**
     *
     * @param object
     * @param state
     */
    public GatewayActionForm(Gateway object, ActionState state) {
        this.object = object;
        this.state = state;

        initComponents();
        initListeners();
        initState();
    }

    // <editor-fold defaultstate="collapsed" desc="Region Inititalization">  
    /**
     * Initialize components of the panel
     */
    private void initComponents() {
        setPreferredSize(new Dimension(450, 350));
        setLayout(new BorderLayout());

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

        bTest = new JButton(RES_GLOBAL.getString("label.test.gateway"),
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_modem_connect_16.png")));
        pToolbar.add(bTest);
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
        JPanel pForm = new JPanel();

        tfName = new JTextField();
        tfPort = new JTextField();
        tfManufacture = new JTextField();
        tfManufacture.setEnabled(false);
        tfModel = new JTextField();
        tfModel.setEnabled(false);
        tfModel.setEnabled(false);
        tfSerial = new JTextField();
        tfSerial.setEnabled(false);
        tfISMI = new JTextField();
        tfISMI.setEnabled(false);
        tfSignal = new JTextField();
        tfSignal.setEnabled(false);
        tfBattery = new JTextField();
        tfBattery.setEnabled(false);
        tfProvider = new JTextField();
        tfCCenter = new JTextField();
        tfCBalance = new JTextField();
        cfRate = new JComboBox();
        cfRate.addItem("9600");
        cfRate.addItem("115200");
//        cfRate.setEnabled(false);
        cfStatus = new JComboBox();
        cfStatus.setEnabled(false);
        cfHandler = new JComboBox();

        DesignGridLayout layout = new DesignGridLayout(pForm);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.name") + " :"))
                .add(tfName)
                .grid(new JLabel(RES_GLOBAL.getString("label.gateway.port"))).add(tfPort);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.manufacture") + " :"))
                .add(tfManufacture);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.model") + " :"))
                .add(tfModel);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gataway.rate") + " :"))
                .add(cfRate).empty(3);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.serial") + " :"))
                .add(tfSerial, 2).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.ismi") + " :"))
                .add(tfISMI, 2).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.signal") + " :"))
                .add(tfSignal)
                .grid(new JLabel(RES_GLOBAL.getString("label.gateway.battery") + " :"))
                .add(tfBattery);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.provider") + " :"))
                .add(tfProvider, 2).empty();
//        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.ccenter") + " :"))
//                .add(tfCCenter, 2).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.cbalance") + " :"))
                .add(tfCBalance).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.status") + " :"))
                .add(cfStatus).empty(3);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.handler") + " :"))
                .add(cfHandler).empty(3);

        add(pForm, BorderLayout.CENTER);
    }

    /**
     * Initialize listeners for all components of frame
     */
    private void initListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {

            }
        });

        bTest.addActionListener(this);
        bSave.addActionListener(this);
    }

    /**
     *
     */
    private void initState() {
        cfStatus.removeAllItems();
        cfHandler.removeAllItems();
        if (state == ActionState.CREATE) {
            cfStatus.addItem("Create");
            cfHandler.addItem("Created");
            cfHandler.setEnabled(false);

//            bTest.setVisible(false);
            bSaveNew.setVisible(false);
            bNew.setVisible(false);
            bEdit.setVisible(false);
            bDelete.setVisible(false);

            bSave.setEnabled(false);
        }
    }

    /**
     *
     */
    private void initData() {

    }

    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="private Methods"> 
    private boolean isDataValid(int index) {
        if (index == 1) {
            if (tfProvider.getText().equals("")) {
                tfProvider.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                return false;
            }

            if (tfCBalance.getText().equals("")) {
                tfCBalance.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                return false;
            }
        }
        if (tfName.getText().equals("")) {
            tfName.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        } else {
            tfName.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        }

        if (tfPort.getText().equals("")) {
            tfPort.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            return false;
        } else {
            tfName.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        }

        return true;
    }

    /**
     *
     */
    private void connect() {
        final HWaitDialog dialog = new HWaitDialog("Connect Gateway");
        firePropertyChange(PropertyChangeField.CONNECTING.toString(), false, true);
        modem = new SerialModemGateway(tfName.getText().trim(),
                tfPort.getText().trim(),
                Integer.parseInt(cfRate.getSelectedItem().toString()),
                tfManufacture.getText().trim(),
                tfModel.getText().trim());

        SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    if (Service.getInstance().getServiceStatus() == Service.ServiceStatus.STARTED
                            || Service.getInstance().getServiceStatus() == Service.ServiceStatus.STARTING) {
                        Service.getInstance().stopService();
                    }

                    if (Service.getInstance().getServiceStatus() == Service.ServiceStatus.STOPPED) {
                        Service.getInstance().addGateway(modem);
                        Service.getInstance().startService();
                    }
                    return true;
                } catch (SMSLibException | IOException | InterruptedException ex) {
                    Logger.getLogger(GatewayActionForm.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            }

            @Override
            protected void done() {
                try {
                    if (!isCancelled()) {

                        if (get() == true) {
                            tfManufacture.setText(modem.getManufacturer());
                            tfModel.setText(modem.getModel());
                            tfSerial.setText(modem.getSerialNo());
                            tfISMI.setText(modem.getImsi());
                            tfSignal.setText(String.valueOf(modem.getSignalLevel()));
                            tfBattery.setText(String.valueOf(modem.getBatteryLevel()));
                            tfCCenter.setText(modem.getSmscNumber());

                            Service.getInstance().stopService();
                            Service.getInstance().removeGateway(modem);
                            bSave.setEnabled(true);
                        }
                        
                    }
                } catch (TimeoutException | GatewayException | IOException | InterruptedException | ExecutionException ex) {
                    Logger.getLogger(GatewayActionForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SMSLibException ex) {
                    Logger.getLogger(GatewayActionForm.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    dialog.setVisible(false);
                    dialog.dispose();
                    firePropertyChange(PropertyChangeField.CONNECTING.toString(), true, false);
                }
            }
        };
        t1.addPropertyChangeListener(new PropertyChangeListener() {
            /**
             *
             * @param evt
             */
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(PropertyChangeField.CONNECTING.toString())) {
                    dialog.setVisible(false);
                    dialog.dispose();
                    firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
                }
            }
        });
        t1.execute();
        dialog.setVisible(true);
    }

    /**
     *
     */
    private void save() {
        firePropertyChange(PropertyChangeField.SAVING.toString(), false, true);

        object = new Gateway();
        object.setId(UUID.randomUUID().toString());
        object.setName(tfName.getText().trim());
        object.setPort(tfPort.getText().trim());
        object.setManufacture(tfManufacture.getText().trim());
        object.setModel(tfModel.getText().trim());
        object.setBaudRate(Integer.parseInt(cfRate.getSelectedItem().toString()));
        object.setIsmi(tfISMI.getText().trim());
        object.setSerial(tfSerial.getText().trim());
        object.setProvider(tfProvider.getText().trim());
        object.setNumberBalance(tfCBalance.getText().trim());
        object.setCreatedBy("SYSTEM");
        object.setCreatedDate(new Date());
        object.setModifiedBy(object.getCreatedBy());
        object.setModifiedDate(object.getCreatedDate());

        SwingWorker<Boolean, Void> t1 = new SwingWorker<Boolean, Void>() {

            /**
             *
             * @return @throws Exception
             */
            @Override
            protected Boolean doInBackground() throws Exception {
                boolean saved = false;
                try {
                    hSession = HibernateUtil.getSessionFactory().openSession();
                    hSession.getTransaction().begin();
                    hSession.save(object);
                    hSession.getTransaction().commit();
                    hSession.close();
                    saved = true;
                } catch (Exception ex) {
                    Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                return saved;
            }

            @Override
            protected void done() {
                if (!isCancelled()) {
                    try {
                        if (get() == true) {

                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(GatewayActionForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                firePropertyChange(PropertyChangeField.SAVING.toString(), true, false);
            }
        };
        t1.addPropertyChangeListener(new PropertyChangeListener() {
            /**
             *
             * @param evt
             */
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(PropertyChangeField.SAVING.toString())) {
                    firePropertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
                    if (((boolean) evt.getNewValue()) == false) {
                        try {
                            AppUtil.restart(new Runnable() {

                                @Override
                                public void run() {
                                    JOptionPane.showMessageDialog(null, "Saving new modem successfull and \napplication will be restart to take effect", "New Gateway", JOptionPane.INFORMATION_MESSAGE);
                                }
                            });
                        } catch (IOException ex) {
                            Logger.getLogger(HMenuBar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        t1.execute();

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ActionListener Implementation">
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            if (source == bTest) {
                if (!isDataValid(0)) {
                    return;
                }
                connect();

            } else if (source == bSave) {
                if (!isDataValid(1)) {
                    return;
                }
                save();
            }
        }
    }
    // </editor-fold>
}
