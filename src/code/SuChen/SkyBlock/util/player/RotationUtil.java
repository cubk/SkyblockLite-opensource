/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityEgg
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package code.SuChen.SkyBlock.util.player;

import code.SuChen.SkyBlock.events.PreUpdateEvent;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class RotationUtil {
    public static Minecraft mc;

    public static /* bridge */ float[] getRotations(Entity entity) {
        double d = Minecraft.getMinecraft().thePlayer.posX;
        double d2 = Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight();
        double d3 = Minecraft.getMinecraft().thePlayer.posZ;
        double d4 = entity.posX;
        double d5 = entity.posY + (double)(entity.height / 2.0f);
        double d6 = entity.posZ;
        double d7 = d - d4;
        double d8 = d2 - d5;
        double d9 = d3 - d6;
        double d10 = Math.sqrt(Math.pow(d7, 2.0) + Math.pow(d9, 2.0));
        double d11 = Math.toDegrees(Math.atan2(d9, d7)) + 90.0;
        double d12 = Math.toDegrees(Math.atan2(d10, d8));
        return new float[]{(float)d11, (float)(90.0 - d12)};
    }

    public static /* bridge */ float changeRotation(float f, float f2, float f3) {
        float f4 = MathHelper.wrapAngleTo180_float((float)(f2 - f));
        if (f4 > f3) {
            f4 = f3;
        }
        if (f4 < -f3) {
            f4 = -f3;
        }
        return f + f4;
    }

    private static /* bridge */ float[] getDirectionToEntity(Entity entity) {
        return new float[]{RotationUtil.getYaw(entity) + RotationUtil.mc.thePlayer.rotationYaw, RotationUtil.getPitch(entity) + RotationUtil.mc.thePlayer.rotationPitch};
    }

    public static /* bridge */ float[] getDirectionToBlock(double d, double d2, double d3, EnumFacing enumFacing) {
        EntityEgg entityEgg = new EntityEgg((World)RotationUtil.mc.theWorld);
        entityEgg.posX = d + 0.5;
        entityEgg.posY = d2 + 0.5;
        entityEgg.posZ = d3 + 0.5;
        entityEgg.posX += (double)enumFacing.getDirectionVec().getX() * 0.25;
        entityEgg.posY += (double)enumFacing.getDirectionVec().getY() * 0.25;
        entityEgg.posZ += (double)enumFacing.getDirectionVec().getZ() * 0.25;
        return RotationUtil.getDirectionToEntity((Entity)entityEgg);
    }

    public static /* bridge */ float[] getRotationNeededForBlock(EntityPlayer entityPlayer, BlockPos blockPos) {
        double d = (double)blockPos.func_177958_n() - entityPlayer.posX;
        double d2 = (double)blockPos.func_177956_o() + 0.5 - (entityPlayer.posY + (double)entityPlayer.getEyeHeight());
        double d3 = (double)blockPos.func_177952_p() - entityPlayer.posZ;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)(Math.atan2(d3, d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-Math.atan2(d2, d4) * 180.0 / Math.PI);
        return new float[]{f, f2};
    }

    public static /* bridge */ float getYaw(Entity entity) {
        double d = entity.posX - RotationUtil.mc.thePlayer.posX;
        double d2 = entity.posZ - RotationUtil.mc.thePlayer.posZ;
        double d3 = d2 < 0.0 && d < 0.0 ? 90.0 + Math.toDegrees(Math.atan(d2 / d)) : (d2 < 0.0 && d > 0.0 ? -90.0 + Math.toDegrees(Math.atan(d2 / d)) : Math.toDegrees(-Math.atan(d / d2)));
        return MathHelper.wrapAngleTo180_float((float)(-RotationUtil.mc.thePlayer.rotationYaw - (float)d3));
    }

    public static /* bridge */ float getPitch(Entity entity) {
        double d = entity.posX - RotationUtil.mc.thePlayer.posX;
        double d2 = entity.posZ - RotationUtil.mc.thePlayer.posZ;
        double d3 = entity.posY - 1.6 + (double)entity.getEyeHeight() - RotationUtil.mc.thePlayer.posY;
        double d4 = MathHelper.sqrt_double((double)(d * d + d2 * d2));
        double d5 = -Math.toDegrees(Math.atan(d3 / d4));
        return -MathHelper.wrapAngleTo180_float((float)(RotationUtil.mc.thePlayer.rotationPitch - (float)d5));
    }

    public static /* bridge */ float[] getRotationFromPosition(double d, double d2, double d3) {
        double d4 = d - Minecraft.getMinecraft().thePlayer.posX;
        double d5 = d3 - Minecraft.getMinecraft().thePlayer.posZ;
        double d6 = d2 - Minecraft.getMinecraft().thePlayer.posY - (double)Minecraft.getMinecraft().thePlayer.getEyeHeight();
        double d7 = MathHelper.sqrt_double((double)(d4 * d4 + d5 * d5));
        float f = (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-Math.atan2(d6, d7) * 180.0 / Math.PI);
        return new float[]{f, f2};
    }

    public static /* bridge */ float[] getRotationsNeededBlock(double d, double d2, double d3) {
        double d4 = d + 0.5 - Minecraft.getMinecraft().thePlayer.posX;
        double d5 = d3 + 0.5 - Minecraft.getMinecraft().thePlayer.posZ;
        double d6 = d2 + 0.5 - (Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight());
        double d7 = MathHelper.sqrt_double((double)(d4 * d4 + d5 * d5));
        float f = (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-Math.atan2(d6, d7) * 180.0 / Math.PI);
        return new float[]{Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float((float)(f - Minecraft.getMinecraft().thePlayer.rotationYaw)), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float((float)(f2 - Minecraft.getMinecraft().thePlayer.rotationPitch))};
    }

    public static /* bridge */ float[] getIntaveRots(BlockPos blockPos, EnumFacing enumFacing) {
        double d = (double)blockPos.func_177958_n() + 0.5;
        double d2 = (double)blockPos.func_177956_o() + 0.5;
        double d3 = (double)blockPos.func_177952_p() + 0.5;
        if (enumFacing != null) {
            if (EnumFacing.UP != null) {
                d2 += 0.5;
            } else if (EnumFacing.DOWN != null) {
                d2 -= 0.5;
            } else if (EnumFacing.WEST != null) {
                d += 0.5;
            } else if (EnumFacing.EAST != null) {
                d -= 0.5;
            } else if (EnumFacing.NORTH != null) {
                d3 += 0.5;
            } else if (EnumFacing.SOUTH != null) {
                d3 -= 0.5;
            }
        }
        double d4 = d - RotationUtil.mc.thePlayer.posX;
        double d5 = d2 - RotationUtil.mc.thePlayer.posY + (double)RotationUtil.mc.thePlayer.getEyeHeight();
        double d6 = d3 - RotationUtil.mc.thePlayer.posZ;
        float f = (float)(Math.atan2(d6, d4) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-Math.atan2(d5, Math.sqrt(d4 * d4 + d6 * d6)) * 180.0 / Math.PI);
        f = MathHelper.wrapAngleTo180_float((float)f);
        f2 = MathHelper.wrapAngleTo180_float((float)f2);
        return new float[]{f, f2};
    }

    public static /* bridge */ float[] getHypixelRotationsNeededBlock(double d, double d2, double d3) {
        double d4 = d + 0.5 - Minecraft.getMinecraft().thePlayer.posX;
        double d5 = d3 + 0.5 - Minecraft.getMinecraft().thePlayer.posZ;
        double d6 = d2 + 0.5 - (Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight());
        double d7 = MathHelper.sqrt_double((double)(d4 * d4 + d5 * d5));
        float f = (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-Math.atan2(d6, d7) * 180.0 / Math.PI);
        return new float[]{Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float((float)(f - (float)(120 + new Random().nextInt(2)))), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float((float)(f2 - Minecraft.getMinecraft().thePlayer.rotationPitch))};
    }

    public static /* bridge */ float[] getRotationsNeededBlock(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d4 + 0.5 - d;
        double d8 = d6 + 0.5 - d3;
        double d9 = d5 + 0.5 - (d2 + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight());
        double d10 = MathHelper.sqrt_double((double)(d7 * d7 + d8 * d8));
        float f = (float)(Math.atan2(d8, d7) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-Math.atan2(d9, d10) * 180.0 / Math.PI);
        return new float[]{f, f2};
    }

    public static /* bridge */ float getTrajAngleSolutionLow(float f, float f2, float f3) {
        float f4 = 0.006f;
        float f5 = f3 * f3 * f3 * f3 - f4 * (f4 * (f * f) + 2.0f * f2 * (f3 * f3));
        return (float)Math.toDegrees(Math.atan(((double)(f3 * f3) - Math.sqrt(f5)) / (double)(f4 * f)));
    }

    public static /* bridge */ float getNewAngle(float f) {
        float f2;
        f %= 360.0f;
        if (f2 >= 180.0f) {
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

    public static /* bridge */ Vec3[] getCorners(AxisAlignedBB axisAlignedBB) {
        return new Vec3[]{new Vec3(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ), new Vec3(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ), new Vec3(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ), new Vec3(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ), new Vec3(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ), new Vec3(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ), new Vec3(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ), new Vec3(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ)};
    }

    public static /* bridge */ AxisAlignedBB getCloserBox(AxisAlignedBB axisAlignedBB, AxisAlignedBB axisAlignedBB2) {
        for (Vec3 vec3 : RotationUtil.getCorners(axisAlignedBB2)) {
            if (!RotationUtil.isRotationIn(RotationUtil.getRotationFromPosition(vec3.xCoord, vec3.yCoord, vec3.zCoord), axisAlignedBB)) continue;
            return Minecraft.getMinecraft().thePlayer.getDistance((axisAlignedBB2.minX + axisAlignedBB2.maxX) / 2.0, (axisAlignedBB2.minY + axisAlignedBB2.maxY) / 2.0, (axisAlignedBB2.minZ + axisAlignedBB2.maxZ) / 2.0) < Minecraft.getMinecraft().thePlayer.getDistance((axisAlignedBB.minX + axisAlignedBB.maxX) / 2.0, (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.0, (axisAlignedBB.minZ + axisAlignedBB.maxZ) / 2.0) ? axisAlignedBB2 : axisAlignedBB;
        }
        return axisAlignedBB2;
    }

    public static /* bridge */ boolean isRotationIn(float[] fArray, AxisAlignedBB axisAlignedBB) {
        float[] fArray2 = RotationUtil.getMaxRotations(axisAlignedBB);
        return fArray2[0] < fArray[0] && fArray2[2] < fArray[1] && fArray2[1] > fArray[0] && fArray2[3] > fArray[1];
    }

    public static /* bridge */ float[] getRandomRotationsInBox(AxisAlignedBB axisAlignedBB) {
        float[] fArray = RotationUtil.getMaxRotations(axisAlignedBB);
        float f = (float)MathHelper.getRandomDoubleInRange((Random)new Random(), (double)fArray[0], (double)fArray[1]);
        float f2 = (float)MathHelper.getRandomDoubleInRange((Random)new Random(), (double)fArray[2], (double)fArray[3]);
        return new float[]{f, f2};
    }

    public static /* bridge */ float[] getMaxRotations(AxisAlignedBB axisAlignedBB) {
        float f = 2.14748365E9f;
        float f2 = -2.14748365E9f;
        float f3 = 2.14748365E9f;
        float f4 = -2.14748365E9f;
        for (Vec3 vec3 : RotationUtil.getCorners(axisAlignedBB)) {
            float[] fArray = RotationUtil.getRotationFromPosition(vec3.xCoord, vec3.yCoord, vec3.zCoord);
            if (fArray[0] < f) {
                f = fArray[0];
            }
            if (fArray[0] > f2) {
                f2 = fArray[0];
            }
            if (fArray[1] < f3) {
                f3 = fArray[1];
            }
            if (!(fArray[1] > f4)) continue;
            f4 = fArray[1];
        }
        return new float[]{f, f2, f3, f4};
    }

    public static /* bridge */ AxisAlignedBB expandBox(AxisAlignedBB axisAlignedBB, double d) {
        d = 1.0 - d / 100.0;
        return axisAlignedBB.expand((axisAlignedBB.maxX - axisAlignedBB.minX) * d, 0.12, (axisAlignedBB.maxZ - axisAlignedBB.minZ) * d);
    }

    public static /* bridge */ AxisAlignedBB contractBox(AxisAlignedBB axisAlignedBB, double d) {
        d = 1.0 - d / 100.0;
        return axisAlignedBB.contract((axisAlignedBB.maxX - axisAlignedBB.minX) * d, 0.12, (axisAlignedBB.maxZ - axisAlignedBB.minZ) * d);
    }

    public static /* bridge */ float getYawDifference(float f, float f2) {
        float f3;
        return f3 + ((f3 = (f2 + 180.0f - f) % 360.0f) > 0.0f ? -180.0f : 180.0f);
    }

    public static /* bridge */ float getPitchDifference(float f, float f2) {
        float f3;
        return f3 + ((f3 = (f2 + 90.0f - f) % 180.0f) > 0.0f ? -90.0f : 90.0f);
    }

    public static /* bridge */ float[] getRotations(Object object) {
        Entity entity = (Entity)object;
        double d = Minecraft.getMinecraft().thePlayer.posX;
        double d2 = Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight();
        double d3 = Minecraft.getMinecraft().thePlayer.posZ;
        double d4 = entity.posX;
        double d5 = entity.posY + (double)(entity.height / 2.0f);
        double d6 = entity.posZ;
        double d7 = d - d4;
        double d8 = d2 - d5;
        double d9 = d3 - d6;
        double d10 = Math.sqrt(Math.pow(d7, 2.0) + Math.pow(d9, 2.0));
        double d11 = Math.toDegrees(Math.atan2(d9, d7)) + 90.0;
        double d12 = Math.toDegrees(Math.atan2(d10, d8));
        return new float[]{(float)d11, (float)(90.0 - d12)};
    }

    public static /* bridge */ float[] getRotations(Entity entity, double d) {
        double d2;
        AxisAlignedBB axisAlignedBB = RotationUtil.mc.thePlayer.getEntityBoundingBox();
        if (entity == null) {
            System.out.println("Fuck you ! Entity is nul!");
            return null;
        }
        double d3 = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
        double d4 = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        PreUpdateEvent preUpdateEvent = new PreUpdateEvent(entity.posX, entity.posY, entity.posZ);
        PreUpdateEvent preUpdateEvent2 = new PreUpdateEvent(RotationUtil.mc.thePlayer.posX, RotationUtil.mc.thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight(), RotationUtil.mc.thePlayer.posZ);
        for (d2 = axisAlignedBB.minY + 0.7; d2 < axisAlignedBB.maxY - 0.1; d2 += 0.1) {
            PreUpdateEvent preUpdateEvent3 = new PreUpdateEvent(entity.posX, d2, entity.posZ);
            if (!(preUpdateEvent2.distanceTo(preUpdateEvent3) < preUpdateEvent2.distanceTo(preUpdateEvent))) continue;
            preUpdateEvent = new PreUpdateEvent(entity.posX, d2, entity.posZ);
        }
        if (preUpdateEvent2.distanceTo(preUpdateEvent) > d) {
            return null;
        }
        d2 = preUpdateEvent.getY() - (RotationUtil.mc.thePlayer.posY + (double)RotationUtil.mc.thePlayer.getEyeHeight());
        double d5 = MathHelper.sqrt_double((double)(d3 * d3 + d4 * d4));
        float f = (float)(Math.atan2(d4, d3) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d2, d5) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

    public static /* bridge */ float[] getRotations(EntityLivingBase entityLivingBase) {
        double d = entityLivingBase.posX - Minecraft.getMinecraft().thePlayer.posX;
        double d2 = entityLivingBase.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double d3 = entityLivingBase.posY - Minecraft.getMinecraft().thePlayer.posY - 0.5;
        float f = (float)(-(Math.atan2(d, d2) * 60.0));
        float f2 = (float)(-(Math.asin(d3 /= (double)Minecraft.getMinecraft().thePlayer.getDistanceToEntity((Entity)entityLivingBase)) * 60.0));
        return new float[]{f, f2};
    }

    public static /* bridge */ float[] getRotationsNeeded(EntityLivingBase entityLivingBase) {
        double d;
        AxisAlignedBB axisAlignedBB = RotationUtil.mc.thePlayer.getEntityBoundingBox();
        double d2 = entityLivingBase.posX - Minecraft.getMinecraft().thePlayer.posX;
        double d3 = entityLivingBase.posY - Minecraft.getMinecraft().thePlayer.posY;
        double d4 = entityLivingBase.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double d5 = entityLivingBase.posX - Minecraft.getMinecraft().thePlayer.posX;
        if (entityLivingBase instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase2 = entityLivingBase;
            d = entityLivingBase2.posY + (double)entityLivingBase2.getEyeHeight() * 0.55 - (Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight());
        } else {
            d = (axisAlignedBB.minY + axisAlignedBB.maxY) / 2.25 - (Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight());
        }
        double d6 = entityLivingBase.posX - RotationUtil.mc.thePlayer.posX;
        double d7 = entityLivingBase.posZ - RotationUtil.mc.thePlayer.posZ;
        double d8 = RotationUtil.mc.thePlayer.posY - (double)entityLivingBase.getEyeHeight() + (entityLivingBase.posY - (double)entityLivingBase.getEyeHeight());
        double d9 = Math.sqrt(d6 * d6 + d7 * d7);
        double d10 = entityLivingBase.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double d11 = MathHelper.sqrt_double((double)(d2 * d2 + d3 * d3 + d4 * d4));
        float f = (float)(Math.atan2(d7, d6) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d, d11) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}

