package com.example.mokinvaraus_backend.models;

import jakarta.persistence.*;

/**
 * Class for services attached to a reservation.
 * <p>
 *     While service and reservation have their own table, they have a many-to-many relationship represented by a join table.
 * </p>
 */
@Entity
@Table(name = "reservation_services")
public class VarauksenPalvelu {

    @EmbeddedId
    VarauksenPalveluKey id;
    
    @ManyToOne
    @MapsId("reservationId")
    @JoinColumn(name = "reservation_id")
    Varaus reservation;

    @ManyToOne
    @MapsId("serviceId")
    @JoinColumn(name = "service_id")
    Palvelu service;

    /**
     * Count of how many times the service has been taken in a reservation.
     */
    @Column(name = "number")
    private int lkm;

    /**
     * Creates a new empty VarauksenPalvelu object.
     */
    public VarauksenPalvelu() {
    }

    /**
     * Returns the object's reservation field.
     * @return Reservation in Varaus entity.
     */
    public Varaus getReservation() {
        return reservation;
    }

    /**
     * Returns the object's service field.
     * @return Service in Palvelu entity.
     */
    public Palvelu getService() {
        return service;
    }

    /**
     * Returns the object's lkm field.
     * @return lkm in int.
     */
    public int getLkm() {
        return lkm;
    }

    /**
     * Sets the object's reservation field.
     * @param reservation Reservation in Varaus-class entity.
     */
    public void setReservationId(Varaus reservation) {
        this.reservation = reservation;
    }

    /**
     * Sets the object's service field.
     * @param service Service in Palvelu-class entity.
     */
    public void setServiceId(Palvelu service) {
        this.service = service;
    }
}
