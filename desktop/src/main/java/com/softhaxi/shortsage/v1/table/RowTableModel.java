package com.softhaxi.shortsage.v1.table;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static javax.swing.UIManager.get;
import javax.swing.table.AbstractTableModel;

/**
 * A TableModel that better supports the processing of rows of data. That is,
 * the data is treated more like a row than an individual cell. Hopefully this
 * class can be used as a parent class instead of extending the
 * AbstractTableModel when you need custom models that contain row related data.
 *
 * A few methods have also been added to make it easier to customize properties
 * of the model, such as the column class and column editability.
 *
 * Any class that extends this class must make sure to invoke the setRowClass()
 * and setDataAndColumnNames() methods either directly, by using the various
 * constructors, or indirectly.
 *
 * @author Ivo Hutasoit
 * @param <T>
 * @since 1
 * @version 1.0.0
 */
public abstract class RowTableModel<T> extends AbstractTableModel {

    protected List<T> data;
    protected List<String> columnNames;
    protected Class[] columnClasses;
    protected Boolean[] columnEditable;
    private Class rowClass = Object.class;
    private boolean modelEditable = true;

    /**
     * Constructs a <code>RowTableModel</code> with the row class.
     *
     * This value is used by the getRowsAsArray() method. Sub classes creating a
     * model using this constructor must make sure to invoke the
     * setDataAndColumnNames() method.
     *
     * @param rowClass the class of row data to be added to the model
     */
    protected RowTableModel(Class rowClass) {
        setRowClass(rowClass);
    }

    /**
     * Constructs a <code>RowTableModel</code> with column names.
     *
     * Each column's name will be taken from the <code>columnNames</code> List
     * and the number of colums is determined by thenumber of items in the
     * <code>columnNames</code> List.
     *
     * Sub classes creating a model using this constructor must make sure to
     * invoke the setRowClass() method.
     *
     * @param columnNames	   <code>List</code> containing the names of the new
     * columns
     */
    protected RowTableModel(List<String> columnNames) {
        this(new ArrayList<T>(), columnNames);
    }

    /**
     * Constructs a <code>RowTableModel</code> with initial data and customized
     * column names.
     *
     * Each item in the <code>data</code> List must also be a List Object
     * containing items for each column of the row.
     *
     * Each column's name will be taken from the <code>columnNames</code> List
     * and the number of colums is determined by thenumber of items in the
     * <code>columnNames</code> List.
     *
     * Sub classes creating a model using this constructor must make sure to
     * invoke the setRowClass() method.
     *
     * @param data	the data of the table
     * @param columnNames	   <code>List</code> containing the names of the new
     * columns
     */
    protected RowTableModel(List<T> data, List<String> columnNames) {
        setDataAndColumnNames(data, columnNames);
    }

    /**
     * Full Constructor for creating a <code>RowTableModel</code>.
     *
     * Each item in the <code>modelData</code> List must also be a List Object
     * containing items for each column of the row.
     *
     * Each column's name will be taken from the <code>columnNames</code> List
     * and the number of colums is determined by thenumber of items in the
     * <code>columnNames</code> List.
     *
     * @param data the data of the table
     * @param columnNames <code>List</code> containing the names of the new
     * columns
     * @param rowClass the class of row data to be added to the model
     */
    protected RowTableModel(List<T> data, List<String> columnNames, Class rowClass) {
        setDataAndColumnNames(data, columnNames);
        setRowClass(rowClass);
    }

    /**
     * Reset the data and column names of the model.
     *
     * A fireTableStructureChanged event will be generated.
     *
     * @param data the data of the table
     * @param columnNames  <code>List</code> containing the names of the new
     * columns
     */
    protected void setDataAndColumnNames(List<T> data, List<String> columnNames) {
        this.data = data;
        this.columnNames = columnNames;
        columnClasses = new Class[getColumnCount()];
        columnEditable = new Boolean[getColumnCount()];
        fireTableStructureChanged();
    }

    /**
     * The class of the Row being stored in the TableModel
     *
     * This is required for the getRowsAsArray() method to return the proper
     * class of row.
     *
     * @param rowClass the class of the row
     */
    protected void setRowClass(Class rowClass) {
        this.rowClass = rowClass;
    }

    /**
     * Returns the Class of the queried <code>column</code>.
     *
     * First it will check to see if a Class has been specified for the
     * <code>column</code> by using the <code>setColumnClass</code> method. If
     * not, then the superclass value is returned.
     *
     * @param column the column being queried
     * @return the Class of the column being queried
     */
    @Override
    public Class getColumnClass(int column) {
        Class columnClass = null;

        // Get the class, if set for the specific column
        if (column < columnClasses.length) {
            columnClass = columnClasses[column];
        }

        // Get Default Class
        if (columnClass == null) {
            columnClass = super.getColumnClass(column);
        }

        return columnClass;
    }

