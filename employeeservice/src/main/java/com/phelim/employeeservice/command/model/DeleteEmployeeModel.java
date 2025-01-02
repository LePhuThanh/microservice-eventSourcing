package com.phelim.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;

public class DeleteEmployeeModel {
    @NotBlank(message = "Id is mandatory")
    private String id;
    public DeleteEmployeeModel() {
    }

    public DeleteEmployeeModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
