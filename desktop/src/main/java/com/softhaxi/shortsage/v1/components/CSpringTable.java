package com.softhaxi.shortsage.v1.components;

import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * Link Reference <a href="http://stackoverflow.com/questions/15126651/how-to-auto-resize-all-columns-of-a-jtable-to-the-same-size">Same Width Column</a>
 * Link Reference <a href="http://stackoverflow.com/questions/13013989/how-to-adjust-jtable-columns-to-fit-the-longest-content-in-column-cells">Fit Data Column</a>
 */
public class CSpringTable extends JTable {
    
    public CSpringTable() {
        setAutoResizeMode(AUTO_RESIZE_OFF);
    }
    
    public CSpringTable(TableModel model) {
        super(model);
        setAutoResizeMode(AUTO_RESIZE_OFF);
    }
    
    @Override
    public void doLayout() {
        int ww = getWidth();
        int cc = getColumnCount();
        int cs = ww / cc;
        for (int ii = 0; ii < cc; ii++) {
            TableColumn clmn = getColumnModel().getColumn(ii);
            clmn.setResizable(false);
            clmn.setPreferredWidth(ww);
        }
        super.doLayout();
    }
    
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }
}
