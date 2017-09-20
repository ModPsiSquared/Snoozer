package com.cox.app;


public class Alarm {
    protected int location = -1;

    public Alarm(int t) {
        location = t;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getLocation() {
        return this.location;
    }

    public double calculate(Alarm previousAlarm, float w) {
        int relativelocation = this.location - previousAlarm.location;

        return Math.pow(w, relativelocation + 2);
    }

    @Override
    public boolean equals(Object o) {
        if ((o instanceof Alarm) && (((Alarm) o).getLocation() == this.getLocation())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getLocation() + 23;
    }

    public String formatString() {
        return String.valueOf(getLocation());
    }
}


