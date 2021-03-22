package com.larztalk.backend.config;

/**
 * Created by raghvendra.mishra on 09/08/20.
 */
public class SystemConfig {

    public static String LOGIN_SESSION_TABLE = "%s-ddb-login-session";

    public static String ANSWER_BUCKET = "larztalk-%s-s3-answer";
    public static String ANSWER_BUCKET_KEY = "video";
    public static String PIC_BUCKET = "larztalk-%s-s3-pic";
    public static String DISPLAY_PIC_BUCKET_KEY = "display";
    public static String COVER_PIC_BUCKET_KEY = "cover";
    public static String SYSTEM_BUCKET = "larztalk-%s-s3-system";
    public static String SYSTEM_BUCKET_KEY = "";

    public static String AWS_ACCESS_KEY;
    public static String AWS_SECREET_KEY;

    public static boolean initialize(String env) {
        LOGIN_SESSION_TABLE = String.format(LOGIN_SESSION_TABLE, env);
        ANSWER_BUCKET = String.format(ANSWER_BUCKET, env);
        PIC_BUCKET = String.format(PIC_BUCKET, env);
        SYSTEM_BUCKET = String.format(SYSTEM_BUCKET, env);
        return true;
    }
}
