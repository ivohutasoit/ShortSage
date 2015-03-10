package com.softhaxi.shortsage.v1.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 * The TextPrompt class will display a prompt over top of a text component when
 * the Document of the text field is empty. The Show property is used to
 * determine the visibility of the prompt.
 *
 * The Font and foreground Color of the prompt will default to those properties
 * of the parent text component. You are free to change the properties after
 * class construction.
 * 
 * Reference Link <a href="https://tips4java.wordpress.com/2009/11/29/text-prompt/">Text Prompt</a>
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class CTextPrompt extends JLabel
        implements FocusListener, DocumentListener {

    public enum PromptShowed {
        ALWAYS,
        FOCUS_GAINED,
        FOCUS_LOST;
    }
    
    private JTextComponent component;
    private Document document;
    
    private PromptShowed promptShowed;
    private boolean showPromptOnce;
    private int focustLost;
    
    /**
     * 
     * @param text
     * @param component 
     */
    public CTextPrompt(String text, JTextComponent component) {
        this(text, component, PromptShowed.ALWAYS);
    }
    
    public CTextPrompt(String text, JTextComponent component, PromptShowed promptShowed) {
        this.component = component;
        this.promptShowed = promptShowed;
        this.document = component.getDocument();
        
        setText(text);
        setFont(this.component.getFont());
        setForeground(this.component.getForeground());
        setBorder(new EmptyBorder(this.component.getInsets()));
        setHorizontalAlignment(JLabel.LEADING);
        
        this.component.addFocusListener(this);
        this.document.addDocumentListener(this);
        
        this.component.setLayout(new BorderLayout());
        this.component.add(this);
        checkForPrompt();
    }
    
    /**
     * Convenience method to change the alpha value of the current foreground
     * color to the specific value
     * 
     * @param alpha value in the range of 0 - 10.0
     */
    public void changeAlpha(float alpha) {
        changeAlpha((int) (alpha * 255));    
    }
    
    /**
     * Convenience method to change the alpha value of the current foreground
     * color to the specific value
     * 
     * @param alpha value in the range of 0 - 255.
     */
    public void changeAlpha(int alpha) {
            alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;
            
            Color fg = getForeground();
            int rr = fg.getRed();
            int gg = fg.getGreen();
            int bb = fg.getBlue();
            
            Color withAlpha = new Color(rr, gg, bb, alpha);
            super.setForeground(withAlpha);
    }
    
    /**
     * Convenience method to change the style of the current font.
     * The style value are found in the Font Class. 
     * Common values might be:
     * <ul>
     * <li>Font.BOLD</li>
     * <li>Font.ITALIC</li>
     * <li>Font.BOLD + Font.ITALIC</li>
     * </ul>
     * 
     * @param style value representing the new style of the Font
     */
    public void changeStyle(int style) {
        setFont(getFont().deriveFont(style));    
    }
    
    /**
     * Get the PromptShowed property
     * 
     * @return the PromptShowed property.
     */
    public PromptShowed getPromptShowed() {
        return this.promptShowed;
    }
    
    /**
     * Set the prompt Show property to control when the prompt is shown.
     * Valid values are:
     * <ul>
     * <li>PromptShowed.AWLAYS (default) - always show the prompt</li>
     * <li>PromptShowed.Focus_GAINED - show the prompt when the component gains focus
     *          (and hide the prompt when focus is lost)</li>
     * <li>PromptShowed.Focus_LOST - show the prompt when the component loses focus
     *          (and hide the prompt when focus is gained)</li>
     * </ul>
     * 
     * @param promptShowed a valid PromptShowed enum
     */
    public void setPromptShowed(PromptShowed promptShowed) {
        this.promptShowed = promptShowed;
    }
    
    /**
     * 
     * 
     * @return the showPromptOnce property
     */
    public boolean isShowPromptOnce() {
        return this.showPromptOnce;
    }
    
    /**
     * Show the prompt once. Once the component has gained/lost focus
     * once, the prompt will not be shown again.
     * 
     * @param showPromptOnce  when true the prompt will only be shown once,
     * otherwise it will be shown repeatedly.
     */
    public void setShowPromptOnce(boolean showPromptOnce) {
        this.showPromptOnce = showPromptOnce;
    }

    //<editor-fold defaultstate="collapsed" desc=" Implementation Methods from Listener ">
    @Override
    public void focusGained(FocusEvent e) {
        checkForPrompt();
    }

    @Override
    public void focusLost(FocusEvent e) {
        focustLost++;
        checkForPrompt();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    //</editor-fold>
    /**
     * Check whether the prompt should be visible or not. The visibility
     * will change on updates to the Document and on focus changes.
     */
    private void checkForPrompt() {
        // Text has been entered, remove the prompt
        if(document.getLength() > 0) {
                setVisible(false);
                return;
        }
        
        // Prompt has already been show once, remove it
        if(showPromptOnce && focustLost > 0) {
                setVisible(false);
                return;
        }
        
        // Check the show property and component focus to determine if 
        // the prompt should be displayed
        if(component.hasFocus()) {
                if(promptShowed == PromptShowed.ALWAYS 
                || promptShowed == PromptShowed.FOCUS_GAINED) {
                        setVisible(true);
                } else {
                        setVisible(false);
                }
        } else {
                if(promptShowed == PromptShowed.ALWAYS 
                || promptShowed == PromptShowed.FOCUS_LOST) {
                        setVisible(true);
                } else {
                        setVisible(false);
                }
        }
    }
}
