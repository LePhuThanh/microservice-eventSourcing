package com.phelim.userservice.dto;

//import lombok.Builder;
//import lombok.Data;

import java.time.LocalDate;

//@Data
//@Builder
//public class UserResponseDTO {
//    private Long id;
//
//    private String userId;
//    private String email;
//    private String username;
//    private String firstName;
//    private String lastName;
//    private LocalDate dob;
//    private String name;
//}

public class UserResponseDTO {
    private Long id;

    private String userId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String name;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String userId, String email, String username, String firstName, String lastName, LocalDate dob, String name) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    //Builder
    public static class Builder {
        private Long id;
        private String userId;
        private String email;
        private String username;
        private String firstName;
        private String lastName;
        private LocalDate dob;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder dob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public UserResponseDTO build() {
            return new UserResponseDTO(id, userId, email, username, firstName, lastName, dob, name);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}