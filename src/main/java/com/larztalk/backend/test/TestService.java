package com.larztalk.backend.test;

import com.larztalk.androidsdk.constant.Gender;
import com.larztalk.androidsdk.constant.ResponseCode;
import com.larztalk.androidsdk.dto.response.ResponseDto;
import com.larztalk.androidsdk.dto.response.account.LogInResponseDto;
import com.larztalk.androidsdk.dto.response.user.UserDto;
import com.larztalk.backend.otp.OtpService;
import com.larztalk.backend.session.SessionService;
import com.larztalk.backend.user.IUserService;
import com.larztalk.backend.user.User;
import com.larztalk.backend.user.UserRepository;
import com.larztalk.backend.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by raghvendra.mishra on 01/08/20.
 */
@Slf4j
@Service
public class TestService {

    @Autowired
    TestRepository testRepository;

    public TestDto createTestObj() {
        Test t = Test.builder().fname("Raghaav").lname("Mishar").build();
        testRepository.save(t);
        return TestDto.builder().build();
    }

}
