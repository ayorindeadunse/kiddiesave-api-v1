package com.kiddiesave.kiddiesave.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kiddiesave.kiddiesave.RequestsAndResponses.CreateWalletResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.ValidatePhoneNumberResponse;
import com.kiddiesave.kiddiesave.security.util.LocalDateAdapter;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.time.LocalDate;

public class WalletServiceRestImpl implements WalletServiceRest{

    @Value("${get_wallets_secret_key}")
    private String getWalletsSecretKey;
    @Value("${get_wallets_base_url}")
    private String getWalletsUrl;

    private final LocalDateAdapter localDateAdapter;

    @Autowired
    public WalletServiceRestImpl(LocalDateAdapter localDateAdapter) {
        this.localDateAdapter = localDateAdapter;
    }
    @Override
    public Response createWallet(String email) throws IOException {
      /*  Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post(smsOtpUrl)
                .header("Content-Type", "application/json")
                .body("{\r\n  \"api_key\" : \""+smsOtpApiKey+"\",\r\n \"message_type\" : \"NUMERIC\",\r\n  \"to\" : \""+phoneNumber+"\",\r\n       \"from\" : \""+senderId+"\",\r\n       \"channel\" : \""+smsOtpChannel+"\",\r\n       \"pin_attempts\" : 3,\r\n       \"pin_time_to_live\" :  5,\r\n       \"pin_length\" : 6,\r\n       \"pin_placeholder\" : \"<1234>\",\r\n       \"message_text\" : \"Welcome to Kiddiesave! Use this OTP <1234> to complete your registration. OTP expires in "+smsOtpExpiryTime+" minutes."+"\",\r\n       \"pin_type\" : \"NUMERIC\"\r\n   }\r\n      ")
                .asString();

        //deserialize string response back to json
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, localDateAdapter)
                .create();

        ValidatePhoneNumberResponse otpResponse = gson.fromJson(String.valueOf(response.getBody()), ValidatePhoneNumberResponse.class);
        return otpResponse;*/
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getWalletsUrl)
                .post(null)
                .addHeader("accept","application/json")
                .addHeader("content-type","application/json")
                .addHeader("Authorization",getWalletsSecretKey)
                .build();

        Response response = client.newCall(request).execute();
    //return null;
        return response;
    }
}
