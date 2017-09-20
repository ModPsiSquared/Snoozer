package com.cox.app;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TimeLineFactoryTests {
    @Test
    public void continuity() {
        float p = 0.8f;
        int w = 6;
        TimeLineFactory tlf = new TimeLineFactory(w, p);
        for (int i = 0; i < 13; i++) {
            TimeLine tl = tlf.getTimeLine(i);
            if (tl == null){
                assertEquals(i, 13);
                break;
            }

            float calculated = tl.getProbablityOfNotWaking();
            int power = i == 0 ? 1 : (3 + i) + (i == 12 ? 1 : 0);  //The otherwise continuous exponent jumps two at the last
            double expected = Math.pow(p, power);

            assertEquals(expected, calculated, 0.001);
        }
    }
}
