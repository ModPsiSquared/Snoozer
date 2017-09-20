package com.cox.app;


public class TimeLineSolver extends WAndP {

    public TimeLineSolver (int w, float p){
        setW(w);
        setP(p);
    }

    public TimeLine solve () {
        TimeLineFactory tlf = new TimeLineFactory( w, p);
        TimeLine tl = null;
        int index = 0;
        do {
            tl = tlf.getTimeLine(index++);
            if (tl != null) {
                if (tl.getProbablityOfNotWaking() < 0.01f) {
                    break;
                }
            }
        } while (tl != null);
        return tl;
    }

}
