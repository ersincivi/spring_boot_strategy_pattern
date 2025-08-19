package com.strategypattern.service;

import com.strategypattern.component.PaymentContextFactory;
import com.strategypattern.component.PaymentFactory;
import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResponse;
import com.strategypattern.dto.PaymentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
//    IPaymentStrategy paymentStrategy;
    private final PaymentContextFactory paymentContextFactory;

    @Autowired
    public PaymentService(PaymentContextFactory paymentContextFactory) {
        this.paymentContextFactory = paymentContextFactory;
    }

//    public PaymentResponse processPayment(PaymentRequest req) {
//        paymentStrategy = PaymentFactory.executeStrategy(req.getPaymentMethod());
//        PaymentResult res = paymentStrategy.processPayment(req);
//        return new PaymentResponse(res.success(), res.message(), req.getAmount());
//    }

    public PaymentResponse processPayment(PaymentRequest req) {
        PaymentResult res = paymentContextFactory.executeStrategy(req.getPaymentMethod(), req);
        return new PaymentResponse(res.success(), res.message(), req.getAmount());
    }
}
