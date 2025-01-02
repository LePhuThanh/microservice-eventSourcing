package com.phelim.employeeservice.command.event;

public class EmployeeDeletedEvent {
    private String id;
    public EmployeeDeletedEvent() {
    }

    public EmployeeDeletedEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
