package com.larztalk.backend.session;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by raghvendra.mishra on 09/08/20.
 */
@AllArgsConstructor
@Getter
public class CacheData {

    private String accessToken;
    private long userId;
}
