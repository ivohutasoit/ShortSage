package com.softhaxi.shortsage.v1.table.model;

/**
 *  A TableModel that better supports the processing of rows of data. That
 *  is, the data is treated more like a row than an individual cell. Hopefully
 *  this class can be used as a parent class instead of extending the
 *  AbstractTableModel when you need custom models that contain row related
 *  data.
 *
 *  A few methods have also been added to make it easier to customize
 *  properties of the model, such as the column class and column editability.
 *
 *  Any class that extends this class must make sure to invoke the
 *  setRowClass() and setDataAndColumnNames() methods either directly,
 *  by using the various constructors, or indirectly.
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class RowTableModel<T> extends AbstractTableModel {
    protected List<T> data;
    protected List<String> columnNames;
    protected Class[] columnClasses;
    protected Boolean[] columnEditable;
    private Class rowClass = Object.class;
    private boolean modelEditable = true;
    
    /**
	   *  Constructs a <code>RowTableModel</code> with the row class.
  	 *
  	 *  This value is used by the getRowsAsArray() method.
  	 *
  	 *  Sub classes creating a model using this constructor must make sure
  	 *  to invoke the setDataAndColumnNames() method.
  	 *
  	 * @param rowClass  the class of row data to be added to the model
  	 */
    protected RowTableModel(Class rowClass) {
        setRowClass(rowClass);
    }
    
    /**
  	 *  Constructs a <code>RowTableModel</code> with column names.
  	 *
  	 *  Each column's name will be taken from the <code>columnNames</code>
  	 *  List and the number of colums is determined by thenumber of items
  	 *  in the <code>columnNames</code> List.
  	 *
  	 *  Sub classes creating a model using this constructor must make sure
  	 *  to invoke the setRowClass() method.
  	 *
  	 * @param columnNames	   <code>List</code> containing the names
  	 *							of the new columns
  	 */
    protected RowTableModel(List<String> columnNames) {
        this(new ArrayList<T>(), columnNames);
    }
    
    /**
  	 *  Constructs a <code>RowTableModel</code> with initial data and
  	 *  customized column names.
  	 *
  	 *  Each item in the <code>data</code> List must also be a List Object
  	 *  containing items for each column of the row.
  	 *
  	 *  Each column's name will be taken from the <code>columnNames</code>
  	 *  List and the number of colums is determined by thenumber of items
  	 *  in the <code>columnNames</code> List.
  	 *
  	 *  Sub classes creating a model using this constructor must make sure
  	 *  to invoke the setRowClass() method.
  	 *
  	 * @param data		 the data of the table
  	 * @param columnNames	   <code>List</code> containing the names
  	 *							of the new columns
  	 */
    protected RowTableModel(List<T> data, List<String> columnNames) {
        setDataAndColumnNames(data, columnNames);
    }
    
    /**
  	 *  Full Constructor for creating a <code>RowTableModel</code>.
  	 *
  	 *  Each item in the <code>modelData</code> List must also be a List Object
  	 *  containing items for each column of the row.
  	 *
  	 *  Each column's name will be taken from the <code>columnNames</code>
  	 *  List and the number of colums is determined by thenumber of items
  	 *  in the <code>columnNames</code> List.
  	 *
  	 *  @param modelData	the data of the table
  	 *  @param columnNames	<code>List</code> containing the names
  	 *						of the new columns
  	 *  @param rowClass     the class of row data to be added to the model
  	 */
    protected RowTableModel(List<T> data, List<String> columnNames, Class rowClass) {
        setDataAndColumnNames(data, columnNames);
        setRowClass(rowClass);
    }
    
    /**
  	 *  Reset the data and column names of the model.
  	 *
  	 *	A fireTableStructureChanged event will be generated.
  	 *
  	 * @param modelData		 the data of the table
  	 * @param columnNames	   <code>List</code> containing the names
  	 *							of the new columns
  	 */
    protected void setDataAndColumnNames(List<T> data, List<String> columnNames) {
        this.data = data;
        this.columnNames = columnNames;
        columnClasses = new Class[getColumnCount()];
        columnEditable = new Boolean[getColumnCount()];
        fireTableStructureChanged();
    }
    
    /**
  	 *  The class of the Row being stored in the TableModel
  	 *
  	 *  This is required for the getRowsAsArray() method to return the
  	 *  proper class of row.
  	 *
  	 * @param rowClas		 the class of the row
  	 */
    protected void setRowClass(Class rowClass) {
        this.rowClass = rowClass;
    }
    
    /**
  	 *  Returns the Class of the queried <code>column</code>.
  
  	 *  First it will check to see if a Class has been specified for the
  	 *  <code>column</code> by using the <code>setColumnClass</code> method.
  	 *  If not, then the superclass value is returned.
  	 *
  	 *  @param column  the column being queried
  	 *  @return the Class of the column being queried
  	 */
    public Class getColumnClass(int column) {
        Class columnClass = null;
        
        // Get the class, if set for the specific column
        if(column < columnClasses.length)
          columnClass = columnClasses[column];
          
        // Get Default Class
        if(columnClass == null)
          columnClass = super.getColumnClass(column);
          
        return columnClass;
    }
}
