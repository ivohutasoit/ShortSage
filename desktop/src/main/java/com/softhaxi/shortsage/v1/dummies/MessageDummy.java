package com.softhaxi.shortsage.v1.dummies;

import java.util.List;
import java.util.ArrayList;

public class MessageDummy {
    public static List<Message> getInbox() {
        return new ArrayList<Message>();
    }
    
    public static List<Message> getOutbox() {
        return new ArrayList<Message>();
    }
    
    public static List<Message> getBulk() {
        return new ArrayList<Message>();
    }
}
