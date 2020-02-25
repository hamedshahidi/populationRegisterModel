package com.dne.populationregisterymodel.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PersonTest {

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
        sd.john.addNewFamilyRelation(Person.Relations.CHILD, sd.johnny);
        assertSame(sd.johnny, sd.john.getFamilyRelation().getChildren().get(0));
        assertSame(sd.john, sd.johnny.getFamilyRelation().getBiologicalFather());
    }

    @Test
    public void addNewFamilyRelation_child2() {
        SampleData sd = new SampleData();
        sd.john.addNewFamilyRelation(Person.Relations.CHILD, sd.johnny);
        sd.john.addNewFamilyRelation(Person.Relations.CHILD, sd.janie);
        assertSame(sd.johnny, sd.john.getFamilyRelation().getChildren().get(0));
        assertSame(sd.janie, sd.john.getFamilyRelation().getChildren().get(1));
    }

    @Test
    public void addNewFamilyRelation_mother() {
        SampleData sd = new SampleData();
        sd.johnny.addNewFamilyRelation(Person.Relations.MOTHER, sd.jane);
        assertSame(sd.jane, sd.johnny.getFamilyRelation().getBiologicalMother());
        assertSame(sd.johnny, sd.jane.getFamilyRelation().getChildren().get(0));
    }

    @Test
    public void addNewFamilyRelation_father() {
        SampleData sd = new SampleData();
        sd.janie.addNewFamilyRelation(Person.Relations.FATHER, sd.john);
        assertSame(sd.john, sd.janie.getFamilyRelation().getBiologicalFather());
        assertSame(sd.janie, sd.john.getFamilyRelation().getChildren().get(0));
    }

    @Test
    public void addNewFamilyRelation_guardian1() {
        SampleData sd = new SampleData();
        sd.janie.addNewFamilyRelation(Person.Relations.GUARDIAN, sd.john);
        assertSame(sd.john, sd.janie.getFamilyRelation().getGuardians().get(0));
    }

    @Test
    public void addNewFamilyRelation_guardian2() {
        SampleData sd = new SampleData();
        sd.janie.addNewFamilyRelation(Person.Relations.GUARDIAN, sd.john);
        sd.janie.addNewFamilyRelation(Person.Relations.GUARDIAN, sd.jane);
        assertSame(sd.john, sd.janie.getFamilyRelation().getGuardians().get(0));
        assertSame(sd.jane, sd.janie.getFamilyRelation().getGuardians().get(1));
    }

    @Test
    public void addBirthDeathEvent_deathEvent_hasPartner_married() {
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
        sd.john.addBirthDeathEvent(Person.Events.DEATH, "2020-01-01", "Bermuda");
        assertSame(MaritalStatus.Status.WIDOWED, sd.jane.getFamilyRelation()
                .getLastMaritalStatusData().getRelationshipStatus());
        assertSame(null, sd.jane.getFamilyRelation().getMaritalStatusInfo()
                .get(sd.jane.getFamilyRelation().getMaritalStatusInfo().size() - 1)
                .getPartner());
    }

    @Test
    public void addBirthDeathEvent_deathEvent_hasPartner_cohabitation() {
        SampleData sd = new SampleData();
        MaritalStatus cohabitantToJohn = new MaritalStatus(MaritalStatus.Status.COHABITATION, sd.john);
        MaritalStatus cohabitantToJane = new MaritalStatus(MaritalStatus.Status.COHABITATION, sd.jane);
        cohabitantToJohn.setPartner(sd.john);
        cohabitantToJane.setPartner(sd.jane);
        cohabitantToJohn.setStartDate("1980-01-01");
        cohabitantToJane.setStartDate("1980-01-01");
        ArrayList<MaritalStatus> maritalInfoForJane =
                new ArrayList<>(Arrays.asList(cohabitantToJohn));
        ArrayList<MaritalStatus> maritalInfoForJohn =
                new ArrayList<>(Arrays.asList(cohabitantToJane));
        sd.jane.getFamilyRelation().setMaritalStatusInfo(maritalInfoForJane);
        sd.john.getFamilyRelation().setMaritalStatusInfo(maritalInfoForJohn);
        sd.john.addBirthDeathEvent(Person.Events.DEATH, "2020-01-01", "Bermuda");
        assertSame(MaritalStatus.Status.WIDOWED, sd.jane.getFamilyRelation()
                .getLastMaritalStatusData().getRelationshipStatus());
        assertSame(null, sd.jane.getFamilyRelation().getMaritalStatusInfo()
                .get(sd.jane.getFamilyRelation().getMaritalStatusInfo().size() - 1)
                .getPartner());
    }

    @Test
    public void getParentalRelationToChild_mother() {
        SampleData sd = new SampleData();
        assertSame(Person.Relations.MOTHER, sd.jane.getParentalRelationToChild(sd.jane.getGender()));
    }

    @Test
    public void getParentalRelationToChild_father() {
        SampleData sd = new SampleData();
        assertSame(Person.Relations.FATHER, sd.john.getParentalRelationToChild(sd.john.getGender()));
    }

}