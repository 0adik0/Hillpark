package com.hillpark.hillpark.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class javaUtils {
    public static byte[] decodeString(String encoded) {
        byte[] dataDec = Base64.decode(encoded, Base64.DEFAULT);
        String decodedString = "";
        try {
            decodedString = new String(dataDec, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return decodedString.getBytes();
        }
    }
}
