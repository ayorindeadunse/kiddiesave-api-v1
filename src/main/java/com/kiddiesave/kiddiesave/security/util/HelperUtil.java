package com.kiddiesave.kiddiesave.security.util;

import java.util.Random;

public class HelperUtil {

    private static String GenerateBvnReference()
    {
        return "";
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
