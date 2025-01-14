package app.bola.flywell.services.users;

import app.bola.flywell.data.model.persons.Customer;
import app.bola.flywell.data.model.persons.OTP;
import app.bola.flywell.data.model.persons.UserBioData;
import app.bola.flywell.data.repositories.CustomerRepository;
import app.bola.flywell.data.repositories.UserBioDataRepository;
import app.bola.flywell.dto.request.NotificationRequest;
import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.services.notifications.mail.MailService;
import app.bola.flywell.dto.request.CustomerRequest;
import app.bola.flywell.exceptions.FailedRegistrationException;
import app.bola.flywell.exceptions.InvalidRequestException;


import app.bola.flywell.utils.Constants;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.sql.SQLException;
import java.util.*;

@Service
@AllArgsConstructor
public class FlyWellCustomerService implements CustomerService {

	final Validator validator;
	final ModelMapper mapper;
	final MailService mailer;
	final OTPService otpService;
	final CustomerRepository customerRepository;
	final UserBioDataRepository userBioDataRepository;
	final Logger logger = LoggerFactory.getLogger(FlyWellCustomerService.class);

    @Override
	@Transactional(rollbackFor = {SQLException.class, FailedRegistrationException.class})
	public CustomerResponse createNew(@NotNull CustomerRequest customerRequest) {

		Set<ConstraintViolation<CustomerRequest>> violations = validator.validate(customerRequest);
		Assert.isTrue(violations.isEmpty(), violations.stream().map(violation -> String.format("Field '%s': %s",
				violation.getPropertyPath(), violation.getMessage())).toList().toString());

		UserBioData bioData = mapper.map(customerRequest, UserBioData.class);
		UserBioData savedBio = userBioDataRepository.save(bioData);
		Customer customer = new Customer();
		customer.setBioData(savedBio);
		Customer savedCustomer = customerRepository.save(customer);
		
		OTP otp = otpService.createNew(bioData.getEmail());
		long otpData = otp.getData();
		mailer.sendOtp(buildNotificationRequest(bioData.getFirstName(), bioData.getEmail(), otpData));
		
		savedBio.addOtp(otp);
		userBioDataRepository.save(savedBio);
        return toResponse(savedCustomer);
	}


	@Override
	@Transactional
	public CustomerResponse activateCustomerAccount(String OTP, String publicId) throws InvalidRequestException {
		OTP otp = otpService.verifyOtp(OTP);
		logger.info("OTP successfully verified. Generated OTP: {}. For User {}", otp, publicId);
		Customer customer = customerRepository.findByPublicId(publicId).orElseThrow(()->new InvalidRequestException("USER WITH EMAIL NOT FOUND"));
		return toResponse(customer);
	}

	public CustomerResponse toResponse(Customer customer) {
        return mapper.map(customer, CustomerResponse.class);
	}

	private NotificationRequest buildNotificationRequest(String firstName, String email, long otp){
		return NotificationRequest.builder()
				       .email(email)
				       .code(String.valueOf(otp))
				       .OTP(otp)
				       .mailPath("otp")
				       .firstName(firstName)
				       .build();
	}

	
	@Override
	public List<CustomerResponse> findAll() {
		return customerRepository.findAll().stream().map(this::toResponse).toList();
	}

	
	@Override
	public CustomerResponse findByPublicId(String publicId) {
		Customer customer = customerRepository.findByPublicId(publicId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Customer")));
		return toResponse(customer);
	}

	@Override
	public boolean existsByPublicId(String publicId) {
		return customerRepository.existsByPublicId(publicId);
	}

	@Override public long getCountOfCustomers() {
		return customerRepository.count();
	}
	

	@Override
	public void removeAll() {
		customerRepository.deleteAll();
		userBioDataRepository.deleteAll();
	}
}
