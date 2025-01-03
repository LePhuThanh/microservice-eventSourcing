package com.phelim.employeeservice.query.queries;


public class GetAllEmployeeQuery {
    private Boolean isDisciplined;

    public GetAllEmployeeQuery() {
    }

    public GetAllEmployeeQuery(Boolean isDisciplined) {
        this.isDisciplined = isDisciplined;
    }

    public Boolean getDisciplined() {
        return isDisciplined;
    }

    public void setDisciplined(Boolean disciplined) {
        isDisciplined = disciplined;
    }
}
