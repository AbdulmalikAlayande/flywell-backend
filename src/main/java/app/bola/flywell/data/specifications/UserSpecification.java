package app.bola.flywell.data.specifications;

import app.bola.flywell.data.model.users.Otp;
import app.bola.flywell.data.model.users.User;

import app.bola.flywell.security.models.Role;
import jakarta.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;


public class UserSpecification {

    public static Specification<User> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<User> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<User> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<User> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber);
    }

    public static Specification<User> hasRole(String roleName) {
        return (root, query, criteriaBuilder) -> {
            Join<User, Role> roles = root.join("roles");
            return criteriaBuilder.equal(roles.get("name"), roleName);
        };
    }

    public static Specification<User> hasOtp(String otpCode) {
        return (root, query, criteriaBuilder) -> {
            Join<User, Otp> otps = root.join("Otps");
            return criteriaBuilder.equal(otps.get("code"), otpCode);
        };
    }

}
