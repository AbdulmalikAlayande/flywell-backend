package app.bola.flywell.broker;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@AllArgsConstructor
public class SimpleEventPublisher {


    @Value("${spring.pulsar.producer.topic-name}")
    private static String defaultTopic;
    @Value("${spring.pulsar.producer.name}")
    private static String defaultProducer;
    final PulsarTemplate<String> pulsarTemplate;

    public void publishPlainMessage(String message){
        pulsarTemplate.send(defaultTopic, message);
        log.info("{}::publishPlainMessage: str:: Message:: {}", getClass().getSimpleName(), message);
    }

    public void publishPlainMessage(String topic, String message){
        pulsarTemplate.send(topic, message);
        log.info("{}::publishPlainMessage: str, str:: Topic:: {}, Message:: {}", getClass().getSimpleName(), topic, message);
    }

    public void publishWithPulsarClient() {
        try(
            PulsarClient pulsarClient = PulsarClient.builder()
                    .serviceUrl("pulsar://localhost:6650")
                    .build();

            Producer<String> producer = pulsarClient.newProducer(Schema.STRING)
                    .topic(defaultTopic)
                    .producerName(defaultProducer)
                    .create()) {

            TypedMessageBuilder<String> message = producer.newMessage()
                    .key("")
                    .value("")
                    .property("", "")
                    .properties(Map.of());

            message.deliverAfter(10L, TimeUnit.SECONDS);
            producer.send("");
            producer.sendAsync("");

        }catch(PulsarClientException exception){

        }
    }
}
