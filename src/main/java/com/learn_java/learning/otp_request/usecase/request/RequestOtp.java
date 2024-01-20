package com.learn_java.learning.otp_request.usecase.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn_java.learning.app.AppResponse;
import com.learn_java.learning.global.response.RequestOtpResponse;
import com.learn_java.learning.otp_request.command.RequestOtpCommand;
import com.learn_java.learning.otp_request.contract.RequestOtpContract;
import com.learn_java.learning.otp_request.handler.RequestOtpHandler;

@Component
public class RequestOtp implements IRequestOtpUsecase {
    @Autowired
    private RequestOtpHandler reqOtpHandler;

    @Override
    public ResponseEntity<AppResponse<RequestOtpResponse>> requestOtp(RequestOtpCommand otpCommand) {
        final AppResponse<RequestOtpResponse> requestOtpResponse = new AppResponse<>();
        RequestOtpContract requestOtpContract = reqOtpHandler.execute(otpCommand);
        requestOtpResponse.setCode(HttpStatus.CREATED.value());
        requestOtpResponse.setErrorMessage(null);
        requestOtpResponse.setMessage("Success created request otp.");
        requestOtpResponse.setData(requestOtpContract);
        return ResponseEntity.ok().body(requestOtpResponse);
    }
    
}
