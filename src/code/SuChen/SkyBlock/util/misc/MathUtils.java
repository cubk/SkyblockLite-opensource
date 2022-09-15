/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.util.misc;

public class MathUtils {
    public static /* bridge */ Object castNumber(String string, Object object) {
        if (string.contains(".")) {
            if (string.toLowerCase().contains("f")) {
                return Float.valueOf(Float.parseFloat(string));
            }
            return Double.parseDouble(string);
        }
        if (MathUtils.isNumeric(string)) {
            return Integer.parseInt(string);
        }
        return string;
    }

    public static /* bridge */ boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException numberFormatException) {
            return false;
        }
    }
}

