package com.larztalk.backend.notification;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@Slf4j
public class NotificationService {

    public static OkHttpClient client = new OkHttpClient();
    public static final String SMS_API_URL = "https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f";

    public static boolean sendSMS(String phoneNo, String message) throws Exception {
        log.info("sending sms [{}] to [{}]", message, phoneNo);
        RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("phone_number", phoneNo)
            .addFormDataPart("message", message)
            .build();

        Request request = new Request.Builder()
            .url(SMS_API_URL)
            .post(requestBody)
            .build();
        Response response = client.newCall(request).execute();
        return response.isSuccessful();
    }
}