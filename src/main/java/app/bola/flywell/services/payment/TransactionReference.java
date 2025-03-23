package app.bola.flywell.services.payment;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TransactionReference {

    final String FLYWELL_PREFIX = "FLW-";

    public String generateTxRef() {

        String uuid = beautify(UUID.randomUUID().toString());
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());

        String uuidExtract = uuid.substring(0, 8);
        String currentTimeMillisExtract = currentTimeMillis.substring(0, 8);

        return FLYWELL_PREFIX+uuidExtract+currentTimeMillisExtract;
    }

    private @NotNull String beautify(@NotNull String uuid) {

        StringBuilder newUUId = new StringBuilder();
        for (int i = 0; i < uuid.length(); i++) {
            if (uuid.charAt(i) != '-') {
                newUUId.append(uuid.charAt(i));
            }
        }
        return newUUId.toString().toUpperCase();
    }
}
