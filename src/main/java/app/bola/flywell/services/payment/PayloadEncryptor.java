package app.bola.flywell.services.payment;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;
import java.util.logging.Logger;

@Slf4j
public class PayloadEncryptor {

    private static final Logger logger = Logger.getLogger(PayloadEncryptor.class.getName());
    public static final String TRANSFORMATION = "DESede/ECB/PKCS5Padding";
    public static final String ALGORITHM = "DESede";

    public static String encryptPayload(String payload, String encryptionKey) {

        try {
            byte[] encryptionKeyBytes = encryptionKey.getBytes();

            DESedeKeySpec spec = new DESedeKeySpec(encryptionKeyBytes);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey secretKey = keyFactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] cipherTextBytes = cipher.doFinal(payload.getBytes());

            String cipherText = Base64.getEncoder().encodeToString(cipherTextBytes);
            logger.fine(() -> String.format("Generated Cipher Text is %s", cipherText));

            return cipherText;
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            throw new RuntimeException(exception);
        }
    }
}
