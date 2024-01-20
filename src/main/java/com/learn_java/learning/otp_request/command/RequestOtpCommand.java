package com.learn_java.learning.otp_request.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RequestOtpCommand {
    @NotBlank(message = "property phoneNumber is undifiend.")
    @NotNull(message = "phoneNumber is required.")
    public String phoneNumber;
}
