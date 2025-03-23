package app.bola.flywell.services.payment;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInitiationResponse {

    private boolean status;
    private String message;
    private Data data;

    @Getter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Data{
        private String authorization_url;
        private String access_code;
        private String reference;
    }
}
