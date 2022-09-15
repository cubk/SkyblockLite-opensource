/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.events;

import com.darkmagician6.eventapi.events.Cancellable;
import com.darkmagician6.eventapi.events.Event;

public class LivingEvent
implements Event,
Cancellable {
    public double y;
    public float yaw;
    public float pitch;
    public boolean onGround;
    public boolean cancel;
    public static float YAW;
    public static float PITCH;
    public static float PREVYAW;
    public static float PREVPITCH;
    public static boolean SNEAKING;

    public LivingEvent(double d, float f, float f2, boolean bl) {
        this.y = d;
        this.yaw = f;
        this.pitch = f2;
        this.onGround = bl;
    }
}

