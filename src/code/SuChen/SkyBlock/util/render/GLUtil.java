/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package code.SuChen.SkyBlock.util.render;

import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL11;

public class GLUtil {
    private static final Map<Integer, Boolean> glCapMap;

    public static /* bridge */ void setGLCap(int n, boolean bl) {
        glCapMap.put(n, GL11.glGetBoolean((int)n));
        if (bl) {
            GL11.glEnable((int)n);
        } else {
            GL11.glDisable((int)n);
        }
    }

    private static /* bridge */ void revertGLCap(int n) {
        Boolean bl = glCapMap.get(n);
        if (bl != null) {
            if (bl.booleanValue()) {
                GL11.glEnable((int)n);
            } else {
                GL11.glDisable((int)n);
            }
        }
    }

    public static /* bridge */ void glEnable(int n) {
        GLUtil.setGLCap(n, true);
    }

    public static /* bridge */ void glDisable(int n) {
        GLUtil.setGLCap(n, false);
    }

    public static /* bridge */ void revertAllCaps() {
        for (Integer n : glCapMap.keySet()) {
            GLUtil.revertGLCap(n);
        }
    }

    static {
        glCapMap = new HashMap<Integer, Boolean>();
    }
}

