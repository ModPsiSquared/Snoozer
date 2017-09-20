package com.cox.app;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class TimeLineSolverTests
{
    @Test
    public void firstExample()
    {
        TimeLineSolver tls = new TimeLineSolver(30,0.8f);
        TimeLine tl = tls.solve();
        String actual = tl.formatString();
        String expected = "{11,29}";
        assertTrue(actual.equals( expected));
    }

    @Test
    public void secondExample()
    {
        TimeLineSolver tls = new TimeLineSolver(8,0.8f);
        TimeLine tl = tls.solve();
        String actual = tl.formatString();
        String expected = "{0,1,2,3,4,5,6,7}";
        assertTrue(actual.equals( expected));
    }
}
