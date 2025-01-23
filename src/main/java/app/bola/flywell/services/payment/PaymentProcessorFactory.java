package app.bola.flywell.services.payment;

import app.bola.flywell.data.model.enums.PaymentMethod;

public class PaymentProcessorFactory {

    public static PaymentProcessor getPaymentProcessor(PaymentMethod paymentMethod) {

        switch (paymentMethod) {

            case CARD: new CardPaymentProcessor();
            case DIRECT_DEBIT: new DirectDebitProcessor();
            case BANK_TRANSFER: new BankTransferProcessor();
            default: throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }
}
