package com.strategypattern.service;

import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResult;
import org.springframework.stereotype.Service;

@Service
public class PayPalStrategyService implements IPaymentStrategy {
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("\n[PayPal İşlemi]");
        System.out.println("Email: " + request.getEmail());

        // Check Paypal Payment Details
        if (validatePayment(request)) {
            return new PaymentResult(false, "Geçersiz PayPal bilgileri");
        }

        return new PaymentResult(true, "Paypal ile ödeme başarıyla yapıldı");
    }

    public boolean validatePayment(PaymentRequest request) {
        return !request.getEmail().contains("@") || request.getPassword().length() < 6;
    }
}
