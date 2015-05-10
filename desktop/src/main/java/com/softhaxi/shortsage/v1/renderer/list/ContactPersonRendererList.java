package com.softhaxi.shortsage.v1.renderer.list;

import com.softhaxi.shortsage.v1.dto.ContactPerson;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 * Render list of contacts Reference
 * <a href="http://stackoverflow.com/questions/22266506/how-to-add-image-in-jlist">List
 * With Image</a>
 * <a href="http://www.nguyenvanquan7826.com/2014/04/25/java-swing-tuy-bien-jlist-jlist-custom-renderer/?lang=en">Testing</a>
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ContactPersonRendererList extends DefaultListCellRenderer {

//    private JLabel lfIcon;
//    private JLabel lfName;
//    private JLabel lfPhone;

    /**
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList list,
            Object value, int index, boolean isSelected, boolean cellHasFocus) {
//        JPanel pItem = new JPanel();
//        lfIcon = new JLabel();
//        lfName = new JLabel();
//        lfPhone = new JLabel();
//
//        GridBagLayout layout = new GridBagLayout();
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        pItem.setLayout(layout);
//        gbc.fill = GridBagConstraints.VERTICAL;
//        gbc.gridx = 0;
//        gbc.insets =new Insets(4, 4, 4, 4);
//        layout.setConstraints(lfIcon, gbc);
//        pItem.add(lfIcon);
//
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.gridx= 1;
//        gbc.gridy= 0;
//        layout.setConstraints(lfName, gbc);
//        pItem.add(lfName);
//        
//        layout.setConstraints(lfPhone, gbc);
//        pItem.add(lfPhone);
//        
        ContactPerson person = (ContactPerson) value;
//        
//        lfIcon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_contact_person_48.png")));
//        lfName.setText(String.format("%s, %s", person.getLastName(), person.getFirstName()));
//        lfPhone.setText(person.getPhone());
        
        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, String.format("%s, %s", person.getLastName(), person.getFirstName()), index, isSelected, cellHasFocus);
        label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/ic_contact_person_24.png")));
        label.setHorizontalTextPosition(JLabel.RIGHT);
//        label.setFont(font);
        return label;
    }

}
