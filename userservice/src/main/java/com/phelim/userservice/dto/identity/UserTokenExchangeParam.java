package com.phelim.userservice.dto.identity;

public class UserTokenExchangeParam {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String scope;
    private String username;
    private String password;
    public UserTokenExchangeParam() {
    }

    public UserTokenExchangeParam(String grant_type, String client_id, String client_secret, String scope, String username, String password) {
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.scope = scope;
        this.username = username;
        this.password = password;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //Builder
    public static class Builder {
        private String grant_type;
        private String client_id;
        private String client_secret;
        private String scope;
        private String username;
        private String password;

        public Builder grantType(String grant_type) {
            this.grant_type = grant_type;
            return this;
        }

        public Builder clientId(String client_id) {
            this.client_id = client_id;
            return this;
        }

        public Builder clientSecret(String client_secret) {
            this.client_secret = client_secret;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserTokenExchangeParam build() {
            return new UserTokenExchangeParam(
                    grant_type, client_id, client_secret, scope, username, password
            );
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
