package com.larztalk.backend.alert;

import com.larztalk.backend.developer.Developer;
import com.larztalk.backend.developer.DeveloperDto;
import com.larztalk.backend.developer.DeveloperRepository;
import com.larztalk.backend.developer.DeveloperService;
import com.larztalk.backend.notification.NotificationService;
import com.larztalk.backend.team.Team;
import com.larztalk.backend.team.TeamDto;
import com.larztalk.backend.team.TeamService;
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
public class AlertService {

    public static final String ALERT_MESSAGE = "Error in poroduction";

    @Autowired
    DeveloperService developerService;

    public boolean alertTeamDeveloper(long teamId) throws Exception {
        // todo request validation
        List<Developer> developers = developerService.getDevelopersByTeamId(teamId);
        Developer developer = developers.get(0);
        log.info("sending alert to developer [{}], [{}] by sms", developer.getId(), developer.getName());
        NotificationService.sendSMS(developer.getPhoneNo(), ALERT_MESSAGE);
        // send email
        return true;
    }

    public boolean sendAlert(Developer developer) {
        // call sms, email, push
        return true;
    }
}
