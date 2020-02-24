package com.dne.populationregisterymodel.Model;

import java.util.Date;

public class Address {

    private String country;
    private String state;
    private String city;
    private String street;
    private String postalCode;
    private Date startDate;
    private Date endDate;

    public String getCountry() {
        return this.country;
    }

    public String getState() {
        return this.state;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
