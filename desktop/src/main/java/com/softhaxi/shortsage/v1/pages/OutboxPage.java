package com.softhaxi.shortsage.v1.pages;

import javax.swing.JPanel;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class OutboxPage extends JPanel {
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
    private void initTable() {
        
    }
}
