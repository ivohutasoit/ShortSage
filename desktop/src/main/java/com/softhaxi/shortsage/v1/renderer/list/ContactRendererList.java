package com.softhaxi.shortsage.v1.renderer.list;

public class ContactRendererList extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = null;
        if(value instanceof ContactGroup) {
            ContactGroup group = (ContactGroup) value;
            label = (JLabel) super.getListCellRendererComponent(list, 
                String.format("%s", group.getName().toUpperCase()), index, isSelected, cellHasFocus);
            label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_contact_group_16.png")));
        } else if(value instanceof ContactPerson) {
            ContactPerson person = (ContactPerson) value;
            label = (JLabel) super.getListCellRendererComponent(
                list, String.format("%s, %s", person.getLastName(), person.getFirstName()), index, isSelected, cellHasFocus);
            label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_contact_person_16.png")));
        }
        label.setHorizontalTextPosition(JLabel.RIGHT);
        return label;
    }
}
