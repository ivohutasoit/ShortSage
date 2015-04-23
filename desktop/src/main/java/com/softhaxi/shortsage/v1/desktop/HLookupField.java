package com.softhaxi.shortsage.v1.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jdesktop.swingx.JXTextField;

/**
 * http://stackoverflow.com/questions/4330076/joptionpane-showmessagedialog-truncates-jtextarea-message
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public abstract class HLookupField extends JPanel
    implements ActionListener {

    private JXTextField tfLookup;
    private JButton bbLookup;

    private String promptText;

    public HLookupField(String promptText) {
        this.promptText = promptText;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        tfLookup = new JXTextField(promptText);
        bbLookup = new JButton(
                new ImageIcon(getClass().getClassLoader().getResource("images/ic_lookup_16.png")));

        add(tfLookup, BorderLayout.CENTER);
        add(bbLookup, BorderLayout.EAST);

        setBackground(tfLookup.getBackground());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setPreferredSize(new Dimension(getWidth(), 23));
 
        bbLookup.setPreferredSize(new Dimension(bbLookup.getIcon().getIconWidth() + 5, 
                bbLookup.getIcon().getIconHeight()));
        bbLookup.setFocusPainted(false);
        bbLookup.setContentAreaFilled(false);
        bbLookup.setBorderPainted(false);
        bbLookup.setOpaque(true);
        bbLookup.addActionListener(this);
        tfLookup.setBorder(new EmptyBorder(0, 2, 0, 2));
    }

    /**
     *
     * @param editable
     */
    public void setEditable(boolean editable) {
        tfLookup.setEditable(editable);
    }

    /**
     *
     * @return
     */
    public String getText() {
        return tfLookup.getText();
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        tfLookup.setText(text);
    }

    /**
     *
     * @param listener
     */
    public void addActionListener(ActionListener listener) {
        bbLookup.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lookupPerformed();
    }
    
    public abstract void lookupPerformed();
}
