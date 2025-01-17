package app.bola.flywell.services.users;

import app.bola.flywell.data.model.users.User;
import app.bola.flywell.data.repositories.*;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.dto.response.*;
import app.bola.flywell.exceptions.*;
import app.bola.flywell.services.flightservice.FlightService;
import app.bola.flywell.services.notifications.Validator;
import app.bola.flywell.services.notifications.mail.MailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlyWellAdminService implements AdminService{
    
    final UserRepository userRepository;
    private Validator validator;
    private MailService mailService;
    private ModelMapper mapper;
    private FlightService flightService;

    @Override
    public CreateAdminResponse createNew(CreateAdminRequest createAdminRequest) {
       return null;
    }

    @Override
    public AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) throws InvalidRequestException, FieldInvalidException, EmptyFieldException {
        if (invitationRequest.getEmail() == null || invitationRequest.getEmail().isEmpty() || invitationRequest.getEmail().isBlank())
            throw new EmptyFieldException("Email Cannot Be Blank Or Empty Spaces");
        else {
            validator.validateEmail(invitationRequest.getEmail());
            String adminCode = generateAdminCode(invitationRequest.getEmail());
            User user = new User();
            user.setEmail(invitationRequest.getEmail());
            user.setAdminCode(adminCode);
            userRepository.save(user);
            NotificationRequest notificationRequest = buildNotificationRequest(invitationRequest, adminCode);
            ResponseEntity<NotificationResponse> response = mailService.sendAdminInvitationEmail(notificationRequest);
            System.out.println(response);
            return AdminInvitationResponse.builder()
                           .email(invitationRequest.getEmail())
                           .code(adminCode)
                           .message("An Invitation Mail Has Been Sent To " + invitationRequest.getEmail())
                           .build();
        }
    }
    
    private NotificationRequest buildNotificationRequest(AdminInvitationRequest invitationRequest, String code) {
        return NotificationRequest.builder()
                                  .email(invitationRequest.getEmail())
                                  .code(code)
                                  .mailPath("invite")
                                  .firstName("Abdulmalik")
                                  .build();
    }
    
    private String generateAdminCode(String adminEmail) {
        String adminCodePrefix = "__BOLA--AIR__";
        String prefixHashCode = String.valueOf(adminCodePrefix.hashCode());
        String adminEmailHashCode = String.valueOf(adminEmail.hashCode());
        return prefixHashCode+adminEmailHashCode;
    }
    
    @Override
    public FlightResponse addNewFlight(FlightRequest flightRequest) {
        return flightService.createNew(flightRequest);
    }
    
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
    
}
