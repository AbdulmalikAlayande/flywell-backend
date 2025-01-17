package app.bola.flywell.utils;

public final class Constants {
	public static final String TEST_IMAGE_LOCATION = "C:\\Users\\USER\\Downloads\\DJANGO 3\\part 1 Uploading Files\\puppy.jpeg";
	public static final String SENDER_FIRSTNAME = "FlyWell";
	public static final String SENDER_LASTNAME = "Inc";
	public static final String SENDER_FULL_NAME = SENDER_FIRSTNAME +" "+ SENDER_LASTNAME;
	public static final String API_KEY = "api-key";
	public static final String PASSWORD_DOES_NOT_CONTAIN_EXPECTED_CHARACTER = "Password should contain either of the special characters %s";
	public static final String INVALID_EMAIL_FORMAT = "Email pattern is not valid:: Please enter a valid email format";
	public static final String INVALID_DOMAIN = "The domain part %s of %s is invalid";
	public static final String INVALID_PASSWORD_LENGTH = "Password %s length is invalid it should be between 8 and 15 characters";
	public static final String SENDER_EMAIL = "alaabdulmalik03@gmail.com";
	public static final String MESSAGE_SUCCESSFULLY_SENT = "Email sent successfully";
	public static final String SUCCESSFUL_ACTIVATION_MESSAGE = "Account Activation Successful";
	public static final String ERROR_MESSAGE = "Error Message:: {}";
	public static final String MESSAGE_FAILED_TO_SEND = "Failed to send email";
	public static final String TEMPLATE_LOAD_FAILED = "Error loading template content Template failed to load:: ";
	public static final String ACCOUNT_ACTIVATION_EMAIL_TEMPLATE_URL = "classpath:/templates/otp.html";
	public static final String ACCOUNT_ACTIVATION_MAIL_SUBJECT = "Bola Air Account Activation";
	public static final String FRONTEND_BASE_URL = "http://localhost:3000/";
	public static final String BREVO_CONTACTS_IMPORT_URL = "https://api.brevo.com/v3/contacts/import";
	public static final String BREVO_SEND_EMAIL_API_URL = "https://api.brevo.com/v3/smtp/email";
	public static final String EMPTY_FIELDS_MESSAGE = "Field %s is null or empty";
	public static final String EMPTY_FIELD_MESSAGE = "Field Cannot Be Null Or Empty";
	public static final String REGISTRATION_SUCCESSFUL_MESSAGE = "Registration Successful";
	public static final String ENTITY_CREATION_SUCCESSFUL = "%s Successfully Created";
	public static final String INCOMPLETE_DETAILS_MESSAGE = "Incomplete Details:: %s";
	public static final String INVALID_REQUEST_MESSAGE = "Invalid Request:: %s with %s %s not found";
	public static final String ENTITY_NOT_FOUND = "%s Not Found";
	public static final String ENTITY_SUCCESSFULLY_RETRIEVED = "Flight Was Successfully Retrieved";

}
