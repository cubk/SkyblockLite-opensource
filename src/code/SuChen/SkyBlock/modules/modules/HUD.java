/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.opengl.GL11
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.SkyBlockLite;
import code.SuChen.SkyBlock.events.RenderGameOverlayEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.modules.ValueManager;
import code.SuChen.SkyBlock.util.render.Colors;
import com.darkmagician6.eventapi.EventTarget;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class HUD
extends Module {
    public ValueManager<String> Array_mode;
    public ValueManager<Boolean> Logo;
    public ValueManager<Boolean> ArrayList;
    public ValueManager<Boolean> ArmorHUD;
    FontRenderer fr;

    public HUD() {
        super("HUD", ModuleType.RENDER);
        this.Array_mode = new ValueManager("HUD", "Array", 0);
        this.Logo = new ValueManager<Boolean>("HUD_Logo", true);
        this.ArrayList = new ValueManager<Boolean>("HUD_ArrayList", true);
        this.ArmorHUD = new ValueManager<Boolean>("HUD_ArmorHUD", true);
        this.fr = HUD.mc.fontRendererObj;
        this.Array_mode.mode.add("Sakura");
        this.Array_mode.mode.add("Power");
        this.Array_mode.mode.add("NewPower");
    }

    @EventTarget
    public void onRender2D(RenderGameOverlayEvent renderGameOverlayEvent) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        if (this.Logo.getValueState().booleanValue()) {
            this.renderStringWave(SkyBlockLite.CLIENT_name + " " + SkyBlockLite.CLEINT_VERSION, 1.0f, 1.0f, 0.6f);
        }
        if (this.ArmorHUD.getValueState().booleanValue()) {
            this.renderStuffStatus(scaledResolution);
        }
        if (this.ArrayList.getValueState().booleanValue()) {
            this.PowerrenderMods();
        }
    }

    public static /* bridge */ Color rainbow(long l, float f, float f2) {
        float f3 = ((float)l + (1.0E-9f + f) * 4.0E8f) / 1.75000003E10f * 3.0f;
        long l2 = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(f3, 0.5f, 1.0f)), 16);
        Color color = new Color((int)l2);
        return new Color((float)color.getRed() / 255.0f * f2, (float)color.getGreen() / 255.0f * f2, (float)color.getBlue() / 255.0f * f2, (float)color.getAlpha() / 255.0f);
    }

    private static /* bridge */ void drawEnchantTag(String string, int n, int n2) {
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(string, (float)(n *= 2), (float)(n2 * 2), Colors.getColor(255));
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    private static /* bridge */ void drawEnchantTags(String string, int n, int n2) {
        String[] stringArray;
        String string2 = string;
        for (String string3 : stringArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "m", "o", "r", "g"}) {
            string = string.replaceAll("\u00a7" + string3, "");
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        Minecraft.getMinecraft().fontRendererObj.drawString(string, (n *= 2) + 1, n2 * 2, 0);
        Minecraft.getMinecraft().fontRendererObj.drawString(string, n - 1, n2 * 2, 0);
        Minecraft.getMinecraft().fontRendererObj.drawString(string, n, n2 * 2 + 1, 0);
        Minecraft.getMinecraft().fontRendererObj.drawString(string, n, n2 * 2 - 1, 0);
        Minecraft.getMinecraft().fontRendererObj.drawString(string2, n, n2 * 2, Colors.getColor(255));
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    public static /* bridge */ int getColor(Color color) {
        return HUD.getColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }

    public static /* bridge */ int getColor(int n, int n2) {
        return HUD.getColor(n, n, n, n2);
    }

    public static /* bridge */ int getColor(int n, int n2, int n3) {
        return HUD.getColor(n, n2, n3, 255);
    }

    public static /* bridge */ int getColor(int n, int n2, int n3, int n4) {
        int n5 = 0;
        n5 |= n4 << 24;
        n5 |= n << 16;
        n5 |= n2 << 8;
        return n5 |= n3;
    }
}

