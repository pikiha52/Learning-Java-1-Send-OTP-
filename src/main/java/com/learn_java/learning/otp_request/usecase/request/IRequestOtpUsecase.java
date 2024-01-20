package com.learn_java.learning.otp_request.usecase.request;

import org.springframework.http.ResponseEntity;

import com.learn_java.learning.app.AppResponse;
import com.learn_java.learning.global.response.RequestOtpResponse;
import com.learn_java.learning.otp_request.command.RequestOtpCommand;

public interface IRequestOtpUsecase {
    public ResponseEntity<AppResponse<RequestOtpResponse>> requestOtp(RequestOtpCommand otpCommand);
}
