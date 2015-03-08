package com.softhaxi.shortsage.v1.pages;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Hutasoit
 */
public class InboxPage extends JPanel {

    private JPanel pDetail;

    public InboxPage() {
        initComponents();
        initTable();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 4));
        setBorder(new EmptyBorder(4, 4, 4, 4));
        
        JPanel p1 = new JPanel(new BorderLayout(5, 0));
        p1.add(new JLabel("Search :"), BorderLayout.WEST);
        JPanel p2 = new JPanel(new BorderLayout());
        p2.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(1, 4, 1, 4)));
        JTextField tSearch = new JTextField();
        p2.add(tSearch, BorderLayout.CENTER);
        p2.setBackground(tSearch.getBackground());
        tSearch.setBorder(null);
        JButton bSearch = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/ic_search.png")));
        bSearch.setBorderPainted(false); 
        bSearch.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        bSearch.setContentAreaFilled(false); 
        bSearch.setFocusPainted(false); 
        bSearch.setOpaque(false);
        p2.add(bSearch, BorderLayout.EAST);
        p1.add(p2, BorderLayout.CENTER);

        add(p1, BorderLayout.NORTH);

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
            "Date"};

        Object[][] data = {
            {"1", "+6282166013128",
                "Hello! Are you at home?", new Date().toString()},
            {"2", "+6282165961213",
                "Do you want to trip next month?", new Date().toString()}
        };

        JTable table = new JTable(data, columnNames);
//        table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
//        for (int column = 0; column < table.getColumnCount(); column++) {
//            TableColumn tableColumn = table.getColumnModel().getColumn(column);
//            int preferredWidth = tableColumn.getMinWidth();
//            int maxWidth = tableColumn.getMaxWidth();
//
//            for (int row = 0; row < table.getRowCount(); row++) {
//                TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
//                Component c = table.prepareRenderer(cellRenderer, row, column);
//                int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
//                preferredWidth = Math.max(preferredWidth, width);
//
//        //  We've exceeded the maximum width, no need to check other rows
//                if (preferredWidth >= maxWidth) {
//                    preferredWidth = maxWidth;
//                    break;
//                }
//            }
//
//            tableColumn.setPreferredWidth(preferredWidth);
//        }
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        pDetail.add(scrollPane, BorderLayout.CENTER);
    }
}
