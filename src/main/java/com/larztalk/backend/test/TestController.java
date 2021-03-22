package com.larztalk.backend.test;

import static com.larztalk.androidsdk.service.Service.AUTH_TOKEN;
import static com.larztalk.androidsdk.service.Service.GOOGLE_SIGN_IN_UP;
import static com.larztalk.androidsdk.service.Service.SEND_OTP;
import static com.larztalk.androidsdk.service.Service.SIGN_IN;
import static com.larztalk.androidsdk.service.Service.SIGN_OUT;
import static com.larztalk.androidsdk.service.Service.UPDATE;
import static com.larztalk.androidsdk.service.Service.USER;

import com.larztalk.androidsdk.constant.ResponseCode;
import com.larztalk.androidsdk.dto.response.DataResponseDto;
import com.larztalk.androidsdk.dto.response.ResponseDto;
import com.larztalk.androidsdk.dto.response.ResponseDtoFactory;
import com.larztalk.androidsdk.dto.response.page.PageBigDto;
import com.larztalk.androidsdk.dto.response.user.UserDto;
import com.larztalk.androidsdk.util.StringUtil;
import com.larztalk.backend.Controller;
import com.larztalk.backend.otp.OtpService;
import com.larztalk.backend.session.SessionService;
import com.larztalk.backend.user.GoogleLoginService;
import com.larztalk.backend.user.UpdateUserService;
import com.larztalk.backend.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by raghvendra.mishra on 01/08/20.
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController implements Controller {

    @Autowired
    TestService testService;

    @GetMapping("/create")
    public ResponseDto create() {
        log.info("Request : ");
        ResponseDto response;
        try {
            TestDto t = testService.createTestObj();
            response = DataResponseDto.<TestDto>builder().data(t).success(true)
                .code(ResponseCode.SUCCESS).build();
        } catch (Exception e) {
            log.error("Exception : ", e);
            response = ResponseDtoFactory.exception();
        }
        log.info("Response : " + response);
        return response;
    }
}