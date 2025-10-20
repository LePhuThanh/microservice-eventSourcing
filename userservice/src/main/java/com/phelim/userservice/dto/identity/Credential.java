package com.phelim.userservice.dto.identity;

public class Credential {
    private String type;
    private String value;
    private boolean temporary;

    public Credential() {
    }

    public Credential(String type, String value, boolean temporary) {
        this.type = type;
        this.value = value;
        this.temporary = temporary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }
    //Builder
    public static class Builder {
        private String type;
        private String value;
        private boolean temporary;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder temporary(boolean temporary) {
            this.temporary = temporary;
            return this;
        }

        public Credential build() {
            return new Credential(type, value, temporary);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
