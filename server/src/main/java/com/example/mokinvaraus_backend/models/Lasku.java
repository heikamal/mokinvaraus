package com.example.mokinvaraus_backend.models;

import jakarta.persistence.*;

/**
 * Class for invoice entities.
 * <p>
 *     Each invoice is stored in relational database and these entities represent one entry in the invoice table.
 * </p>
 */
@Entity
@Table(name = "invoice")
public class Lasku {

    /**
     * The ID of the invoice, generated by the database and the primary key of the table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private int invoiceId;

    /**
     * The ID of the reservation the invoice is attached to. Foreign key from the reservation table.
     */
    @Column(name = "reservation_id")
    private int reservationId;

    /**
     * The amount how much the customer owes in double.
     */
    @Column(name = "total")
    private double amount;

    /**
     * The amount of Value Added Tax for the invoice in double. Gained by multiplying the price with the tax rate.
     */
    @Column(name = "vat")
    private double tax;

    /**
     * Creates a new empty Lasku object.
     */
    public Lasku() {
    }

    /**
     * Returns the object's invoice ID field.
     * @return Invoice ID in int.
     */
    public int getInvoiceId() {
        return invoiceId;
    }

    /**
     * Returns the object's reservation ID field.
     * @return Reservation ID in int.
     */
    public int getReservationId() {
        return reservationId;
    }

    /**
     * Returns the object's amount field.
     * @return Amount in double.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the object's tax field.
     * @return Tax in double.
     */
    public double getTax() {
        return tax;
    }

    /**
     * Sets the object's invoice ID field.
     * @param invoiceId Invoice ID in int.
     */
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * Converts the Lasku object to a string representation.
     * @return the string representation of the Lasku object.
     */
    @Override
    public String toString() {
        return "Lasku{" +
                "invoiceId=" + invoiceId +
                ", reservationId=" + reservationId +
                ", amount=" + amount +
                ", tax=" + tax +
                '}';
    }
}
