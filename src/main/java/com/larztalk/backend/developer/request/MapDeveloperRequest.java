package com.larztalk.backend.developer.request;

import com.larztalk.backend.developer.Developer;
import com.larztalk.backend.developer.DeveloperDto;
import com.larztalk.backend.team.TeamDto;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@Builder
@Getter
@Setter
public class MapDeveloperRequest implements Serializable{

    private TeamDto team;
    private List<DeveloperDto> developers;
}
