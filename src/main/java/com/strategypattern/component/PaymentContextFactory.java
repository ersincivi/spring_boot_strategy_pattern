package com.strategypattern.component;

import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResult;
import com.strategypattern.enums.PaymentMethod;
import com.strategypattern.service.BankTransferStrategyService;
import com.strategypattern.service.CreditCardStrategyService;
import com.strategypattern.service.IPaymentStrategy;
import com.strategypattern.service.PayPalStrategyService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PaymentContextFactory {
    private final Map<PaymentMethod, IPaymentStrategy> strategies;

//    Java 16 instanceof
//    public PaymentContextService(List<IPaymentStrategy> strategyList) {
//        this.strategies = strategyList.stream()
//                .collect(Collectors.toMap(
//                        strategy -> {
//                            if (strategy instanceof CreditCardStrategyService) return PaymentMethod.CREDIT_CARD;
//                            if (strategy instanceof PayPalStrategyService) return PaymentMethod.PAYPAL;
//                            if (strategy instanceof BankTransferStrategyService) return PaymentMethod.BANK_TRANSFER;
//                            throw new IllegalStateException("Bilinmeyen strateji");
//                        },
//                        Function.identity()
//                ));
//    }

//    Java 21 switch case
    public PaymentContextFactory(List<IPaymentStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        strategy -> switch (strategy) {
                            case CreditCardStrategyService creditCard -> PaymentMethod.CREDIT_CARD;
                            case PayPalStrategyService payPal -> PaymentMethod.PAYPAL;
                            case BankTransferStrategyService bankTransfer -> PaymentMethod.BANK_TRANSFER;
                            default -> throw new IllegalStateException("Bilinmeyen strateji: " + strategy.getClass().getName());
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
