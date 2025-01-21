package app.bola.flywell.services.users;

import app.bola.flywell.data.model.users.Otp;
import app.bola.flywell.data.model.users.User;
import app.bola.flywell.data.repositories.UserRepository;
import app.bola.flywell.data.specifications.UserSpecification;
import app.bola.flywell.dto.request.LoginRequest;
import app.bola.flywell.dto.request.NotificationRequest;
import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.dto.response.LoginResponse;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.security.models.Role;
import app.bola.flywell.security.repositories.RoleRepository;
import app.bola.flywell.security.services.AuthService;
import app.bola.flywell.services.notifications.mail.MailService;
import app.bola.flywell.dto.request.CustomerRequest;
import app.bola.flywell.exceptions.FailedRegistrationException;
import app.bola.flywell.exceptions.InvalidRequestException;

import app.bola.flywell.utils.Constants;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
@AllArgsConstructor
public class FlyWellCustomerService implements CustomerService {

	final ModelMapper mapper;
	final MailService mailer;
	final OtpService otpService;
	final AuthService authService;
	final RoleRepository roleRepository;
	final UserRepository userRepository;
	final PasswordEncoder passwordEncoder;
	final Logger logger = LoggerFactory.getLogger(FlyWellCustomerService.class);
	final UserServiceCommonImplementationProvider<CustomerRequest> implementationProvider;


    @Override
	@Transactional(rollbackFor = {SQLException.class, FailedRegistrationException.class})
	public CustomerResponse createNew(@NotNull CustomerRequest customerRequest) {

		implementationProvider.validateFields(customerRequest);

		User customer = mapper.map(customerRequest, User.class);
		customer.setPassword(passwordEncoder.encode(customerRequest.getPassword()));
		customer.getRoles().add(roleRepository.findByName("USER").getFirst());

		Otp otp = otpService.createNew(customer.getEmail());
		mailer.sendOtp(buildNotificationRequest(customer.getFirstName(), customer.getEmail(), otp.getData()));

		customer.addOtp(otp);
		User savedCustomer = userRepository.save(customer);
        return toResponse(savedCustomer);
	}

	private CustomerResponse toResponse(User user) {
		return mapper.map(user, CustomerResponse.class);
	}


	@Override
	@Transactional
	public LoginResponse activateCustomerAccount(String OTP, String publicId) throws InvalidRequestException {
		Otp otp = otpService.verifyOtp(OTP);
		logger.info("OTP successfully verified. Generated OTP: {}. For User {}", otp, publicId);
		User user = userRepository.findByPublicId(publicId).orElseThrow(()->new InvalidRequestException("USER WITH EMAIL NOT FOUND"));
		return authService.login(new LoginRequest(user.getEmail(), user.getPassword()));
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
		Specification<User> userSpec = Specification.where(UserSpecification.hasRole("USER"));
		return userRepository.findAll(userSpec).stream().map(this::toResponse).toList();
	}

	@Override
	public List<CustomerResponse> findAll(Pageable pageable) {
		Specification<User> userSpec = Specification.where(UserSpecification.hasRole("USER"));
		Page<User> page = userRepository.findAll(userSpec, pageable);
		return page.map(this::toResponse).toList();
	}

	
	@Override
	public CustomerResponse findByPublicId(String publicId) {
		User customer = userRepository.findByPublicId(publicId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Customer")));
		return toResponse(customer);
	}

	@Override
	public boolean existsByPublicId(String publicId) {
		return userRepository.existsByPublicId(publicId);
	}

	@Override
	public long getCountOfCustomers() {
		Specification<User> spec = Specification.where(UserSpecification.hasRole("USER"));
		return userRepository.count(spec);
	}
	

	@Override
	public void removeAll() {
		userRepository.deleteUsersByRoles(Set.of(Role.builder().name("USER").build()));
	}
}
