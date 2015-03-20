package com.softhaxi.shortsage.v1.enums;

public enum MessageFolder {
  INBOX(1), OUTBOX(2), TEMPLATE(3);

  private final int value;
  
  private MessageFolder(int value) {
    this.value = value;
  }
}
