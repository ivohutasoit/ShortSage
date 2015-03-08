package com.softhaxi.shortsage.v1.enums;

/**
 *
 * @author Hutasoit
 */
public enum MenuLevel {
    
    ZERO_LEVEL(0),
    FIRST_LEVEL(1),
    SECOND_LEVEL(2);
    
    private final int value;
    
    private MenuLevel(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
