package app.bola.flywell.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${app.base.url}")
    private String prodServerUrl;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("ws")
                .setAllowedOrigins(
                        prodServerUrl,
                        "https://flywell.tech",
                        "http://localhost:3000",
                        "https://www.flywell.tech",
                        "https://flywell.vercel.app"
                )
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app", "/flywell");
//                .enableStompBrokerRelay("/topic", "/queue")
//                .setRelayHost("")
//                .setRelayPort(61613)
//                .setClientLogin("flywell")
//                .setClientPasscode("flywell");
    }

}
