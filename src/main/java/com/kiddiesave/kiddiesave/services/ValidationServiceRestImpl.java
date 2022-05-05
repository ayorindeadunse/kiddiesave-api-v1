package com.kiddiesave.kiddiesave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumber;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

// test implementation
public class ValidationServiceRestImpl implements ValidationServiceRest{

    @Value("${SmsOtpUrl}")
    private String smsOtpUrl;
    @Value("${SmsOtpApiKey}")
    private String smsOtpApiKey;
    @Value("${SmsOtpSenderId}")
    private String senderId;
    @Value("${SmsOtpChannel}")
    private String smsOtpChannel;
    @Value("${SmsOtpExpirtyTime}")
    private String smsOtpExpiryTime;
    @Override
    //public ValidatePhoneNumberResponse sendOTPCode(String phoneNumber) throws IOException {
            public StringBuilder sendOTPCode(String phoneNumber) throws IOException
    {
        String responseLine = null;
        StringBuilder response = null;
            ValidatePhoneNumber validatePhoneNumber = new ValidatePhoneNumber();
            validatePhoneNumber.setApiKey(smsOtpApiKey);
            validatePhoneNumber.setMessageType("NUMERIC");
            validatePhoneNumber.setTo(phoneNumber);
            validatePhoneNumber.setFrom(senderId);
            validatePhoneNumber.setChannel(smsOtpChannel);
            validatePhoneNumber.setPinAttempts(3);
            validatePhoneNumber.setPinTimeToLive(5);
            validatePhoneNumber.setPinLength(6);
            validatePhoneNumber.setPinPlaceholder("<1234>");
            validatePhoneNumber.setMessageText("Welcome to Kiddiesave! Use this OTP <1234> to " +
                    "complete your registration. OTP expires in "+smsOtpExpiryTime);
            validatePhoneNumber.setPinType("Numeric");

            // send request
            // wrap this in a method
            URL url = new URL(smsOtpUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json; utf-8");
            connection.setRequestProperty("Accept","application/json");
            connection.setDoOutput(true);
            // wrap object as a string
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(validatePhoneNumber);

            try(OutputStream os = connection.getOutputStream())
            {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                os.write(input,0,input.length);
            }
            catch(Exception e)
            {
                System.out.println("Error in parsing json object as string: "+e);
            }

            // get response code
            int responseCode = connection.getResponseCode();
           // if(responseCode == HttpURLConnection.HTTP_OK)
          // get output
        System.out.println("Response Code: "+responseCode);

                // display response to console
               try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8")))
               {
                   response = new StringBuilder();
                   while((responseLine = br.readLine()) != null)
                   {
                       response.append(responseLine.trim());
                   }
               }
    return response;
    }
}
