package com.learn_java.learning.otp_request.handler;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learn_java.learning.otp_request.command.RequestOtpCommand;
import com.learn_java.learning.otp_request.contract.ExternalReqOtp;
import com.learn_java.learning.otp_request.contract.RequestOtpContract;
import com.learn_java.learning.otp_request.contract.SendOtpContract;

import io.micrometer.common.lang.NonNull;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RequestOtpHandler {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public RequestOtpContract execute(RequestOtpCommand reqCommandOtp) {
        final RequestOtpContract requestOtpContract = new RequestOtpContract();
        String url = "https://waba-sandbox.360dialog.io/v1/messages";
        final SendOtpContract sendOtpContract = new SendOtpContract();
        sendOtpContract.setMessagingProduct("whatsapp");
        sendOtpContract.setRecipientType("individual");
        sendOtpContract.setTo(reqCommandOtp.phoneNumber);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("D360-API-KEY", "iy2gOn_sandbox");
        
        String otp = generateOtp();
        sendOtpContract.setText(new SendOtpContract.Text(otp + " adalah kode verifikasi Anda. Demi keamanan, jangan bagikan kode ini."));
        HttpEntity<String> entity = new HttpEntity<>(sendOtpContract.toString(), headers);
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpMethod  httpMethod = HttpMethod.POST;
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, entity, String.class);
            setRequestOtp(reqCommandOtp.phoneNumber, otp);
            requestOtpContract.setStatus(true);
            ObjectMapper objectMapper = new ObjectMapper();
            ExternalReqOtp apiResponse = objectMapper.readValue(responseEntity.getBody(), ExternalReqOtp.class);
        } catch (Exception e) {
            e.printStackTrace();
            requestOtpContract.setStatus(false);
        }

        requestOtpContract.setLastRequest(0);
        requestOtpContract.setNextRequest(0);
        return requestOtpContract;
    }

    public String generateOtp() {
        Random rand = new Random();
        int rand_int = rand.nextInt(999999);

        return String.format("%06d", rand_int);
    }

    public Boolean setRequestOtp(String phoneNumber, @NonNull String otpCode) {
        String redisKeyIncrement = phoneNumber + "_requestOtp_increment";
        String redisKey = phoneNumber + "_requestOtp";
        redisTemplate.opsForValue().get(redisKey);
        
        if (redisTemplate.opsForValue().get(redisKeyIncrement) == null) {
            Duration expirationIncr = Duration.ofDays(1);
            redisTemplate.opsForValue().increment(redisKeyIncrement);
            redisTemplate.expire(redisKeyIncrement, expirationIncr.toMinutes(), TimeUnit.MINUTES);
        } else {
            String countRequestOtp = redisTemplate.opsForValue().get(redisKeyIncrement);
            Integer count = Integer.parseInt(countRequestOtp);
            if (count > 10) {
                return false;
            }

            Long incrementTTL = redisTemplate.getExpire(redisKeyIncrement, TimeUnit.MINUTES);
            redisTemplate.opsForValue().increment(redisKeyIncrement);
            redisTemplate.expire(redisKeyIncrement, incrementTTL, TimeUnit.MINUTES);
        }


        Duration expirationOtp = Duration.ofMinutes(5);
        redisTemplate.opsForValue().set(redisKey, otpCode);
        redisTemplate.expire(redisKey, expirationOtp.toMinutes(), TimeUnit.MINUTES);
        return true;
    }
}
