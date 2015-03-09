package com.softhaxi.shortsage.v1.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * Reference Link <a href="https://tips4java.wordpress.com/2009/11/29/text-prompt/">Text Prompt</a>
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class CTextPrompt extends JLabel
        implements FocusListener, DocumentListener {

    public enum Show {
        ALWAYS,
        FOCUS_GAINED,
        FOCUS_LOST;
    }
    
    /**
     * 
     * @param text
     * @param component 
     */
    public CTextPrompt(String text, JTextComponent component) {
        
    }

    //<editor-fold defaultstate="collapsed" desc=" Implementation Methods from Listener ">
    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    //</editor-fold>
}
