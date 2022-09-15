/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.events;

import com.darkmagician6.eventapi.events.Event;

public class MotionEvent
implements Event {
    public double x;
    public double y;
    public double z;

    public MotionEvent(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.z = d3;
    }
}

