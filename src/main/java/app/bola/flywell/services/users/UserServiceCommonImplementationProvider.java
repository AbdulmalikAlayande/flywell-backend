package app.bola.flywell.services.users;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Set;

@Component
@AllArgsConstructor
public class UserServiceCommonImplementationProvider<T> {

    final Validator validator;

    public void validateFields(T request) {

        Set<ConstraintViolation<T>> violations = validator.validate(request);
        Assert.isTrue(violations.isEmpty(), violations.stream().map(violation -> String.format("Field '%s': %s",
                violation.getPropertyPath(), violation.getMessage())).toList().toString());
    }
}
