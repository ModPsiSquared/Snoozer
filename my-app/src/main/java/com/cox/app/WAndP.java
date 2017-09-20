package com.cox.app;


public abstract class WAndP {
    protected int w = -1;
    protected float p = -1;

    public void setW(int w) {
        if ((1 > w) || (w > 60)) {
            throw new TimeLineIllegalParameter("W must be 1 <= w <= 60.");
        }
        this.w = w;
    }

    public int getW() {
        return w;
    }

    public void setP(float p) {
        if ((0 > p) || (p > 1)) {
            throw new TimeLineIllegalParameter("P must be 0 <= p <= 1");
        }
        this.p = p;
    }

    public double getP() {
        return p;
    }
}
