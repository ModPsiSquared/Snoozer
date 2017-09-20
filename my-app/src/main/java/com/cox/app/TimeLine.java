package com.cox.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeLine extends WAndP {

    public TimeLine(int w, float p) {
        setW(w);
        setP(p);
    }

    List<Alarm> alarms = new ArrayList<Alarm>();

    void addAlarm(Alarm alarm) {
        if (alarms.contains(alarm)) {
            throw new TimeLineIllegalParameter("Alarm already part of this Timeline.");
        } else if (alarm.getLocation() > (this.getW() - 1)) {
            throw new TimeLineIllegalParameter("Cannot add an alarm >= w.");
        }
        alarms.add(alarm);
        Collections.sort(alarms,
                (o1, o2) -> (o1.getLocation() > o2.getLocation() ? 1 : -1));
    }

    List<Alarm> getAlarms() {
        return Collections.unmodifiableList(this.alarms);
    }

    public boolean canTakeNewAlarm() {
        return alarms.size() < getW();
    }

    public void addLastOpenAlarmAndPullBackStartOne() {
        alarms.remove(0);
        this.addAlarm(new Alarm(1));

        for (int i = w - 1; i >= 0; i--) {
            Alarm potentialNewAlarm = new Alarm(i);
            if (!alarms.contains(potentialNewAlarm)) {
                this.addAlarm(potentialNewAlarm);
                break;
            }
        }
    }

    public void pushBackOutFrontAlarm() {
        alarms.remove(0);
        this.addAlarm(new Alarm(0));
    }

    public void addFirstAlarm() {
        if (alarms.size() != 0) {
            throw new TimeLineIllegalParameter("Must be no alarms in this call");
        }
        this.addAlarm(new Alarm(w - 1));
    }

    public void addOrMoveSecondAlarm() {
        if (!((alarms.size() == 1) || (alarms.size() == 2))) {
            throw new TimeLineIllegalParameter("Must be only one alarm in this call");
        }
        int location = alarms.get(0).getLocation();
        if (location <= 0) {
            throw new TimeLineIllegalParameter("Cannot move location less than zero by addSecondAlarm");
        }
        if (alarms.size() == 2) {
            alarms.remove(0);
        }
        this.addAlarm(new Alarm(--location));
    }

    public float getProbablityOfNotWaking() {
        int lastPos = 0;
        int exponent = 0;

        for (int i = 0; i < alarms.size(); i++) {
            Alarm current = alarms.get(i);
            if (i == 0) {
                exponent++;
            } else {
                exponent += (current.getLocation() - lastPos + 2);
            }
            lastPos = current.getLocation();
        }
        return (float) Math.pow(p, exponent);
    }

    public String formatString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Boolean firstThrough = true;
        for (Alarm a : alarms){
            if (!firstThrough){
                sb.append(",");
            }
            sb.append(a.formatString());
            firstThrough = false;
        }
        sb.append("}");
        return sb.toString();
    }
}

