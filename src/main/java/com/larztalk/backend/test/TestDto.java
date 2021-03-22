package com.larztalk.backend.test;

import com.larztalk.androidsdk.constant.Gender;
import com.larztalk.androidsdk.dto.response.Dto;
import java.util.Calendar;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@Getter
@Setter
@SuperBuilder
public class TestDto extends Dto {

    private long id;
    private String fname;
    private String lname;
}
