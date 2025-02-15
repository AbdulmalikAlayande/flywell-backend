package app.bola.flywell.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ResourceConfig {

    @Value("${payment-service.encryption-key}")
    private String encryptionKey;

    @Value("${payment-service.secret-key}")
    private String secretKey;

    @Value("${payment-service.public-key}")
    private String publicKey;

}
