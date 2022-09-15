/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package code.SuChen.SkyBlock.modules;

import net.minecraft.client.Minecraft;

public class Module {
    public static Minecraft mc;
    public ValueManager showValue;
    private String name;
    private int key;
    private ModuleType category;
    private boolean isEnabled;
    private String desc;
    public boolean openValues;
    public double arrowAnlge;
    public double animateX;
    public double hoverOpacity;
    public float circleValue;
    public boolean canSeeCircle;
    public int[] circleCoords;
    public boolean clickedCircle;
    public String displayName;
    public float posX;
    public float animPos;

    public Module(String string, ModuleType moduleType) {
        this.displayName = "";
        this.animPos = -1.0f;
        this.arrowAnlge = 0.0;
        this.animateX = 0.0;
        this.hoverOpacity = 0.0;
        this.name = string;
        this.key = -1;
        this.category = moduleType;
        this.circleCoords = new int[2];
    }

    public Module(String string, ModuleType moduleType, int n) {
        this.displayName = "";
        this.animPos = -1.0f;
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}

