package com.strategypattern.component;

import com.strategypattern.enums.PaymentMethod;
import com.strategypattern.service.BankTransferStrategyService;
import com.strategypattern.service.CreditCardStrategyService;
import com.strategypattern.service.IPaymentStrategy;
import com.strategypattern.service.PayPalStrategyService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFactory {
    public static IPaymentStrategy executeStrategy(PaymentMethod method) {
        // Java 21 switch case
        return switch (method) {
            case CREDIT_CARD -> new CreditCardStrategyService();
            case PAYPAL -> new PayPalStrategyService();
            case BANK_TRANSFER -> new BankTransferStrategyService();
        };
    }
}
