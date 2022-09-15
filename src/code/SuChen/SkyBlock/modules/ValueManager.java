/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.modules;

import java.util.ArrayList;
import java.util.List;

public class ValueManager<T> {
    private T value;
    private T valueMin;
    private T valueMax;
    private double step;
    private final T defaultValue;
    private final String name;
    public boolean isValueBoolean;
    public boolean isValueInteger;
    public boolean isValueFloat;
    public boolean isValueDouble;
    public boolean isValueMode;
    public boolean isValueLong;
    public boolean isValueByte;
    private int current;
    public ArrayList<String> mode;
    public double sliderX;
    public boolean set;
    public static final List<ValueManager> list;
    public boolean isSettingMode;
    public boolean openMods;
    public double maxSliderSize;
    public final int RADIUS = 4;
    public float currentRadius;
    private String modeTitle;

    public ValueManager(String string, String string2, int n) {
        this.RADIUS = 4;
        this.set = false;
        this.currentRadius = 4.0f;
        this.isValueBoolean = false;
        this.isValueInteger = false;
        this.isValueFloat = false;
        this.isValueDouble = false;
        this.isValueLong = false;
        this.isValueByte = false;
        this.defaultValue = this.value;
        this.isValueMode = true;
        this.step = 0.1;
        this.mode = new ArrayList();
        this.current = n;
        this.name = String.valueOf(string) + "_Mode";
        this.modeTitle = string2;
        list.add(this);
    }

    public ValueManager(String string, T t, T t2, T t3) {
        this.RADIUS = 4;
        this.set = false;
        this.currentRadius = 4.0f;
        this.isValueBoolean = false;
        this.isValueInteger = false;
        this.isValueFloat = false;
        this.isValueDouble = false;
        this.isValueLong = false;
        this.isValueByte = false;
        this.defaultValue = this.value;
        this.name = string;
        this.value = t;
        this.valueMin = t2;
        this.valueMax = t3;
        this.step = 0.1;
        if (this.value instanceof Double) {
            this.isValueDouble = true;
        }
        list.add(this);
    }

    public ValueManager(String string, T t, T t2, T t3, double d) {
        this.RADIUS = 4;
        this.set = false;
        this.currentRadius = 4.0f;
        this.isValueBoolean = false;
        this.isValueInteger = false;
        this.isValueFloat = false;
        this.isValueDouble = false;
        this.isValueLong = false;
        this.isValueByte = false;
        this.defaultValue = t;
        this.name = string;
        this.value = t;
        this.valueMin = t2;
        this.valueMax = t3;
        this.step = d;
        if (t instanceof Double) {
            this.isValueDouble = true;
        }
        list.add(this);
    }

    public ValueManager(String string, T t) {
        this.RADIUS = 4;
        this.set = false;
        this.currentRadius = 4.0f;
        this.isValueBoolean = false;
        this.isValueInteger = false;
        this.isValueFloat = false;
        this.isValueDouble = false;
        this.isValueLong = false;
        this.isValueByte = false;
        this.defaultValue = t;
        this.name = string;
        this.value = t;
        if (t instanceof Boolean) {
            this.isValueBoolean = true;
        } else if (t instanceof Integer) {
            this.isValueInteger = true;
        } else if (t instanceof Float) {
            this.isValueFloat = true;
        } else if (t instanceof Long) {
            this.isValueLong = true;
        } else if (t instanceof Byte) {
            this.isValueByte = true;
        }
        list.add(this);
    }

    public ValueManager(String string, String string2, String string3, T t, T t2, T t3) {
        this.RADIUS = 4;
        this.set = false;
        this.currentRadius = 4.0f;
        this.isValueBoolean = false;
        this.isValueInteger = false;
        this.isValueFloat = false;
        this.isValueDouble = false;
        this.isValueLong = false;
        this.isValueByte = false;
        this.defaultValue = t;
        this.name = string;
        this.value = t;
        if (t instanceof Boolean) {
            this.isValueBoolean = true;
        } else if (t instanceof Integer) {
            this.isValueInteger = true;
        } else if (t instanceof Float) {
            this.isValueFloat = true;
        } else if (t instanceof Double) {
            this.isValueDouble = true;
        } else if (t instanceof Long) {
            this.isValueLong = true;
        } else if (t instanceof Byte) {
            this.isValueByte = true;
        }
        list.add(this);
    }

    public static /* bridge */ ValueManager getBooleanValueByName(String string) {
        for (ValueManager valueManager : list) {
            if (!valueManager.isValueBoolean || !valueManager.getValueName().equalsIgnoreCase(string)) continue;
            return valueManager;
        }
        return null;
    }

    public static /* bridge */ ValueManager getDoubleValueByName(String string) {
        for (ValueManager valueManager : list) {
            if (!valueManager.isValueDouble || !valueManager.getValueName().equalsIgnoreCase(string)) continue;
            return valueManager;
        }
        return null;
    }

    public static /* bridge */ ValueManager getModeValue(String string, String string2) {
        for (ValueManager valueManager : list) {
            if (!valueManager.isValueMode || !valueManager.getValueName().equalsIgnoreCase(string) || !valueManager.getModeTitle().equalsIgnoreCase(string2)) continue;
            return valueManager;
        }
        return null;
    }

    static {
        list = new ArrayList<ValueManager>();
    }
}

