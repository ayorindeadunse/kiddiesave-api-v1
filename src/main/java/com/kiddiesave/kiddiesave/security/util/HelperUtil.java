package com.kiddiesave.kiddiesave.security.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class HelperUtil {

    private static String GenerateBvnReference()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return "BVN"+ RandomString(4) + df;
    }

    private static String RandomString(int length)
    {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
            return sb.toString();
    }

}
