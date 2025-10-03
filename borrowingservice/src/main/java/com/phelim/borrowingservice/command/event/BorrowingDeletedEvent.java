package com.phelim.borrowingservice.command.event;

public class BorrowingDeletedEvent {
    private String id;

    public BorrowingDeletedEvent() {
    }

    public BorrowingDeletedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
