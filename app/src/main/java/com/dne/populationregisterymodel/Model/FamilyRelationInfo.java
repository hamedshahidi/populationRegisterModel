package com.dne.populationregisterymodel.Model;

import android.util.Log;

import java.util.ArrayList;

public class FamilyRelationInfo {

    private Person biologicalMother;
    private Person biologicalFather;
    private ArrayList<Person> children = new ArrayList<Person>();
    private ArrayList<Person> guardians = new ArrayList<Person>();
    private ArrayList<MaritalStatus> maritalStatusInfo = new ArrayList<MaritalStatus>();
    private Person thisPerson;

    public FamilyRelationInfo(Person thisPerson) {
        setThisPerson(thisPerson);
    }

    // ===================================
    //            main functions
    // ===================================
    public void setAsParentOfChild(Person child, Person.Relations parentalRelation, Person parent) {

        switch (parentalRelation) {
            case MOTHER:
                child.getFamilyRelation().setBiologicalMother(this.thisPerson);
                break;
            case FATHER:
                child.getFamilyRelation().setBiologicalFather(this.thisPerson);
                break;
            default:
                break;
        }
    }

    public void addNewMaritalStatus(MaritalStatus.Status status, Person partnerOrSelf) {
        MaritalStatus newMaritalStatus = null;
        MaritalStatus personLastStatus =
                getThisPerson().getFamilyRelation().getLastMaritalStatusData();
        MaritalStatus partnerLastStatus =
                partnerOrSelf.getFamilyRelation().getLastMaritalStatusData();
        switch (status) {
            case SINGLE:
                newMaritalStatus = new MaritalStatus(status, partnerOrSelf);
                break;
            case COHABITATION:
            case MARRIED:
                newMaritalStatus = new MaritalStatus(
                        status, getThisPerson(), partnerOrSelf, 1);
                break;
            case DIVORCED:
            case WIDOWED:
                newMaritalStatus = new MaritalStatus(
                        status, getThisPerson(), partnerOrSelf, -1);
                break;
        }

        //TODO (toFix) bug in changing cohabitation/married partner status to single.
        // Instead updates person status x2.
        getThisPerson().getFamilyRelation().getMaritalStatusInfo().add(newMaritalStatus);


        //Check and update marital status for partner if needed
        switch (status) {
            case MARRIED:
            case COHABITATION:
                partnerLastStatus = partnerOrSelf.getFamilyRelation().getLastMaritalStatusData();
                if (partnerLastStatus.getPartner() != null
                        && partnerLastStatus.getPartner() == getThisPerson()
                        && partnerLastStatus.getRelationshipStatus() == status) {
                    // status already registered for partner. Do nothing.
                    break;
                } else {
                    partnerOrSelf.getFamilyRelation()
                            .addNewMaritalStatus(status, getThisPerson());
                }
                break;
            case SINGLE:
                if (personLastStatus.getRelationshipStatus() == MaritalStatus.Status.COHABITATION) {
                    Person partner = personLastStatus.getPartner();
                    partnerOrSelf.getFamilyRelation()
                            .addNewMaritalStatus(MaritalStatus.Status.SINGLE, partner);
                }
            default:
                break;
        }
    }

    public void addGuardian(Person guardian) {
        getGuardians().add(guardian);
    }

    // TODO store godChildren for guardian. Remove guardian from child in case of guardian's death.
    public void removeGuardian(Person guardian, Person child) {
        child.getFamilyRelation().getGuardians().remove(guardian);
    }

    public MaritalStatus getLastMaritalStatusData() {
        return getMaritalStatusInfo().get(getMaritalStatusInfo().size() - 1);
    }

    public boolean isMarried(Person person) {
        MaritalStatus status = person.getFamilyRelation().getLastMaritalStatusData();
        return (status.getRelationshipStatus() == MaritalStatus.Status.MARRIED);
    }

    public boolean isCohabitant(Person person) {
        MaritalStatus status = person.getFamilyRelation().getLastMaritalStatusData();
        return (status.getRelationshipStatus() == MaritalStatus.Status.COHABITATION);
    }

    public void chengesToMarried(Person person, MaritalStatus.Status newStatus) {
        MaritalStatus status = person.getFamilyRelation().getLastMaritalStatusData();
    }

    public void changesToCohabitant(Person person) {
        MaritalStatus status = person.getFamilyRelation().getLastMaritalStatusData();
    }


    // ===================================
    //   get/set functions for variables
    // ===================================
    public Person getBiologicalMother() {
        return this.biologicalMother;
    }

    public Person getBiologicalFather() {
        return this.biologicalFather;
    }

    public ArrayList<Person> getChildren() {
        return this.children;
    }

    public ArrayList<Person> getGuardians() {
        return this.guardians;
    }

    public ArrayList<MaritalStatus> getMaritalStatusInfo() {
        return this.maritalStatusInfo;
    }

    public Person getThisPerson() {
        return thisPerson;
    }

    public void setBiologicalMother(Person biologicalMother) {
        this.biologicalMother = biologicalMother;
    }

    public void setBiologicalFather(Person biologicalFather) {
        this.biologicalFather = biologicalFather;
    }

    public void setGuardians(ArrayList<Person> guardians) {
        this.guardians = guardians;
    }

    public void setChildren(ArrayList<Person> children) {
        this.children = children;
    }

    public void setMaritalStatusInfo(ArrayList<MaritalStatus> maritalStatusInfo) {
        this.maritalStatusInfo = maritalStatusInfo;
    }

    public void setThisPerson(Person thisPerson) {
        this.thisPerson = thisPerson;
    }
}
