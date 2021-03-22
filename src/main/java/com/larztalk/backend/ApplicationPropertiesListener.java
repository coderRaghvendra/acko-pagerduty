package com.larztalk.backend;

import com.larztalk.backend.aws.AwsClient;
import com.larztalk.backend.aws.SecretsManagerService;
import com.larztalk.backend.config.SystemConfig;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

@Slf4j
public class ApplicationPropertiesListener implements ApplicationListener<ApplicationPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        log.info("pre application initialization : start");
        String env = System.getProperty("env", "dev");
        SystemConfig.AWS_ACCESS_KEY = System.getProperty("awsAccessKey");
        SystemConfig.AWS_SECREET_KEY = System.getProperty("awsSecretKey");
        AwsClient.init();
        SystemConfig.initialize(env);
        setPostgresDatabaseCredentials(event, env);
        log.info("pre application initialization : end");
    }

   /**
    ** sets postgres datasource username and password through aws-secret manager
    **/
    private void setPostgresDatabaseCredentials(ApplicationPreparedEvent event, String env) {
        String secretName = String.format("%s-sms-rdsinstance1", env);
        String secretJson = SecretsManagerService.getInstance().getSecret(secretName);
        String username = SecretsManagerService.getInstance().getString(secretJson, "username");
        String password = SecretsManagerService.getInstance().getString(secretJson, "password");
        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        Properties props = new Properties();
        props.put("spring.datasource.username", username);
        props.put("spring.datasource.password", password);
        environment.getPropertySources().addFirst(new PropertiesPropertySource("aws.secret.manager", props));
    }
}