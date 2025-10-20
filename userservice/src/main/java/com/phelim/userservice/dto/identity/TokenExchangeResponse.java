package com.phelim.userservice.dto.identity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenExchangeResponse {
    private String accessToken;
    private String expiresIn;
    private String refreshExpiresIn;
    private String tokenType;
    private String idToken;
    private String scope;

    public TokenExchangeResponse() {
    }

    public TokenExchangeResponse(String accessToken, String expiresIn, String refreshExpiresIn, String tokenType, String idToken, String scope) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshExpiresIn = refreshExpiresIn;
        this.tokenType = tokenType;
        this.idToken = idToken;
        this.scope = scope;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void setRefreshExpiresIn(String refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    //Builder
    public static class Builder {
        private String accessToken;
        private String expiresIn;
        private String refreshExpiresIn;
        private String tokenType;
        private String idToken;
        private String scope;

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder expiresIn(String expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public Builder refreshExpiresIn(String refreshExpiresIn) {
            this.refreshExpiresIn = refreshExpiresIn;
            return this;
        }

        public Builder tokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public Builder idToken(String idToken) {
            this.idToken = idToken;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public TokenExchangeResponse build() {
            return new TokenExchangeResponse(accessToken, expiresIn, refreshExpiresIn, tokenType, idToken, scope);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
