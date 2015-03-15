package com.softhaxi.shortsage.v1.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Class to manage the widths of colunms in a table.
 *
 * Various properties control how the width of the column is calculated. Another
 * property controls whether column width calculation should be dynamic.
 * Finally, various Actions will be added to the table to allow the user to
 * customize the functionality.
 *
 * This class was designed to be used with tables that use an auto resize mode
 * of AUTO_RESIZE_OFF. With all other modes you are constrained as the width of
 * the columns must fit inside the table. So if you increase one column, one or
 * more of the other columns must decrease. Because of this the resize mode of
 * RESIZE_ALL_COLUMNS will work the best.
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
    public TableColumnAdjuster(JTable table, int gap) {
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

        for (int ii = 0; ii < tcm.getColumnCount(); ii++) {
            adjustColumn(ii);
        }
    }

    /**
     * Adjust the width of the specified column in the table
     *
     */
    public void adjustColumn(int column) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);

        if (!tableColumn.getResizable()) {
            return;
        }

        int chWidth = getColumnHeaderWidth(column);
        int cdWidth = getColumnDataWidth(column);
        int prWidth = Math.max(chWidth, cdWidth);

        updateTableColumn(column, prWidth);
    }

    /**
     * Calculated the width based on the column name
     */
    private int getColumnHeaderWidth(int column) {
        if (!columnHeaderIncluded) {
            return 0;
        }

        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        Object value = tableColumn.getHeaderValue();
        TableCellRenderer renderer = tableColumn.getHeaderRenderer();

        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        Component c = renderer.getTableCellRendererComponent(table, value, false, false, -1, column);
        return c.getPreferredSize().width;
    }

    /**
     * Calculate the width based on the widest cell renderer for the given
     * column
     */
    private int getColumnDataWidth(int column) {
        if (!columnDataIncluded) {
            return 0;
        }

        int prWidth = 0;
        int mxWidth = table.getColumnModel().getColumn(column).getMaxWidth();

        for (int rr = 0; rr < table.getRowCount(); rr++) {
            prWidth = Math.max(prWidth, getCellDataWidth(rr, column));

            if (prWidth >= mxWidth) {
                break;
            }
        }
        return prWidth;
    }

    /**
     * Get the preferred width for the specified cell
     */
    private int getCellDataWidth(int row, int column) {
        //  Inovke the renderer for the cell to calculate the preferred width

        TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
        Component c = table.prepareRenderer(cellRenderer, row, column);
        int width = c.getPreferredSize().width + table.getIntercellSpacing().width;

        return width;
    }

    /**
     *  Update the TableColumn with the newly calculated width
     */
    private void updateTableColumn(int column, int width) {
        final TableColumn tableColumn = table.getColumnModel().getColumn(column);

        if (!tableColumn.getResizable()) {
            return;
        }

        width += gap;

        //  Don't shrink the column width
        if (onlyAdjustLarger) {
            width = Math.max(width, tableColumn.getPreferredWidth());
        }

        columnSizes.put(tableColumn, tableColumn.getWidth());

        table.getTableHeader().setResizingColumn(tableColumn);
        tableColumn.setWidth(width);
    }

    /*
     *  Restore the widths of the columns in the table to its previous width
     */
    public void restoreColumns() {
        TableColumnModel tcm = table.getColumnModel();

        for (int i = 0; i < tcm.getColumnCount(); i++) {
            restoreColumn(i);
        }
    }

    /*
     *  Restore the width of the specified column to its previous width
     */
    private void restoreColumn(int column) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        Integer width = columnSizes.get(tableColumn);

        if (width != null) {
            table.getTableHeader().setResizingColumn(tableColumn);
            tableColumn.setWidth(width);
        }
    }

    /*
     *	Indicates whether to include the header in the width calculation
     */
    public void setColumnHeaderIncluded(boolean columnHeaderIncluded) {
        this.columnHeaderIncluded = columnHeaderIncluded;
    }

    /*
     *	Indicates whether to include the model data in the width calculation
     */
    public void setColumnDataIncluded(boolean isColumnDataIncluded) {
        this.columnDataIncluded = columnDataIncluded;
    }

    /*
     *	Indicates whether columns can only be increased in size
     */
    public void setOnlyAdjustLarger(boolean onlyAdjustLarger) {
        this.onlyAdjustLarger = onlyAdjustLarger;
    }

    /*
     *  Indicate whether changes to the model should cause the width to be
     *  dynamically recalculated.
     */
    public void setDynamicAdjustment(boolean dynamicAdjustment) {
        //  May need to add or remove the TableModelListener when changed

        if (this.dynamicAdjustment != dynamicAdjustment) {
            if (dynamicAdjustment) {
                table.addPropertyChangeListener(this);
                table.getModel().addTableModelListener(this);
            } else {
                table.removePropertyChangeListener(this);
                table.getModel().removeTableModelListener(this);
            }
        }

        this.dynamicAdjustment = dynamicAdjustment;
    }