    /**
     * Returns the number of columns in this table model.
     *
     * @return the number of columns int the model
     */
    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    /**
     * Returns the column name
     *
     * @return a name for this column using the string value of the appropriate
     * member in <code>columnNames</code>. If <code>columnNames</code> does not
     * have an entry fot this index then the default name provided by the
     * superclass is returned.
     */
    @Override
    public String getColumnName(int column) {
        Object columnName = null;

        if (column < columnNames.size()) {
            columnName = columnNames.get(column);
        }

        return (columnName == null) ? super.getColumnName(column) : columnName.toString();
    }

    /**
     * Returns number of rows int this table model
     *
     * @return number of the rows in the model.
     */
    @Override
    public int getRowCount() {
        return data.size();
    }

    /**
     * Returns true regardless of parameter values.
     *
     * @param row whose value to be queried
     * @param column whose value to be queried
     * @return true
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        Boolean isEditable = null;

        // Check is column editing capabilities has been set
        if (column < columnEditable.length) {
            isEditable = columnEditable[column];
        }

        return (isEditable == null) ? modelEditable : isEditable;
    }

    /**
     * Adds a row of data to the end of model Notofication of the row being
     * added will be generated.
     *
     * @param rowData data of the row being edded.
     */
    public void addRow(T rowData) {
        insertRow(getRowCount(), rowData);
    } 
    
    /**
     * Returns object of the requested <code>row</code>
     * 
     * @param row
     * @return object of the requested row.
     */
    public T getRow(int row) {
        return data.get(row);
    }

    /**
     * Returns an array of objects for the requested <code>row</code>
     *
     * @param rows
     * @return an array of objects for the requested row.
     */
    public T[] getRowAsArray(int... rows) {
        List<T> rowData = getRowAsList(rows);

        T[] ar = (T[]) Array.newInstance(rowClass, rowData.size());
        return (T[]) rowData.toArray(ar);
    }

    /**
     * Returns a list of objects for the requested <code>rows</code>
     *
     * @param rows
     * @return a list of objects for the requested rows.
     */
    public List<T> getRowAsList(int... rows) {
        ArrayList<T> rowData = new ArrayList<>(rows.length);

        for (int ii = 0; ii < rows.length; ii++) {
            rowData.add(getRow(rows[ii]));
        }

        return rowData;
    }

    /**
     * Insert a row of data at the <code>row</code> location in the model.
     * Notification of the row being added will be generated.
     *
     * @param row	row in the model where the data will be inserted
     * @param rowData data of the row being added
     */
    public void insertRow(int row, T rowData) {
        data.add(row, rowData);
        fireTableRowsInserted(row, row);
    }

    /**
     * Insert multiple rows of data at the <code>row</code> location in the
     * model. Notification of the row being added will be generated.
     *
     * @param row	row in the model where the data will be inserted
     * @param rowList each item in the list is a separate row of data
     */
    public void insertRows(int row, List<T> rowList) {
        data.addAll(row, rowList);
        fireTableRowsInserted(row, row + rowList.size() - 1);
    }

    /**
     * Insert multiple rows of data at the <code>row</code> location in the
     * model. Notification of the row being added will be generated.
     *
     * @param row	row in the model where the data will be inserted
     * @param rowArray each item in the Array is a separate row of data
     */
    public void insertRows(int row, T[] rowArray) {
        List<T> rowList = new ArrayList<>(rowArray.length);

        rowList.addAll(Arrays.asList(rowArray));

        insertRows(row, rowList);
    }

