package com.vdgarcia.appointment_service.publisher.inter;

import com.vdgarcia.events.AppointmentCreated;

public interface CreateEventPublisher {

    void publishAppointmentCreated(AppointmentCreated evento);
}
