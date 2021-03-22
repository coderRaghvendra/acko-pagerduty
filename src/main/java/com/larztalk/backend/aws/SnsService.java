package com.larztalk.backend.aws;

import static com.larztalk.backend.aws.AwsClient.SNS_CLIENT;

import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by raghvendra.mishra on 10/08/20.
 */
@Slf4j
@Service
public class SnsService {

    private final static String AWS_SNS_SMS_MaxPrice = "0.0050";
    private final static String AWS_SNS_SMS_SENDER_ID = "LARZTALK";
    private final static String TRANSACTIONAL = "Transactional";
    private final static String PROMOTIONAL = "Promotional";

    public void sendSMS(String message, String phoneNumber) {
        sendTransactionalSMS(message, phoneNumber);
    }

    public void sendPromotionalSMS(String message, String phoneNumber) {
        sendSMSMessage(message, phoneNumber, PROMOTIONAL);
    }

    public void sendTransactionalSMS(String message, String phoneNumber) {
        sendSMSMessage(message, phoneNumber, TRANSACTIONAL);
    }

//    public String sendSMSMessageRetunId( String message, String phoneNumber) {
//        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
//            .withStringValue(TRANSACTIONAL) //Sets the type to promotional.
//            .withDataType("String"));
//        PublishResult result = amazonSNSService.publish(new PublishRequest()
//            .withMessage(message)
//            .withPhoneNumber(phoneNumber)
//            .withMessageAttributes(smsAttributes));
//        log.info(String.format("Message Id [%s],  mobile [%s] and message [%s]",result.getMessageId(),phoneNumber, message));
//        return result.getMessageId();
//    }

    private void sendSMSMessage(String message, String phoneNumber, String messageType) {
        Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
        		smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue()
				.withStringValue(AWS_SNS_SMS_SENDER_ID) //The sender ID shown on the device.
				.withDataType("String"));
        smsAttributes.put("AWS.SNS.SMS.MaxPrice", new MessageAttributeValue()
            .withStringValue(AWS_SNS_SMS_MaxPrice) //Sets the max price to 0.50 USD.
            .withDataType("Number"));
        smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue()
            .withStringValue(messageType) //Sets the type to promotional.
            .withDataType("String"));
        log.debug(smsAttributes.toString());

        PublishResult result = SNS_CLIENT.publish(new PublishRequest()
            .withMessage(message)
            .withPhoneNumber(phoneNumber)
            .withMessageAttributes(smsAttributes));
        log.info(String.format("Message Id [%s],  mobile [%s] and message [%s]",result.getMessageId(),phoneNumber, message));
    }
}
