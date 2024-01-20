package com.learn_java.learning.otp_request.contract;

public class ExternalReqOtp {
    private Meta meta;

    public ExternalReqOtp(Meta meta) {
        this.meta = meta;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public static class Meta {
        private boolean success;
        private int httpCode;
        private String developerMessage;

        public Meta(boolean success, int httpCode, String developerMessage) {
            this.success = success;
            this.httpCode = httpCode;
            this.developerMessage = developerMessage;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public int getHttpCode() {
            return httpCode;
        }

        public void setHttpCode(int httpCode) {
            this.httpCode = httpCode;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

        public void setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
        }
    }
}
