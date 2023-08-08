package com.example.airlinereservation.services.notifications.mail;
//import com.mailgun.api.v3.MailgunMessagesApi;
//import com.mailgun.client.MailgunClient;
//import com.mailgun.model.message.Message;
//import com.mailgun.model.message.MessageResponse;
//import com.mashape.unirest.http.HttpResponse;
//import com.mailgun.api.v4.MailgunEmailVerificationApi;
//import com.mailgun.model.verification.AddressValidationResponse;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Mailer {
	String ritch = System.getenv("Richards");
	static String DOMAIN_NAME = System.getenv("MAIL_GUN_DOMAIN_NAME");
	static String PRIVATE_API_KEY = System.getenv("MAIL_GUN_PRIVATE_API_KEY");
// ...
//	public AddressValidationResponse validateEmail() {
//		MailgunEmailVerificationApi mailgunEmailVerificationApi = MailgunClient.config(PRIVATE_API_KEY)
//				                                                          .createApi(MailgunEmailVerificationApi.class);
//
//		return mailgunEmailVerificationApi.validateAddress("foo@mailgun.com");
//	}
//
//	public MailgunMessagesApi mailgunMessagesApi() {
//		return MailgunClient.config(PRIVATE_API_KEY)
//				       .createApi(MailgunMessagesApi.class);
//	}
//	public static JsonNode sendSimpleMessage() throws UnirestException {
//		HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + DOMAIN_NAME  + "/messages")
//		.basicAuth("api", PRIVATE_API_KEY)
//				 .queryString("from", "Excited User <USER@YOURDOMAIN.COM>")
//				 .queryString("to", "farindebabajide@gmail.com")
//				 .queryString("subject", "hello")
//				 .queryString("text", "testing")
//				 .asJson();
//		return request.getBody();
//	}
//
//	public MessageResponse sendMessage() {
//		MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(PRIVATE_API_KEY)
//				                                        .createApi(MailgunMessagesApi.class);
//
//		Message message = Message.builder()
//				                  .from("alaabdulmalik03@gmail.com")
//				                  .to("farindebabajide@gmail.com")
//				                  .subject("Hello")
//				                  .text("Testing out some Mailgun awesomeness!")
//				                  .build();
//
//		return mailgunMessagesApi.sendMessage(DOMAIN_NAME, message);
//	}
	
	public static void main(String[] args) {
		Mailer mailer = new Mailer();

//		AddressValidationResponse validationResponse = mailer.validateEmail();
//		log.info("Email Validated Successfully");
//		System.out.println("Result is: "+validationResponse.getResult());
//		System.out.println("Response is: "+validationResponse.getAddress());
		
//		MessageResponse response = mailer.sendMessage();
//		log.info("Email Sent Successfully");
//		System.out.println(response.getMessage());
		
	}
}
