package com.cox.app;

import java.util.Iterator;

public class TimeLineFactory extends WAndP implements Iterable<TimeLine>  {
    TimeLineFactory(int w, float p) {
        setW(w);
        setP(p);
    }

    public TimeLine getTimeLine(int pos) {
        TimeLine tl = new TimeLine(w, p);
        Boolean doFirst = true;
        for (int i = 0; i <= pos; i++) {
            if (!tl.canTakeNewAlarm()) {
                return null;
            }
            if (i == 0) {
                tl.addFirstAlarm();
            } else if (i < w) {
                tl.addOrMoveSecondAlarm();
            } else if (doFirst) {
                tl.addLastOpenAlarmAndPullBackStartOne();
                doFirst = false;
            } else {
                tl.pushBackOutFrontAlarm();
                doFirst = true;
            }
        }
        return tl;
    }

    @Override
    public Iterator<TimeLine> iterator() {
        Iterator<TimeLine> it = new Iterator<TimeLine>() {

            private int currentIndex = 0;

            private TimeLine referenceTimeLine = new TimeLine(w, p);

            @Override
            public boolean hasNext() {

                TimeLine tl = getTimeLine(currentIndex);
                return tl != null;
            }

            @Override
            public TimeLine next() {
                return getTimeLine(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;

    }
}