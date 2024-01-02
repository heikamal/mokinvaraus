package com.example.mokinvaraus_backend.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class VarauksenPalveluKey implements Serializable {
    
    /**
     * Reservation ID. Foreign key from the reservation table. Forms the primary key with service_id field.
     */
    @Column(name = "reservation_id")
    private int reservationId;

    /**
     * Service ID. Foreign key from the service table. Forms the primary key with service_id field.
     */
    @Column(name = "service_id")
    private int serviceId;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + reservationId;
        result = prime * result + serviceId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VarauksenPalveluKey other = (VarauksenPalveluKey) obj;
        if (reservationId != other.reservationId)
            return false;
        if (serviceId != other.serviceId)
            return false;
        return true;
    }
    
}
