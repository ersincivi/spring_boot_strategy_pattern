package com.strategypattern.dto;

public record PaymentResponse(boolean success, String message, double amount) {}
