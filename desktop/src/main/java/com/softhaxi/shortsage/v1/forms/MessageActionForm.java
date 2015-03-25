package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.dto.Message;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.modem.impl.OutboundNotification;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.hibernate.Session;
import org.jdesktop.swingx.JXDatePicker;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class MessageActionForm extends CActionForm<Message>
        implements ActionListener {

    private final static String[] STATUS_LIST = {
        "CREATED",
        "DRAFT",
        "SENT",
        "FAILED",
        "OBSOLETED",
        "UNREAD",
        "READ",
        "REPLIED"
    };

    private final static String[] HANDLE_LIST = {
        "CREATE",
        "SEND IMMEDIATELY",
        "RESEND",
        "UNSEND"
    };

    private JRadioButton rImmidiate;
    private JRadioButton rSchedule;
    private JTextField tContact;
    private JDateChooser cDate;
    private JDatePickerImpl tDate;
    private JTextArea tText;
    private JComboBox<String> cStatus;
    private JComboBox<String> cHandler;
    private DatePicker dDate;
    private JXDatePicker cxDate;

    private JButton bReply;
    private JDialog dLoading;

    private Session session;

    public MessageActionForm() {
        super();
    }

    public MessageActionForm(Message object) {
        super(object);
    }

    public MessageActionForm(ActionState state, Message message) {
        super(state, message);
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setPreferredSize(new Dimension(450, 300));

        bReply = new JButton("Reply");
        tBar.add(bReply, 4);

        JPanel pForm = new JPanel();
        GroupLayout lForm = new GroupLayout(pForm);
        pForm.setLayout(lForm);
        lForm.setAutoCreateGaps(true);
        lForm.setAutoCreateContainerGaps(true);

        rImmidiate = new JRadioButton("JIT Sending");
        rImmidiate.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                cxDate.setEnabled(!rImmidiate.isSelected());
            }
        });
        rSchedule = new JRadioButton("Schedule Sending");
        rSchedule.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                cxDate.setEnabled(rSchedule.isSelected());
            }
        });

        JLabel lSend = new JLabel("Send Type:");
        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(rImmidiate);
        bGroup.add(rSchedule);

        JLabel lContact = new JLabel("Contact:");
        tContact = new JTextField();

        JLabel lDate = new JLabel("Transaction Date:");
        cDate = new JDateChooser();
        tDate = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
        cxDate = new JXDatePicker();

        JLabel lStatus = new JLabel("Message Status:");
        cStatus = new JComboBox(STATUS_LIST);

        JLabel lHandler = new JLabel("Handler:");
        cHandler = new JComboBox(HANDLE_LIST);

        JLabel lText = new JLabel("Message:");
        tText = new JTextArea();
        tText.setFont(lText.getFont());
        JScrollPane pText = new JScrollPane(tText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lForm.setHorizontalGroup(lForm.createSequentialGroup()
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lSend)
                        .addComponent(lContact)
                        .addComponent(lDate)
                        .addComponent(lStatus)
                        .addComponent(lHandler)
                        .addComponent(lText))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(rImmidiate)
                        .addComponent(rSchedule)
                        .addComponent(tContact)
                        .addComponent(cxDate)
                        .addComponent(cStatus)
                        .addComponent(cHandler)
                        .addComponent(pText))
        );
        lForm.setVerticalGroup(lForm.createSequentialGroup()
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lSend)
                        .addComponent(rImmidiate))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(rSchedule))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lContact)
                        .addComponent(tContact))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lDate)
                        .addComponent(cxDate))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lStatus)
                        .addComponent(cStatus))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lHandler)
                        .addComponent(cHandler))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lText)
                        .addComponent(pText))
        );
        add(pForm, BorderLayout.CENTER);

        bSave.addActionListener(this);
    }

    @Override
    public void initState() {
        super.initState();
        if (object != null) {
            if (!object.getFolder().equalsIgnoreCase("INBOX")
                    && (state == ActionState.CREATE || state == ActionState.EDIT)) {
                bReply.setVisible(false);
            } else {
                bReply.setVisible(true);
            }
        } else {
            if (state == ActionState.CREATE) {
                cStatus.setEnabled(false);
                cHandler.setEnabled(false);
                rImmidiate.setSelected(true);
            }
            bReply.setVisible(false);
        }
    }

    @Override
    public void initData() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton bs = (JButton) e.getSource();
            if (bs == bSave || bs == bSaveNew) {
                dLoading = new JDialog(null, "Message", ModalityType.APPLICATION_MODAL);
                JProgressBar progressBar = new JProgressBar();
                progressBar.setIndeterminate(true);
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(progressBar, BorderLayout.CENTER);
                panel.add(new JLabel("Please wait......."), BorderLayout.PAGE_START);
                dLoading.add(panel);
                dLoading.pack();
                dLoading.setLocationRelativeTo(null);
                dLoading.setVisible(true);

                SendMessageTask t1 = null;
                SaveMessageTask t2 = new SaveMessageTask();
                t2.addPropertyChangeListener(new PropertyChangeListener() {

                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if (evt.getPropertyName().equals("state")) {
                            if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                dLoading.dispose();
                            }
                        }
                    }
                });

                if (rImmidiate.isSelected()) {
                    t1 = new SendMessageTask();
                    t1.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            if (evt.getPropertyName().equals("state")) {
                                if (evt.getNewValue() == SwingWorker.StateValue.DONE) {
                                    dLoading.dispose();
                                    //t2.execute();
                                }
                            }
                        }
                    });
                    t1.execute();
                }

                if (t1 == null) {
                    t2.execute();
                }
            }
        }
    }

    private class SendMessageTask extends SwingWorker<Boolean, Void> {

        @Override
        protected Boolean doInBackground() throws Exception {
            //if(Service.getInstance().getServiceStatus() != Service.ServiceStatus.STARTED) {
            try {
                SerialModemGateway gateway = new SerialModemGateway("modem.wavecom", "COM8", 115200, "Wavecom", "");
                gateway.setInbound(true);
                gateway.setOutbound(true);
                Service.getInstance().setOutboundMessageNotification(new OutboundNotification());
                Service.getInstance().addGateway(gateway);
                Service.getInstance().startService();

                System.out.println();
                System.out.println("Modem Information:");
                System.out.println(" Manufacturer: " + gateway.getManufacturer());
                System.out.println(" Model: " + gateway.getModel());
                System.out.println(" Serial No: " + gateway.getSerialNo());
                System.out.println(" SIM IMSI: " + gateway.getImsi());
                System.out.println(" Signal Level: " + gateway.getSignalLevel() + " dBm");
                System.out.println(" Battery Level: " + gateway.getBatteryLevel() + "%");
                System.out.println();

                OutboundMessage msg = new OutboundMessage(tContact.getText().trim(), tText.getText().trim());

                boolean res = Service.getInstance().sendMessage(msg);

                Service.getInstance().stopService();

                return res;
            } catch (GatewayException ex) {
                Logger.getLogger(MessageActionForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SMSLibException ex) {
                Logger.getLogger(MessageActionForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(MessageActionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            //}
            return false;
        }

    }

    private class SaveMessageTask extends SwingWorker<Integer, Void> {

        @Override
        protected Integer doInBackground() throws Exception {
            return -1;
        }
    }
}
