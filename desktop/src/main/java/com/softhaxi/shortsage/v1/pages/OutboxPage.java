package com.softhaxi.shortsage.v1.pages;

import com.softhaxi.shortsage.v1.components.CNumberedTable;
import com.softhaxi.shortsage.v1.components.CSearchField;
import com.softhaxi.shortsage.v1.components.CZebraTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class OutboxPage extends JPanel {
    
    private JPanel pDetail;
    /**
     * 
     */
    public OutboxPage() {
        initComponents();
        initTable();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(0, 4));
        setBorder(new EmptyBorder(4, 4, 4, 4));
        
        JPanel pUtil = new JPanel(new GridLayout(1, 2));
        pUtil.add(new CSearchField() {
            
            @Override
            public void doSearch() {
                JOptionPane.showMessageDialog(null, "Search action was not implemented full!");
            }
        });
        
        add(pUtil, BorderLayout.NORTH);
        
        pDetail = new JPanel(new BorderLayout(0, 3));
        JToolBar tpDetail = new JToolBar();
        tpDetail.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(2, 2, 2, 2)));
        tpDetail.setFloatable(false);
        tpDetail.add(new JButton("New", new ImageIcon(getClass().getClassLoader().getResource("images/ic_new.png"))));
        tpDetail.add(new JButton("Edit", new ImageIcon(getClass().getClassLoader().getResource("images/ic_edit.png"))));
        tpDetail.add(new JToolBar.Separator());
        tpDetail.add(new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_delete.png"))));
        tpDetail.add(Box.createHorizontalGlue());
        tpDetail.add(new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_refresh.png"))));
        
        pDetail.add(tpDetail, BorderLayout.NORTH);
        add(pDetail, BorderLayout.CENTER);
    }
    
    /**
     * Reference <a href="http://stackoverflow.com/questions/7137786/how-can-i-put-a-control-in-the-jtableheader-of-a-jtable/7137801#7137801">JTable Select All</a>
     */ 
    private void initTable() {
        String[] columnNames = {
            "Number",
            "Message",
            "Date"};

        Object[][] data = {
            {"+6282166013128",
                "Hello! Are you at home?", new Date().toString()},
            {"+6282165961213",
                "Do you want to trip next month?", new Date().toString()}
        };

        CZebraTable table = new CZebraTable(data, columnNames);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
        
        CNumberedTable rowTable = new CNumberedTable(table);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setRowHeaderView(rowTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
                    rowTable.getTableHeader());

        //Add the scroll pane to this panel.
        pDetail.add(scrollPane, BorderLayout.CENTER);
    }
}
