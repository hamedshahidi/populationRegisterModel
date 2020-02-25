package com.dne.populationregisterymodel.Model;

public class MaritalStatus {

    private Person partner;
    private Status relationshipStatus;
    private String startDate;
    private String endDate;
    private Person thisPerson;

    public enum Status {SINGLE, COHABITATION, MARRIED, DIVORCED, WIDOWED}

    public MaritalStatus(Status status, Person thisPerson) {
        setStatusOrPartner(status, thisPerson, null, 0);
    }

    public MaritalStatus(Status status, Person thisPerson, Person partner, int flag) {
        setStatusOrPartner(status, thisPerson, partner, flag);
    }

    // ===================================
    //            main functions
    // ===================================
    public void setStatusOrPartner(Status status, Person thisPerson, Person partner, int flag) {
        setThisPerson(thisPerson);
        setRelationshipStatus(status);
        if (partner != null) {
            switch (flag) {
                case 1:
                    // TODO (Refine) change logic for adding partner
                    //  addLifePartner();
                    break;
                case -1:
                    //TODO (Refine) change logic for removing partner
                    //  removeLifePartner();
                    break;
                default:
                    break;
            }
        }
    }
/*
    public void addLifePartner(Status status, Person thisPerson, Person partner) {
        this.setPartner(partner);
        partner.getFamilyRelation().addNewMaritalStatus(status, thisPerson);
        // TODO (Refine) change previous relation endDate
    }

    public void removeLifePartner(Status status, Person thisPerson, Person partner) {
        switch (status) {
            case DIVORCED:
            case WIDOWED:
                //TODO (Refine) change previous relation endDate
                //thisPerson.getFamilyRelation().getLastMaritalStatusData().setEndDate(date);
                //partner.getFamilyRelation().addNewMaritalStatus(status, thisPerson);
                break;
        }
    }
*/
    // ===================================
    //   get/set functions for variables
    // ===================================
    public Person getThisPerson() {
        return this.thisPerson;
    }

    public Person getPartner() {
        return this.partner;
    }

    public Status getRelationshipStatus() {
        return this.relationshipStatus;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setThisPerson(Person thisPerson) {
        this.thisPerson = thisPerson;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public void setRelationshipStatus(Status relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
