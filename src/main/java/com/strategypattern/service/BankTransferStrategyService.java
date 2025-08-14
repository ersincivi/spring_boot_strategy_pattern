package com.strategypattern.service;

import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResult;
import org.springframework.stereotype.Service;

@Service
public class BankTransferStrategyService implements IPaymentStrategy {
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("\n[Havale İşlemi]");
        System.out.println("IBAN: " + request.getIban().replaceAll("\\s",""));

        // Check IBAN
        if (validatePayment(request)) {
            return new PaymentResult(false, "Geçersiz IBAN numarası");
        }

        return new PaymentResult(true, "Banka havalesi başarıyla yapıldı");
    }

    public boolean validatePayment(PaymentRequest request) {
        String iban = request.getIban().replaceAll("\\s","");
        return request.getIban() == null || iban.length() != 20;
    }
}
