package app.bola.flywell.services.payment;

import app.bola.flywell.data.model.Payment;

public interface PaymentProcessor {


    void processPayment(Payment payment);
}
