/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.opengl.GL11
 */
package code.SuChen.SkyBlock.gui.clickgui;

import code.SuChen.SkyBlock.gui.altlogin.GuiClientButton;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleManager;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.SkyBlockLite;
import code.SuChen.SkyBlock.modules.ValueManager;
import code.SuChen.SkyBlock.util.fontManager.UnicodeFontRenderer;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class ClickGui
extends GuiScreen {
    ArrayList<Module> mods;
    private GuiClientButton handlerMid;
    private GuiClientButton handlerRight;
    private GuiClientButton handler;
    UnicodeFontRenderer font2;
    UnicodeFontRenderer font;
    ScaledResolution res;
    public int moveX;
    public int moveY;
    public int startX;
    public int startY;
    public int selectCategory;
    private float scrollY;
    private float modscrollY;
    public static boolean binding;
    public Module bmod;
    ValueManager v;
    ValueManager mode;
    public static Module currentMod;
    public boolean dragging;
    public boolean drag;
    public boolean Mdrag;
    ScaledResolution rs;

    public ClickGui() {
        this.mods = new ArrayList<Module>(SkyBlockLite.instance.modMgr.getopenValues());
        this.handlerMid = new GuiClientButton(2);
        this.handlerRight = new GuiClientButton(1);
        this.handler = new GuiClientButton(0);
        this.font2 = SkyBlockLite.instance.fontMgr.simpleton14;
        this.font = SkyBlockLite.instance.fontMgr.simpleton17;
        this.res = new ScaledResolution(Minecraft.getMinecraft());
        this.moveX = 0;
        this.moveY = 0;
        this.startX = 50;
        this.startY = 40;
        this.selectCategory = 0;
        this.rs = new ScaledResolution(Minecraft.getMinecraft());
    }

    public static /* bridge */ void erase(boolean bl) {
        GL11.glStencilFunc((int)(bl ? 514 : 517), (int)1, (int)65535);
        GL11.glStencilOp((int)7680, (int)7680, (int)7681);
        GlStateManager.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GL11.glAlphaFunc((int)516, (float)0.0f);
    }

    public static /* bridge */ List getValueList(Module module) {
        ArrayList<ValueManager> arrayList = new ArrayList<ValueManager>();
        for (ValueManager valueManager : ValueManager.list) {
            if (!valueManager.getValueName().split("_")[0].equalsIgnoreCase(module.getName())) continue;
            arrayList.add(valueManager);
        }
        return arrayList;
    }

    public static /* bridge */ List getModsInCategory(ModuleType moduleType) {
        ArrayList<Module> arrayList = new ArrayList<Module>();
        for (Module module : ModuleManager.getModList()) {
            if (module.getCategory() != moduleType) continue;
            arrayList.add(module);
        }
        return arrayList;
    }

    static {
        binding = false;
        currentMod = null;
    }
}

