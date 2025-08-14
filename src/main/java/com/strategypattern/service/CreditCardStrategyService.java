package com.strategypattern.service;

import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResult;
import org.springframework.stereotype.Service;

@Service
public class CreditCardStrategyService implements IPaymentStrategy {
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("\n[Kredi Kartı İşlemi]");
        System.out.println("Kart No: " + request.getCardNumber().replaceAll("\\s",""));
        System.out.println("Son Kullanma: " + request.getExpiryDate());
        System.out.println("CVV: " + request.getCvv());

        // Check Card Details
        if (validatePayment(request)) {
            return new PaymentResult(false, "Geçersiz kart numarası");
        }

        return new PaymentResult(true, "Kredi kartıyla ödeme başarıyla yapıldı");
    }

    public boolean validatePayment(PaymentRequest request) {
        String cardNumber = request.getCardNumber().replaceAll("\\s","");
        return request.getCardNumber() == null || cardNumber.length() < 16;
    }
}
