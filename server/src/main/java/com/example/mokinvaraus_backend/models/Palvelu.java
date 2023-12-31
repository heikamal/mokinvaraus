package com.example.mokinvaraus_backend.models;

import jakarta.persistence.*;

/**
 * Class for Service entities.
 *
 * Each service is stored in relational database and these entities represent one entry in the cabin table.
 */
@Entity
@Table(name = "service")
public class Palvelu {

    /**
     * Service's unique ID in int, given when creating the database entry. Primary Key.
     */
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) -- TODO: katso tarvitseeko fiksata
    @Column(name = "service_id")
    private int serviceId;

    /**
     * ID of the area where the service is offered in int, restricted by the area table.
     */
    @Column(name = "location_Id")
    private int areaId;

    /**
     * Name of the service in string.
     */
    @Column(name = "name")
    private String name;

    /**
     * The service's type in string.
     */
    @Column(name = "type")
    private int type;

    /**
     * Description of the service offered in string.
     */
    @Column(name = "description")
    private String description;

    /**
     * The price of the service in double.
     */
    @Column(name = "price")
    private double price;

    /**
     * The amount of Value Added Tax for the service in double. Gained by multiplying the price with the tax rate.
     */
    @Column(name = "vat")
    private double tax;

    /**
     * Creates a new empty Palvelu object.
     */
    public Palvelu() {
    }

    /**
     * Returns the object's service ID field.
     * @return The service's ID in int.
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * Returns the object's area ID field.
     * @return The area's ID in int.
     */
    public int getAreaId() {
        return areaId;
    }

    /**
     * Returns the object's name field.
     * @return The service's name in string.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the object's type field.
     * @return The service's type in string.
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the object's description field.
     * @return The service's description in string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the object's price field.
     * @return The service's price in double.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the object's tax field.
     * @return The service's value added tax in double.
     */
    public double getTax() {
        return tax;
    }

    /**
     * Sets the object's service ID field.
     * @param serviceId The ID of the service to set
     */
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * Sets the area ID.
     * @param areaId    The ID of the area to set
     */
    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    /**
     * Sets the name of the service.
     * @param name  The name of the service.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of the service.
     * @param type  The type of the service.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Sets the description of the service.
     * @param description   The description of the service.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the price of the service.
     * @param price  The price of the service.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the value added tax for the service.
     * @param tax   The value added tax for the service
     */
    public void setTax(double tax) {
        this.tax = tax;
    }

    /**
     * Returns a string representation of the object.
     * @return    A string representation of the object
     */
    @Override
    public String toString() {
        return "Palvelu{" +
                "serviceId=" + serviceId +
                ", areaId=" + areaId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", tax=" + tax +
                '}';
    }
}
