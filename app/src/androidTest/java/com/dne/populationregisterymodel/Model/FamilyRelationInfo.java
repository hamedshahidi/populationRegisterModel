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
        this.thisPerson = thisPerson;
    }

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
