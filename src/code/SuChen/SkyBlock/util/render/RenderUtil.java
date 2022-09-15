/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package code.SuChen.SkyBlock.util.render;

import code.SuChen.SkyBlock.events.RenderEvent;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
    public static float delta;
    public static Minecraft mc;
    private static final AxisAlignedBB DEFAULT_AABB;

    public static /* bridge */ void drawFilledCircle(int n, int n2, float f, int n3) {
        float f2 = (float)(n3 >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(n3 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n3 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n3 & 0xFF) / 255.0f;
        int n4 = 50;
        double d = Math.PI * 2 / (double)n4;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)6);
        for (int i = 0; i < n4; ++i) {
            float f6 = (float)((double)f * Math.sin((double)i * d));
            float f7 = (float)((double)f * Math.cos((double)i * d));
            GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)f2);
            GL11.glVertex2f((float)((float)n + f6), (float)((float)n2 + f7));
        }
        GlStateManager.color((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static /* bridge */ int rainbow(int n) {
        double d = Math.ceil((double)(System.currentTimeMillis() + (long)n) / 4.0);
        return Color.getHSBColor((float)((d %= 360.0) / 360.0), 0.6f, 1.0f).getRGB();
    }

    public static /* bridge */ void drawFilledCircle(float f, float f2, float f3, int n) {
        float f4 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(n & 0xFF) / 255.0f;
        int n2 = 50;
        double d = Math.PI * 2 / (double)n2;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)6);
        for (int i = 0; i < n2; ++i) {
            float f8 = (float)((double)f3 * Math.sin((double)i * d));
            float f9 = (float)((double)f3 * Math.cos((double)i * d));
            GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
            GL11.glVertex2f((float)(f + f8), (float)(f2 + f9));
        }
        GlStateManager.color((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static /* bridge */ void drawFilledCircle(int n, int n2, float f, int n3, int n4, int n5, int n6, int n7) {
        float f2 = (float)(n3 >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(n3 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n3 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n3 & 0xFF) / 255.0f;
        int n8 = 50;
        double d = Math.PI * 2 / (double)n8;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)6);
        for (int i = 0; i < n8; ++i) {
            float f6 = (float)((double)f * Math.sin((double)i * d));
            float f7 = (float)((double)f * Math.cos((double)i * d));
            float f8 = (float)n + f6;
            float f9 = (float)n2 + f7;
            if (f8 < (float)n4) {
                f8 = n4;
            }
            if (f8 > (float)n6) {
                f8 = n6;
            }
            if (f9 < (float)n5) {
                f9 = n5;
            }
            if (f9 > (float)n7) {
                f9 = n7;
            }
            GL11.glColor4f((float)f3, (float)f4, (float)f5, (float)f2);
            GL11.glVertex2f((float)f8, (float)f9);
        }
        GlStateManager.color((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static /* bridge */ void drawRoundedRect(float f, float f2, float f3, float f4, float f5, int n) {
        Gui.drawRect((int)((int)(f += (float)((double)(f5 / 2.0f) + 0.5))), (int)((int)(f2 += (float)((double)(f5 / 2.0f) + 0.5))), (int)((int)(f3 -= (float)((double)(f5 / 2.0f) + 0.5))), (int)((int)(f4 -= (float)((double)(f5 / 2.0f) + 0.5))), (int)n);
        RenderUtil.arc(f3 - f5 / 2.0f, f2 + f5 / 2.0f, 0.0f, 360.0f, f5, n);
        RenderUtil.arc(f + f5 / 2.0f, f4 - f5 / 2.0f, 0.0f, 360.0f, f5, n);
        RenderUtil.arc(f + f5 / 2.0f, f2 + f5 / 2.0f, 0.0f, 360.0f, f5, n);
        RenderUtil.arc(f3 - f5 / 2.0f, f4 - f5 / 2.0f, 0.0f, 360.0f, f5, n);
        Gui.drawRect((int)((int)(f - f5 / 2.0f - 0.5f)), (int)((int)(f2 + f5 / 2.0f)), (int)((int)f3), (int)((int)(f4 - f5 / 2.0f)), (int)n);
        Gui.drawRect((int)((int)f), (int)((int)(f2 + f5 / 2.0f)), (int)((int)(f3 + f5 / 2.0f + 0.5f)), (int)((int)(f4 - f5 / 2.0f)), (int)n);
        Gui.drawRect((int)((int)(f + f5 / 2.0f)), (int)((int)(f2 - f5 / 2.0f - 0.5f)), (int)((int)(f3 - f5 / 2.0f)), (int)((int)(f4 - f5 / 2.0f)), (int)n);
        Gui.drawRect((int)((int)(f + f5 / 2.0f)), (int)((int)f2), (int)((int)(f3 - f5 / 2.0f)), (int)((int)(f4 + f5 / 2.0f + 0.5f)), (int)n);
    }

    public static /* bridge */ void circle(float f, float f2, float f3, Color color) {
        RenderUtil.arcEllipse(f, f2, 0.0f, 360.0f, f3, f3, color);
    }

    public static /* bridge */ void doGlScissor(int n, int n2, int n3, int n4) {
        Minecraft minecraft = Minecraft.getMinecraft();
        int n5 = 1;
        int n6 = minecraft.gameSettings.guiScale;
        if (n6 == 0) {
            n6 = 1000;
        }
        while (n5 < n6 && minecraft.displayWidth / (n5 + 1) >= 320 && minecraft.displayHeight / (n5 + 1) >= 240) {
            ++n5;
        }
        GL11.glScissor((int)(n * n5), (int)(minecraft.displayHeight - (n2 + n4) * n5), (int)(n3 * n5), (int)(n4 * n5));
    }

    public static /* bridge */ void arcEllipse(float f, float f2, float f3, float f4, float f5, float f6, int n) {
        float f7;
        float f8;
        float f9;
        GlStateManager.color((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        float f10 = 0.0f;
        if (f3 > f4) {
            f10 = f4;
            f4 = f3;
            f3 = f10;
        }
        float f11 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f12 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f13 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f14 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)f12, (float)f13, (float)f14, (float)f11);
        if (f11 > 0.5f) {
            GL11.glEnable((int)2848);
            GL11.glLineWidth((float)2.0f);
            GL11.glBegin((int)3);
            for (f9 = f4; f9 >= f3; f9 -= 4.0f) {
                f8 = (float)Math.cos((double)f9 * Math.PI / 180.0) * f5 * 1.001f;
                f7 = (float)Math.sin((double)f9 * Math.PI / 180.0) * f6 * 1.001f;
                GL11.glVertex2f((float)(f + f8), (float)(f2 + f7));
            }
            GL11.glEnd();
            GL11.glDisable((int)2848);
        }
        GL11.glBegin((int)6);
        for (f9 = f4; f9 >= f3; f9 -= 4.0f) {
            f8 = (float)Math.cos((double)f9 * Math.PI / 180.0) * f5;
            f7 = (float)Math.sin((double)f9 * Math.PI / 180.0) * f6;
            GL11.glVertex2f((float)(f + f8), (float)(f2 + f7));
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static /* bridge */ void arcEllipse(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        float f7;
        float f8;
        float f9;
        GlStateManager.color((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        float f10 = 0.0f;
        if (f3 > f4) {
            f10 = f4;
            f4 = f3;
            f3 = f10;
        }
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        if ((float)color.getAlpha() > 0.5f) {
            GL11.glEnable((int)2848);
            GL11.glLineWidth((float)2.0f);
            GL11.glBegin((int)3);
            for (f9 = f4; f9 >= f3; f9 -= 4.0f) {
                f8 = (float)Math.cos((double)f9 * Math.PI / 180.0) * f5 * 1.001f;
                f7 = (float)Math.sin((double)f9 * Math.PI / 180.0) * f6 * 1.001f;
                GL11.glVertex2f((float)(f + f8), (float)(f2 + f7));
            }
            GL11.glEnd();
            GL11.glDisable((int)2848);
        }
        GL11.glBegin((int)6);
        for (f9 = f4; f9 >= f3; f9 -= 4.0f) {
            f8 = (float)Math.cos((double)f9 * Math.PI / 180.0) * f5;
            f7 = (float)Math.sin((double)f9 * Math.PI / 180.0) * f6;
            GL11.glVertex2f((float)(f + f8), (float)(f2 + f7));
        }
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static /* bridge */ double getAnimationState(double d, double d2, double d3) {
        float f = (float)(0.01 * d3);
        d = d < d2 ? (d + (double)f < d2 ? (d += (double)f) : d2) : (d - (double)f > d2 ? (d -= (double)f) : d2);
        return d;
    }

    public static /* bridge */ void drawRect(float f, float f2, float f3, float f4, int n) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        RenderUtil.color(n);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)f3, (double)f2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)f, (double)f4);
        GL11.glVertex2d((double)f3, (double)f4);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static /* bridge */ void color(int n) {
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f);
    }

    public static /* bridge */ void drawImage(ResourceLocation resourceLocation, int n, int n2, int n3, int n4) {
        new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDepthMask((boolean)false);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture((int)n, (int)n2, (float)0.0f, (float)0.0f, (int)n3, (int)n4, (float)n3, (float)n4);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
    }

    public static /* bridge */ void drawImage(ResourceLocation resourceLocation, int n, int n2, int n3, int n4, Color color) {
        new ScaledResolution(Minecraft.getMinecraft());
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDepthMask((boolean)false);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture((int)n, (int)n2, (float)0.0f, (float)0.0f, (int)n3, (int)n4, (float)n3, (float)n4);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
    }

    public static /* bridge */ void entityESPBox(Entity entity, Color color, RenderEvent renderEvent) {
        double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d2 = d - RenderUtil.mc.getRenderManager().renderPosX;
        d = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d3 = d - RenderUtil.mc.getRenderManager().renderPosY;
        d = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d4 = d - RenderUtil.mc.getRenderManager().renderPosZ;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.fromBounds((double)(d2 - (double)entity.width), (double)d3, (double)(d4 - (double)entity.width), (double)(d2 + (double)entity.width), (double)(d3 + (double)entity.height + 0.2), (double)(d4 + (double)entity.width));
        if (entity instanceof EntityLivingBase) {
            axisAlignedBB = AxisAlignedBB.fromBounds((double)(d2 - (double)entity.width + 0.2), (double)d3, (double)(d4 - (double)entity.width + 0.2), (double)(d2 + (double)entity.width - 0.2), (double)(d3 + (double)entity.height + (entity.isSneaking() ? 0.02 : 0.2)), (double)(d4 + (double)entity.width - 0.2));
        }
        GL11.glLineWidth((float)1.0f);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)1.0f);
        RenderUtil.drawOutlinedBoundingBox(axisAlignedBB);
    }

    public static /* bridge */ void SoulsCheckESP(Entity entity, Color color, RenderEvent renderEvent) {
        double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d2 = d - RenderUtil.mc.getRenderManager().renderPosX;
        d = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d3 = d - RenderUtil.mc.getRenderManager().renderPosY;
        d = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d4 = d - RenderUtil.mc.getRenderManager().renderPosZ;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.fromBounds((double)(d2 - (double)entity.width), (double)d3, (double)(d4 - (double)entity.width), (double)(d2 + (double)entity.width), (double)(d3 + (double)entity.height + 0.2), (double)(d4 + (double)entity.width));
        if (entity instanceof EntityLivingBase) {
            axisAlignedBB = AxisAlignedBB.fromBounds((double)(d2 - (double)entity.width + 0.2), (double)(d3 + 1.42), (double)(d4 - (double)entity.width + 0.2), (double)(d2 + (double)entity.width - 0.2), (double)(d3 + (double)entity.height + 0.14), (double)(d4 + (double)entity.width - 0.2));
        }
        GL11.glLineWidth((float)1.0f);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)1.0f);
        RenderUtil.drawOutlinedBoundingBox(axisAlignedBB);
    }

    public static /* bridge */ void SumEyeCheckbox(Entity entity, Color color, RenderEvent renderEvent) {
        GL11.glPushMatrix();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d2 = d - RenderUtil.mc.getRenderManager().renderPosX;
        d = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d3 = d - RenderUtil.mc.getRenderManager().renderPosY;
        d = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d4 = d - RenderUtil.mc.getRenderManager().renderPosZ;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.fromBounds((double)(d2 - (double)entity.width), (double)d3, (double)(d4 - (double)entity.width), (double)(d2 + (double)entity.width), (double)(d3 + (double)entity.height + 0.2), (double)(d4 + (double)entity.width));
        if (entity instanceof EntityLivingBase) {
            axisAlignedBB = AxisAlignedBB.fromBounds((double)(d2 - (double)entity.width + 0.2), (double)d3, (double)(d4 - (double)entity.width + 0.2), (double)(d2 + (double)entity.width - 0.2), (double)(d3 + (double)entity.height + (entity.isSneaking() ? 0.02 : 0.2)), (double)(d4 + (double)entity.width - 0.2));
        }
        GL11.glLineWidth((float)1.0f);
        GL11.glTranslated((double)d2, (double)d3, (double)d4);
        GL11.glRotated((double)(-entity.rotationYaw % 360.0f), (double)0.0, (double)1.0, (double)0.0);
        GL11.glTranslated((double)(-d2), (double)(-d3), (double)(-d4));
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)1.0f);
        RenderUtil.drawOutlinedBoundingBox(axisAlignedBB);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.2f);
        RenderUtil.drawBoundingBox(axisAlignedBB);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static /* bridge */ void ItemESP(Entity entity, Color color, RenderEvent renderEvent) {
        GL11.glPushMatrix();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d2 = d - RenderUtil.mc.getRenderManager().renderPosX;
        d = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d3 = d - RenderUtil.mc.getRenderManager().renderPosY;
        d = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)renderEvent.getPartialTicks();
        Minecraft.getMinecraft().getRenderManager();
        double d4 = d - RenderUtil.mc.getRenderManager().renderPosZ;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.fromBounds((double)(d2 - (double)entity.width), (double)(d3 + 0.1), (double)(d4 - (double)entity.width), (double)(d2 + (double)entity.width), (double)(d3 + (double)entity.height - 0.05), (double)(d4 + (double)entity.width));
        if (entity instanceof EntityLivingBase) {
            axisAlignedBB = AxisAlignedBB.fromBounds((double)(d2 - (double)entity.width + 0.2), (double)d3, (double)(d4 - (double)entity.width + 0.2), (double)(d2 + (double)entity.width - 0.2), (double)(d3 + (double)entity.height + 0.2), (double)(d4 + (double)entity.width - 0.2));
        }
        GL11.glLineWidth((float)1.0f);
        GL11.glTranslated((double)d2, (double)d3, (double)d4);
        GL11.glRotated((double)(-entity.rotationYaw % 360.0f), (double)0.0, (double)1.0, (double)0.0);
        GL11.glTranslated((double)(-d2), (double)(-d3), (double)(-d4));
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)1.0f);
        RenderUtil.drawOutlinedBoundingBox(axisAlignedBB);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.2f);
        RenderUtil.drawBoundingBox(axisAlignedBB);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static /* bridge */ void drawOutlinedBoundingBox(AxisAlignedBB axisAlignedBB) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(1, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
    }

    public static /* bridge */ void drawEntityESP(double d, double d2, double d3, double d4, double d5, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
        RenderUtil.drawBoundingBox(new AxisAlignedBB(d - d4, d2, d3 - d4, d + d4, d2 + d5, d3 + d4));
        GL11.glLineWidth((float)f9);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f8);
        RenderUtil.drawOutlinedBoundingBox(new AxisAlignedBB(d - d4, d2, d3 - d4, d + d4, d2 + d5, d3 + d4));
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static /* bridge */ void drawBoundingBox(AxisAlignedBB axisAlignedBB) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
    }

    public static /* bridge */ void drawSolidBox() {
        RenderUtil.drawSolidBox(DEFAULT_AABB);
    }

    public static /* bridge */ void drawOutlinedBox() {
        RenderUtil.drawOutlinedBox(DEFAULT_AABB);
    }

    public static /* bridge */ void drawOutlinedBox(AxisAlignedBB axisAlignedBB) {
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glEnd();
    }

    public static /* bridge */ void drawSolidBox(AxisAlignedBB axisAlignedBB) {
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glEnd();
    }

    public static /* bridge */ void drawRect(float f, float f2, float f3, float f4, Color color) {
        float f5;
        if (f < f3) {
            f5 = f;
            f = f3;
            f3 = f5;
        }
        if (f2 < f4) {
            f5 = f2;
            f2 = f4;
            f4 = f5;
        }
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos((double)f, (double)f4, 0.0).endVertex();
        worldRenderer.pos((double)f3, (double)f4, 0.0).endVertex();
        worldRenderer.pos((double)f3, (double)f2, 0.0).endVertex();
        worldRenderer.pos((double)f, (double)f2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static /* bridge */ void drawRoundedRect(float f, float f2, float f3, float f4, int n, int n2) {
        RenderUtil.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderUtil.drawVLine(f *= 2.0f, (f2 *= 2.0f) + 1.0f, (f4 *= 2.0f) - 2.0f, n);
        RenderUtil.drawVLine((f3 *= 2.0f) - 1.0f, f2 + 1.0f, f4 - 2.0f, n);
        RenderUtil.drawHLine(f + 2.0f, f3 - 3.0f, f2, n);
        RenderUtil.drawHLine(f + 2.0f, f3 - 3.0f, f4 - 1.0f, n);
        RenderUtil.drawHLine(f + 1.0f, f + 1.0f, f2 + 1.0f, n);
        RenderUtil.drawHLine(f3 - 2.0f, f3 - 2.0f, f2 + 1.0f, n);
        RenderUtil.drawHLine(f3 - 2.0f, f3 - 2.0f, f4 - 2.0f, n);
        RenderUtil.drawHLine(f + 1.0f, f + 1.0f, f4 - 2.0f, n);
        RenderUtil.drawRect(f + 1.0f, f2 + 1.0f, f3 - 1.0f, f4 - 1.0f, n2);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderUtil.disableGL2D();
    }

    public static /* bridge */ void drawHLine(float f, float f2, float f3, int n) {
        if (f2 < f) {
            float f4 = f;
            f = f2;
            f2 = f4;
        }
        RenderUtil.drawRect(f, f3, f2 + 1.0f, f3 + 1.0f, n);
    }

    public static /* bridge */ void drawVLine(float f, float f2, float f3, int n) {
        if (f3 < f2) {
            float f4 = f2;
            f2 = f3;
            f3 = f4;
        }
        RenderUtil.drawRect(f, f2 + 1.0f, f + 1.0f, f3, n);
    }

    public static /* bridge */ void drawHLine(float f, float f2, float f3, int n, int n2) {
        if (f2 < f) {
            float f4 = f;
            f = f2;
            f2 = f4;
        }
        RenderUtil.drawGradientRect(f, f3, f2 + 1.0f, f3 + 1.0f, n, n2);
    }

    public static /* bridge */ void drawGradientRect(float f, float f2, float f3, float f4, int n, int n2) {
        RenderUtil.enableGL2D();
        int n3 = 765002628;
        GL11.glShadeModel((int)7425);
        n3 = 937192933;
        GL11.glBegin((int)7);
        RenderUtil.glColor(n);
        GL11.glVertex2f((float)f, (float)f4);
        GL11.glVertex2f((float)f3, (float)f4);
        RenderUtil.glColor(n2);
        GL11.glVertex2f((float)f3, (float)f2);
        GL11.glVertex2f((float)f, (float)f2);
        GL11.glEnd();
        n3 = -1342353794;
        GL11.glShadeModel((int)7424);
        RenderUtil.disableGL2D();
    }

    public static /* bridge */ void enableGL2D() {
        int n = 1124259912;
        GL11.glDisable((int)2929);
        n = 1371046376;
        GL11.glEnable((int)3042);
        n = 1357778546;
        GL11.glDisable((int)3553);
        n = 478174525;
        int n2 = 770;
        int n3 = -169838257;
        GL11.glBlendFunc((int)n2, (int)771);
        n = -810992972;
        GL11.glDepthMask((boolean)true);
        n = -61578343;
        GL11.glEnable((int)2848);
        n = 2103834587;
        n2 = 3154;
        n3 = 475894010;
        GL11.glHint((int)n2, (int)4354);
        n = 1813031821;
        n2 = 3155;
        n3 = 1298618958;
        GL11.glHint((int)n2, (int)4354);
    }

    public static /* bridge */ void disableGL2D() {
        int n = -382605771;
        GL11.glEnable((int)3553);
        n = -2129715258;
        GL11.glDisable((int)3042);
        n = -553256799;
        GL11.glEnable((int)2929);
        n = -1076676790;
        GL11.glDisable((int)2848);
        n = 1123473789;
        int n2 = 3154;
        int n3 = 958450265;
        GL11.glHint((int)n2, (int)4352);
        n = -1107347103;
        n2 = 3155;
        n3 = -2145291913;
        GL11.glHint((int)n2, (int)4352);
    }

    public static /* bridge */ Color glColor(int n, float f) {
        int n2 = -1243526782;
        int n3 = n >> 16;
        n2 = -1947007894;
        float f2 = (float)(n3 & 0xFF) / 255.0f;
        n2 = -2127944098;
        n3 = n >> 8;
        n2 = -191028827;
        float f3 = (float)(n3 & 0xFF) / 255.0f;
        n2 = 1286872184;
        float f4 = (float)(n & 0xFF) / 255.0f;
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f);
        return new Color(f2, f3, f4, f);
    }

    public static /* bridge */ Color glColor(int n) {
        int n2 = 1911576991;
        int n3 = n >> 24;
        n2 = -906326265;
        float f = (float)(n3 & 0xFF) / 256.0f;
        n2 = -2014451144;
        n3 = n >> 16;
        n2 = 1449174302;
        float f2 = (float)(n3 & 0xFF) / 255.0f;
        n2 = -711778984;
        n3 = n >> 8;
        n2 = -1489853377;
        float f3 = (float)(n3 & 0xFF) / 255.0f;
        n2 = -1241218198;
        float f4 = (float)(n & 0xFF) / 255.0f;
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f);
        return new Color(f2, f3, f4, f);
    }

    public static /* bridge */ void drawRect(int n, int n2, int n3, int n4, int n5) {
        int n6;
        if (n < n3) {
            n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        float f = (float)(n5 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n5 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n5 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n5 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos((double)n, (double)n4, 0.0).endVertex();
        worldRenderer.pos((double)n3, (double)n4, 0.0).endVertex();
        worldRenderer.pos((double)n3, (double)n2, 0.0).endVertex();
        worldRenderer.pos((double)n, (double)n2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static /* bridge */ void drawRect(double d, double d2, double d3, double d4, int n) {
        double d5;
        if (d < d3) {
            d5 = d;
            d = d3;
            d3 = d5;
        }
        if (d2 < d4) {
            d5 = d2;
            d2 = d4;
            d4 = d5;
        }
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(d, d4, 0.0).endVertex();
        worldRenderer.pos(d3, d4, 0.0).endVertex();
        worldRenderer.pos(d3, d2, 0.0).endVertex();
        worldRenderer.pos(d, d2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static /* bridge */ void rectangleBordered(double d, double d2, double d3, double d4, double d5, int n, int n2) {
        RenderUtil.rectangle(d + d5, d2 + d5, d3 - d5, d4 - d5, n);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtil.rectangle(d + d5, d2, d3 - d5, d2 + d5, n2);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtil.rectangle(d, d2, d + d5, d4, n2);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtil.rectangle(d3 - d5, d2, d3, d4, n2);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtil.rectangle(d + d5, d4 - d5, d3 - d5, d4, n2);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static /* bridge */ void rectangle(double d, double d2, double d3, double d4, int n) {
        double d5;
        if (d < d3) {
            d5 = d;
            d = d3;
            d3 = d5;
        }
        if (d2 < d4) {
            d5 = d2;
            d2 = d4;
            d4 = d5;
        }
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f);
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(d, d4, 0.0).endVertex();
        worldRenderer.pos(d3, d4, 0.0).endVertex();
        worldRenderer.pos(d3, d2, 0.0).endVertex();
        worldRenderer.pos(d, d2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static /* bridge */ void drawCircle(float f, float f2, float f3, int n, int n2) {
        GL11.glPushMatrix();
        f *= 2.0f;
        f2 *= 2.0f;
        float f4 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(n2 & 0xFF) / 255.0f;
        float f8 = (float)(6.2831852 / (double)n);
        float f9 = (float)Math.cos(f8);
        float f10 = (float)Math.sin(f8);
        float f11 = f3 *= 2.0f;
        float f12 = 0.0f;
        RenderUtil.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glBegin((int)2);
        for (int i = 0; i < n; ++i) {
            GL11.glVertex2f((float)(f11 + f), (float)(f12 + f2));
            float f13 = f11;
            f11 = f9 * f11 - f10 * f12;
            f12 = f10 * f13 + f9 * f12;
        }
        GL11.glEnd();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderUtil.disableGL2D();
        GL11.glPopMatrix();
    }

    public static /* bridge */ void drawGradientSideways(double d, double d2, double d3, double d4, int n, int n2) {
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        float f5 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n2 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f);
        GL11.glVertex2d((double)d, (double)d2);
        GL11.glVertex2d((double)d, (double)d4);
        GL11.glColor4f((float)f6, (float)f7, (float)f8, (float)f5);
        GL11.glVertex2d((double)d3, (double)d4);
        GL11.glVertex2d((double)d3, (double)d2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static /* bridge */ void SumEye(Entity entity, Color color, RenderEvent renderEvent) {
        double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)renderEvent.getPartialTicks() - RenderUtil.mc.getRenderManager().renderPosX;
        double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)renderEvent.getPartialTicks() - RenderUtil.mc.getRenderManager().renderPosY;
        double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)renderEvent.getPartialTicks() - RenderUtil.mc.getRenderManager().renderPosZ;
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.fromBounds((double)(d - (double)entity.width), (double)d2, (double)(d3 - (double)entity.width), (double)(d + (double)entity.width), (double)(d2 + (double)entity.height + 0.2), (double)(d3 + (double)entity.width));
        AxisAlignedBB axisAlignedBB2 = AxisAlignedBB.fromBounds((double)(d - (double)entity.width), (double)d2, (double)(d3 - (double)entity.width), (double)(d + (double)entity.width), (double)(d2 + (double)entity.height + 0.2), (double)(d3 + (double)entity.width));
        if (entity instanceof EntityLivingBase) {
            axisAlignedBB = AxisAlignedBB.fromBounds((double)(d - (double)entity.width + 0.11), (double)(d2 + 0.05), (double)(d3 - (double)entity.width + 0.11), (double)(d + (double)entity.width - 0.11), (double)(d2 + (double)entity.height + -1.75), (double)(d3 + (double)entity.width - 0.11));
            axisAlignedBB2 = AxisAlignedBB.fromBounds((double)(d - (double)entity.width + 0.0), (double)(d2 + 0.05), (double)(d3 - (double)entity.width + 0.0), (double)(d + (double)entity.width - 0.0), (double)(d2 + (double)entity.height + -1.75), (double)(d3 + (double)entity.width - 0.0));
        }
        GL11.glLineWidth((float)1.5f);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        RenderUtil.drawOutlinedBoundingBox(axisAlignedBB);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)1.0f);
        RenderUtil.drawOutlinedBoundingBox(axisAlignedBB2);
    }

    public static /* bridge */ void DrawSolidBlockESP(double d, double d2, double d3, int n) {
        float f = (float)(n >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(n & 0xFF) / 255.0f;
        float f4 = (float)(n >> 24 & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glLineWidth((float)1.0f);
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
        RenderUtil.drawOutlinedBoundingBox(new AxisAlignedBB(d, d2, d3, d + 1.0, d2 + 1.0, d3 + 1.0));
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static /* bridge */ int getScaleFactor() {
        int n = 1;
        boolean bl = Minecraft.getMinecraft().isUnicode();
        int n2 = Minecraft.getMinecraft().gameSettings.guiScale;
        if (n2 == 0) {
            n2 = 1000;
        }
        while (n < n2 && Minecraft.getMinecraft().displayWidth / (n + 1) >= 320 && Minecraft.getMinecraft().displayHeight / (n + 1) >= 240) {
            ++n;
        }
        if (bl && n % 2 != 0 && n != 1) {
            --n;
        }
        return n;
    }

    public static /* bridge */ void doGlScissor1(float f, float f2, float f3, float f4) {
        int n = RenderUtil.getScaleFactor();
        GL11.glScissor((int)((int)(f * (float)n)), (int)((int)((float)Minecraft.getMinecraft().displayHeight - f4 * (float)n)), (int)((int)((f3 - f) * (float)n)), (int)((int)((f4 - f2) * (float)n)));
    }

    public static /* bridge */ void drawBorderedRect(double d, double d2, double d3, double d4, double d5, int n, int n2) {
        double d6 = 0.0;
        if (d5 < 1.0) {
            d6 = 1.0;
        }
        RenderUtil.drawRect(d + d5, d2 + d5, d3 - d5, d4 - d5, n);
        RenderUtil.drawRect(d, d2 + 1.0 - d6, d + d5, d4, n2);
        RenderUtil.drawRect(d, d2, d3 - 1.0 + d6, d2 + d5, n2);
        RenderUtil.drawRect(d3 - d5, d2, d3, d4 - 1.0 + d6, n2);
        RenderUtil.drawRect(d + 1.0 - d6, d4 - d5, d3, d4, n2);
    }

    public static /* bridge */ void pre3D() {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glHint((int)3154, (int)4354);
    }

    public static /* bridge */ void post3D() {
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static /* bridge */ void renderCircle(double d, double d2, double d3, double d4, float f, Color color, float f2) {
        double d5;
        int n;
        GlStateManager.pushMatrix();
        GL11.glLineWidth((float)(f + 1.2f));
        GL11.glBegin((int)1);
        for (n = 0; n <= 90; ++n) {
            d5 = (double)n * 0.13962634015954636;
            GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)f2);
            GL11.glVertex3d((double)(d + d4 * Math.cos(d5)), (double)d2, (double)(d3 + d4 * Math.sin(d5)));
        }
        GL11.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GL11.glLineWidth((float)f);
        GL11.glBegin((int)1);
        for (n = 0; n <= 90; ++n) {
            d5 = (double)n * 0.13962634015954636;
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)f2);
            GL11.glVertex3d((double)(d + d4 * Math.cos(d5)), (double)d2, (double)(d3 + d4 * Math.sin(d5)));
        }
        GL11.glEnd();
        GlStateManager.popMatrix();
    }

    static {
        mc = Minecraft.getMinecraft();
        DEFAULT_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
    }
}

