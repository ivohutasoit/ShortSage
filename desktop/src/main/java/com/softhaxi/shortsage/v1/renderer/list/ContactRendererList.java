package com.softhaxi.shortsage.v1.renderer.list;

import com.softhaxi.shortsage.v1.dto.ContactGroup;
import com.softhaxi.shortsage.v1.dto.ContactPerson;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

public class ContactRendererList extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = null;
        if (value instanceof ContactGroup) {
            ContactGroup group = (ContactGroup) value;
            label = (JLabel) super.getListCellRendererComponent(list,
                    String.format("%s", group.getName().toUpperCase()), index, isSelected, cellHasFocus);
            label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_group.png")));
        } else if (value instanceof ContactPerson) {
            ContactPerson person = (ContactPerson) value;
            label = (JLabel) super.getListCellRendererComponent(
                    list, String.format("%s, %s", person.getLastName(), person.getFirstName()), index, isSelected, cellHasFocus);
            label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_contact.png")));
        }
        if (label != null) {
            label.setHorizontalTextPosition(JLabel.RIGHT);
        }
        return label;
    }
}
