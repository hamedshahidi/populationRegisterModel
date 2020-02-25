package com.dne.populationregisterymodel.Model;

import java.util.ArrayList;

public class Person {

    private String firstName;
    private String lastName;
    private String ssn;
    private String motherTongue;
    private Gender gender;
    private ArrayList<String> citizenshipInfo = new ArrayList<String>();
    private ArrayList<Address> addressInfo = new ArrayList<Address>();
    private FamilyRelationInfo familyRelation = new FamilyRelationInfo(this);
    private BirthDeathInfo birthDeathInfo = new BirthDeathInfo(this);

    public enum Relations {MOTHER, FATHER, GUARDIAN, CHILD}

    public enum Events {BIRTH, DEATH}

    public enum Gender {MALE, FEMALE}

    public Person(Gender gender) {
        this.gender = gender;
    }

    // ===================================
    //            main functions
    // ===================================
    public void addNewAddress(Address address) {
        // TODO(Refine) use Calendar & Date.
        //  Set previous address endDate to new address startDate-1
        ArrayList<Address> adrInfo = getAddressInfo();
        Address lastAdr = getAddressInfo().get(getAddressInfo().size() - 1);
        adrInfo.remove(lastAdr);
        lastAdr.setEndDate(address.getStartDate());
        adrInfo.add(lastAdr);
        adrInfo.add(address);
        setAddressInfo(adrInfo);
    }

    public void addNewFamilyRelation(Relations relation, Person person) {
        switch (relation) {
            case MOTHER:
                getFamilyRelation().setBiologicalMother(person);
                person.addNewFamilyRelation(Relations.CHILD, this);
                break;
            case FATHER:
                getFamilyRelation().setBiologicalFather(person);
                person.addNewFamilyRelation(Relations.CHILD, this);
                break;
            case GUARDIAN:
                getFamilyRelation().addGuardian(person);
                break;
            case CHILD:
                getFamilyRelation().getChildren().add(person);
                getFamilyRelation()
                        .addParent(person, getParentalRelationToChild(getGender()), this);
                break;
        }
    }

    public void addBirthDeathEvent(Events event, String date, String place) {
        switch (event) {
            case BIRTH:
                // this case might not be necessary
                this.getBirthDeathInfo().addBirthEvent(date, place);
                break;
            case DEATH:
                this.getBirthDeathInfo().addDeathEvent(date, place);
                break;
        }
    }

    public Relations getParentalRelationToChild(Gender gender) {
        Relations parentalRelation = null;
        switch (gender) {
            case FEMALE:
                parentalRelation = Relations.MOTHER;
                break;
            case MALE:
                parentalRelation = Relations.FATHER;
                break;
            default:
                break;
        }
        return parentalRelation;
    }

    public void addCitizenship(String citizenship) {
        this.citizenshipInfo.add(citizenship);
    }

    public void removeCitizenship(String citizenship) {

        this.citizenshipInfo.remove(citizenship);
    }

    // ===================================
    //   get/set functions for variables
    // ===================================
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public String getSSN() {
        return this.ssn;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public String getMotherTongue() {
        return this.motherTongue;
    }

    public void setMotherTongue(String mTongue) {
        this.motherTongue = mTongue;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ArrayList<String> getCitizenshipInfo() {
        return this.citizenshipInfo;
    }

    public void setCitizenshipInfo(ArrayList<String> citizenshipInfo) {
        this.citizenshipInfo = citizenshipInfo;
    }

    public ArrayList<Address> getAddressInfo() {
        return this.addressInfo;
    }

    public void setAddressInfo(ArrayList<Address> addressInfo) {
        this.addressInfo = addressInfo;
    }

    public FamilyRelationInfo getFamilyRelation() {
        return this.familyRelation;
    }

    public void setFamilyRelation(FamilyRelationInfo familyRelation) {
        this.familyRelation = familyRelation;
    }

    public BirthDeathInfo getBirthDeathInfo() {
        return this.birthDeathInfo;
    }

    public void setBirthDeathInfo(BirthDeathInfo birthDeathInfo) {
        this.birthDeathInfo = birthDeathInfo;
    }
}
