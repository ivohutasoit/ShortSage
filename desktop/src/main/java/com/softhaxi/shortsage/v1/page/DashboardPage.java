package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.dto.Gateway;
import com.softhaxi.shortsage.v1.enums.PropertyChangeField;
import com.softhaxi.shortsage.v1.util.HibernateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.LabelAlignment;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class DashboardPage extends JPanel {

    private final static ResourceBundle RES_GLOBAL = ResourceBundle.getBundle("global");

    private JPanel pNorth;

    private JPanel pGateway;
    private JPanel pModem;
    private JPanel pChart;

    /**
     * Gateway fields
     */
    private JComboBox cfName;
    private JTextField tfPort, tfManufacture, tfModel, tfSerial, tfISMI, tfSignal, tfBattery;
    private JTextField tfProvider, tfCCenter, tfCBalance;

    /**
     * Modem labels and fields
     */
    private JLabel lfInbox, lfOutbox, lfBalance;
    private JButton baBalance;

    /**
     * Database access
     */
    private Session hSession;
    private List<Gateway> gData;

    /**
     * Main constructor without parameters
     */
    public DashboardPage() {
        setVisible(false);
        initComponents();
        initListeners();
    }

    // <editor-fold defaultstate="collapsed" desc="Region Inititalization">  
    /**
     * Initialize components of the panel
     */
    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.white);

        pNorth = new JPanel(new GridLayout(1, 3, 5, 0));
        add(pNorth, BorderLayout.NORTH);

        initGatewayPanel();
        initModemPanel();
    }

    /**
     *
     */
    private void initGatewayPanel() {
        pGateway = new JPanel();
        pGateway.setBackground(new Color(189, 195, 199));
        pGateway.setForeground(Color.white);

        cfName = new JComboBox();
        cfName.addItem("-- Select Gateway --");
        tfPort = new JTextField();
        tfPort.setEnabled(false);
        tfManufacture = new JTextField();
        tfManufacture.setEnabled(false);
        tfModel = new JTextField();
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
        tfProvider.setEnabled(false);
        tfCCenter = new JTextField();
        tfCCenter.setEnabled(false);
        tfCBalance = new JTextField();
        tfCBalance.setEnabled(false);

        DesignGridLayout layout = new DesignGridLayout(pGateway);
        layout.labelAlignment(LabelAlignment.RIGHT);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.name") + " :"))
                .add(cfName, 2).add(tfPort);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.manufacture") + " :"))
                .add(tfManufacture);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.model") + " :"))
                .add(tfModel);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.serial") + " :"))
                .add(tfSerial, 2).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.ismi") + " :"))
                .add(tfISMI, 2).empty();
//        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.signal") + " :"))
//                .add(tfSignal)
//                .grid(new JLabel(RES_GLOBAL.getString("label.gateway.battery") + " :"))
//                .add(tfBattery);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.provider") + " :"))
                .add(tfProvider, 2).empty();
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.ccenter") + " :"))
                .add(tfCCenter, 2).empty(2);
        layout.row().grid(new JLabel(RES_GLOBAL.getString("label.gateway.cbalance") + " :"))
                .add(tfCBalance).empty(3);

        pNorth.add(pGateway);
    }

    /**
     *
     */
    private void initModemPanel() {
        pModem = new JPanel(new BorderLayout(5, 5));
        pModem.setBackground(new Color(52, 152, 219));

        JToolBar pHeader = new JToolBar();
        pHeader.setFloatable(false);
        pHeader.setBackground(pModem.getBackground());
        pHeader.setBorder(new EmptyBorder(0, 5, 0, 5));
        JLabel lfTitle = new JLabel(RES_GLOBAL.getString("label.modem.information"));
        lfTitle.setFont(new Font("Segoe UI Light", 0, 16));
        lfTitle.setForeground(Color.white);
        pHeader.add(lfTitle);
        pHeader.add(Box.createHorizontalGlue());

        JButton baRefresh = new JButton(
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh_white_16.png")));
        baRefresh.setBackground(pModem.getBackground());
        pHeader.add(baRefresh);

        pModem.add(pHeader, BorderLayout.NORTH);

        JPanel pDetail = new JPanel(new GridLayout(1, 3, 4, 0));
        pDetail.setBackground(pModem.getBackground());
        pDetail.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel pD1 = new JPanel(new BorderLayout(0, 5));
        pD1.setBackground(pModem.getBackground());
        JLabel lfInboxInfo = new JLabel(RES_GLOBAL.getString("label.modem.inbox"));
        lfInboxInfo.setFont(lfTitle.getFont());
        lfInboxInfo.setForeground(lfTitle.getForeground());
        lfInboxInfo.setHorizontalAlignment(SwingConstants.CENTER);
        pD1.add(lfInboxInfo, BorderLayout.NORTH);

        lfInbox = new JLabel("2");
        lfInbox.setFont(new Font("Segoe UI Light", 0, 36));
        lfInbox.setForeground(lfTitle.getForeground());
        lfInbox.setHorizontalAlignment(SwingConstants.CENTER);
        pD1.add(lfInbox, BorderLayout.CENTER);

        pDetail.add(pD1);

        JPanel pD2 = new JPanel(new BorderLayout(0, 5));
        pD2.setBackground(pModem.getBackground());
        JLabel lfOutboxInfo = new JLabel(RES_GLOBAL.getString("label.modem.outbox"));
        lfOutboxInfo.setFont(lfTitle.getFont());
        lfOutboxInfo.setForeground(lfTitle.getForeground());
        lfOutboxInfo.setHorizontalAlignment(SwingConstants.CENTER);
        pD2.add(lfOutboxInfo, BorderLayout.NORTH);

        lfOutbox = new JLabel("10");
        lfOutbox.setFont(new Font("Segoe UI Light", 0, 36));
        lfOutbox.setForeground(lfTitle.getForeground());
        lfOutbox.setHorizontalAlignment(SwingConstants.CENTER);
        pD2.add(lfOutbox, BorderLayout.CENTER);

        pDetail.add(pD2);

        JPanel pD3 = new JPanel(new BorderLayout(0, 5));
        pD3.setBackground(pModem.getBackground());
        JLabel lfBalanceInfo = new JLabel(RES_GLOBAL.getString("label.modem.balance"));
        lfBalanceInfo.setFont(lfTitle.getFont());
        lfBalanceInfo.setForeground(lfTitle.getForeground());
        lfBalanceInfo.setHorizontalAlignment(SwingConstants.CENTER);
        pD3.add(lfBalanceInfo, BorderLayout.NORTH);

        lfBalance = new JLabel("100.000");
        lfBalance.setFont(new Font("Segoe UI Light", 0, 36));
        lfBalance.setForeground(lfTitle.getForeground());
        lfBalance.setHorizontalAlignment(SwingConstants.CENTER);
        pD3.add(lfBalance, BorderLayout.CENTER);

        baBalance = new JButton(RES_GLOBAL.getString("label.check.balance"));
        pD3.add(baBalance, BorderLayout.SOUTH);

        pDetail.add(pD3);

        pModem.add(pDetail, BorderLayout.CENTER);

        pNorth.add(pModem);
    }

    /**
     * Initialize listeners for all components of frame
     */
    private void initListeners() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                firePropertyChange(PropertyChangeField.LOADING.toString(), false, true);
                LoadDataTask t1 = new LoadDataTask();
                t1.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals(PropertyChangeField.LOADING.toString())) {
                            boolean value = (boolean) evt.getNewValue();
                            if (value == false) {
                                firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
                            }
                        }
                    }
                });
                t1.execute();
            }
        });

        cfName.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (cfName.getSelectedIndex() != 0) {
                    Gateway g = gData.get(cfName.getSelectedIndex() - 1);
                    tfPort.setText(g.getPort());
                    tfManufacture.setText(g.getManufacture());
                    tfModel.setText(g.getModel());
                    tfProvider.setText(g.getProvider());
                    tfCCenter.setText(g.getMessageCenter());
                    tfCBalance.setText(g.getCheckBalance());
                    tfSerial.setText(g.getSerial());
                    tfISMI.setText(g.getIsmi());
                    return;
                }
                
            }
        });
    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="Task Classes">  
    class LoadDataTask extends SwingWorker<Boolean, Void> {

        @Override
        protected Boolean doInBackground() throws Exception {
            try {
                hSession = HibernateUtil.getSessionFactory().openSession();
                hSession.getTransaction().begin();
                Query query = hSession.createQuery("from Gateway");
                gData = query.list();
                hSession.getTransaction().commit();
                hSession.close();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(DashboardPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

        @Override
        protected void done() {
            if (!isCancelled()) {
                for (Gateway gateway : gData) {
                    cfName.addItem(gateway.getName());
                }
            }
            firePropertyChange(PropertyChangeField.LOADING.toString(), true, false);
        }
    }
    // </editor-fold> 
}
