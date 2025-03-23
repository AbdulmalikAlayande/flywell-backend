package app.bola.flywell.broker;

import app.bola.flywell.data.model.users.User;
import app.bola.flywell.dto.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlyWellPublisher {

//    final PulsarTemplate<User> pulsarTemplate;
//    final ModelMapper mapper;
//    @Value("${spring.pulsar.producer.topic-name}")
//    String topicName;

    public CustomerResponse sendMessage(){
//        User customer = User.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("john.doe@example.com")
//                .phoneNumber("07036174617")
//               .build();
//
//        pulsarTemplate.sendAsync(topicName, customer);
//        log.info("Published customer: {}", customer);
//        return mapper.map(customer, CustomerResponse.class);
        return null;
    }
}
