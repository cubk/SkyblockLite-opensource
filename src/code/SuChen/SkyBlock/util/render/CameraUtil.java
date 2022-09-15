/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 */
package code.SuChen.SkyBlock.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class CameraUtil {
    protected static final Minecraft mc;

    public static /* bridge */ void orientCamera(float f) {
        Entity entity = mc.getRenderViewEntity();
        float f2 = entity.getEyeHeight();
        if (CameraUtil.mc.gameSettings.thirdPersonView > 0) {
            double d = 4.0 + 0.0 * (double)f;
            if (CameraUtil.mc.gameSettings.debugCamEnable) {
                GlStateManager.translate((float)0.0f, (float)0.0f, (float)((float)(-d)));
            } else {
                float f3 = entity.rotationYaw;
                float f4 = entity.rotationPitch;
                if (CameraUtil.mc.gameSettings.thirdPersonView == 2) {
                    f4 += 180.0f;
                }
                if (CameraUtil.mc.gameSettings.thirdPersonView == 2) {
                    GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                }
                GlStateManager.rotate((float)(entity.rotationPitch - f4), (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.rotate((float)(entity.rotationYaw - f3), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.translate((float)0.0f, (float)0.0f, (float)((float)(-d)));
                GlStateManager.rotate((float)(f3 - entity.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.rotate((float)(f4 - entity.rotationPitch), (float)1.0f, (float)0.0f, (float)0.0f);
            }
        } else {
            GlStateManager.translate((float)0.0f, (float)0.0f, (float)-0.1f);
        }
        if (!CameraUtil.mc.gameSettings.debugCamEnable) {
            GlStateManager.rotate((float)(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * f), (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * f + 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GlStateManager.translate((float)0.0f, (float)(-f2), (float)0.0f);
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}

