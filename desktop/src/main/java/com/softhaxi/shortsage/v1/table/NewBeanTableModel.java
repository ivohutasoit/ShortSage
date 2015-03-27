package com.softhaxi.shortsage.v1.table;

import com.softhaxi.shortsage.v1.entities.BeanColumn;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Reference
 * <a href="http://blog.lesc.se/2009/03/beantablemodel-as-tablemodel-for-swing.html">Bean
 * Table Model</a>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class NewBeanTableModel<T> extends AbstractTableModel {

    private List<T> data;
    private List<BeanColumn> columns = new ArrayList<BeanColumn>();
    private Class<?> clazz;

    public NewBeanTableModel(Class clazz) {
        this.clazz = clazz;
    }

    public void setData(List<T> data) {
        this.data = data;
        fireTableDataChanged();
    }

    public List<T> getData() {
        return this.data;
    }

    @Override
    public int getRowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
