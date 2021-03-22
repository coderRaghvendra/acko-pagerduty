package com.larztalk.backend.team;

import com.larztalk.androidsdk.dto.response.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@Getter
@Setter
@SuperBuilder
public class TeamDto {

    private long id;
    private String name;

    public TeamDto() {
    }
}
