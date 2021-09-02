package com.example.devan.medicine_reminder;

import org.junit.Test;
import com.example.devan.medicine_reminder.businesslayer.*;
import static org.junit.Assert.assertEquals;

public class BusinessLayerTest {
    @Test
    public void testBusinessLayerOutput(){
        assertEquals(new example().DemoBusinessLayerFunction(),"This is a demo business layer function");
    }
}
