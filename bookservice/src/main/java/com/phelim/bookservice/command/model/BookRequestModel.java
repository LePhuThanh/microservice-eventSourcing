package com.phelim.bookservice.command.model;


public class BookRequestModel {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    public BookRequestModel() {
    }

    public BookRequestModel(String id, String name, String author, Boolean isReady) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isReady = isReady;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean getIsReady() {
        return isReady;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsReady(Boolean ready) {
        this.isReady = ready;
    }
}
