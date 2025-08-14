package com.strategypattern.service;

import com.strategypattern.enums.PaymentMethod;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactoryService {
    public static IPaymentStrategy createPaymentStrategy(PaymentMethod method) {
        return switch (method) {
            case CREDIT_CARD -> new CreditCardStrategyService();
            case PAYPAL -> new PayPalStrategyService();
            case BANK_TRANSFER -> new BankTransferStrategyService();
        };
    }
}
