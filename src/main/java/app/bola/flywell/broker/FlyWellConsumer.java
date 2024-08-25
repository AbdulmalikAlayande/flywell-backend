package app.bola.flywell.broker;

import app.bola.flywell.data.model.persons.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlyWellConsumer {


    @PulsarListener(
            topics = {"${spring.pulsar.producer.topic-name}"},
            subscriptionName = "${spring.pulsar.consumer.subscription.name}",
            subscriptionType = SubscriptionType.Shared
    )
    public void handleCustomerMessage(Customer customer){
        log.info("FlyWellConsumer::handleCustomerMessage:[Consumed Event Customer:: {}]", customer.toString());
    }
}
