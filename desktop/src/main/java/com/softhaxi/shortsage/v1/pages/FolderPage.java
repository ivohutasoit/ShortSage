package com.softhaxi.shortsage.v1.pages;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Hutasoit
 */
public class FolderPage extends JPanel {
    
    private JPanel pDetail;

    public FolderPage() {
        initComponents();
        initTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 4));
        setBorder(new EmptyBorder(4, 4, 4, 4));

        JPanel pSelection = new JPanel(new GridLayout(1, 2, 4, 0));
        JPanel p1 = new JPanel(new BorderLayout(0, 0));
        p1.add(new JLabel("Search :"), BorderLayout.WEST);
        JTextField tSearch = new JTextField();
        p1.add(tSearch, BorderLayout.CENTER);
        p1.add(new JButton("Search"), BorderLayout.EAST);

        JPanel p2 = new JPanel(new BorderLayout(0, 0));
        p2.add(new JLabel("Selection Status:"), BorderLayout.WEST);
        p2.add(new JComboBox(), BorderLayout.CENTER);

        pSelection.add(p1);
        pSelection.add(p2);

        add(pSelection, BorderLayout.NORTH);

        pDetail = new JPanel(new BorderLayout(0, 3));
        JToolBar tpDetail = new JToolBar();
        tpDetail.setBorder(new EtchedBorder());
        tpDetail.setFloatable(false);

        tpDetail.add(new JButton("New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_new_message.png"))));
        tpDetail.add(new JToolBar.Separator());
        tpDetail.add(new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png"))));

//        JPanel p6 = new JPanel();
//        p6.add(b);
//        pDetail.add(p6, BorderLayout.CENTER);
        pDetail.add(tpDetail, BorderLayout.NORTH);
        add(pDetail, BorderLayout.CENTER);
    }

    private void initTable() {
        String[] columnNames = {"No",
            "Number",
            "Message",
            "Date",
            "Status"};

        Object[][] data = {
            {"1", "+6282166013128",
                "Hello! Are you at home?", new Date().toString(), "SUCCESS"},
            {"2", "+6282165961213",
                "Do you want to trip next month?", new Date().toString(), "DRAFT"}
        };
        
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);
        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        pDetail.add(scrollPane, BorderLayout.CENTER);
    }
}
