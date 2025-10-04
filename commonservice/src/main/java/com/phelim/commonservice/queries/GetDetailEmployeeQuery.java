package com.phelim.commonservice.queries;

public class GetDetailEmployeeQuery {
    private String id;

    public GetDetailEmployeeQuery() {
    }

    public GetDetailEmployeeQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

