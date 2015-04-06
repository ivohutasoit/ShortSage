package com.softhaxi.shortsage.v1.page;

import com.softhaxi.shortsage.v1.component.CNumberedTable;
import com.softhaxi.shortsage.v1.component.CSearchField;
import com.softhaxi.shortsage.v1.component.CZebraTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
 * References <a href="http://stackoverflow.com/questions/20526917/load-arraylist-data-into-jtable">Array List</a>
 * 
* @author Ivo Hutasoit
* @since 1
* @version 1.0.0
*/
public class MessageTemplatePage extends JPanel {
  
  private final String[] COLUMN_LIST = {
    "Name",
    "Message Text",
    "Created At",
    "Created By"
  };
  
  private JPanel pDetail;
  private CZebraTable tData;
  private JXSearchField fSearch; 
  
  private DefaultTableModel mData;
  
  /**
   * Constructor
   */
  public MessageTemplatePage() {
    initComponents();
    initTable();
    
    // Create model with 0 number row
    tModel = new DefaultTableModel(COLUMN_LIST, 0);
    initData();
  }
  
  private void initComponents() {
    setLayout(new BorderLayout(4, 4));
    setBorder(new EmptyBorder(4, 4, 4, 4));
    //CSearchField pSearch = new CSearchField() {
    //    @Override
    //    public void doSearch() {
    //      JOptionPane.showMessageDialog(null, "Search action was not implemented full!");
    //    }
    //};
    //add(pSearch, BorderLayout.NORTH);
    fSearch = new JXSearchField("Search Message Template...");
    fSearch.setSearchMode(SearchMode.REGULAR);
    fSearch.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null, "Search action was not implemented full!");
        }
    });
    add(fSearch, BorderLayout.NORTH);
    
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
  
  private void initTable() {
    tData = new CZebraTable();
    tData.setShowGrid(false);
    tData.setIntercellSpacing(new Dimension(0, 0));
    // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tData.getColumnModel().getColumn(0).setPreferredWidth(100);
    tData.getColumnModel().getColumn(1).setPreferredWidth(700);
    CNumberedTable rowTable = new CNumberedTable(tData);
    //Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(tData);
    scrollPane.setRowHeaderView(rowTable);
    scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
    rowTable.getTableHeader());
    //Add the scroll pane to this panel.
    pDetail.add(scrollPane, BorderLayout.CENTER);
  }
  
  private void initData() {
    // 1. Load data from database
    
    // 2. For each object add to model
    
    // 3. Set model table
  }
}
