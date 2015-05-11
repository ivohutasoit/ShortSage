package com.softhaxi.shortsage.v1.model.table;

public class SearchContactPersonTableModel extends AbstractTableModel {
  private final static String[] COLUMN_NAMES = {
    "Name", "Phone Number"
  };
  
  private List<ContactPerson> data;
  private HashMap<String, String> columns;
  
  public SearchContactPersonTableModel() {
  
  }
  
  public SearchContactPersonTableModel(List<ContactPerson> data) {
    this.data = data;
  }
  
  public List<ContactPerson> getData() {
    return this.data;
  }
  
  public void setData(List<ContactPerson> data) {
    this.data = data;
    fireTableDataChanged();
  }
  
  public int getColumnCount() {
    if(columns == null)
      return COLUMN_NAMES.length;
    return columns.size();
  }
  
  public int getRowCount() {
    return data.size();
  }
  
  public Object getValueAt(int row, int col) {
    ContactPerson person = data.get(row);
    switch (col) {
      case 0:
       return person.getName();
      case 1:
       return person.getPhone();
    }
  }
  
  public boolean isCellEditable(int row, int col) {
    return false;
  }
}
