package com.kiddiesave.kiddiesave.services;

import com.google.gson.*;
import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnValidationResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateOTPResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


// test implementation
@Service
public class ValidationServiceRestImpl implements ValidationServiceRest{

    @Value("${SmsOtpUrl}")
    private String smsOtpUrl;
    @Value("${SmsOtpVerify}")
    private String smsOtpVerify;
    @Value("${SmsOtpApiKey}")
    private String smsOtpApiKey;
    @Value("${SmsOtpSenderId}")
    private String senderId;
    @Value("${SmsOtpChannel}")
    private String smsOtpChannel;
    @Value("${SmsOtpExpiryTime}")
    private String smsOtpExpiryTime;
    @Value("${BvnValidationUrl}")
    private String bvnValidationUrl;
    @Value("${MonoTestSecKey}")
    private String monoSecretKey;

            public ValidatePhoneNumberResponse sendOTPCode(String phoneNumber) throws UnirestException {

                Unirest.setTimeouts(0, 0);
                HttpResponse<String> response = Unirest.post(smsOtpUrl)
                        .header("Content-Type", "application/json")
                        .body("{\r\n  \"api_key\" : \""+smsOtpApiKey+"\",\r\n \"message_type\" : \"NUMERIC\",\r\n  \"to\" : \""+phoneNumber+"\",\r\n       \"from\" : \""+senderId+"\",\r\n       \"channel\" : \""+smsOtpChannel+"\",\r\n       \"pin_attempts\" : 3,\r\n       \"pin_time_to_live\" :  5,\r\n       \"pin_length\" : 6,\r\n       \"pin_placeholder\" : \"<1234>\",\r\n       \"message_text\" : \"Welcome to Kiddiesave! Use this OTP <1234> to complete your registration. OTP expires in "+smsOtpExpiryTime+" minutes."+"\",\r\n       \"pin_type\" : \"NUMERIC\"\r\n   }\r\n      ")
                        .asString();

        //desirialize the response to json object
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                        .create();

                ValidatePhoneNumberResponse otpResponse = gson.fromJson(String.valueOf(response.getBody()), ValidatePhoneNumberResponse.class);
return otpResponse;
            }

    // Sms Otp Verify
    public ValidateOTPResponse validateOTPCode(String pinId, String code) throws IOException, UnirestException
    {
        Unirest.setTimeouts(0,0);
        HttpResponse<String> response = Unirest.post(smsOtpVerify)
                .header("Content-Type","application/json")
                .body("{\r\n  \"api_key\": \""+smsOtpApiKey+"\",\r\n    \"pin_id\": \""+pinId+"\",\r\n    \"pin\": \""+code+"\"\r\n\t}")
                .asString();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        ValidateOTPResponse validResponse = gson.fromJson(String.valueOf(response.getBody()),ValidateOTPResponse.class);
        return validResponse;
    }

    @Override
    public BvnValidationResponse bvnLookup(String bvn) throws IOException, UnirestException {
                Unirest.setTimeouts(0,0);
                HttpResponse<String> response = Unirest.post(bvnValidationUrl)
                        .header("Content-Type","application/json")
                        .header("mono-secret-key",monoSecretKey)
                        .body("{\r\n  \"bvn\": \""+bvn+"\"")
                        .asString();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        BvnValidationResponse bvnValidationResponse = gson.fromJson(String.valueOf(response.getBody()),BvnValidationResponse.class);
        return bvnValidationResponse;
    }
}



class LocalDateAdapter implements JsonSerializer<LocalDate> {

    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }
}