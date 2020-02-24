package com.dne.populationregisterymodel.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Person {

    private String firstName;
    private String lastName;
    private String ssn;
    private String motherTongue;
    private ArrayList<String> citizenshipInfo = new ArrayList<String>();
    private ArrayList<Address> addressInfo = new ArrayList<Address>();
    private FamilyRelationInfo familyRelation = new FamilyRelationInfo(this);
    private BirthDeathInfo birthDeathInfo = new BirthDeathInfo(this);

    private enum Relations {MOTHER, FATHER, GUARDIAN, CHILD}
    private enum Events {BIRTH, DEATH}


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

    public ArrayList<String> getCitizenshipInfo() {
        return this.citizenshipInfo;
    }

    public void addCitizenship(String citizenship) {
        this.citizenshipInfo.add(citizenship);
    }

    public void removeCitizenship(String citizenship) {
        this.citizenshipInfo.remove(citizenship);
    }

    public ArrayList<Address> getAddressInfo() {
        return this.addressInfo;
    }

    public void addNewAddress(Address address) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(address.getStartDate());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        addressInfo.get(addressInfo.size() - 1).setEndDate(calendar.getTime());
        this.addressInfo.add(address);
    }

    public FamilyRelationInfo getFamilyRelation() {
        return this.familyRelation;
    }

    public void setNewFamilyRelation(Relations relation, Person person) {
        switch (relation) {
            case MOTHER:

                // TODO getFamilyRelation().addMother(person);
                break;
            case FATHER:
                // TODO getFamilyRelation().addFather(person);
                break;
            case GUARDIAN:
                // TODO getFamilyRelation().addGuardian(person);
                break;
            case CHILD:
                // TODO getFamilyRelation().addChild(person);
                break;
        }
    }

    public BirthDeathInfo getBirthDeathInfo(){
        return this.birthDeathInfo;
    }

    public void setBirthDeathEvent(Events event, Date date, String place) {
        switch(event){
            case BIRTH:
                // TODO getBirthDeathInfo().addBirthEvent(date, place);
                break;
            case DEATH:
                // TODO getBirthDeathInfo().addDeathEvent(date, place);
        }
    }
}
