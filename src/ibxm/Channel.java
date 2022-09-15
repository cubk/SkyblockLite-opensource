/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  Code_By_SuChen.SongManager
 */
package ibxm;

import Code_By_SuChen.SongManager;
import ibxm.Instrument;
import ibxm.LogTable;
import ibxm.Sample;

public class Channel {
    public int pattern_loop_row;
    private SongManager module;
    private Instrument instrument;
    private Sample sample;
    private int[] global_volume;
    private int[] current_note;
    private boolean linear_periods;
    private boolean fast_volume_slides;
    private boolean key_on;
    private boolean silent;
    private int sample_idx;
    private int sample_frac;
    private int step;
    private int left_gain;
    private int right_gain;
    private int volume;
    private int panning;
    private int fine_tune;
    private int period;
    private int porta_period;
    private int key_add;
    private int tremolo_speed;
    private int tremolo_depth;
    private int tremolo_tick;
    private int tremolo_wave;
    private int tremolo_add;
    private int vibrato_speed;
    private int vibrato_depth;
    private int vibrato_tick;
    private int vibrato_wave;
    private int vibrato_add;
    private int volume_slide_param;
    private int portamento_param;
    private int retrig_param;
    private int volume_envelope_tick;
    private int panning_envelope_tick;
    private int effect_tick;
    private int trigger_tick;
    private int fade_out_volume;
    private int random_seed;
    private int log_2_sampling_rate;
    private int log_2_c2_rate;
    private static final int LOG_2_29024;
    private static final int LOG_2_1712;
    private static final int[] sine_table;

    public Channel(SongManager songManager, int n, int[] nArray) {
        this.module = songManager;
        this.global_volume = nArray;
        this.linear_periods = this.module.linear_periods;
        this.fast_volume_slides = this.module.fast_volume_slides;
        this.current_note = new int[5];
        this.log_2_sampling_rate = LogTable.log_2(n);
    }

    static {
        LOG_2_29024 = LogTable.log_2(29024);
        LOG_2_1712 = LogTable.log_2(1712);
        sine_table = new int[]{0, 24, 49, 74, 97, 120, 141, 161, 180, 197, 212, 224, 235, 244, 250, 253, 255, 253, 250, 244, 235, 224, 212, 197, 180, 161, 141, 120, 97, 74, 49, 24};
    }
}

