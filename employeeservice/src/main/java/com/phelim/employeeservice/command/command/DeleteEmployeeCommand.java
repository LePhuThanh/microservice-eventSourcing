package com.phelim.employeeservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DeleteEmployeeCommand {
    @TargetAggregateIdentifier
    private String id;

    public DeleteEmployeeCommand() {
    }

    public DeleteEmployeeCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
