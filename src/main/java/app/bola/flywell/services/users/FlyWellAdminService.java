package app.bola.flywell.services.users;

import app.bola.flywell.data.model.users.User;
import app.bola.flywell.data.repositories.*;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.dto.response.*;
import app.bola.flywell.exceptions.*;
import app.bola.flywell.security.repositories.RoleRepository;
import app.bola.flywell.services.notifications.mail.MailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class FlyWellAdminService implements AdminService{

    final ModelMapper mapper;
    final MailService mailService;
    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final UserServiceCommonImplementationProvider<CreateAdminRequest> implementationProvider;

    @Override
    public AdminResponse createNew(CreateAdminRequest request) {

        implementationProvider.validateFields(request);

        User admin = mapper.map(request, User.class);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.getRoles().add(roleRepository.findByName("ADMIN").getFirst());

        User savedAdmin = userRepository.save(admin);
        return toResponse(savedAdmin);
    }

    @Override
    public AdminInvitationResponse inviteAdmin(AdminInvitationRequest request) throws InvalidRequestException {

        String adminCode = generateAdminCode(request.getEmail());

        NotificationRequest.NotificationRequestBuilder notificationBuilder = NotificationRequest.builder();
        notificationBuilder.email(request.getEmail());
        notificationBuilder.code(adminCode);
        notificationBuilder.mailPath("invite");

        mailService.sendAdminInvitationEmail(notificationBuilder.build());
        return AdminInvitationResponse.builder().email(request.getEmail()).code(adminCode).build();
    }

    private String generateAdminCode(String adminEmail) {

        String adminCodePrefix = "__FW-ADMIN__";
        String prefixHashCode = String.valueOf(adminCodePrefix.hashCode());
        String adminEmailHashCode = String.valueOf(adminEmail.hashCode());
        return prefixHashCode+adminEmailHashCode;
    }

    private AdminResponse toResponse(User admin) {
        return mapper.map(admin, AdminResponse.class);
    }

    @Override
    public AdminResponse findByPublicId(String publicId) {
        return null;
    }

    @Override
    public boolean existsByPublicId(String publicId) {
        return false;
    }

    @Override
    public Collection<AdminResponse> findAll() {
        return List.of();
    }

    @Override
    public void removeAll() {
        userRepository.deleteAll();
    }

}
