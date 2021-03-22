package com.larztalk.backend.team;

import com.larztalk.androidsdk.constant.ResponseCode;
import com.larztalk.androidsdk.dto.response.DataResponseDto;
import com.larztalk.androidsdk.dto.response.ResponseDto;
import com.larztalk.androidsdk.dto.response.ResponseDtoFactory;
import com.larztalk.backend.Controller;
import com.larztalk.backend.test.TestDto;
import com.larztalk.backend.test.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by raghvendra.mishra on 01/08/20.
 */
@Slf4j
@RestController
@RequestMapping("/team")
public class TeamController implements Controller {

    @Autowired
    TeamService teamService;
}