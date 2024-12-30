package com.phelim.bookservice.command.event;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BookDeletedEvent {
    private String id;

    public BookDeletedEvent() {
    }

    public BookDeletedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
