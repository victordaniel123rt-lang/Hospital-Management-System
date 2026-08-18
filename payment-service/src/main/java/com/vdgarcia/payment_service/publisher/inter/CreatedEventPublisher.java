package com.vdgarcia.payment_service.publisher.inter;

import com.vdgarcia.events.PaymentCompleted;

public interface CreatedEventPublisher {
    void publishPaymentCreated(PaymentCompleted evento);
}
