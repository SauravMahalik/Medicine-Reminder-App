package com.example.devan.medicine_reminder;


import com.example.devan.medicine_reminder.datalayer.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataLayerTest
{
    @Test
    public void testBusinessLayerOutput(){
        assertEquals(new example().DemoBusinessLayerFunction(),"This is a demo data layer function");
    }
}
