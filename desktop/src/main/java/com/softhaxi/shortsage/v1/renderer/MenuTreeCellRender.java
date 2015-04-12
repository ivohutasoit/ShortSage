package com.softhaxi.shortsage.v1.renderer;

import com.softhaxi.shortsage.v1.entities.SystemMenu;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class MenuTreeCellRender extends DefaultTreeCellRenderer {
    
    /**
     * 
     * @param tree
     * @param value
     * @param selected
     * @param expanded
     * @param leaf
     * @param row
     * @param hasFocus
     * @return 
     */
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if (leaf) {
            if (o instanceof SystemMenu) {
                SystemMenu menu = (SystemMenu) o;
                if (menu.getIcon() != null) {
                    setIcon(new ImageIcon(getClass().getClassLoader().getResource(menu.getIcon())));
                } else {
                    setIcon(null);
                }
            }
        } else {
            setIcon(null);
        }
        return this;
    }
}
