package com.softhaxi.shortsage.v1.dummies;

import com.softhaxi.shortsage.v1.models.Message;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class MessageDummy {
    /**
     * 
     * @return 
     */
    public static List<Message> getInbox() {
        return new ArrayList<>();
    }
    
    /**
     * 
     * @return 
     */
    public static List<Message> getOutbox() {
        return new ArrayList<>();
    }
    
    /**
     * 
     * @return 
     */
    public static List<Message> getBulk() {
        return new ArrayList<>();
    }
}
