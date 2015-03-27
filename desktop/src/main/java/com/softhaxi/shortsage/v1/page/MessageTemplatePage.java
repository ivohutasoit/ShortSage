package com.softhaxi.shortsage.v1.page;

import javax.swing.JPanel;

/**
* @author Ivo Hutasoit
* @since 1
* @version 1.0.0
*/
public class MessageTemplatePage extends JPanel {
  
  private JPanel pDetail;
  
  /**
   * Constructor
   */
  public MessageTemplatePage() {
    initComponents();
  }
  
  private void initComponents() {
    setLayout(new BorderLayout(4, 4));
    setBorder(new EmptyBorder(4, 4, 4, 4));
    CSearchField pSearch = new CSearchField() {
        @Override
        public void doSearch() {
          JOptionPane.showMessageDialog(null, "Search action was not implemented full!");
        }
    };
    add(pSearch, BorderLayout.NORTH);
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
    CZebraTable table = new CZebraTable();
    table.setShowGrid(false);
    table.setIntercellSpacing(new Dimension(0, 0));
    // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.getColumnModel().getColumn(0).setPreferredWidth(100);
    table.getColumnModel().getColumn(1).setPreferredWidth(700);
    CNumberedTable rowTable = new CNumberedTable(table);
    //Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setRowHeaderView(rowTable);
    scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,
    rowTable.getTableHeader());
    //Add the scroll pane to this panel.
    pDetail.add(scrollPane, BorderLayout.CENTER);
    ReadMessageTask t1 = new ReadMessageTask();
    t1.execute();
  }
}
