package com.dne.populationregisterymodel.Model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FamilyRelationInfoTest {

    @Test
    public void setAsParentOfChild() {
        SampleData sd = new SampleData();
        sd.john.getFamilyRelation().setAsParentOfChild(sd.johnny, Person.Relations.FATHER, sd.john);
        assertSame(sd.john, sd.johnny.getFamilyRelation().getBiologicalFather());
    }

    @Test
    public void addNewMaritalStatus_single() {
        SampleData sd = new SampleData();
        sd.john.getFamilyRelation().addNewMaritalStatus(MaritalStatus.Status.SINGLE, sd.john);
        assertSame(MaritalStatus.Status.SINGLE, sd.john.getFamilyRelation()
                .getLastMaritalStatusData().getRelationshipStatus());
    }

    @Test
    public void addNewMaritalStatus_singleToMarried() {
        SampleData sd = new SampleData();
        sd.jane.getFamilyRelation().addNewMaritalStatus(MaritalStatus.Status.MARRIED, sd.john);
        assertSame(MaritalStatus.Status.MARRIED,
                sd.jane.getFamilyRelation().getLastMaritalStatusData().getRelationshipStatus());
        assertSame(MaritalStatus.Status.MARRIED,
                sd.john.getFamilyRelation().getLastMaritalStatusData().getRelationshipStatus());
    }

    @Test
    public void addNewMaritalStatus_singleToCohabitation() {
        SampleData sd = new SampleData();
        sd.jane.getFamilyRelation().addNewMaritalStatus(MaritalStatus.Status.COHABITATION, sd.john);
        assertSame(MaritalStatus.Status.COHABITATION,
                sd.jane.getFamilyRelation().getLastMaritalStatusData().getRelationshipStatus());
        assertSame(MaritalStatus.Status.COHABITATION,
                sd.john.getFamilyRelation().getLastMaritalStatusData().getRelationshipStatus());
    }

    @Test
    public void addNewMaritalStatus_cohabitationToMarried() {
        SampleData sd = new SampleData();
        sd.jane.getFamilyRelation().addNewMaritalStatus(MaritalStatus.Status.COHABITATION, sd.john);
        sd.jane.getFamilyRelation().addNewMaritalStatus(MaritalStatus.Status.MARRIED,sd.john);
        assertSame(MaritalStatus.Status.MARRIED,
                sd.jane.getFamilyRelation().getLastMaritalStatusData().getRelationshipStatus());
        assertSame(MaritalStatus.Status.MARRIED,
                sd.john.getFamilyRelation().getLastMaritalStatusData().getRelationshipStatus());
    }

    @Test
    public void testPersonAttributes() {
        Person p1 = new Person(Person.Gender.MALE);
    }


    @Test
    public void addGuardian1() {
        SampleData sd = new SampleData();
        sd.janie.getFamilyRelation().addGuardian(sd.john);
        assertSame(sd.john, sd.janie.getFamilyRelation().getGuardians().get(0));
    }

    @Test
    public void addGuardian2() {
        SampleData sd = new SampleData();
        sd.janie.getFamilyRelation().addGuardian(sd.john);
        sd.janie.getFamilyRelation().addGuardian(sd.jane);
        assertSame(sd.john, sd.janie.getFamilyRelation().getGuardians().get(0));
        assertSame(sd.jane, sd.janie.getFamilyRelation().getGuardians().get(1));
    }

    @Test
    public void removeGuardian() {
        SampleData sd = new SampleData();
        sd.janie.getFamilyRelation().setGuardians(
                new ArrayList<Person>(Arrays.asList(sd.john, sd.jane)));
        sd.janie.getFamilyRelation().removeGuardian(sd.john, sd.janie);
        assertSame(sd.jane, sd.janie.getFamilyRelation().getGuardians().get(0));
    }
}