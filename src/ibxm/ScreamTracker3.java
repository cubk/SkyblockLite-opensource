/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  Code_By_SuChen.SongManager
 */
package ibxm;

import Code_By_SuChen.SongManager;
import ibxm.Instrument;
import ibxm.Pattern;
import ibxm.Sample;
import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ScreamTracker3 {
    private static final int[] effect_map;
    private static final int[] effect_s_map;

    public static /* bridge */ boolean is_s3m(byte[] byArray) {
        String string = ScreamTracker3.ascii_text(byArray, 44, 4);
        return string.equals("SCRM");
    }

    public static /* bridge */ SongManager load_s3m(byte[] byArray, DataInput dataInput) throws IOException {
        int n;
        int n2;
        byte[] byArray2 = ScreamTracker3.read_s3m_file(byArray, dataInput);
        SongManager songManager = new SongManager();
        songManager.song_title = ScreamTracker3.ascii_text(byArray2, 0, 28);
        int n3 = n = ScreamTracker3.unsigned_short_le(byArray2, 32);
        int n4 = n = ScreamTracker3.unsigned_short_le(byArray2, 34);
        int n5 = n = ScreamTracker3.unsigned_short_le(byArray2, 36);
        int n6 = ScreamTracker3.unsigned_short_le(byArray2, 38);
        int n7 = ScreamTracker3.unsigned_short_le(byArray2, 40);
        if ((n6 & 0x40) == 64 || n7 == 4864) {
            songManager.fast_volume_slides = true;
        }
        boolean bl = false;
        if (ScreamTracker3.unsigned_short_le(byArray2, 42) == 1) {
            bl = true;
        }
        songManager.global_volume = byArray2[48] & 0xFF;
        songManager.default_speed = byArray2[49] & 0xFF;
        songManager.default_tempo = byArray2[50] & 0xFF;
        int n8 = byArray2[51] & 0x7F;
        songManager.channel_gain = n8 << 15 >> 7;
        boolean bl2 = (byArray2[51] & 0x80) == 128;
        boolean bl3 = (byArray2[53] & 0xFF) == 252;
        int[] nArray = new int[32];
        int n9 = 0;
        for (n2 = 0; n2 < 32; ++n2) {
            n = byArray2[64 + n2] & 0xFF;
            nArray[n2] = -1;
            if (n >= 16) continue;
            nArray[n2] = n9++;
        }
        songManager.set_num_channels(n9);
        int n10 = 96 + n3 + n4 * 2 + n5 * 2;
        for (n2 = 0; n2 < 32; ++n2) {
            n = byArray2[64 + n2] & 0xFF;
            if (nArray[n2] < 0) continue;
            int n11 = 128;
            if (bl2) {
                n11 = n < 8 ? 64 : 192;
            }
            if (bl3 && ((n6 = byArray2[n10 + n2] & 0xFF) & 0x20) == 32) {
                n11 = (n6 & 0xF) << 4;
            }
            songManager.set_initial_panning(nArray[n2], n11);
        }
        int[] nArray2 = ScreamTracker3.read_s3m_sequence(byArray2);
        songManager.set_sequence_length(nArray2.length);
        for (int i = 0; i < nArray2.length; ++i) {
            songManager.set_sequence(i, nArray2[i]);
        }
        songManager.set_num_instruments(n4);
        for (int i = 0; i < n4; ++i) {
            Instrument instrument = ScreamTracker3.read_s3m_instrument(byArray2, i, bl);
            songManager.set_instrument(i + 1, instrument);
        }
        songManager.set_num_patterns(n5);
        for (int i = 0; i < n5; ++i) {
            songManager.set_pattern(i, ScreamTracker3.read_s3m_pattern(byArray2, i, nArray));
        }
        return songManager;
    }

    private static /* bridge */ int[] read_s3m_sequence(byte[] byArray) {
        int n;
        int n2;
        int n3 = n = ScreamTracker3.unsigned_short_le(byArray, 32);
        int n4 = 0;
        for (n2 = 0; n2 < n3 && (n = byArray[96 + n2] & 0xFF) != 255; ++n2) {
            if (n >= 254) continue;
            ++n4;
        }
        int[] nArray = new int[n4];
        int n5 = 0;
        for (n2 = 0; n2 < n3 && (n = byArray[96 + n2] & 0xFF) != 255; ++n2) {
            if (n >= 254) continue;
            nArray[n5] = n;
            ++n5;
        }
        return nArray;
    }

    private static /* bridge */ Instrument read_s3m_instrument(byte[] byArray, int n, boolean bl) {
        int n2 = ScreamTracker3.get_instrument_offset(byArray, n);
        Instrument instrument = new Instrument();
        instrument.name = ScreamTracker3.ascii_text(byArray, n2 + 48, 28);
        Sample sample = new Sample();
        if (byArray[n2] == 1) {
            int n3 = ScreamTracker3.get_sample_data_length(byArray, n2);
            int n4 = ScreamTracker3.unsigned_short_le(byArray, n2 + 20);
            int n5 = ScreamTracker3.unsigned_short_le(byArray, n2 + 24) - n4;
            sample.volume = byArray[n2 + 28] & 0xFF;
            if (byArray[n2 + 30] != 0) {
                throw new IllegalArgumentException("ScreamTracker3: Packed samples not supported!");
            }
            if ((byArray[n2 + 31] & 1) == 0) {
                n5 = 0;
            }
            sample.c2_rate = ScreamTracker3.unsigned_short_le(byArray, n2 + 32);
            short[] sArray = new short[n3];
            int n6 = ScreamTracker3.get_sample_data_offset(byArray, n2);
            if (bl) {
                for (int i = 0; i < n3; ++i) {
                    int n7 = byArray[n6 + i] << 8;
                    sArray[i] = (short)(n7 << 8);
                }
            } else {
                for (int i = 0; i < n3; ++i) {
                    int n8 = (byArray[n6 + i] & 0xFF) - 128;
                    sArray[i] = (short)(n8 << 8);
                }
            }
            sample.set_sample_data(sArray, n4, n5, false);
        }
        instrument.set_num_samples(1);
        instrument.set_sample(0, sample);
        return instrument;
    }

    private static /* bridge */ Pattern read_s3m_pattern(byte[] byArray, int n, int[] nArray) {
        int n2;
        int n3 = 0;
        for (n2 = 0; n2 < 32; ++n2) {
            if (nArray[n2] < n3) continue;
            n3 = n2 + 1;
        }
        int n4 = n3 * 64;
        byte[] byArray2 = new byte[n4 * 5];
        int n5 = 0;
        int n6 = ScreamTracker3.get_pattern_offset(byArray, n) + 2;
        while (n5 < 64) {
            int n7 = byArray[n6] & 0xFF;
            ++n6;
            if (n7 > 0) {
                n2 = nArray[n7 & 0x1F];
                int n8 = (n3 * n5 + n2) * 5;
                if ((n7 & 0x20) == 32) {
                    if (n2 >= 0) {
                        int n9 = byArray[n6] & 0xFF;
                        if (n9 == 255) {
                            n9 = 0;
                        } else if (n9 == 254) {
                            n9 = 97;
                        } else {
                            for (n9 = ((n9 & 0xF0) >> 4) * 12 + (n9 & 0xF) + 1; n9 > 96; n9 -= 12) {
                            }
                        }
                        byArray2[n8] = (byte)n9;
                        byArray2[n8 + 1] = byArray[n6 + 1];
                    }
                    n6 += 2;
                }
                if ((n7 & 0x40) == 64) {
                    if (n2 >= 0) {
                        int n10 = (byArray[n6] & 0xFF) + 16;
                        byArray2[n8 + 2] = (byte)n10;
                    }
                    ++n6;
                }
                if ((n7 & 0x80) != 128) continue;
                if (n2 >= 0) {
                    int n11 = byArray[n6] & 0xFF;
                    int n12 = byArray[n6 + 1] & 0xFF;
                    if ((n11 = effect_map[n11 & 0x1F]) == 255) {
                        n11 = 0;
                        n12 = 0;
                    }
                    if (n11 == 14) {
                        n11 = effect_s_map[(n12 & 0xF0) >> 4];
                        n12 &= 0xF;
                        switch (n11) {
                            case 8: {
                                n11 = 8;
                                n12 <<= 4;
                                break;
                            }
                            case 9: {
                                n11 = 8;
                                if (n12 > 7) {
                                    n12 -= 8;
                                    break;
                                }
                                n12 += 8;
                                break;
                            }
                            case 255: {
                                n11 = 0;
                                n12 = 0;
                                break;
                            }
                            default: {
                                n12 = (n11 & 0xF) << 4 | n12 & 0xF;
                                n11 = 14;
                            }
                        }
                    }
                    byArray2[n8 + 3] = (byte)n11;
                    byArray2[n8 + 4] = (byte)n12;
                }
                n6 += 2;
                continue;
            }
            ++n5;
        }
        Pattern pattern = new Pattern();
        pattern.num_rows = 64;
        pattern.set_pattern_data(byArray2);
        return pattern;
    }

    private static /* bridge */ byte[] read_s3m_file(byte[] byArray, DataInput dataInput) throws IOException {
        int n;
        int n2;
        int n3;
        int n4;
        if (!ScreamTracker3.is_s3m(byArray)) {
            throw new IllegalArgumentException("ScreamTracker3: Not an S3M file!");
        }
        byte[] byArray2 = byArray;
        int n5 = byArray.length;
        int n6 = n = ScreamTracker3.unsigned_short_le(byArray2, 32);
        int n7 = n = ScreamTracker3.unsigned_short_le(byArray2, 34);
        int n8 = n = ScreamTracker3.unsigned_short_le(byArray2, 36);
        n5 += n6;
        n5 += n7 * 2;
        byArray2 = ScreamTracker3.read_more(byArray2, n5 += n8 * 2, dataInput);
        for (n4 = 0; n4 < n7; ++n4) {
            n3 = ScreamTracker3.get_instrument_offset(byArray2, n4);
            if ((n3 += 80) <= n5) continue;
            n5 = n3;
        }
        for (n2 = 0; n2 < n8; ++n2) {
            n = ScreamTracker3.get_pattern_offset(byArray2, n2);
            if ((n += 2) <= n5) continue;
            n5 = n;
        }
        byArray2 = ScreamTracker3.read_more(byArray2, n5, dataInput);
        for (n4 = 0; n4 < n7; ++n4) {
            n3 = ScreamTracker3.get_instrument_offset(byArray2, n4);
            int n9 = ScreamTracker3.get_sample_data_offset(byArray2, n3);
            if ((n9 += ScreamTracker3.get_sample_data_length(byArray2, n3)) <= n5) continue;
            n5 = n9;
        }
        for (n2 = 0; n2 < n8; ++n2) {
            n = ScreamTracker3.get_pattern_offset(byArray2, n2);
            n += (n2 = ScreamTracker3.unsigned_short_le(byArray2, n));
            if ((n += 2) <= n5) continue;
            n5 = n;
        }
        byArray2 = ScreamTracker3.read_more(byArray2, n5, dataInput);
        return byArray2;
    }

    private static /* bridge */ int get_instrument_offset(byte[] byArray, int n) {
        int n2 = 96 + (n = ScreamTracker3.unsigned_short_le(byArray, 32));
        int n3 = ScreamTracker3.unsigned_short_le(byArray, n2 + n * 2) << 4;
        return n3;
    }

    private static /* bridge */ int get_sample_data_offset(byte[] byArray, int n) {
        int n2 = 0;
        if (byArray[n] == 1) {
            n2 = (byArray[n + 13] & 0xFF) << 20;
            n2 |= ScreamTracker3.unsigned_short_le(byArray, n + 14) << 4;
        }
        return n2;
    }

    private static /* bridge */ int get_sample_data_length(byte[] byArray, int n) {
        int n2 = 0;
        if (byArray[n] == 1) {
            n2 = ScreamTracker3.unsigned_short_le(byArray, n + 16);
        }
        return n2;
    }

    private static /* bridge */ int get_pattern_offset(byte[] byArray, int n) {
        int n2 = 96 + (n = ScreamTracker3.unsigned_short_le(byArray, 32));
        int n3 = ScreamTracker3.unsigned_short_le(byArray, (n2 += (n = ScreamTracker3.unsigned_short_le(byArray, 34)) * 2) + n * 2) << 4;
        return n3;
    }

    private static /* bridge */ byte[] read_more(byte[] byArray, int n, DataInput dataInput) throws IOException {
        byte[] byArray2 = byArray;
        if (n > byArray.length) {
            byArray2 = new byte[n];
            System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
            try {
                dataInput.readFully(byArray2, byArray.length, byArray2.length - byArray.length);
            }
            catch (EOFException eOFException) {
                System.out.println("ScreamTracker3: Module has been truncated!");
            }
        }
        return byArray2;
    }

    private static /* bridge */ int unsigned_short_le(byte[] byArray, int n) {
        int n2 = byArray[n] & 0xFF;
        return n2 |= (byArray[n + 1] & 0xFF) << 8;
    }

    private static /* bridge */ String ascii_text(byte[] byArray, int n, int n2) {
        String string;
        byte[] byArray2 = new byte[n2];
        for (int i = 0; i < n2; ++i) {
            int n3 = byArray[n + i];
            if (n3 < 32) {
                n3 = 32;
            }
            byArray2[i] = (byte)n3;
        }
        try {
            string = new String(byArray2, 0, n2, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            string = "";
        }
        return string;
    }

    static {
        effect_map = new int[]{255, 37, 11, 13, 10, 2, 1, 3, 4, 29, 0, 6, 5, 255, 255, 9, 255, 27, 7, 14, 15, 36, 16, 255, 255, 255, 255, 255, 255, 255, 255, 255};
        effect_s_map = new int[]{0, 3, 5, 4, 7, 255, 255, 255, 8, 255, 9, 6, 12, 13, 14, 15};
    }
}

