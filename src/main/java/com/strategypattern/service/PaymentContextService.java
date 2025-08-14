package com.strategypattern.service;

import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResult;
import com.strategypattern.enums.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentContextService {
    private final Map<PaymentMethod, IPaymentStrategy> strategies;

    @Autowired
    public PaymentContextService(List<IPaymentStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        strategy -> {
                            if (strategy instanceof CreditCardStrategyService) return PaymentMethod.CREDIT_CARD;
                            if (strategy instanceof PayPalStrategyService) return PaymentMethod.PAYPAL;
                            if (strategy instanceof BankTransferStrategyService) return PaymentMethod.BANK_TRANSFER;
                            throw new IllegalStateException("Bilinmeyen strateji");
                        },
                        Function.identity()
                ));
    }

    public PaymentResult executeStrategy(PaymentMethod method, PaymentRequest request) {
        IPaymentStrategy strategy = strategies.get(method);
        if (strategy == null) {
            throw new IllegalArgumentException("Geçersiz ödeme yöntemi");
        }
        return strategy.processPayment(request);
    }
}
