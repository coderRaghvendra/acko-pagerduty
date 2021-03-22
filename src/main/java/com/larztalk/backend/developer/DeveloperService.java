package com.larztalk.backend.developer;

import com.larztalk.backend.developer.request.MapDeveloperRequest;
import com.larztalk.backend.team.Team;
import com.larztalk.backend.team.TeamDto;
import com.larztalk.backend.team.TeamService;
import com.larztalk.backend.test.Test;
import com.larztalk.backend.test.TestDto;
import com.larztalk.backend.test.TestRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by raghvendra.mishra on 01/08/20.
 */
@Slf4j
@Service
public class DeveloperService {

    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    TeamService teamService;

    public long mapDevelopers(TeamDto teamDto, List<DeveloperDto> developerDtos) {
        // todo request validation
        String teamName = teamDto.getName();
        if(StringUtils.isBlank(teamName)) {
            throw new RuntimeException("Invalid argumments");
        }
        Team team = teamService.getByName(teamName);
        if(team == null) {
            team = teamService.createTeam(teamName);
        }
        List<Developer> developers = new ArrayList<>();
        for (DeveloperDto developerDto : developerDtos) {
            developers.add(Developer.builder()
                .name(developerDto.getName())
                .teamId(team.getId())
                .phoneNo(developerDto.getPhoneNo())
                .build());
        }
        developerRepository.saveAll(developers);
        return team.getId();
    }

    public List<Developer> getDevelopersByTeamId(long teamId) throws Exception {
        return developerRepository.getByTeamId(teamId);
    }
}


// exits :  A - d1 d2 d3
// request  B - d1 d2 d3



// team {id, name} , developer : {id, name, },
//
// team_developer_mapping : team_id, developer_id



