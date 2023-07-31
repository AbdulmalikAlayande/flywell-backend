package com.example.airlinereservation.services.messages.textmessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class ScoopTextMessenger {
	public static final String ACCOUNT_SID = "ACa7fee56d0b823400a73c0b2f47a8c336";
	public static final String ACCOUNT_AUTH_TOKEN = "21a8ce5b060a202e8511c20c9241d381";
	public static final String PHONE_NUMBER = "+14068047139";
	public static void main(String[] args) {
		Twilio.init(ACCOUNT_SID, ACCOUNT_AUTH_TOKEN);
		MessageCreator messageCreator = Message.creator(new PhoneNumber("+2349061228207"), new PhoneNumber(PHONE_NUMBER),
				"Hello First Message");
		Message createdMessage = messageCreator.create();
		System.out.println("Account Sid Is: "+createdMessage.getAccountSid());
		System.out.println("Sid is: "+createdMessage.getSid());
		System.out.println(System.getenv());
		System.out.println(System.getenv("TWILIO_ACCOUNT_SID"));
		System.out.println(System.getenv("TWILIO_AUTH_TOKEN"));
	}
}
