package com.phelim.userservice.dto.identity;

import java.util.List;

public class UserCreationParam {
    private String username;
    private boolean enabled;
    private String email;
    private boolean emailVerified;
    private String firstName;
    private String lastName;
    List<Credential> credentials;

    public UserCreationParam() {
    }

    public UserCreationParam(String username, boolean enabled, String email, boolean emailVerified, String firstName, String lastName, List<Credential> credentials) {
        this.username = username;
        this.enabled = enabled;
        this.email = email;
        this.emailVerified = emailVerified;
        this.firstName = firstName;
        this.lastName = lastName;
        this.credentials = credentials;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
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

    public List<Credential> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<Credential> credentials) {
        this.credentials = credentials;
    }
    //Builder
    public static class Builder {
        private String username;
        private boolean enabled;
        private String email;
        private boolean emailVerified;
        private String firstName;
        private String lastName;
        private List<Credential> credentials;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder emailVerified(boolean emailVerified) {
            this.emailVerified = emailVerified;
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

        public Builder credentials(List<Credential> credentials) {
            this.credentials = credentials;
            return this;
        }

        public UserCreationParam build() {
            return new UserCreationParam(username, enabled, email, emailVerified, firstName, lastName, credentials);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
