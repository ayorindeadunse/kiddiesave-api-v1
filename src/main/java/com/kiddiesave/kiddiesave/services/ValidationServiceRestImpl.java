package com.kiddiesave.kiddiesave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumber;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// test implementation
@Service
public class ValidationServiceRestImpl implements ValidationServiceRest{

    @Value("${SmsOtpUrl}")
    private String smsOtpUrl;
    @Value("${SmsOtpApiKey}")
    private String smsOtpApiKey;
    @Value("${SmsOtpSenderId}")
    private String senderId;
    @Value("${SmsOtpChannel}")
    private String smsOtpChannel;
    @Value("${SmsOtpExpiryTime}")
    private String smsOtpExpiryTime;

    //public ValidatePhoneNumberResponse sendOTPCode(String phoneNumber) throws IOException {
            public String sendOTPCode(String phoneNumber) throws IOException
    {
        // for purposes of your test, use the validate phone number objects getters and setters below,
        // then pass the parameters in a rest api endpoint which will then make an Url connection to Termii's api
       // String responseLine = null;
      //  StringBuilder response = null;
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
           /* URL url = new URL(smsOtpUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            connection.setDoOutput(true);

        OutputStream os = connection.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        os.write(mapper.writeValueAsBytes(validatePhoneNumber));
        os.flush();
        os.close();


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
    return response.toString();*/
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(smsOtpUrl);

       // String json = "{"id":1,"name":"John"}";
        // replace the above with a parsed json object
        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(validatePhoneNumber);
        StringEntity entity = new StringEntity(jsonString);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
      // assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println(responseCode);
        System.out.println(response);
        client.close();

        return "The response is: "+response;
    }
}
