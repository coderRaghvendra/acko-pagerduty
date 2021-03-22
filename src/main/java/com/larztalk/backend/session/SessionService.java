package com.larztalk.backend.session;

/**
 * Created by raghvendra.mishra on 11/09/19.
 */

import static com.larztalk.backend.aws.AwsClient.*;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.larztalk.backend.config.SystemConfig;
import com.larztalk.backend.util.DoublyLinkedList;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 ** stores limited amount of key value based data
 ** eviction is based on oldest entry first
 **/
@Slf4j
@Service
public class SessionService {

    public static final DoublyLinkedList<CacheData> CACHE_DATA_LIST = new DoublyLinkedList<>();
    public static final Map<String, DoublyLinkedList.Node> CACHE_MAP = new HashMap<>();
    public static final int MAX_SIZE = 1000;

    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String USER_ID_KEY = "user_id";
    private static final String LOG_IN_TIME_KEY = "log_in_time";

    // return accessToken
    public String saveUser(long userId) {
        String accessToken = generateAccessToken(userId);
        // add user to aws db
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put(ACCESS_TOKEN_KEY, new AttributeValue(accessToken));
        itemValues.put(USER_ID_KEY, new AttributeValue(String.valueOf(userId)));
        itemValues.put(LOG_IN_TIME_KEY, new AttributeValue(LocalDateTime.now().toString()));
        DYNAMO_DB_CLIENT.putItem(SystemConfig.LOGIN_SESSION_TABLE, itemValues);

        CacheData data = new CacheData(accessToken, userId);
        synchronized(SessionService.class) {
            if(CACHE_DATA_LIST.size() == MAX_SIZE) {
                DoublyLinkedList.Node node = CACHE_DATA_LIST.removeBottom();
                CACHE_MAP.remove(((CacheData)node.getData()).getAccessToken());
            }
        }
        CACHE_MAP.put(accessToken, CACHE_DATA_LIST.addTop(data));
        return accessToken;
    }

    public boolean deleteUser(String accessToken) {
        // delete from aws db
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put(ACCESS_TOKEN_KEY, new AttributeValue(accessToken));
        DYNAMO_DB_CLIENT.deleteItem(SystemConfig.LOGIN_SESSION_TABLE, itemValues);
        // delete from local map
        CACHE_DATA_LIST.remove(CACHE_MAP.get(accessToken));
        CACHE_MAP.remove(accessToken);
        return true;
    }

    public long getUserId(String accessToken) {
        // first check in local map
        if(CACHE_MAP.containsKey(accessToken)) {
            DoublyLinkedList.Node node = CACHE_MAP.get(accessToken);
            CACHE_DATA_LIST.shiftTop(node);
            return ((CacheData)node.getData()).getUserId();
        }
        // bring from aws db
        HashMap<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put(ACCESS_TOKEN_KEY, new AttributeValue(accessToken));
        GetItemResult result = DYNAMO_DB_CLIENT.getItem(SystemConfig.LOGIN_SESSION_TABLE, itemValues);
        Map<String, AttributeValue> valueMap = result.getItem();
        if(valueMap == null) {
            return -1;
        }
        long userId = Long.parseLong(valueMap.get(USER_ID_KEY).getS());
        // save to local map
        CacheData data = new CacheData(accessToken, userId);
        synchronized (SessionService.class) {
            if(CACHE_DATA_LIST.size() == MAX_SIZE) {
                DoublyLinkedList.Node node = CACHE_DATA_LIST.removeBottom();
                CACHE_MAP.remove(((CacheData)node.getData()).getAccessToken());
            }
        }
        CACHE_MAP.put(accessToken, CACHE_DATA_LIST.addTop(data));
        return userId;
    }

    public String generateAccessToken(long userId) {
        return String.valueOf(userId) + "_" + UUID.randomUUID().toString();
    }
}