    /**
     * Moves one or more rows from the inlcusive range <code>start</code> to
     * <code>end</code> to the <code>to</code> position in the model. After the
     * move, the row that was at index <code>start</code> will be at index
     * <code>to</code>.
     *
     * This method will send a <code>tableRowsUpdated</code> notification
     * message to all the listeners.
     * <p>
     * <
     * pre>Examples of moves:
     * 1. moveRow(1,3,5);
     * 		a|B|C|D|e|f|g|h|i|j|k   - before
     * 		a|e|f|g|h|B|C|D|i|j|k   - after
     * 2. moveRow(6,7,1);
     * 		a|b|c|d|e|f|G|H|i|j|k   - before
     * 		a|G|H|b|c|d|e|f|i|j|k   - after
     * </pre>
     * </p>
     *
     * @param start	the starting row index to be moved
     * @param end	the ending row index to be moved
     * @param to	the destination of the rows to be moved
     * @exception IllegalArgumentException if any of the elements would be moved
     * out of the tables range
     */
    public void moveRow(int start, int end, int to) {
        String message = null;
        if (start < 0) {
            message = "Start index must be positive: " + start;
            throw new IllegalArgumentException(message);
        }

        if (end > getRowCount() - 1) {
            message = "End index must be less than total rows: " + end;
            throw new IllegalArgumentException(message);
        }

        if (start > end) {
            message = "Start index cannot be greater than end index";
            throw new IllegalArgumentException(message);
        }

        int rowsMoved = end - start + 1;

        if (to < 0 || to > getRowCount() - rowsMoved) {
            message = "New destination row (" + to + ") is invalid";
            throw new IllegalArgumentException(message);
        }

        // Save references to the rows that are about moved
        ArrayList<T> temp = new ArrayList<>();

        for (int ii = 0; ii < end; ii++) {
            temp.add(data.get(ii));
        }

    	// Remove the rows from the current location and add the back
        // at the specified new location
        data.subList(start, end + 1).clear();
        data.addAll(to, temp);

        // Determine the rows that need to be repainted to reflect the move
        int first;
        int last;

        if (to < start) {
            first = to;
            last = end;
        } else {
            first = start;
            last = to + end - start;
        }

        fireTableRowsUpdated(first, last);
    }

    /**
     * Remove the specified rows from the model. Rows between the starting and
     * ending indexes, inclusively, will be removed. Notification of the rows
     * being removed will be generated.
     *
     * @param start	starting row index
     * @param end	ending row index
     * @exception ArrayIndexOutOfBoundsException if any row index is invalid
     */
    public void removeRowRange(int start, int end) {
        data.subList(start, end + 1).clear();
        fireTableRowsDeleted(start, end);
    }

    /**
     * Remove the specified rows from the model. The row indexes in the array
     * must be in increasing order. Notification of the rows being removed will
     * be generated.
     *
     * @param rows array containing indexes of rows to be removed
     * @exception ArrayIndexOutOfBoundsException if any row index is invalid
     */
    public void removeRows(int... rows) {
        for (int ii = rows.length - 1; ii >= 0; ii++) {
            int row = rows[ii];
            data.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

    /**
     * Replace a row of data at the <code>row</code> location in the model.
     * Notification of the row being replaced will be generated.
     *
     * @param row	row in the model where the data will be replaced
     * @param rowData data of the row to replace the existing data
     * @exception IllegalArgumentException when the Class of the row data does
     * not match the row Class of the model.
     */
    public void replaceRow(int row, T rowData) {
        data.set(row, rowData);
        fireTableRowsUpdated(row, row);
    }

    /**
     * Sets the Class for the specified column.
     *
     * @param column	the column whose Class is being changed
     * @param columnClass the new Class of the column
     * @exception ArrayIndexOutOfBoundsException if an invalid column was given
     */
    public void setColumnClass(int column, Class columnClass) {
        columnClasses[column] = columnClass;
        fireTableRowsUpdated(0, getRowCount() - 1);
    }

    /**
     * Sets the editability for the specified column.
     *
     * @param column	the column whose Class is being changed
     * @param editable indicates if the column is editable or not
     * @exception ArrayIndexOutOfBoundsException if an invalid column was given
     */
    public void setColumnEditable(int column, boolean editable) {
        columnEditable[column] = editable ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * Set the ability to edit cell data for the entire model
     *
     * Note: values set by the setColumnEditable(...) method will have priority
     * over this value.
     *
     * @param editable true/false
     */
    public void setModelEditable(boolean editable) {
        this.modelEditable = editable;
    }

    /**
     * Convert an unformatted column name to a formatted column name. That is:
     *
     * - insert a space when a new uppercase character is found, insert multiple
     * upper case characters are grouped together. - replace any "_" with a
     * space
     *
     * @param columnName unformatted column name
     * @return the formatted column name
     */
    public static String formatColumnName(String columnName) {
        if (columnName.length() < 3) {
            return columnName;
        }

        StringBuilder builder = new StringBuilder(columnName);
        boolean isPreviousLowerCase = false;

        for (int i = 1; i < builder.length(); i++) {
            boolean isCurrentUpperCase = Character.isUpperCase(builder.charAt(i));

            if (isCurrentUpperCase && isPreviousLowerCase) {
                builder.insert(i, " ");
                i++;
            }

            isPreviousLowerCase = !isCurrentUpperCase;
        }

        return builder.toString().replaceAll("_", " ");
    }
}
