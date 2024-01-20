package com.learn_java.learning.otp_request.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.learn_java.learning.otp_request.command.VerificationOtpCommand;
import com.learn_java.learning.otp_request.contract.VerificationOtpContract;

@Service
public class VerificationOtpHandler {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public VerificationOtpContract verificationOtp(VerificationOtpCommand verificationOtpCommand) {
        final VerificationOtpContract responseHandler = new VerificationOtpContract();
        String redisKey = verificationOtpCommand.phoneNumber + "_requestOtp";
        responseHandler.setStatus(true);
        responseHandler.setMessage("Verification success.");
        String otpCode = redisTemplate.opsForValue().get(redisKey);
        if (otpCode == null) {
            responseHandler.setStatus(false);
            responseHandler.setMessage("Otp code wrong or expired.");
        }

        if (!verificationOtpCommand.otpCode.equals(otpCode)) {
            responseHandler.setStatus(false);
            responseHandler.setMessage("Otp code wrong or expired.");
        } else {
            redisTemplate.opsForValue().getAndDelete(redisKey);
        }

        return responseHandler;
    }
}
