package com.learn_java.learning.otp_request.contract;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendOtpContract {
    @JsonProperty("messaging_product")
    private String messagingProduct;

    @JsonProperty("recipient_type")
    private String recipientType;

    private String to;
    private String type;
    private Text text;

    public SendOtpContract() {
    }

    public SendOtpContract(String messagingProduct, String recipientType, String to, String type, Text text) {
        this.messagingProduct = messagingProduct;
        this.recipientType = recipientType;
        this.to = to;
        this.type = type;
        this.text = text;
    }

    public String getMessagingProduct() {
        return messagingProduct;
    }

    public void setMessagingProduct(String messagingProduct) {
        this.messagingProduct = messagingProduct;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"messaging_product\": \"" + this.messagingProduct + "\",\n" +
                "    \"recipient_type\": \"" + this.recipientType + "\",\n" +
                "    \"to\": \"" + this.to + "\",\n" +
                "    \"type\": \"text\",\n" +
                this.getText() +
                "}";
    }

    public static class Text {
        private String body;

        public Text() {
        }

        public Text(String body) {
            this.body = body;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return "    \"text\": {\n" +
                    "        \"body\": \"" + this.getBody() + "\"\n" +
                    "    }\n";
        }
    }
}
