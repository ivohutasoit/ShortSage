package com.softhaxi.shortsage.v1.forms;

import com.softhaxi.shortsage.v1.components.CZebraTable;
import com.softhaxi.shortsage.v1.enums.ActionState;
import com.softhaxi.shortsage.v1.components.CActionForm;
import com.softhaxi.shortsage.v1.model.ContactPerson;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private JToolbar tdBar;
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
        setPreferredSize(new Dimension(500, 600));

        JPanel pForm = new JPanel(new GridLayout(2, 1));

        pHeader = new JPanel(new GridLayout(1, 2, 4, 0));

        JPanel pH1 = new JPanel();
        GroupLayout lH1 = new GroupLayout(pH1);
        pH1.setLayout(lH1);
        lH1.setAutoCreateGaps(true);
        lH1.setAutoCreateContainerGaps(true);

        JLabel lName = new JLabel("Contact Name:");
        tName = new JTextField();

        JLabel lCompany = new JLabel("Company:");
        tCompany = new JTextField();

        lH1.setHorizontalGroup(lH1.createSequentialGroup()
                .addGroup(lH1.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lName)
                        .addComponent(lCompany))
                .addGroup(lH1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tName)
                        .addComponent(tCompany))
        );
        lH1.setVerticalGroup(lH1.createSequentialGroup()
                .addGroup(lH1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lName)
                        .addComponent(tName))
                .addGroup(lH1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lCompany)
                        .addComponent(tCompany))
        );

        JPanel pH2 = new JPanel();
        GroupLayout lH2 = new GroupLayout(pH2);
        pH2.setLayout(lH2);
        lH2.setAutoCreateGaps(true);
        lH2.setAutoCreateContainerGaps(true);

        JLabel lStatus = new JLabel("Status:");
        cStatus = new JComboBox(STATUS_LIST);

        JLabel lHandler = new JLabel("Handler:");
        cHandler = new JComboBox(HANDLE_LIST);

        JLabel lRemark = new JLabel("Remark:");
        tRemark = new JTextArea(2, 2);

        lH2.setHorizontalGroup(lH2.createSequentialGroup()
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lStatus)
                        .addComponent(lHandler)
                        .addComponent(lRemark))
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cStatus)
                        .addComponent(cHandler)
                        .addComponent(tRemark, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lH2.setVerticalGroup(lH2.createSequentialGroup()
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lStatus)
                        .addComponent(cStatus))
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lHandler)
                        .addComponent(cHandler))
                .addGroup(lH2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lRemark)
                        .addComponent(tRemark, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pHeader.add(pH1);
        pHeader.add(pH2);
        pForm.add(pHeader);
        
        pDetail = nee JPanel(new BorderLayout());
        tdBar = new JToolBar();
        tdBar.setFloatable(false);
        
        bdNew = new JButton("New Number");
        tdBar.add(bdNew);
        
        bdImport = new JButton("Import Numbers");
        tdBar.add(bdImport);
        
        bdExport = new JButton("Export Numbers");
        tdBar.add(bdExport);
        
        dbDelete = new JButton("Delete Number");
        tdBar.add(bdDetele); 
        
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
        pForm.add(pDetail);
        
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
