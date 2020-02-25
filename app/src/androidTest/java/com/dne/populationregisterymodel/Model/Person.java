package com.dne.populationregisterymodel.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertSame;

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

    private enum Events {BIRTH, DEATH}

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

    public void addCitizenship(String citizenship) {
        this.citizenshipInfo.add(citizenship);
    }

    public void removeCitizenship(String citizenship) {
        this.citizenshipInfo.remove(citizenship);
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


    // ===================================
    //                tests
    // ===================================
    public static class PersonTest {

        @Test
        public void addNewAddress() {
            SampleData sd = new SampleData();
            ArrayList<Address> adrList = new ArrayList<Address>();
            adrList.add(sd.adr1);
            sd.john.setAddressInfo(adrList);
            sd.john.addNewAddress(sd.adr2);
            assertSame("Austin",
                    sd.john.getAddressInfo().get(sd.john.getAddressInfo().size() - 1).getCity());
        }

        @Test
        public void addNewFamilyRelation_child1() {
            SampleData sd = new SampleData();
            sd.john.addNewFamilyRelation(Relations.CHILD, sd.johnny);
            assertSame(sd.johnny, sd.john.getFamilyRelation().getChildren().get(0));
            assertSame(sd.john, sd.johnny.getFamilyRelation().getBiologicalFather());
        }

        @Test
        public void addNewFamilyRelation_child2() {
            SampleData sd = new SampleData();
            sd.john.addNewFamilyRelation(Relations.CHILD, sd.johnny);
            sd.john.addNewFamilyRelation(Relations.CHILD, sd.janie);
            assertSame(sd.johnny, sd.john.getFamilyRelation().getChildren().get(0));
            assertSame(sd.janie, sd.john.getFamilyRelation().getChildren().get(1));
        }

        @Test
        public void addNewFamilyRelation_mother() {
            SampleData sd = new SampleData();
            sd.johnny.addNewFamilyRelation(Relations.MOTHER, sd.jane);
            assertSame(sd.jane, sd.johnny.getFamilyRelation().getBiologicalMother());
            assertSame(sd.johnny, sd.jane.getFamilyRelation().getChildren().get(0));
        }

        @Test
        public void addNewFamilyRelation_father() {
            SampleData sd = new SampleData();
            sd.janie.addNewFamilyRelation(Relations.FATHER, sd.john);
            assertSame(sd.john, sd.janie.getFamilyRelation().getBiologicalFather());
            assertSame(sd.janie, sd.john.getFamilyRelation().getChildren().get(0));
        }

        @Test
        public void addNewFamilyRelation_guardian1() {
            SampleData sd = new SampleData();
            sd.janie.addNewFamilyRelation(Relations.GUARDIAN, sd.john);
            assertSame(sd.john, sd.janie.getFamilyRelation().getGuardians().get(0));
        }

        @Test
        public void addNewFamilyRelation_guardian2() {
            SampleData sd = new SampleData();
            sd.janie.addNewFamilyRelation(Relations.GUARDIAN, sd.john);
            sd.janie.addNewFamilyRelation(Relations.GUARDIAN, sd.jane);
            assertSame(sd.john, sd.janie.getFamilyRelation().getGuardians().get(0));
            assertSame(sd.jane, sd.janie.getFamilyRelation().getGuardians().get(1));
        }

        @Test
        public void addBirthDeathEvent_deathEvent_AVIOLIITTO() {
            SampleData sd = new SampleData();
            MaritalStatus marriedToJohn = new MaritalStatus(MaritalStatus.Status.MARRIED, sd.john);
            MaritalStatus marriedToJane = new MaritalStatus(MaritalStatus.Status.MARRIED, sd.jane);
            marriedToJohn.setPartner(sd.john);
            marriedToJane.setPartner(sd.jane);
            marriedToJohn.setStartDate("1980-01-01");
            marriedToJane.setStartDate("1980-01-01");
            ArrayList<MaritalStatus> maritalInfoForJane =
                    new ArrayList<>(Arrays.asList(marriedToJohn));
            ArrayList<MaritalStatus> maritalInfoForJohn =
                    new ArrayList<>(Arrays.asList(marriedToJane));
            sd.jane.getFamilyRelation().setMaritalStatusInfo(maritalInfoForJane);
            sd.john.getFamilyRelation().setMaritalStatusInfo(maritalInfoForJohn);
            sd.john.addBirthDeathEvent(Events.DEATH, "2020-01-01", "Bermuda");
            assertSame(MaritalStatus.Status.WIDOWED, sd.jane.getFamilyRelation()
                    .getLastMaritalStatusData().getRelationshipStatus());
            assertSame(null, sd.jane.getFamilyRelation().getMaritalStatusInfo()
                    .get(sd.jane.getFamilyRelation().getMaritalStatusInfo().size() - 1)
                    .getPartner());
        }

        @Test
        public void addBirthDeathEvent_deathEvent_AVOLIITTO() {
            SampleData sd = new SampleData();
            MaritalStatus marriedToJohn =
                    new MaritalStatus(MaritalStatus.Status.COHABITATION, sd.john);
            MaritalStatus marriedToJane =
                    new MaritalStatus(MaritalStatus.Status.COHABITATION, sd.jane);
            marriedToJohn.setPartner(sd.john);
            marriedToJane.setPartner(sd.jane);
            marriedToJohn.setStartDate("1980-01-01");
            marriedToJane.setStartDate("1980-01-01");
            ArrayList<MaritalStatus> maritalInfoForJane =
                    new ArrayList<>(Arrays.asList(marriedToJohn));
            ArrayList<MaritalStatus> maritalInfoForJohn =
                    new ArrayList<>(Arrays.asList(marriedToJane));
            sd.jane.getFamilyRelation().setMaritalStatusInfo(maritalInfoForJane);
            sd.john.getFamilyRelation().setMaritalStatusInfo(maritalInfoForJohn);
            sd.john.addBirthDeathEvent(Events.DEATH, "2020-01-01", "Bermuda");
            assertSame(MaritalStatus.Status.WIDOWED, sd.jane.getFamilyRelation()
                    .getLastMaritalStatusData().getRelationshipStatus());
            assertSame(null, sd.jane.getFamilyRelation().getMaritalStatusInfo()
                    .get(sd.jane.getFamilyRelation().getMaritalStatusInfo().size() - 1)
                    .getPartner());
        }

        @Test
        public void getParentalRelationToChild_mother() {
            SampleData sd = new SampleData();
            assertSame(Relations.MOTHER, sd.jane.getParentalRelationToChild(sd.jane.getGender()));
        }

        @Test
        public void getParentalRelationToChild_father() {
            SampleData sd = new SampleData();
            assertSame(Relations.FATHER, sd.john.getParentalRelationToChild(sd.john.getGender()));
        }
    }
}
