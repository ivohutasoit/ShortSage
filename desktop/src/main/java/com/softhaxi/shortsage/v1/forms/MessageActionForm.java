package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.dto.Message;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private JCalendar cDate;
    private JTextArea tText;
    private JComboBox<String> cStatus;
    private JComboBox<String> cHandler;

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
        setPreferredSize(new Dimension(500, 600));

        bReply = new JButton("Reply");
        tBar.add(bReply, 4);

        JPanel pForm = new JPanel();
        GroupLayout lForm = new GroupLayout(pForm);
        pForm.setLayout(lForm);
        lForm.setAutoCreateGaps(true);
        lForm.setAutoCreateContainerGaps(true);

        JLabel lContact = new JLabel("Contact:");
        tContact = new JTextField();

        JLabel lDate = new JLabel("Transaction Date:");
        cDate = new JCalendar();

        JLabel lStatus = new JLabel("Message Status:");
        cStatus = new JComboBox(STATUS_LIST);

        JLabel lHandler = new JLabel("Handler:");
        cHandler = new JComboBox(HANDLE_LIST);

        JLabel lText = new JLabel("Message:");
        tText = new JTextArea();

        lForm.setHorizontalGroup(lForm.createSequentialGroup()
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lContact)
                        .addComponent(lDate)
                        .addComponent(lStatus)
                        .addComponent(lHandler)
                        .addComponent(lText))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tContact)
                        .addComponent(cDate)
                        .addComponent(cStatus)
                        .addComponent(cHandler)
                        .addComponent(tText))
        );
        lForm.setVerticalGroup(lForm.createSequentialGroup()
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lContact)
                        .addComponent(tContact))
                .addGroup(lForm.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lDate)
                        .addComponent(cDate))
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

        if (!object.getFolder().equalsIgnoreCase("INBOX")
                && (state == ActionState.CREATE || state == ActionState.EDIT)) {
            bReply.setVisible(false);
        } else {
            bReply.setVisible(true);
        }
    }

    @Override
    public void initData() {
    }

}
