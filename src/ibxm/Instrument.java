/*
 * Decompiled with CFR 0.152.
 */
package ibxm;

import ibxm.Envelope;
import ibxm.Sample;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public class Instrument {
    public String name;
    public int vibrato_type;
    public int vibrato_sweep;
    public int vibrato_depth;
    public int vibrato_rate;
    public boolean volume_envelope_active;
    public boolean panning_envelope_active;
    public int volume_fade_out;
    private Envelope volume_envelope;
    private Envelope panning_envelope;
    private int[] key_to_sample;
    private Sample[] samples;
    public static final boolean \u200b\u200e\u200a\u2002\u2006\u200a\u2006\u200c\u200f;

    public Instrument() {
        this.name = "";
        this.set_volume_envelope(new Envelope());
        this.set_panning_envelope(new Envelope());
        this.key_to_sample = new int[96];
        this.set_num_samples(1);
    }
}

