/*
 * Decompiled with CFR 0.152.
 */
package ibxm;

public class Envelope {
    public boolean sustain;
    public boolean looped;
    private int sustain_tick;
    private int loop_start_tick;
    private int loop_end_tick;
    private int[] ticks;
    private int[] ampls;

    public Envelope() {
        this.set_num_points(1);
    }
}

