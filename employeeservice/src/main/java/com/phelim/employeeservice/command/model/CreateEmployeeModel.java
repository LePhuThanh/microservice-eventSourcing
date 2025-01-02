package com.phelim.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;

public class CreateEmployeeModel {
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @NotBlank(message = "Kin is mandatory")
    private String Kin;

    public CreateEmployeeModel() {
    }

    public CreateEmployeeModel(String firstName, String lastName, String kin) {
        this.firstName = firstName;
        this.lastName = lastName;
        Kin = kin;
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
}
