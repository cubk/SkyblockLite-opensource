/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.util.timers;

public class DelayTimer {
    static long prevMS;
    private long time;
    protected static long lastMS;
    private long lastMS1;

    public DelayTimer() {
        prevMS = 0L;
        lastMS = -1L;
        this.time = System.nanoTime() / 1000000L;
    }

    public static /* bridge */ boolean delay(float f) {
        return (float)(DelayTimer.getTime() - prevMS) >= f;
    }

    public static /* bridge */ boolean delay(double d) {
        return (double)(DelayTimer.getTime() - prevMS) >= d;
    }

    public static /* bridge */ long getTime() {
        return System.nanoTime() / 1000000L;
    }

    public static /* bridge */ long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public static /* bridge */ boolean hasReached(long l) {
        return DelayTimer.getCurrentMS() - lastMS >= l;
    }
}

