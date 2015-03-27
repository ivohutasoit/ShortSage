package com.softhaxi.shortsage.v1.table;

/**
 * Reference <a href="http://blog.lesc.se/2009/03/beantablemodel-as-tablemodel-for-swing.html">Bean Table Model</a>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
public class NewBeanTableModel<T> extends AbstractTableModel {
  private List<T> data;
  private List<BeanColumn> columns = new ArrayList<BeanColumn>():
  private Class<?> clazz;
  
  public NewBeanTableModel(Class clazz) {
    this.clazz = clazz;
  }
  
  public void setData(List<T> data) {
    this.data = data;
    fireTableDataChanged();
  }
  
  public void getData() {
    return this.data;
  }
}
