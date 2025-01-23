package app.bola.flywell.services.payment;

import app.bola.flywell.data.model.Payment;
import app.bola.flywell.data.repositories.PaymentRepository;
import app.bola.flywell.dto.request.PaymentRequest;
import app.bola.flywell.dto.response.PaymentResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class FlyWellPaymentService implements PaymentService{


    final ModelMapper mapper;
    final PaymentRepository paymentRepository;


    @Override
    public PaymentResponse createNew(PaymentRequest request) {
        Payment payment = mapper.map(request, Payment.class);

        PaymentProcessor paymentProcessor = PaymentProcessorFactory.getPaymentProcessor(payment.getPaymentMethod());
        paymentProcessor.processPayment(payment);
        return null;
    }

    @Override
    public PaymentResponse findByPublicId(String publicId) {
        return null;
    }

    @Override
    public boolean existsByPublicId(String publicId) {
        return false;
    }

    @Override
    public Collection<PaymentResponse> findAll() {
        return List.of();
    }

    @Override
    public void removeAll() {

    }

    @Override
    public Collection<PaymentResponse> findAll(Pageable pageable) {
        return List.of();
    }
}
