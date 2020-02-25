package com.dne.populationregisterymodel.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaritalStatusTest {

    @Test
    public void setStatusOrPartner() {
        SampleData sd = new SampleData();
        MaritalStatus ms = new MaritalStatus(MaritalStatus.Status.SINGLE, sd.john);
        ms.setStatusOrPartner(MaritalStatus.Status.MARRIED,sd.john,sd.jane,1);
        assertSame(sd.john, ms.getThisPerson());
        assertSame(MaritalStatus.Status.MARRIED, ms.getRelationshipStatus());
    }
}