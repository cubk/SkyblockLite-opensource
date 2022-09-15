/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  Code_By_SuChen.SongManager
 */
package ibxm;

import Code_By_SuChen.SongManager;
import ibxm.Channel;

public class IBXM {
    public static final String VERSION = "ibxm alpha 45 (c)2006 mumart@gmail.com";
    public static final int FP_SHIFT = 15;
    public static final int FP_ONE = 32768;
    public static final int FP_MASK = Short.MAX_VALUE;
    private int sampling_rate;
    private int resampling_quality;
    private int volume_ramp_length;
    private int tick_length_samples;
    private int current_tick_samples;
    private int[] mixing_buffer;
    private int[] volume_ramp_buffer;
    private SongManager module;
    private Channel[] channels;
    private int[] global_volume;
    private int[] note;
    private int current_sequence_index;
    private int next_sequence_index;
    private int current_row;
    private int next_row;
    private int tick_counter;
    private int ticks_per_row;
    private int pattern_loop_count;
    private int pattern_loop_channel;

    public IBXM(int n) {
        System.out.println(VERSION);
        if (n < 8000) {
            n = 8000;
        }
        this.sampling_rate = n;
        this.volume_ramp_length = this.sampling_rate >> 10;
        this.volume_ramp_buffer = new int[this.volume_ramp_length * 2];
        this.mixing_buffer = new int[this.sampling_rate / 6];
        this.global_volume = new int[1];
        this.note = new int[5];
        this.set_module(new SongManager());
        this.set_resampling_quality(1);
    }
}

