/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  Code_By_SuChen.SongManager
 *  org.lwjgl.BufferUtils
 */
package ibxm;

import Code_By_SuChen.SongManager;
import ibxm.FastTracker2;
import ibxm.IBXM;
import ibxm.ProTracker;
import ibxm.ScreamTracker3;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

public class OpenALMODPlayer {
    private static final int sectionSize = 40960;
    private IntBuffer bufferNames;
    private IBXM ibxm;
    private int songDuration;
    private byte[] data;
    private ByteBuffer bufferData;
    private IntBuffer unqueued;
    private int source;
    private boolean soundWorks;
    private SongManager module;
    private boolean loop;
    private boolean done;
    private int remainingBufferCount;

    public OpenALMODPlayer() {
        this.data = new byte[163840];
        this.bufferData = BufferUtils.createByteBuffer((int)163840);
        this.unqueued = BufferUtils.createIntBuffer((int)1);
        this.soundWorks = true;
        this.done = true;
    }

    public static /* bridge */ SongManager loadModule(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        SongManager songManager = null;
        byte[] byArray = new byte[60];
        dataInputStream.readFully(byArray);
        if (FastTracker2.is_xm(byArray)) {
            songManager = FastTracker2.load_xm(byArray, dataInputStream);
        } else {
            byte[] byArray2 = new byte[96];
            System.arraycopy(byArray, 0, byArray2, 0, 60);
            dataInputStream.readFully(byArray2, 60, 36);
            if (ScreamTracker3.is_s3m(byArray2)) {
                songManager = ScreamTracker3.load_s3m(byArray2, dataInputStream);
            } else {
                byte[] byArray3 = new byte[1084];
                System.arraycopy(byArray2, 0, byArray3, 0, 96);
                dataInputStream.readFully(byArray3, 96, 988);
                songManager = ProTracker.load_mod(byArray3, dataInputStream);
            }
        }
        dataInputStream.close();
        return songManager;
    }
}

