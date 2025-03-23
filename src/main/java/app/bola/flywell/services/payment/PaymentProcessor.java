package app.bola.flywell.services.payment;

import app.bola.flywell.data.model.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.iyanuadelekan.paystackjava.core.ApiQuery;
import me.iyanuadelekan.paystackjava.core.Transactions;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface PaymentProcessor {

    Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    default TransactionInitiationResponse initiateTransaction(String email, String txName, String amount, String callbackUrl) {

        TransactionReference transactionReference  = new TransactionReference();

        ApiQuery transactionParams = new ApiQuery();
        transactionParams.putParams("email", email);
        transactionParams.putParams("name", txName);
        transactionParams.putParams("amount", amount);
        transactionParams.putParams("reference", transactionReference.generateTxRef());
        transactionParams.putParams("channels", new String[]{"card", "bank", "bank_transfer"});
        transactionParams.putParams("callback_url", callbackUrl);
        transactionParams.putParams("metadata", new JSONObject("{" +
                "\"cancel_action\":\"https://www.flywell.tech\", " +
                "\"custom_filters\":\"{\"recurring\": \"true\"}\", "+
                "}"
        ));

        Transactions transaction = new Transactions();
        JSONObject object = transaction.initializeTransaction(transactionParams);

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(object.toString(), TransactionInitiationResponse.class);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    void processPayment(Payment payment);
}
