package com.example.mokinvaraus_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Class for the Postal Code Entities.
 * <p>
 * All the Postal/Zip Codes are stored in an appropriate table in the database as a key-value pair tying a zip code to a city/municipality.
 */
@Entity
@Table(name = "post")
public class Posti {

    /**
     * Entry ID, represents postal/zip code.
     */
    @Id
    @Column(name = "zip")
    private String postalCode;

    /**
     * The name of the city/municipality.
     */
    @Column(name = "city")
    private String city;

    /**
     * Creates a new empty Posti instance.
     */
    public Posti() {
    }

    /**
     * Returns the postal code.
     * @return Postal/zip code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Returns the city/municipality name.
     * @return City name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city/municipality name.
     * @param city The name of the city in a string.
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Posti{" +
                "postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
