package com.learn_java.learning.otp_request.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn_java.learning.app.AppResponse;
import com.learn_java.learning.global.response.RequestOtpResponse;
import com.learn_java.learning.global.response.VerificationOtpResponse;
import com.learn_java.learning.otp_request.command.RequestOtpCommand;
import com.learn_java.learning.otp_request.command.VerificationOtpCommand;
import com.learn_java.learning.otp_request.usecase.request.IRequestOtpUsecase;
import com.learn_java.learning.otp_request.usecase.verification.IVerificationOtpUsecase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("otp")
public class RequestOtpController {
    @Autowired
    private IRequestOtpUsecase iRequestOtp;
    @Autowired
    private IVerificationOtpUsecase iVerificationOtpUsecase;

    @PostMapping("/request")
    public ResponseEntity<AppResponse<RequestOtpResponse>> requestOtp(@RequestBody @Valid RequestOtpCommand otpCommand) {
        return iRequestOtp.requestOtp(otpCommand);
    }

    @PostMapping("/verification")
    public ResponseEntity<AppResponse<VerificationOtpResponse>> verificationOtp(@RequestBody @Valid VerificationOtpCommand verificationOtpCommand) {
        return iVerificationOtpUsecase.verificationOtp(verificationOtpCommand);
    }
}
