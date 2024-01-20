package com.learn_java.learning.otp_request.usecase.verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.learn_java.learning.app.AppResponse;
import com.learn_java.learning.global.response.VerificationOtpResponse;
import com.learn_java.learning.otp_request.command.VerificationOtpCommand;
import com.learn_java.learning.otp_request.contract.VerificationOtpContract;
import com.learn_java.learning.otp_request.handler.VerificationOtpHandler;

@Component
public class VerificationOtpUsecase implements IVerificationOtpUsecase {
    @Autowired
    private VerificationOtpHandler verificationOtpHandler;

    @Override
    public ResponseEntity<AppResponse<VerificationOtpResponse>> verificationOtp(VerificationOtpCommand verificationOtpCommand) {
        final AppResponse<VerificationOtpResponse> verificationResponse = new AppResponse<>();
        VerificationOtpContract verification = verificationOtpHandler.verificationOtp(verificationOtpCommand);
        verificationResponse.setCode(200);
        if (verification.getStatus().equals(false)) {
            verificationResponse.setCode(400);
            verificationResponse.setMessage(verification.getMessage());
            verificationResponse.setErrorMessage("Failed.");
            return ResponseEntity.badRequest().body(verificationResponse);
        }

        verificationResponse.setData(null);
        verificationResponse.setMessage(verification.getMessage());
        return ResponseEntity.ok().body(verificationResponse);
    }

}
