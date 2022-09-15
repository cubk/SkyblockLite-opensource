/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package code.SuChen.SkyBlock.util.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RotationHelper {
    public static /* bridge */ float[] getRotations(EntityLivingBase entityLivingBase) {
        double d = entityLivingBase.posX;
        double d2 = entityLivingBase.posZ;
        double d3 = entityLivingBase.posY + (double)(entityLivingBase.getEyeHeight() / 2.0f);
        return RotationHelper.getRotationFromPosition(d, d2, d3);
    }

    public static /* bridge */ float[] getRotation(Entity entity) {
        if (entity == null) {
            return null;
        }
        double d = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
        double d2 = entity.posY + (double)entity.getEyeHeight() * 0.9 - (Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight());
        double d3 = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double d4 = MathHelper.sqrt_double((double)(d * d + d3 * d3));
        float f = (float)(Math.atan2(d3, d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-Math.atan2(d2, d4) * 180.0 / Math.PI);
        float f3 = Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float((float)(f - Minecraft.getMinecraft().thePlayer.rotationYaw));
        float f4 = Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float((float)(f2 - Minecraft.getMinecraft().thePlayer.rotationPitch));
        return new float[]{f3, f4};
    }

    public static /* bridge */ float[] getBowAngles(Entity entity) {
        double d = entity.posX - entity.lastTickPosX;
        double d2 = entity.posZ - entity.lastTickPosZ;
        double d3 = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity);
        d3 -= d3 % 0.8;
        double d4 = 1.0;
        double d5 = 1.0;
        boolean bl = entity.isSprinting();
        d4 = d3 / 0.8 * d * (bl ? 1.25 : 1.0);
        d5 = d3 / 0.8 * d2 * (bl ? 1.25 : 1.0);
        double d6 = entity.posX + d4 - Minecraft.getMinecraft().thePlayer.posX;
        double d7 = entity.posZ + d5 - Minecraft.getMinecraft().thePlayer.posZ;
        double d8 = Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight() - (entity.posY + (double)entity.getEyeHeight());
        double d9 = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity);
        float f = (float)Math.toDegrees(Math.atan2(d7, d6)) - 90.0f;
        float f2 = (float)Math.toDegrees(Math.atan2(d8, d9));
        return new float[]{f, f2};
    }

    public static /* bridge */ float[] getRotationFromPosition(double d, double d2, double d3) {
        double d4 = d - Minecraft.getMinecraft().thePlayer.posX;
        double d5 = d2 - Minecraft.getMinecraft().thePlayer.posZ;
        double d6 = d3 - Minecraft.getMinecraft().thePlayer.posY - 0.6;
        double d7 = MathHelper.sqrt_double((double)(d4 * d4 + d5 * d5));
        float f = (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d6, d7) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

    public static /* bridge */ float getTrajAngleSolutionLow(float f, float f2, float f3) {
        float f4 = 0.006f;
        float f5 = f3 * f3 * f3 * f3 - f4 * (f4 * (f * f) + 2.0f * f2 * (f3 * f3));
        return (float)Math.toDegrees(Math.atan(((double)(f3 * f3) - Math.sqrt(f5)) / (double)(f4 * f)));
    }

    public static /* bridge */ float getYawChange(double d, double d2) {
        double d3 = d - Minecraft.getMinecraft().thePlayer.posX;
        double d4 = d2 - Minecraft.getMinecraft().thePlayer.posZ;
        double d5 = d4 < 0.0 && d3 < 0.0 ? 90.0 + Math.toDegrees(Math.atan(d4 / d3)) : (d4 < 0.0 && d3 > 0.0 ? -90.0 + Math.toDegrees(Math.atan(d4 / d3)) : Math.toDegrees(-Math.atan(d3 / d4)));
        return MathHelper.wrapAngleTo180_float((float)(-(Minecraft.getMinecraft().thePlayer.rotationYaw - (float)d5)));
    }

    public static /* bridge */ float getPitchChange(Entity entity, double d) {
        double d2 = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
        double d3 = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double d4 = d - 2.2 + (double)entity.getEyeHeight() - Minecraft.getMinecraft().thePlayer.posY;
        double d5 = MathHelper.sqrt_double((double)(d2 * d2 + d3 * d3));
        double d6 = -Math.toDegrees(Math.atan(d4 / d5));
        return -MathHelper.wrapAngleTo180_float((float)(Minecraft.getMinecraft().thePlayer.rotationPitch - (float)d6)) - 2.5f;
    }

    public static /* bridge */ float getNewAngle(float f) {
        if ((f %= 360.0f) >= 180.0f) {
            f -= 360.0f;
        }
        if (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }

    public static /* bridge */ float getDistanceBetweenAngles(float f, float f2) {
        float f3 = Math.abs(f - f2) % 360.0f;
        if (f3 > 180.0f) {
            f3 = 360.0f - f3;
        }
        return f3;
    }

    public static /* bridge */ Vec3 getClientLookVec() {
        EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().thePlayer;
        float f = (float)Math.PI / 180;
        float f2 = (float)Math.PI;
        float f3 = MathHelper.cos((float)(-entityPlayerSP.rotationYaw * f - f2));
        float f4 = MathHelper.sin((float)(-entityPlayerSP.rotationYaw * f - f2));
        float f5 = -MathHelper.cos((float)(-entityPlayerSP.rotationPitch * f));
        float f6 = MathHelper.sin((float)(-entityPlayerSP.rotationPitch * f));
        return new Vec3((double)(f4 * f5), (double)f6, (double)(f3 * f5));
    }
}

