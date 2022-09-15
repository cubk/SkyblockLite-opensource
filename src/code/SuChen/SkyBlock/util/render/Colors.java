/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 */
package code.SuChen.SkyBlock.util.render;

import java.awt.Color;
import net.minecraft.util.MathHelper;

public enum Colors {
    BLACK(-16711423),
    BLUE(-16723258),
    DARKBLUE(-15698006),
    GREEN(-9581017),
    DARKGREEN(-11231458),
    WHITE(-65794),
    AQUA(-14163205),
    DARKAQUA(-16548724),
    GREY(-6710887),
    DARKGREY(-12303292),
    RED(-43691),
    DARKRED(-7864320),
    ORANGE(-21931),
    DARKORANGE(-7846912),
    YELLOW(-171),
    DARKYELLOW(-7829504),
    MAGENTA(-43521),
    DARKMAGENTA(-7864184);

    public int c;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private Colors(int n) {
        void var3_1;
        this.c = var3_1;
    }

    public static /* bridge */ int getColor(Color color) {
        return Colors.getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static /* bridge */ int getColor(int n) {
        return Colors.getColor(n, n, n, 255);
    }

    public static /* bridge */ int getColor(int n, int n2) {
        return Colors.getColor(n, n, n, n2);
    }

    public static /* bridge */ int getColor(int n, int n2, int n3) {
        return Colors.getColor(n, n2, n3, 255);
    }

    public static /* bridge */ int getColor(int n, int n2, int n3, int n4) {
        int n5 = 0;
        n5 |= MathHelper.clamp_int((int)n4, (int)0, (int)255) << 24;
        n5 |= MathHelper.clamp_int((int)n, (int)0, (int)255) << 16;
        n5 |= MathHelper.clamp_int((int)n2, (int)0, (int)255) << 8;
        return n5 |= MathHelper.clamp_int((int)n3, (int)0, (int)255);
    }

    public static /* bridge */ int getColor1(int n, int n2, int n3, int n4) {
        int n5 = 0;
        n5 |= MathHelper.clamp_int((int)n4, (int)0, (int)255) << 24;
        n5 |= MathHelper.clamp_int((int)n, (int)0, (int)255) << 16;
        n5 |= MathHelper.clamp_int((int)n2, (int)0, (int)255) << 8;
        return n5 |= MathHelper.clamp_int((int)n3, (int)0, (int)255);
    }
}

