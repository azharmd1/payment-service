package com.paymentservice.paymentgateways;

import com.paymentservice.models.PaymentStatus;

public interface PaymentGatewayInterface {

    String createPaymentLink(
            Long amount,
            String userName,
            String userEmail,
            String userPhone,
            Long orderId
    );

    PaymentStatus getPaymentStatus(
            String paymentId
    );
}