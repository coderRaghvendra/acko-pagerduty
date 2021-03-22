package com.larztalk.backend.developer;

import com.larztalk.androidsdk.constant.ResponseCode;
import com.larztalk.androidsdk.dto.response.DataResponseDto;
import com.larztalk.androidsdk.dto.response.ResponseDto;
import com.larztalk.androidsdk.dto.response.ResponseDtoFactory;
import com.larztalk.androidsdk.service.AbstractService;
import com.larztalk.backend.Controller;
import com.larztalk.backend.developer.request.MapDeveloperRequest;
import com.larztalk.backend.team.Team;
import com.larztalk.backend.team.TeamDto;
import com.larztalk.backend.test.TestDto;
import com.larztalk.backend.test.TestService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by raghvendra.mishra on 01/08/20.
 */
@Slf4j
@RestController
@RequestMapping("/developer")
public class DeveloperController implements Controller {

    @Autowired
    DeveloperService developerService;

    @GetMapping("/test") void test(){
        List<DeveloperDto> list = new ArrayList<>();
        list.add(DeveloperDto.builder().name("ragh").phoneNo("342342").teamId(1).build());
        MapDeveloperRequest json = MapDeveloperRequest.builder()
            .team(TeamDto.builder().id(1).name("claims").build())
            .developers(list)
            .build();

        log.info(" === " + json);
        log.info(" === " + AbstractService.GSON.toJson(json));
    }

    @PostMapping("/map")
    public ResponseDto create(@RequestBody MapDeveloperRequest mapDeveloperRequest) {
        log.info("Request : ");
        ResponseDto response;
        try {
            long teamId = developerService.mapDevelopers(mapDeveloperRequest.getTeam(),
                mapDeveloperRequest.getDevelopers());
            response = DataResponseDto.<Long>builder().data(teamId).success(true)
                .code(ResponseCode.SUCCESS).build();
        } catch (Exception e) {
            log.error("Exception : ", e);
            response = ResponseDtoFactory.exception();
        }
        log.info("Response : " + response);
        return response;
    }
}
