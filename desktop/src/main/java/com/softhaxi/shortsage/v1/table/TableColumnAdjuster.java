package com.softhaxi.shortsage.v1.table;

/**
 *	Class to manage the widths of colunms in a table.
 *
 *  Various properties control how the width of the column is calculated.
 *  Another property controls whether column width calculation should be dynamic.
 *  Finally, various Actions will be added to the table to allow the user
 *  to customize the functionality.
 *
 *  This class was designed to be used with tables that use an auto resize mode
 *  of AUTO_RESIZE_OFF. With all other modes you are constrained as the width
 *  of the columns must fit inside the table. So if you increase one column, one
 *  or more of the other columns must decrease. Because of this the resize mode
 *  of RESIZE_ALL_COLUMNS will work the best.
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class TableColumnAdjuster implements PropertyChangeListener, 
              TableModelListener {
    private JTable table;
    private int gap;
    private boolean columnHeaderIncluded;
    private boolean columnDataIncluded;
    private boolean onlyAdjustLarger;
    private boolean dynamicAdjustment;
    
    private Map<TableColumn, Integer> columnSizes = new HashMap<>();
    
    /**
     * Specify the table and use default gap
     *
     * @param table Component Table to be adjested
     */
    public TableColumnAdjuster(JTable table) {
        this(table, 6);
    }
    
    /**
     * Specify the table and gap
     *
     * @param table Component Table to be adjested
     * @param gap number if gap of column in table
     */
    public TableColumnAdjuster(JTable, int gap) {
        this.table = table;
        this.gap = gap;
        setColumnHeaderIncluded(true);
        setColumnDataIncluded(true);
        setOnlyAdjustLarger(false);
        setDynamicAdjustment(false);
        installActions();
    }
    
    /**
     * Adjust the widths of all the columns in the table
     *
     */
    public void adjustColumns() {
        TableColumnModel tcm = table.getColumnModel();
        
        for(int ii=0;ii<tcm.getColumnCount();ii++) {
            adjustColumn(ii);
        }
    }
    
    /**
     * Adjust the width of the specified  column in the table
     *
     */
    public void adjustColumn(int column) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        
        if(!tableColumn.getResizable()) return;
        
        int chWidth = getColumnHeaderWidth(column);
        int cdWidth = getColumnDataWidth(column);
        int prWidth = Math.max(chWidth, cdWidth);
        
        updateTableColumn(column, prWidth);
    }
    
    /**
     * Calculated the width based on the column name
     */
    private int getColumnHeaderWidth(int column) {
        if(!columnHeaderIncluded) return 0;
        
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        Object value = tableColumn.getHeaderValue();
        TableCellRenderer renderer = tableColumn.getHeaderRenderer();
        
        if(renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        
        Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
        return c.getPreferredSize().width;
    }
    
    /**
     * Calculate the width based on the widest cell renderer for the given column
     */
    private int getColumnDataWidth(int column) {
        if(!columnDataIncluded) return 0;
        
        int prWidth = 0;
        int mxWidth = table.getColumnModel().getColumn(column).getMaxWidth();
        
        for(int rr=0;rr<table.getRowCount();rr++) {
            prWidth = Math.max(prWidth, getCellDataWidth(row, column));
            
            if(prWidth >= mxWidth)
              break;
        }
        return prWidth;
    }
    
    
}
