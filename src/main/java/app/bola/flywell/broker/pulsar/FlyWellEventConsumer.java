package app.bola.flywell.broker.pulsar;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlyWellEventConsumer {


    @PulsarListener(
            topics = {"${spring.pulsar.producer.topic-name}"},
            subscriptionName = "${spring.pulsar.consumer.subscription.name}",
            subscriptionType = SubscriptionType.Key_Shared
    )
    public void consumePlainMessage(String message){
        log.info("{}::handleCustomerMessage:[Consumed Event Customer:: {}]", getClass().getSimpleName(), message);
    }
}
