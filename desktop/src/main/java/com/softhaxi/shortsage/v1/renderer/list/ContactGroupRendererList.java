package com.softhaxi.shortsage.v1.renderer.list;

import com.softhaxi.shortsage.v1.dto.ContactGroup;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ContactGroupRendererList extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        ContactGroup group = (ContactGroup) value;
        
        JLabel label = (JLabel) super.getListCellRendererComponent(list, 
                String.format("%s", group.getName().toUpperCase()), index, isSelected, cellHasFocus);
        return label;
    }
}
