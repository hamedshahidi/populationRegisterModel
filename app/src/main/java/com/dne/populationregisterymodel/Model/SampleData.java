package com.dne.populationregisterymodel.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class SampleData {

    Address adr1 = null;
    Address adr2 = null;
    Address adr3 = null;
    Address adr4 = null;
    Person john = null;
    Person jane = null;
    Person johnny = null;
    Person janie = null;

    public SampleData() {
        this.adr1 = new Address();
        adr1.setCountry("USA");
        adr1.setState("Washington");
        adr1.setCity("Seattle");
        adr1.setStreet("Broadway 1");
        adr1.setPostalCode("00A1");
        adr1.setStartDate("1961-01-01");

        this.adr2 = new Address();
        adr2.setCountry("USA");
        adr2.setState("Texas");
        adr2.setCity("Austin");
        adr2.setStreet("Freeway 2");
        adr2.setPostalCode("00B2");
        adr2.setStartDate("1972-02-02");

        this.adr3 = new Address();
        adr3.setCountry("Finland");
        adr3.setState("Uusimaa");
        adr3.setCity("Helsinki");
        adr3.setStreet("Eerikinkatu 26");
        adr3.setPostalCode("00100");
        adr3.setStartDate("1983-03-03");

        this.adr4 = new Address();
        adr4.setCountry("Finland");
        adr4.setState("Uusimaa");
        adr4.setCity("Helsinki");
        adr4.setStreet("Aleksis Kiven katu 48");
        adr4.setPostalCode("00510");
        adr4.setStartDate("1994-04-04");

        this.john = new Person(Person.Gender.MALE);
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setSSN("010162-A123");
        john.setMotherTongue("English");
        john.setCitizenshipInfo(new ArrayList<String>(Arrays.asList("USA")));
        john.setBirthDeathInfo(new BirthDeathInfo(john));
        john.getBirthDeathInfo().setPlaceOfBirth("Seattle");

        this.jane = new Person(Person.Gender.FEMALE);
        jane.setFirstName("Jane");
        jane.setLastName("Doe");
        jane.setSSN("020272-B456");
        jane.setMotherTongue("English");
        jane.setCitizenshipInfo(new ArrayList<String>(Arrays.asList("USA")));
        jane.setBirthDeathInfo(new BirthDeathInfo(jane));

        this.johnny = new Person(Person.Gender.MALE);
        johnny.setFirstName("Johnny");
        johnny.setLastName("Doe");
        johnny.setSSN("030383-C789");
        johnny.setMotherTongue("English");
        johnny.setCitizenshipInfo(new ArrayList<String>(Arrays.asList("USA", "Finland")));
        johnny.setBirthDeathInfo(new BirthDeathInfo(johnny));

        this.janie = new Person(Person.Gender.MALE);
        janie.setFirstName("Janie");
        janie.setLastName("Doe");
        janie.setSSN("040494-D012");
        janie.setMotherTongue("English");
        janie.setCitizenshipInfo(new ArrayList<String>(Arrays.asList("USA", "Finland")));
        janie.setBirthDeathInfo(new BirthDeathInfo(janie));
    }
}
