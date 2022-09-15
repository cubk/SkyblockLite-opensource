/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.MathHelper
 */
package code.SuChen.SkyBlock.util.player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;

public final class MoveUtil {
    private static Minecraft mc;

    public static /* bridge */ int randomNumber(int n, int n2) {
        return (int)(Math.random() * (double)(n - n2)) + n2;
    }

    public static /* bridge */ double roundToPlace(double d, int n) {
        if (n < 0) {
            return d;
        }
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static /* bridge */ double getIncremental(double d, double d2) {
        double d3 = 1.0 / d2;
        return (double)Math.round(d * d3) / d3;
    }

    public static /* bridge */ boolean isInteger(Double d) {
        if (d != Math.floor(d)) {
            return false;
        }
        return !Double.isInfinite(d);
    }

    public static /* bridge */ int getJumpEffect() {
        if (MoveUtil.mc.thePlayer.func_70644_a(Potion.jump)) {
            return MoveUtil.mc.thePlayer.func_70660_b(Potion.jump).getAmplifier() + 1;
        }
        return 0;
    }

    public static /* bridge */ int getSpeedEffect() {
        if (MoveUtil.mc.thePlayer.func_70644_a(Potion.moveSpeed)) {
            return MoveUtil.mc.thePlayer.func_70660_b(Potion.moveSpeed).getAmplifier() + 1;
        }
        return 0;
    }

    public static /* bridge */ boolean isOnGround(double d) {
        return !MoveUtil.mc.theWorld.getCollidingBoundingBoxes((Entity)MoveUtil.mc.thePlayer, MoveUtil.mc.thePlayer.getEntityBoundingBox().offset(0.0, -d, 0.0)).isEmpty();
    }

    public static /* bridge */ void setMotion(double d) {
        double d2 = MoveUtil.mc.thePlayer.movementInput.moveForward;
        double d3 = MoveUtil.mc.thePlayer.movementInput.moveStrafe;
        float f = MoveUtil.mc.thePlayer.rotationYaw;
        if (d2 == 0.0 && d3 == 0.0) {
            MoveUtil.mc.thePlayer.motionX = 0.0;
            MoveUtil.mc.thePlayer.motionZ = 0.0;
        } else {
            if (d2 != 0.0) {
                if (d3 > 0.0) {
                    f += (float)(d2 > 0.0 ? -45 : 45);
                } else if (d3 < 0.0) {
                    f += (float)(d2 > 0.0 ? 45 : -45);
                }
                d3 = 0.0;
                if (d2 > 0.0) {
                    d2 = 1.0;
                } else if (d2 < 0.0) {
                    d2 = -1.0;
                }
            }
            MoveUtil.mc.thePlayer.motionX = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
            MoveUtil.mc.thePlayer.motionZ = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
        }
    }

    public static /* bridge */ double defaultSpeed() {
        double d = 0.2873;
        if (Minecraft.getMinecraft().thePlayer.func_70644_a(Potion.moveSpeed)) {
            int n = Minecraft.getMinecraft().thePlayer.func_70660_b(Potion.moveSpeed).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public static /* bridge */ boolean isCollidedH(double d) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(MoveUtil.mc.thePlayer.posX - 0.3, MoveUtil.mc.thePlayer.posY + 2.0, MoveUtil.mc.thePlayer.posZ + 0.3, MoveUtil.mc.thePlayer.posX + 0.3, MoveUtil.mc.thePlayer.posY + 3.0, MoveUtil.mc.thePlayer.posZ - 0.3);
        if (!MoveUtil.mc.theWorld.getCollidingBoundingBoxes((Entity)MoveUtil.mc.thePlayer, axisAlignedBB.offset(0.3 + d, 0.0, 0.0)).isEmpty()) {
            return true;
        }
        if (!MoveUtil.mc.theWorld.getCollidingBoundingBoxes((Entity)MoveUtil.mc.thePlayer, axisAlignedBB.offset(-0.3 - d, 0.0, 0.0)).isEmpty()) {
            return true;
        }
        if (!MoveUtil.mc.theWorld.getCollidingBoundingBoxes((Entity)MoveUtil.mc.thePlayer, axisAlignedBB.offset(0.0, 0.0, 0.3 + d)).isEmpty()) {
            return true;
        }
        return !MoveUtil.mc.theWorld.getCollidingBoundingBoxes((Entity)MoveUtil.mc.thePlayer, axisAlignedBB.offset(0.0, 0.0, -0.3 - d)).isEmpty();
    }

    public static /* bridge */ float[] getRotationsBlock(BlockPos blockPos, EnumFacing enumFacing) {
        double d = (double)blockPos.func_177958_n() + 0.5 - MoveUtil.mc.thePlayer.posX + (double)enumFacing.getFrontOffsetX() / 2.0;
        double d2 = (double)blockPos.func_177952_p() + 0.5 - MoveUtil.mc.thePlayer.posZ + (double)enumFacing.getFrontOffsetZ() / 2.0;
        double d3 = (double)blockPos.func_177956_o() + 0.5;
        double d4 = MoveUtil.mc.thePlayer.posY + (double)MoveUtil.mc.thePlayer.getEyeHeight() - d3;
        double d5 = MathHelper.sqrt_double((double)(d * d + d2 * d2));
        float f = (float)(Math.atan2(d2, d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(Math.atan2(d4, d5) * 180.0 / Math.PI);
        if (f < 0.0f) {
            f += 360.0f;
        }
        return new float[]{f, f2};
    }

    public static /* bridge */ float[] constrainAngle(float[] fArray) {
        fArray[0] = fArray[0] % 360.0f;
        fArray[1] = fArray[1] % 360.0f;
        while (fArray[0] <= -180.0f) {
            fArray[0] = fArray[0] + 360.0f;
        }
        while (fArray[1] <= -180.0f) {
            fArray[1] = fArray[1] + 360.0f;
        }
        while (fArray[0] > 180.0f) {
            fArray[0] = fArray[0] - 360.0f;
        }
        while (fArray[1] > 180.0f) {
            fArray[1] = fArray[1] - 360.0f;
        }
        return fArray;
    }

    public static /* bridge */ double getBaseMovementSpeed() {
        double d = 0.2873;
        if (MoveUtil.mc.thePlayer.func_70644_a(Potion.moveSpeed)) {
            int n = MoveUtil.mc.thePlayer.func_70660_b(Potion.moveSpeed).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public static /* bridge */ double getBaseMoveSpeed() {
        double d = 0.272;
        if (MoveUtil.mc.thePlayer.func_70644_a(Potion.moveSpeed)) {
            int n = MoveUtil.mc.thePlayer.func_70660_b(Potion.moveSpeed).getAmplifier();
            d *= 1.0 + 0.2 * (double)n;
        }
        return d;
    }

    public static /* bridge */ double getRandomInRange(double d, double d2) {
        double d3;
        Random random = new Random();
        double d4 = d2 - d;
        double d5 = random.nextDouble() * d4;
        if (d5 > d2) {
            d5 = d2;
        }
        if ((d3 = d5 + d) > d2) {
            d3 = d2;
        }
        return d3;
    }

    public static /* bridge */ double round(double d, int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}

