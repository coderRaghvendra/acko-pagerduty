package com.larztalk.backend.team;

import com.larztalk.backend.test.Test;
import com.larztalk.backend.test.TestDto;
import com.larztalk.backend.test.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by raghvendra.mishra on 01/08/20.
 */
@Slf4j
@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public Team getByName(String name) {
        return teamRepository.getByName(name);
    }

    public Team createTeam(String name) {
        Team team = Team.builder().name(name).build();
        return teamRepository.saveAndFlush(team);
    }

}
