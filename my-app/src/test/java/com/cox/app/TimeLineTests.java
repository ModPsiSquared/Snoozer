package com.cox.app;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class TimeLineTests
{
    @Test
    public void orderAlarms() {
        TimeLine actual = new TimeLine(8, 0.8f);

        actual.addAlarm(new Alarm(3));
        actual.addAlarm(new Alarm(2));
        actual.addAlarm(new Alarm(7));
        actual.addAlarm(new Alarm(1));

        TimeLine tlExpected = new TimeLine(8, 0.8f);
        tlExpected.addAlarm(new Alarm(1));
        tlExpected.addAlarm(new Alarm(2));
        tlExpected.addAlarm(new Alarm(3));
        tlExpected.addAlarm(new Alarm(7));

        assertArrayEquals("failure out of order", actual.getAlarms().toArray(),
                tlExpected.getAlarms().toArray());
    }

    @Test
    public void errorOnDuplicateAlarm(){
        TimeLine actual = new TimeLine(23, 0.9f);
        actual.addAlarm(new Alarm(5));

        boolean exceptionFired = false;

        try {
            actual.addAlarm(new Alarm(5));
        }catch (TimeLineIllegalParameter e){
            exceptionFired = true;
        }
        assertTrue("There should be an error on attempting to insert duplicate alarm", exceptionFired);

    }

    @Test
    public void pOurOfRange(){
        boolean exceptionFired = false;
        try {
            TimeLine actual = new TimeLine(17, 1.5f);
        }catch (TimeLineIllegalParameter e){
            exceptionFired = true;
        }
        assertTrue("There should be an error on attempting to excede p > 1.0", exceptionFired);

        exceptionFired = false;
        try {
            TimeLine actual = new TimeLine(17, -0.1f);
        }catch (TimeLineIllegalParameter e){
            exceptionFired = true;
        }
        assertTrue("There should be an error on attempting to excede p < 0", exceptionFired);
    }

    @Test
    public void wOutOfRange(){
        boolean exceptionFired = false;
        try {
            TimeLine actual = new TimeLine(0, 0.7f);
        }catch (TimeLineIllegalParameter e){
            exceptionFired = true;
        }
        assertTrue("There should be an error on attempting to excede w < 1", exceptionFired);

        exceptionFired = false;
        try {
            TimeLine actual = new TimeLine(61, 0.7f);
        }catch (TimeLineIllegalParameter e){
            exceptionFired = true;
        }
        assertTrue("There should be an error on attempting to excede w > 60", exceptionFired);
    }

    @Test
    public void addAlarmPastWMinusOne(){
        boolean exceptionFired = false;
        int w = 35;
        int wMinusOne = w - 1;
        TimeLine actual = new TimeLine(29, 0.7f);

        try {
            actual.addAlarm(new Alarm(wMinusOne + 1));
        }catch (TimeLineIllegalParameter e){
            exceptionFired = true;
        }

        assertTrue("There should be an error on attempting to add an alarm at w", exceptionFired);
        exceptionFired = false;
        try {
            actual.addAlarm(new Alarm(wMinusOne + 2));
        }catch (TimeLineIllegalParameter e){
            exceptionFired = true;
        }
        assertTrue("There should be an error on attempting to add an alarm greater than w", exceptionFired);
    }
}
