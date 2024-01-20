package com.learn_java.learning.global.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn_java.learning.otp_request.contract.RequestOtpContract;

public class RequestOtpResponse {
    private static RequestOtpResponse mInstance = null;
    private RequestOtpResponse() {}

    public static synchronized RequestOtpResponse getInstance() {
        if (mInstance == null) {
            mInstance = new RequestOtpResponse();
        }
        return mInstance;
    }

    @JsonProperty("data")
    public RequestOtpContract data;

    public void setData(RequestOtpContract dataRequest) {
        this.data = dataRequest;
    }

    public RequestOtpResponse mapToObject(RequestOtpContract item) {
        final RequestOtpResponse response = new RequestOtpResponse();
        response.setData(item);
        return response;
    }
}
