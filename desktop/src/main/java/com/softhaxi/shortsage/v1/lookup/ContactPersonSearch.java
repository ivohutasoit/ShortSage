package com.softhaxi.shortsage.v1.lookup;

import com.softhaxi.shortsage.v1.dto.ContactPerson;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class ContactPersonSearch extends JPanel {
    
    /**
     * 
     */
    private List<ContactPerson> aContacts, sContacts;
    
    /**
     * 
     */
    public ContactPersonSearch() {
        this(new ArrayList<ContactPerson>());
    }
    
    /**
     * 
     * @param contacts 
     */
    public ContactPersonSearch(List<ContactPerson> contacts) {
        this.sContacts = contacts;
        
        initComponents();
        initListeners();
    }
    
    /**
     * 
     */
    private void initComponents() {
        
    }
    
    /**
     * 
     */
    private void initListeners() {
        
    }
    
    /**
     * 
     */
    private void initData() {
        
    }
    
    /**
     * 
     */
    private void loadData() {
        
    }
}
