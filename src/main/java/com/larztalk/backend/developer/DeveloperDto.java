package com.larztalk.backend.developer;

import com.larztalk.androidsdk.dto.response.Dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@Getter
@Setter
@SuperBuilder
public class DeveloperDto  {

    private long id;
    private long teamId;
    private String name;
    private String phoneNo;

    public DeveloperDto() {
    }
}
