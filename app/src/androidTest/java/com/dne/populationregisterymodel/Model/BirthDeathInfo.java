package com.dne.populationregisterymodel.Model;

import java.util.Date;

public class BirthDeathInfo {

    private Date dateOfBirth;
    private String placeOfBirth;
    private Date dateOfDeath;
    private String placeOfDeath;
    private Person thisPerson;

    public BirthDeathInfo(Person thisPerson) {
        this.thisPerson = thisPerson;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public Date getDateOfDeath() {
        return this.dateOfDeath;
    }

    public String getPlaceOfDeath() {
        return this.placeOfDeath;
    }

    public Person getThisPerson() {
        return this.thisPerson;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public void setThisPerson(Person thisPerson) {
        this.thisPerson = thisPerson;
    }
}
