/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.util.timers;

public final class MSTimer {
    public static float timerSpeed;
    private long last;
    private long time;
    private long prevMS;

    public MSTimer() {
        this.prevMS = 0L;
        this.time = System.nanoTime() / 1000000L;
    }
}

