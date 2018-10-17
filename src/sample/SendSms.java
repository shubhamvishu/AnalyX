package sample;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms {
    public static final String ACCOUNT_SID = "ACdb2d36a83f0dd6a51bde7014c3f76f18";
    public static final String AUTH_TOKEN = "5da4dbda04347d23507f6b90ebaf30c1";
    public static String ph;
    public static String body;
    SendSms(String p, String b){
        ph=p;
        body=b;
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       // ValidationRequest validationRequest = ValidationRequest.creator(
        //        new com.twilio.type.PhoneNumber("+91"+ph))
        //        .setFriendlyName("Mine")
        //        .create();

        //System.out.println(validationRequest.getFriendlyName());
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+91"+ph),
                new com.twilio.type.PhoneNumber("+14752437144"),
                body)
                .create();

        System.out.println(message.getSid());
    }
}
