package app.bola.flywell.services.payment;

import app.bola.flywell.data.model.Payment;
import com.flutterwave.bean.CardRequest;
import com.flutterwave.services.CardCharge;
import org.modelmapper.ModelMapper;

public class CardPaymentProcessor implements PaymentProcessor{

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public void processPayment(Payment payment) {
        mapper.map(payment, CardRequest.class);
        CardCharge cardCharge = new CardCharge();

    }
}
