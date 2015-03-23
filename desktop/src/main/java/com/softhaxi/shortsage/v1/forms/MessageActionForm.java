package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.dto.Message;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javafx.scene.control.DatePicker;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXDatePicker;

public class MessageActionForm extends CActionForm<Message> {

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
                        .addComponent(tText))
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
                        .addComponent(tText))
        );
        add(pForm, BorderLayout.CENTER);
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
            if(state == ActionState.CREATE) {
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
    
    private class SendMessageTask extends SwingWorker<Integer, Boolean> {

        @Override
        protected Integer doInBackground() throws Exception {
            return 0;
        }
        
    }
}
