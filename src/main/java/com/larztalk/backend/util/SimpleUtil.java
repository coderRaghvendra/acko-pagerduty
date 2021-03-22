package com.larztalk.backend.util;

import java.util.Random;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by raghvendra.mishra on 10/08/20.
 */
public class SimpleUtil {

    public static final Random rndMax = new Random();

    public static String getMobileNumberWithCountryCode(String countryCode, String mobileNumber) {
        if (StringUtils.isNotEmpty(mobileNumber)) {
            return countryCode + get10DigitMsisdn(mobileNumber);
        }
        return mobileNumber;
    }

    public static String get10DigitMsisdn(String mobileNumber) {
        if (StringUtils.isNotEmpty(mobileNumber) && mobileNumber.length() > 10) {
            return mobileNumber.substring(mobileNumber.length() - 10, mobileNumber.length());
        }
        return mobileNumber;
    }

    public static String generateRandomNumber(int size) {
        return (int) (Math.pow(10, size - 1) + Math.random() * 9 * Math.pow(10, size - 1)) + "";
    }
}
