package com.larztalk.backend.alert;

import com.larztalk.androidsdk.constant.ResponseCode;
import com.larztalk.androidsdk.dto.response.DataResponseDto;
import com.larztalk.androidsdk.dto.response.ResponseDto;
import com.larztalk.androidsdk.dto.response.ResponseDtoFactory;
import com.larztalk.backend.Controller;
import com.larztalk.backend.developer.DeveloperService;
import com.larztalk.backend.developer.request.MapDeveloperRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by raghvendra.mishra on 22/03/21.
 */
@Slf4j
@RestController
@RequestMapping("/alert")
public class AlertController implements Controller {

    @Autowired
    AlertService alertService;

    @PostMapping("/team")
    public ResponseDto create(@RequestParam("id") long id) {
        log.info("Request : ");
        ResponseDto response;
        try {
            boolean success = alertService.alertTeamDeveloper(id);
            response = ResponseDtoFactory.response(success);
        } catch (Exception e) {
            log.error("Exception : ", e);
            response = ResponseDtoFactory.exception();
        }
        log.info("Response : " + response);
        return response;
    }
}
