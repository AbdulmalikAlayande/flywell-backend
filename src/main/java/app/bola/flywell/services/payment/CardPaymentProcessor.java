package app.bola.flywell.services.payment;

import app.bola.flywell.config.ResourceConfig;
import app.bola.flywell.data.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class CardPaymentProcessor implements PaymentProcessor{

    final ResourceConfig resourceConfig = new ResourceConfig();
    private final String CARD_PAYMENT_URL = "https://api.flutterwave.com/v3/charges?type=card";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public void processPayment(Payment payment) {

    }

    public Object initiatePayment(Object cardPaymentRequest){

        try {
            String jsonRequest = objectMapper.writeValueAsString(cardPaymentRequest);
            String client = PayloadEncryptor.encryptPayload(jsonRequest, resourceConfig.getEncryptionKey());

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(CARD_PAYMENT_URL))
                    .header("Authorization", "Bearer " + resourceConfig.getSecretKey())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(new PaymentInitiationRequest(client))))
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            log.info("Response Body {}", response.body());
            return response.body();
        } catch (InterruptedException | IOException e) {
            log.error(e.getMessage(), e.getCause());
            throw new RuntimeException(e);
        }
    }


    @Getter
    @AllArgsConstructor
    static class PaymentInitiationRequest {

        private String client;

    }
}
