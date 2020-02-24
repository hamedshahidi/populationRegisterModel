package com.dne.populationregisterymodel.Model;

import java.util.Date;

public class MaritalStatus {

    private Person partner;
    private String relationshipStatus;
    private Date startDate;
    private Date endDate;

    public Person getPartner() {
        return this.partner;
    }

    public String getRelationshipStatus() {
        return this.relationshipStatus;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setPartner(Person partner) {
        this.partner = partner;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
