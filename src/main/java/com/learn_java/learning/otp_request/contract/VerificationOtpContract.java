package com.learn_java.learning.otp_request.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerificationOtpContract {
    @JsonProperty("status")
    private Boolean status;

    @JsonProperty("message")
    private String message;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
