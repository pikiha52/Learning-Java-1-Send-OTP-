package com.learn_java.learning.otp_request.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VerificationOtpCommand {
    @NotBlank(message = "property otp code is required")
    @NotNull(message = "otp code is required")    
    public String otpCode;

    @NotBlank(message = "property phone number is required")
    @NotNull(message = "phone number is required")
    public String phoneNumber;
}
