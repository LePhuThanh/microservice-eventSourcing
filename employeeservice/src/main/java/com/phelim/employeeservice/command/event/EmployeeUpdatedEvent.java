package com.phelim.employeeservice.command.event;

public class EmployeeUpdatedEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String Kin;
    private Boolean isDisciplined;

    public EmployeeUpdatedEvent() {
    }

    public EmployeeUpdatedEvent(String id, String firstName, String lastName, String kin, Boolean isDisciplined) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        Kin = kin;
        this.isDisciplined = isDisciplined;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
