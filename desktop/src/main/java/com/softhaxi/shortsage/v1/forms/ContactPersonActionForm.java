package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.component.CZebraTable;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.component.CActionForm;
import com.softhaxi.shortsage.v1.component.CNumberedTable;
import com.softhaxi.shortsage.v1.component.CSplitButton;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class ContactPersonActionForm extends CActionForm<ContactPerson> {

    private static String[] STATUS_LIST = {
        "CREATED",
        "ACTIVED",
        "DEACTIVED"
    };

    private static String[] HANDLE_LIST = {
        "CREATED",
        "ACTIVE",
        "DEACTIVE",
        "DELETE"
    };

    private JPanel pHeader;
    private JTextField tID;
    private JTextField tName;
    private JTextField tCompany;
    private JComboBox cStatus;
    private JComboBox cHandler;
    private JTextArea tRemark;

    private JPanel pDetail;
    private JToolBar tdBar;
    private JButton bdNew;
    private JButton bdImport;
    private JButton bdExport;
    private JButton bdDelete;
    private CSplitButton sdMore;
    private CZebraTable ztNumbers;

    public ContactPersonActionForm() {
        super();
    }

    public ContactPersonActionForm(ContactPerson person) {
        super(person);
    }

    public ContactPersonActionForm(ActionState state, ContactPerson person) {
        super(state, person);
    }

    @Override
    public void initComponents() {
        super.initComponents();
        setBorder(new EmptyBorder(0, 4, 4, 0));
        setPreferredSize(new Dimension(500, 600));

        JPanel pForm = new JPanel(new BorderLayout(0, 4));

        pHeader = new JPanel(new GridLayout(1, 2, 8, 4));

        JPanel pH1 = new JPanel();
        GroupLayout lH1 = new GroupLayout(pH1);
        pH1.setLayout(lH1);

        JLabel lName = new JLabel("Contact Name:");
        tName = new JTextField();

        JLabel lCompany = new JLabel("Company:");
        tCompany = new JTextField();

        lH1.setHorizontalGroup(lH1.createSequentialGroup()
                .addGroup(lH1.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lName)
                        .addGap(4, 4, 4)
                        .addComponent(lCompany))
                .addGroup(lH1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tName)
                        .addGap(4, 4, 4)
                        .addComponent(tCompany))
        );
        lH1.setVerticalGroup(lH1.createSequentialGroup()
                .addGroup(lH1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lName)
                        .addGap(10, 10, 10)
                        .addComponent(tName))
                .addGap(4, 4, 4)
                .addGroup(lH1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lCompany)
                        .addGap(10, 10, 10)
                        .addComponent(tCompany))
        );

        JPanel pH2 = new JPanel();
        GroupLayout lH2 = new GroupLayout(pH2);
        pH2.setLayout(lH2);

        JLabel lStatus = new JLabel("Status:");
        cStatus = new JComboBox(STATUS_LIST);

        JLabel lHandler = new JLabel("Handler:");
        cHandler = new JComboBox(HANDLE_LIST);

        JLabel lRemark = new JLabel("Remark:");
        tRemark = new JTextArea(5, 2);
        JScrollPane scRemark = new JScrollPane(tRemark);
        

        lH2.setHorizontalGroup(lH2.createSequentialGroup()
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lStatus)
                        .addGap(4, 4, 4)
                        .addComponent(lHandler)
                        .addGap(4, 4, 4)
                        .addComponent(lRemark))
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cStatus)
                        .addGap(4, 4, 4)
                        .addComponent(cHandler)
                        .addGap(4, 4, 4)
                        .addComponent(scRemark, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
        );
        lH2.setVerticalGroup(lH2.createSequentialGroup()
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lStatus)
                        .addGap(10, 10, 10)
                        .addComponent(cStatus))
                .addGap(4, 4, 4)
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lHandler)
                        .addGap(10, 10, 10)
                        .addComponent(cHandler))
                .addGap(4, 4, 4)
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lRemark)
                        .addGap(10, 10, 10)
                        .addComponent(scRemark, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
        );

        pHeader.add(pH1);
        pHeader.add(pH2);
        pForm.add(pHeader, BorderLayout.NORTH);

        pDetail = new JPanel(new BorderLayout());
        tdBar = new JToolBar();
        tdBar.setFloatable(false);
        tdBar.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED),new EmptyBorder(2,2,2,2)));

        bdNew = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_plus_12.png")));
        tdBar.add(bdNew);
        tdBar.add(new JToolBar.Separator());

        bdImport = new JButton("Import", new ImageIcon(getClass().getClassLoader().getResource("images/ic_upload_12.png")));
        tdBar.add(bdImport);

        bdExport = new JButton("Export", new ImageIcon(getClass().getClassLoader().getResource("images/ic_download_12.png")));
        tdBar.add(bdExport);
        tdBar.add(new JToolBar.Separator());

        bdDelete = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_minus_12.png")));
        tdBar.add(bdDelete);
        
        tdBar.add(Box.createHorizontalGlue());
        tdBar.add(new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh_12.png"))));

        ztNumbers = new CZebraTable();
        ztNumbers.setShowGrid(false);
        ztNumbers.setIntercellSpacing(new Dimension(0, 0));

        CNumberedTable rowTable = new CNumberedTable(ztNumbers);
        JScrollPane scrollPane = new JScrollPane(ztNumbers);
        scrollPane.setRowHeaderView(ztNumbers);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                rowTable.getTableHeader());

        pDetail.add(tdBar, BorderLayout.NORTH);
        pDetail.add(scrollPane, BorderLayout.CENTER);
        pForm.add(pDetail, BorderLayout.CENTER);

        add(pForm, BorderLayout.CENTER);
    }

    @Override
    public void initState() {
        super.initState();

    }

    @Override
    public void initData() {
    }

}
