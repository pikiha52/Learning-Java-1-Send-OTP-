package com.learn_java.learning.otp_request.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestOtpContract {
    @JsonProperty("status")
    private boolean status;
    
    @JsonProperty("expire")
    private Number expire;
    
    @JsonProperty("last_request")
    private Number last_request;

    @JsonProperty("next_request")
    private Number next_request; 

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setExpire(Number expire) {
        this.expire = expire;
    }

    public void setLastRequest(Number lastRequest) {
        this.last_request = lastRequest;
    }

    public void setNextRequest(Number nextRequest) {
        this.next_request = nextRequest;
    }

    public Boolean getStatus() {
        return status;
    }
}
