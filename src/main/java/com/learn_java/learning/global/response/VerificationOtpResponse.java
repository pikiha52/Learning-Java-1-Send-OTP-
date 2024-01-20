package com.learn_java.learning.global.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn_java.learning.otp_request.contract.RequestOtpContract;

public class VerificationOtpResponse {
    private static VerificationOtpResponse mInstance = null;

    private VerificationOtpResponse() {
    }

    public static synchronized VerificationOtpResponse getInstance() {
        if (mInstance == null) {
            mInstance = new VerificationOtpResponse();
        }
        return mInstance;
    }

    @JsonProperty("data")
    public RequestOtpContract data;

    public void setData(RequestOtpContract dataRequest) {
        this.data = dataRequest;
    }

    public VerificationOtpResponse mapToObject(RequestOtpContract item) {
        final VerificationOtpResponse response = new VerificationOtpResponse();
        response.setData(item);
        return response;
    }
}
