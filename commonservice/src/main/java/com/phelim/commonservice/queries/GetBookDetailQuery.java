package com.phelim.commonservice.queries;


public class GetBookDetailQuery {
    private String id;

    public GetBookDetailQuery() {
    }
    public GetBookDetailQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
