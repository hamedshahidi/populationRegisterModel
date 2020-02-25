package com.dne.populationregisterymodel.Model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

public class BirthDeathInfoTest {

    @Test
    public void addBirthEvent(){
        SampleData sd = new SampleData();
        sd.john.getBirthDeathInfo().addBirthEvent("1962-01-01", "Turku");
        assertSame("1962-01-01", sd.john.getBirthDeathInfo().getDateOfBirth());
        assertSame("Turku", sd.john.getBirthDeathInfo().getPlaceOfBirth());
    }

    @Test
    public void addDeathEvent_single() {
        SampleData sd = new SampleData();
        sd.john.getBirthDeathInfo().addDeathEvent("2020-01-01", "Bermuda");
        assertSame("2020-01-01", sd.john.getBirthDeathInfo().getDateOfDeath());
        assertSame("Bermuda", sd.john.getBirthDeathInfo().getPlaceOfDeath());
    }

    @Test
    public void addDeathEvent_hasPartner_married() {
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
        sd.john.getBirthDeathInfo().addDeathEvent("2020-01-01", "Bermuda");
        assertSame("2020-01-01", sd.john.getBirthDeathInfo().getDateOfDeath());
        assertSame("Bermuda", sd.john.getBirthDeathInfo().getPlaceOfDeath());
        assertSame(MaritalStatus.Status.WIDOWED, sd.jane.getFamilyRelation()
                .getLastMaritalStatusData().getRelationshipStatus());
        assertSame(null, sd.jane.getFamilyRelation().getMaritalStatusInfo()
                .get(sd.jane.getFamilyRelation().getMaritalStatusInfo().size() - 1)
                .getPartner());
    }

    @Test
    public void addDeathEvent_hasPartner_cohabitation() {
        SampleData sd = new SampleData();
        MaritalStatus cohabitantToJohn =
                new MaritalStatus(MaritalStatus.Status.COHABITATION, sd.john);
        MaritalStatus cohabitantToJane =
                new MaritalStatus(MaritalStatus.Status.COHABITATION, sd.jane);
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
        sd.john.getBirthDeathInfo().addDeathEvent("2020-01-01", "Bermuda");
        assertSame("2020-01-01", sd.john.getBirthDeathInfo().getDateOfDeath());
        assertSame("Bermuda", sd.john.getBirthDeathInfo().getPlaceOfDeath());
        assertSame(MaritalStatus.Status.WIDOWED, sd.jane.getFamilyRelation()
                .getLastMaritalStatusData().getRelationshipStatus());
        assertSame(null, sd.jane.getFamilyRelation().getMaritalStatusInfo()
                .get(sd.jane.getFamilyRelation().getMaritalStatusInfo().size() - 1)
                .getPartner());
    }
}