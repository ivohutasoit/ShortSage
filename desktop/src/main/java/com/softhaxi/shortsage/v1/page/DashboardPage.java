package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.panels.BalancePanel;
import com.softhaxi.shortsage.v1.panels.MessagesPanel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

/**
 *
 * @author Hutasoit
 */
public class DashboardPage extends JPanel {

    private JPanel pModem;
    private MessagesPanel pMessage;
    private BalancePanel pBalance;
    private JPanel pChart;

    public DashboardPage() {
        initComponents();
        setListener();
    }

    private void initComponents() {
//        setLayout(new BorderLayout());
//        setBorder(new EmptyBorder(4, 4, 4, 4));
//        JPanel p1 = new JPanel(new GridLayout(1, 5, 5, 0));
//        p1.setPreferredSize(new Dimension(getPreferredSize().width,160));
//        add(p1, BorderLayout.NORTH);
//        
//        pModem = new JPanel();
//        pModem.setBackground(Color.white);
//        SpringLayout layout = new SpringLayout();
//        pModem.setLayout(layout);
//        
//        JLabel lManufacture = new JLabel("Manufacture: ", JLabel.TRAILING);
//        JTextField tManufacture = new JTextField("Manfacture Name",JTextField.TRAILING);
//        tManufacture.setEditable(false);
//        lManufacture.setLabelFor(tManufacture);
//        pModem.add(lManufacture);
//        pModem.add(tManufacture);
//        
//        JLabel lModel = new JLabel("Model: ", JLabel.TRAILING);
//        JTextField tModel = new JTextField("Model Modem",JTextField.TRAILING);
//        tModel.setEditable(false);
//        lModel.setLabelFor(tModel);
//        pModem.add(lModel);
//        pModem.add(tModel);
//        
//        JLabel lSerial = new JLabel("Serial Number: ", JLabel.TRAILING);
//        JTextField tSerial = new JTextField("Serial Modem",JTextField.TRAILING);
//        tSerial.setEditable(false);
//        lSerial.setLabelFor(tSerial);
//        pModem.add(lSerial);
//        pModem.add(tSerial);
//        
//        JLabel lISMI = new JLabel("SIM ISMI: ", JLabel.TRAILING);
//        JTextField tISMI = new JTextField("ISMI Modem",JTextField.TRAILING);
//        tISMI.setEditable(false);
//        lISMI.setLabelFor(tISMI);
//        pModem.add(lISMI);
//        pModem.add(tISMI);
//        
//        JLabel lSignal = new JLabel("Signal Level: ", JLabel.TRAILING);
//        JTextField tSignal = new JTextField("Signal Level",JTextField.TRAILING);
//        tSignal.setEditable(false);
//        lSignal.setLabelFor(tSignal);
//        pModem.add(lSignal);
//        pModem.add(tSignal);
//        
//        JLabel lBattery = new JLabel("Battery Level: ", JLabel.TRAILING);
//        JTextField tBattery = new JTextField("Battery Level",JTextField.TRAILING);
//        tBattery.setEditable(false);
//        lBattery.setLabelFor(tBattery);
//        pModem.add(lBattery);
//        pModem.add(tBattery);
//        
//        SpringUtilities.makeCompactGrid(pModem,
//                                6, 2, //rows, cols
//                                6, 6,        //initX, initY
//                                6, 6);       //xPad, yPad
//        
//        p1.add(pModem);
//        
//        pMessage = new MessagesPanel();
//        p1.add(pMessage);
//        
//        pBalance = new BalancePanel();
//        p1.add(pBalance);
//        
//        JPanel p2 = new JPanel(new BorderLayout());
//        p2.setBorder(new EmptyBorder(4, 0, 0, 1));
//        pChart = new JPanel();
//        pChart.setBackground(Color.WHITE);
//        p2.add(pChart, BorderLayout.CENTER);
//        add(p2, BorderLayout.CENTER);
    }

    private void newInitComponents() {

    }

    private void initData() {

    }

    private void setListener() {
        addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorAdded(AncestorEvent e) {
//                System.out.println(getRootPane().getParent().toString());
//                System.out.println(getParent().toString());
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
    }
}
