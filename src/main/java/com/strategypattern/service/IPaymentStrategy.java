package com.strategypattern.service;

import com.strategypattern.dto.PaymentRequest;
import com.strategypattern.dto.PaymentResult;

public interface IPaymentStrategy {
    PaymentResult processPayment(PaymentRequest request);
}
