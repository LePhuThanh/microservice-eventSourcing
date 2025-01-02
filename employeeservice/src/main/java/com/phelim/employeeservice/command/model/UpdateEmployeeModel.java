package com.phelim.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateEmployeeModel {
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Kin is mandatory")
    private String Kin;
    //@NotNull(message = "isDisciplined is mandatory")
    private Boolean isDisciplined;

    public UpdateEmployeeModel() {
    }

    public UpdateEmployeeModel(String firstName, String lastName, String kin, Boolean isDisciplined) {
        this.firstName = firstName;
        this.lastName = lastName;
        Kin = kin;
        this.isDisciplined = isDisciplined;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getKin() {
        return Kin;
    }

    public void setKin(String kin) {
        Kin = kin;
    }

    public Boolean getDisciplined() {
        return isDisciplined;
    }

    public void setDisciplined(Boolean disciplined) {
        isDisciplined = disciplined;
    }
}
