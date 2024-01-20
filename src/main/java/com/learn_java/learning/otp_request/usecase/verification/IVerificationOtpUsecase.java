package com.learn_java.learning.otp_request.usecase.verification;

import org.springframework.http.ResponseEntity;

import com.learn_java.learning.app.AppResponse;
import com.learn_java.learning.global.response.VerificationOtpResponse;
import com.learn_java.learning.otp_request.command.VerificationOtpCommand;

public interface IVerificationOtpUsecase {
    public ResponseEntity<AppResponse<VerificationOtpResponse>> verificationOtp(VerificationOtpCommand verificationOtpCommand);
}
