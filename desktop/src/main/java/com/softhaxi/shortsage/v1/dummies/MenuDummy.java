package com.softhaxi.shortsage.v1.dummies;

import com.softhaxi.shortsage.v1.entities.SystemMenu;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Hutasoit
 */
public class MenuDummy {
    /**
     * Dummy Menu for Workspace
     * @return 
     */
    public static DefaultMutableTreeNode getNodeWorkpace() {
        DefaultMutableTreeNode workspace = new DefaultMutableTreeNode("Workspace");
        
        DefaultMutableTreeNode mainMenu = new DefaultMutableTreeNode("Main Menu");
        
        SystemMenu dashboard = new SystemMenu();
        dashboard.setKey("G0001");
        dashboard.setTitle("G0001");
        dashboard.setName("Dashboard");
        dashboard.setProgram("com.softhaxi.shortsage.v1.pages.DashboardPage");
        dashboard.setIcon("images/ic_dashboard.png");
        dashboard.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(dashboard));
        
        SystemMenu importData = new SystemMenu();
        importData.setKey("G0002");
        importData.setTitle("G0002");
        importData.setName("Import Data");
        importData.setProgram("com.softhaxi.shortsage.v1.pages.ImportDataPage");
        importData.setIcon("images/ic_download.png");
        importData.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(importData));
        
        DefaultMutableTreeNode extMenu = new DefaultMutableTreeNode("Extension [Underconstruction]");
        DefaultMutableTreeNode setupMenu = new DefaultMutableTreeNode("Setup [Underconstruction]");
        
        workspace.add(mainMenu);
        workspace.add(extMenu);
        workspace.add(setupMenu);
        return workspace;
    }
    
    /**
     * 
     * @return 
     */
    public static DefaultMutableTreeNode getNodeModem() {
        DefaultMutableTreeNode modem = new DefaultMutableTreeNode("Modem");
        
        DefaultMutableTreeNode mainMenu = new DefaultMutableTreeNode("Main Menu");
        
        SystemMenu inbox = new SystemMenu();
        inbox.setKey("M0001");
        inbox.setTitle("M0001");
        inbox.setName("Inbox Folder");
        inbox.setProgram("com.softhaxi.shortsage.v1.pages.InboxPage");
        inbox.setIcon("images/ic_inbox.png");
        inbox.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(inbox));
        
        SystemMenu outbox = new SystemMenu();
        outbox.setKey("M0002");
        outbox.setTitle("M0002");
        outbox.setName("Outbox Folder");
        outbox.setProgram("com.softhaxi.shortsage.v1.pages.OutboxPage");
        outbox.setIcon("images/ic_outbox.png");
        outbox.setKeyShow(true);
        mainMenu.add(new DefaultMutableTreeNode(outbox));
        
        DefaultMutableTreeNode entMenu = new DefaultMutableTreeNode("Entities");
        SystemMenu group = new SystemMenu();
        group.setKey("M0101");
        group.setTitle("M0101");
        group.setName("Contact Group");
        group.setProgram("com.softhaxi.shortsage.v1.pages.ContactGroupPage");
        group.setIcon("images/ic_group.png");
        group.setKeyShow(true);
        entMenu.add(new DefaultMutableTreeNode(group));
        
        SystemMenu person = new SystemMenu();
        person.setKey("M0102");
        person.setTitle("M0102");
        person.setName("Contact Person");
        person.setProgram("com.softhaxi.shortsage.v1.pages.ContactPersonPage");
        person.setIcon("images/ic_contact.png");
        person.setKeyShow(true);
        entMenu.add(new DefaultMutableTreeNode(person));
        
        SystemMenu template = new SystemMenu();
        template.setKey("M0103");
        template.setTitle("M0103");
        template.setName("Message Template");
        template.setProgram("com.softhaxi.shortsage.v1.pages.MessageTemplatePage");
        template.setIcon("images/ic_group.png");
        template.setKeyShow(true);
        entMenu.add(new DefaultMutableTreeNode(template));
        
        SystemMenu bulk = new SystemMenu();
        bulk.setKey("M0104");
        bulk.setTitle("M0104");
        bulk.setName("Bulk Message");
        bulk.setProgram("com.softhaxi.shortsage.v1.pages.BulkMessagePage");
        bulk.setIcon("images/ic_group.png");
        bulk.setKeyShow(true);
        entMenu.add(new DefaultMutableTreeNode(bulk));
        
        DefaultMutableTreeNode extMenu = new DefaultMutableTreeNode("Extension [Underconstruction]");
        
        modem.add(mainMenu);
        modem.add(entMenu);
        modem.add(extMenu);
        return modem;
    }
}
