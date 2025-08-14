package com.strategypattern.service;

import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResponse;
import com.strategypattern.dto.PaymentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    IPaymentStrategy paymentStrategy;
//    private final PaymentContextService paymentContextService;

//    @Autowired
//    public PaymentService(PaymentContextService paymentContextService) {
//        this.paymentContextService = paymentContextService;
//    }

    public PaymentResponse processPayment(PaymentRequest request) {
        paymentStrategy = PaymentFactoryService.createPaymentStrategy(request.getPaymentMethod());
        PaymentResult paymentResult = paymentStrategy.processPayment(request);

        return new PaymentResponse(
                paymentResult.success(),
                paymentResult.message(),
                request.getAmount()
        );
    }

//    public PaymentResponse processPayment(PaymentRequest request) {
//        PaymentResult result = paymentContextService.executeStrategy(
//                request.getPaymentMethod(),
//                request
//        );
//
//        return new PaymentResponse(
//                result.success(),
//                result.message(),
//                request.getAmount()
//        );
//    }
}
