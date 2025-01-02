package com.phelim.bookservice.command.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BookRequestModel {
    private String id;
    @NotBlank(message = "Name s mandatory")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @NotBlank(message = "Name s mandatory")
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
