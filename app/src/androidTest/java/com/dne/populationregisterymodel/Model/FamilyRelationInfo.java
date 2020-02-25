package com.dne.populationregisterymodel.Model;

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
    public void addParent(Person child, Person.Relations parentalRelation, Person parent) {

        //ArrayList<Person> children = getChildren();
        //children.add(child);
        //setChildren(children);


        switch (parentalRelation) {
            case MOTHER:
                //FamilyRelationInfo fri1 = new FamilyRelationInfo(child);
                //fri1.setBiologicalMother(this.thisPerson);
                child.getFamilyRelation().setBiologicalMother(this.thisPerson);
                break;
            case FATHER:
                //FamilyRelationInfo fri2 = new FamilyRelationInfo(child);
                //fri2.setBiologicalFather(this.thisPerson);
                child.getFamilyRelation().setBiologicalFather(this.thisPerson);
                break;
            default:
                break;
        }
    }

    public void addNewMaritalStatus(MaritalStatus.Status status, Person partnerOrSelf) {
        MaritalStatus newMaritalStatus = null;
        switch (status) {
            case SINGLE:
                newMaritalStatus = new MaritalStatus(status, getThisPerson());
            case COHABITATION:
            case MARRIED:
                newMaritalStatus = new MaritalStatus(status, getThisPerson(), partnerOrSelf, 1);
            case DIVORCED:
            case WIDOWED:
                newMaritalStatus = new MaritalStatus(status, getThisPerson(), partnerOrSelf, -1);
        }
        getMaritalStatusInfo().add(newMaritalStatus);
    }

    public void addGuardian(Person guardian) {
        getGuardians().add(guardian);
    }

    // TODO store godChildren for guardian. Remove guardian from child in case of guardian's death.
    public void removeGuardian(Person guardian) {
        getGuardians().remove(guardian);
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

    public MaritalStatus getLastMaritalStatusData() {
        return getMaritalStatusInfo().get(getMaritalStatusInfo().size() - 1);
    }
}
