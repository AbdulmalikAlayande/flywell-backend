package app.bola.flywell.services.payment;

import app.bola.flywell.data.model.Payment;
import com.flutterwave.services.BanKTransfer;

public class BankTransferProcessor implements PaymentProcessor{

    @Override
    public void processPayment(Payment payment) {
        BanKTransfer banKTransfer = new BanKTransfer();
    }
}
