package com.phelim.userservice.dto.identity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TokenExchangeLoginResponse {
    private String accessToken;
    private String expiresIn;
    private String refreshExpiresIn;
    private String refreshToken;
    private String tokenType;
    private String idToken;
    private String notBeforePolicy;
    private String sessionState;
    private String scope;

    public TokenExchangeLoginResponse() {
    }

    public TokenExchangeLoginResponse(String accessToken, String expiresIn, String refreshExpiresIn, String refreshToken, String tokenType, String idToken, String notBeforePolicy, String sessionState, String scope) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshExpiresIn = refreshExpiresIn;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.idToken = idToken;
        this.notBeforePolicy = notBeforePolicy;
        this.sessionState = sessionState;
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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

    public String getNotBeforePolicy() {
        return notBeforePolicy;
    }

    public void setNotBeforePolicy(String notBeforePolicy) {
        this.notBeforePolicy = notBeforePolicy;
    }

    public String getSessionState() {
        return sessionState;
    }

    public void setSessionState(String sessionState) {
        this.sessionState = sessionState;
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
        private String refreshToken;
        private String tokenType;
        private String idToken;
        private String notBeforePolicy;
        private String sessionState;
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

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
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

        public Builder notBeforePolicy(String notBeforePolicy) {
            this.notBeforePolicy = notBeforePolicy;
            return this;
        }

        public Builder sessionState(String sessionState) {
            this.sessionState = sessionState;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public TokenExchangeLoginResponse build() {
            return new TokenExchangeLoginResponse(
                    accessToken, expiresIn, refreshExpiresIn, refreshToken,
                    tokenType, idToken, notBeforePolicy, sessionState, scope
            );
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
