package com.kiddiesave.kiddiesave.services;

import com.google.gson.*;
import com.kiddiesave.kiddiesave.RequestsAndResponses.BvnLookupServiceResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidateOTPResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;
import com.kiddiesave.kiddiesave.security.util.LocalDateAdapter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final LocalDateAdapter localDateAdapter;

    @Autowired
    public ValidationServiceRestImpl(LocalDateAdapter localDateAdapter) {
        this.localDateAdapter = localDateAdapter;
    }

    public ValidatePhoneNumberResponse sendOTPCode(String phoneNumber) throws UnirestException {

                Unirest.setTimeouts(0, 0);
                HttpResponse<String> response = Unirest.post(smsOtpUrl)
                        .header("Content-Type", "application/json")
                        .body("{\r\n  \"api_key\" : \""+smsOtpApiKey+"\",\r\n \"message_type\" : \"NUMERIC\",\r\n  \"to\" : \""+phoneNumber+"\",\r\n       \"from\" : \""+senderId+"\",\r\n       \"channel\" : \""+smsOtpChannel+"\",\r\n       \"pin_attempts\" : 3,\r\n       \"pin_time_to_live\" :  5,\r\n       \"pin_length\" : 6,\r\n       \"pin_placeholder\" : \"<1234>\",\r\n       \"message_text\" : \"Welcome to Kiddiesave! Use this OTP <1234> to complete your registration. OTP expires in "+smsOtpExpiryTime+" minutes."+"\",\r\n       \"pin_type\" : \"NUMERIC\"\r\n   }\r\n      ")
                        .asString();

        //deserialize string response back to json
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDate.class, localDateAdapter)
                        .create();

                ValidatePhoneNumberResponse otpResponse = gson.fromJson(String.valueOf(response.getBody()), ValidatePhoneNumberResponse.class);
return otpResponse;
            }

    // Sms Otp Verify
    public ValidateOTPResponse validateOTPCode(String pinId, String code) throws UnirestException
    {
        Unirest.setTimeouts(0,0);
        HttpResponse<String> response = Unirest.post(smsOtpVerify)
                .header("Content-Type","application/json")
                .body("{\r\n  \"api_key\": \""+smsOtpApiKey+"\",\r\n    \"pin_id\": \""+pinId+"\",\r\n    \"pin\": \""+code+"\"\r\n\t}")
                .asString();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, localDateAdapter)
                .create();

        ValidateOTPResponse validResponse = gson.fromJson(String.valueOf(response.getBody()),ValidateOTPResponse.class);
        return validResponse;
    }

    @Override
    public BvnLookupServiceResponse bvnLookup(String bvn) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"bvn\":\""+bvn+"\"}");
        Request request = new Request.Builder()
                .url(bvnValidationUrl)
                .post(body)
                .addHeader("mono-sec-key", monoSecretKey)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        BvnLookupServiceResponse bvnLookupServiceResponse = gson.fromJson((response.body().string()), BvnLookupServiceResponse.class);
        return bvnLookupServiceResponse;
    }
}
// move this to utility class
/*class LocalDateAdapter implements JsonSerializer<LocalDate> {

    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
    }
}*/