//
//  Implement the PropertyChangeListener
//

    @Override
    public void propertyChange(PropertyChangeEvent e) {
		//  When the TableModel changes we need to update the listeners
        //  and column widths

        if ("model".equals(e.getPropertyName())) {
            TableModel model = (TableModel) e.getOldValue();
            model.removeTableModelListener(this);

            model = (TableModel) e.getNewValue();
            model.addTableModelListener(this);
            adjustColumns();
        }
    }
//
//  Implement the TableModelListener
//

    @Override
    public void tableChanged(TableModelEvent e) {
        if (columnDataIncluded) {
            return;
        }

		//  A cell has been updated
        if (e.getType() == TableModelEvent.UPDATE) {
            int column = table.convertColumnIndexToView(e.getColumn());

			//  Only need to worry about an increase in width for this cell
            if (onlyAdjustLarger) {
                int row = e.getFirstRow();
                TableColumn tableColumn = table.getColumnModel().getColumn(column);

                if (tableColumn.getResizable()) {
                    int width = getCellDataWidth(row, column);
                    updateTableColumn(column, width);
                }
            } //	Could be an increase of decrease so check all rows
            else {
                adjustColumn(column);
            }
        } //  The update affected more than one column so adjust all columns
        else {
            adjustColumns();
        }
    }

    /*
     *  Install Actions to give user control of certain functionality.
     */
    private void installActions() {
        installColumnAction(true, true, "adjustColumn", "control ADD");
        installColumnAction(false, true, "adjustColumns", "control shift ADD");
        installColumnAction(true, false, "restoreColumn", "control SUBTRACT");
        installColumnAction(false, false, "restoreColumns", "control shift SUBTRACT");

        installToggleAction(true, false, "toggleDynamic", "control MULTIPLY");
        installToggleAction(false, true, "toggleLarger", "control DIVIDE");
    }

    /*
     *  Update the input and action maps with a new ColumnAction
     */
    private void installColumnAction(
            boolean isSelectedColumn, boolean isAdjust, String key, String keyStroke) {
        Action action = new ColumnAction(isSelectedColumn, isAdjust);
        KeyStroke ks = KeyStroke.getKeyStroke(keyStroke);
        table.getInputMap().put(ks, key);
        table.getActionMap().put(key, action);
    }

    /*
     *  Update the input and action maps with new ToggleAction
     */
    private void installToggleAction(
            boolean isToggleDynamic, boolean isToggleLarger, String key, String keyStroke) {
        Action action = new ToggleAction(isToggleDynamic, isToggleLarger);
        KeyStroke ks = KeyStroke.getKeyStroke(keyStroke);
        table.getInputMap().put(ks, key);
        table.getActionMap().put(key, action);
    }

    /*
     *  Action to adjust or restore the width of a single column or all columns
     */
    class ColumnAction extends AbstractAction {

        private boolean isSelectedColumn;
        private boolean isAdjust;

        public ColumnAction(boolean isSelectedColumn, boolean isAdjust) {
            this.isSelectedColumn = isSelectedColumn;
            this.isAdjust = isAdjust;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //  Handle selected column(s) width change actions

            if (isSelectedColumn) {
                int[] columns = table.getSelectedColumns();

                for (int i = 0; i < columns.length; i++) {
                    if (isAdjust) {
                        adjustColumn(columns[i]);
                    } else {
                        restoreColumn(columns[i]);
                    }
                }
            } else {
                if (isAdjust) {
                    adjustColumns();
                } else {
                    restoreColumns();
                }
            }
        }
    }

    /*
     *  Toggle properties of the TableColumnAdjuster so the user can
     *  customize the functionality to their preferences
     */
    class ToggleAction extends AbstractAction {

        private boolean isToggleDynamic;
        private boolean isToggleLarger;

        public ToggleAction(boolean isToggleDynamic, boolean isToggleLarger) {
            this.isToggleDynamic = isToggleDynamic;
            this.isToggleLarger = isToggleLarger;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isToggleDynamic) {
                setDynamicAdjustment(!dynamicAdjustment);
                return;
            }

            if (isToggleLarger) {
                setOnlyAdjustLarger(!onlyAdjustLarger);
                return;
            }
        }
    }
}
