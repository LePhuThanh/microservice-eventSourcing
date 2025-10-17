package com.phelim.userservice.dto;

import java.time.LocalDate;

public class CreateUserRequestDTO {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String name;

    public CreateUserRequestDTO() {
    }

    public CreateUserRequestDTO(String email, String username, String firstName, String lastName, LocalDate dob, String name) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}