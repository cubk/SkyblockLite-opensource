/*
 * Decompiled with CFR 0.152.
 */
package ibxm;

public class Pattern {
    public int num_rows;
    private int data_offset;
    private int note_index;
    private byte[] pattern_data;

    public Pattern() {
        this.num_rows = 1;
        this.set_pattern_data(new byte[0]);
    }
}

