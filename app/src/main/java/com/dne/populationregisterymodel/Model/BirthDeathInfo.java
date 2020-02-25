package com.dne.populationregisterymodel.Model;

import java.util.ArrayList;

public class BirthDeathInfo {

    private String dateOfBirth;
    private String placeOfBirth;
    private String dateOfDeath;
    private String placeOfDeath;
    private Person thisPerson;

    public BirthDeathInfo(Person thisPerson) {
        this.thisPerson = thisPerson;
    }

    // ===================================
    //            main functions
    // ===================================
    public void addBirthEvent(String date, String place) {
        setDateOfBirth(date);
        setPlaceOfBirth(place);
    }

    public void addDeathEvent(String date, String place) {
        setDateOfDeath(date);
        setPlaceOfDeath(place);
        MaritalStatus lastStatus = null;
        ArrayList<MaritalStatus> maritalInfo = getThisPerson().getFamilyRelation()
                .getMaritalStatusInfo();
        // changing marital status for partner
        if (maritalInfo != null && !maritalInfo.isEmpty()) {
            lastStatus = maritalInfo.get(maritalInfo.size() - 1);
            switch (lastStatus.getRelationshipStatus()) {
                case MARRIED:
                case COHABITATION:
                    lastStatus.getPartner().getFamilyRelation()
                            .addNewMaritalStatus(MaritalStatus.Status.WIDOWED, getThisPerson());
                    break;
            }
        }
    }

    // ===================================
    //   get/set functions for variables
    // ===================================
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public String getDateOfDeath() {
        return this.dateOfDeath;
    }

    public String getPlaceOfDeath() {
        return this.placeOfDeath;
    }

    public Person getThisPerson() {
        return this.thisPerson;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public void setThisPerson(Person thisPerson) {
        this.thisPerson = thisPerson;
    }
}
