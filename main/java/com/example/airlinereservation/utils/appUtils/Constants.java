package com.example.airlinereservation.utils.appUtils;

public final class Constants {
	
	public static final String MESSAGE_SUCCESSFULLY_SENT = "Email sent successfully";
	public static final String MESSAGE_FAILED_TO_SEND = "Failed to send email";
	public static final String TEMPLATE_LOAD_FAILED = "Error loading template content Template failed to load:: ";
	public static final String ACCOUNT_ACTIVATION_EMAIL_TEMPLATE_URL = "C:\\Users\\USER\\IdeaProjects\\AirlineReservation\\AirlineReservation\\main\\resources\\templates\\email.html";
	public static final String BREVO_MAIL_TEMPLATE_ID = "";
	public static final String BREVO_CONTACTS_IMPORT_URL = "https://api.brevo.com/v3/contacts/import";
	public static final String BREVO_SEND_EMAIL_API_URL = "https://api.brevo.com/v3/smtp/email";
	public static final String DUPLICATE_ACCOUNT_REGISTRATION_FAILURE_MESSAGE = "Registration Failed:: Seems Like You Already Have An Account With Us";
	public static final String EMPTY_FIELD_MESSAGE = "Field %s is null or empty";
	public static final String INCOMPLETE_DETAILS_MESSAGE = "Incomplete Details\n %s";
	public static final String INVALID_REQUEST_MESSAGE = "Invalid Request:: User with id %s not found";
	public static final String LOGIN_SUCCESS_MESSAGE = "Login Successful";
	public static final String LOGIN_FAILURE_MESSAGE = "Login Failed:: You do not have an account with us, Please register to create one";
	public static final String INCORRECT_FORMAT_LOGIN_FAILURE_MESSAGE = "Login Failed:: Please provide the full details requested in the correct format";
	public static final String SESSION_NOT_EXHAUSTED_MESSAGE = "Your session has not expired";
	public static final String UPDATE_NOT_COMPLETED = "Update could not be completed";
	public static final String INVALID_DESTINATION = "Invalid Destination";
}
