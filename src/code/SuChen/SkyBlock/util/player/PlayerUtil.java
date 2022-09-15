/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition
 *  net.minecraft.network.play.client.C07PacketPlayerDigging
 *  net.minecraft.network.play.client.C07PacketPlayerDigging$Action
 *  net.minecraft.network.play.client.C08PacketPlayerBlockPlacement
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  org.lwjgl.util.vector.Vector3f
 */
package code.SuChen.SkyBlock.util.player;

import com.google.common.collect.Multimap;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.util.vector.Vector3f;

public class PlayerUtil {
    private static Minecraft mc;

    public static /* bridge */ float[] getRotations(Entity entity) {
        double d = entity.posX;
        double d2 = entity.posZ;
        double d3 = entity.posY + (double)(entity.getEyeHeight() / 4.0f);
        return PlayerUtil.getRotationFromPosition(d, d2, d3);
    }

    private static /* bridge */ float[] getRotationFromPosition(double d, double d2, double d3) {
        double d4 = d - Minecraft.getMinecraft().thePlayer.posX;
        double d5 = d2 - Minecraft.getMinecraft().thePlayer.posZ;
        double d6 = d3 - Minecraft.getMinecraft().thePlayer.posY - 0.6;
        double d7 = MathHelper.sqrt_double((double)(d4 * d4 + d5 * d5));
        float f = (float)(Math.atan2(d5, d4) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d6, d7) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

    public static /* bridge */ double getBaseMovementSpeed() {
        double d = 0.2873;
        if (Minecraft.getMinecraft().thePlayer.func_70644_a(Potion.moveSpeed)) {
            int n = Minecraft.getMinecraft().thePlayer.func_70660_b(Potion.moveSpeed).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public static /* bridge */ float getDirection() {
        float f = Minecraft.getMinecraft().thePlayer.field_70759_as;
        float f2 = Minecraft.getMinecraft().thePlayer.field_70701_bs;
        float f3 = Minecraft.getMinecraft().thePlayer.field_70702_br;
        f += (float)(f2 < 0.0f ? 180 : 0);
        if (f3 < 0.0f) {
            f += (float)(f2 < 0.0f ? -45 : (f2 == 0.0f ? 90 : 45));
        }
        if (f3 > 0.0f) {
            f -= (float)(f2 < 0.0f ? -45 : (f2 == 0.0f ? 90 : 45));
        }
        return f * ((float)Math.PI / 180);
    }

    public static /* bridge */ boolean isInWater() {
        return PlayerUtil.mc.theWorld.getBlockState(new BlockPos(PlayerUtil.mc.thePlayer.posX, PlayerUtil.mc.thePlayer.posY, PlayerUtil.mc.thePlayer.posZ)).getBlock().getMaterial() == Material.water;
    }

    public static /* bridge */ void toFwd(double d) {
        float f = PlayerUtil.mc.thePlayer.rotationYaw * ((float)Math.PI / 180);
        EntityPlayerSP entityPlayerSP = PlayerUtil.mc.thePlayer;
        entityPlayerSP.motionX -= (double)MathHelper.sin((float)f) * d;
        EntityPlayerSP entityPlayerSP2 = PlayerUtil.mc.thePlayer;
        entityPlayerSP2.motionZ += (double)MathHelper.cos((float)f) * d;
    }

    public static /* bridge */ double getSpeed() {
        return Math.sqrt(Minecraft.getMinecraft().thePlayer.motionX * Minecraft.getMinecraft().thePlayer.motionX + Minecraft.getMinecraft().thePlayer.motionZ * Minecraft.getMinecraft().thePlayer.motionZ);
    }

    public static /* bridge */ Block getBlockUnderPlayer(EntityPlayer entityPlayer) {
        return PlayerUtil.getBlock(new BlockPos(entityPlayer.posX, entityPlayer.posY - 1.0, entityPlayer.posZ));
    }

    public static /* bridge */ Block getBlock(BlockPos blockPos) {
        return Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock();
    }

    public static /* bridge */ Block getBlockAtPosC(EntityPlayer entityPlayer, double d, double d2, double d3) {
        return PlayerUtil.getBlock(new BlockPos(entityPlayer.posX - d, entityPlayer.posY - d2, entityPlayer.posZ - d3));
    }

    public static /* bridge */ ArrayList<Vector3f> vanillaTeleportPositions(double d, double d2, double d3, double d4) {
        double d5;
        ArrayList<Vector3f> arrayList = new ArrayList<Vector3f>();
        Minecraft minecraft = Minecraft.getMinecraft();
        double d6 = d - minecraft.thePlayer.posX;
        double d7 = d2 - (minecraft.thePlayer.posY + (double)minecraft.thePlayer.getEyeHeight() + 1.1);
        double d8 = d3 - minecraft.thePlayer.posZ;
        float f = (float)(Math.atan2(d8, d6) * 180.0 / Math.PI - 90.0);
        float f2 = (float)(-Math.atan2(d7, Math.sqrt(d6 * d6 + d8 * d8)) * 180.0 / Math.PI);
        double d9 = minecraft.thePlayer.posX;
        double d10 = minecraft.thePlayer.posY;
        double d11 = minecraft.thePlayer.posZ;
        double d12 = 1.0;
        for (d5 = d4; d5 < PlayerUtil.getDistance(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ, d, d2, d3); d5 += d4) {
            d12 += 1.0;
        }
        for (d5 = d4; d5 < PlayerUtil.getDistance(minecraft.thePlayer.posX, minecraft.thePlayer.posY, minecraft.thePlayer.posZ, d, d2, d3); d5 += d4) {
            d9 = minecraft.thePlayer.posX - Math.sin(PlayerUtil.getDirection(f)) * d5;
            d11 = minecraft.thePlayer.posZ + Math.cos(PlayerUtil.getDirection(f)) * d5;
            arrayList.add(new Vector3f((float)d9, (float)(d10 -= (minecraft.thePlayer.posY - d2) / d12), (float)d11));
        }
        arrayList.add(new Vector3f((float)d, (float)d2, (float)d3));
        return arrayList;
    }

    public static /* bridge */ float getDirection(float f) {
        if (Minecraft.getMinecraft().thePlayer.field_70701_bs < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (Minecraft.getMinecraft().thePlayer.field_70701_bs < 0.0f) {
            f2 = -0.5f;
        } else if (Minecraft.getMinecraft().thePlayer.field_70701_bs > 0.0f) {
            f2 = 0.5f;
        }
        if (Minecraft.getMinecraft().thePlayer.field_70702_br > 0.0f) {
            f -= 90.0f * f2;
        }
        if (Minecraft.getMinecraft().thePlayer.field_70702_br < 0.0f) {
            f += 90.0f * f2;
        }
        return f *= (float)Math.PI / 180;
    }

    public static /* bridge */ double getDistance(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d - d4;
        double d8 = d2 - d5;
        double d9 = d3 - d6;
        return MathHelper.sqrt_double((double)(d7 * d7 + d8 * d8 + d9 * d9));
    }

    public static /* bridge */ boolean MovementInput() {
        return PlayerUtil.mc.gameSettings.keyBindForward.isKeyDown() || PlayerUtil.mc.gameSettings.keyBindLeft.isKeyDown() || PlayerUtil.mc.gameSettings.keyBindRight.isKeyDown() || PlayerUtil.mc.gameSettings.keyBindBack.isKeyDown();
    }

    public static /* bridge */ void blockHit(Entity entity, boolean bl, boolean bl2) {
        Minecraft minecraft = Minecraft.getMinecraft();
        ItemStack itemStack = minecraft.thePlayer.getCurrentEquippedItem();
        if (minecraft.thePlayer.getCurrentEquippedItem() != null && entity != null && bl && itemStack.getItem() instanceof ItemSword && (double)minecraft.thePlayer.field_70733_aJ > 0.2) {
            minecraft.thePlayer.getCurrentEquippedItem().useItemRightClick((World)minecraft.theWorld, (EntityPlayer)minecraft.thePlayer);
        }
        if (minecraft.thePlayer.getCurrentEquippedItem() != null && entity != null && bl2 && itemStack.getItem() instanceof ItemSword && (double)minecraft.thePlayer.field_70733_aJ > 0.2) {
            if (!bl) {
                minecraft.thePlayer.getCurrentEquippedItem().useItemRightClick((World)minecraft.theWorld, (EntityPlayer)minecraft.thePlayer);
            }
            minecraft.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, minecraft.thePlayer.inventory.getCurrentItem(), 0.0f, 0.0f, 0.0f));
            minecraft.getNetHandler().addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        }
    }

    public static /* bridge */ float getItemAtkDamage(ItemStack itemStack) {
        Iterator iterator;
        Multimap multimap = itemStack.getAttributeModifiers();
        if (!multimap.isEmpty() && (iterator = multimap.entries().iterator()).hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            AttributeModifier attributeModifier = (AttributeModifier)entry.getValue();
            double d = attributeModifier.getOperation() != 1 && attributeModifier.getOperation() != 2 ? attributeModifier.getAmount() : attributeModifier.getAmount() * 100.0;
            double d2 = d;
            if (attributeModifier.getAmount() > 1.0) {
                return 1.0f + (float)d;
            }
            return 1.0f;
        }
        return 1.0f;
    }

    public static /* bridge */ int bestWeapon(Entity entity) {
        Minecraft minecraft = Minecraft.getMinecraft();
        minecraft.thePlayer.inventory.currentItem = 0;
        int n = 0;
        int n2 = -1;
        int n3 = 1;
        for (int n4 = 0; n4 < 9; n4 = (int)((byte)(n4 + 1))) {
            minecraft.thePlayer.inventory.currentItem = n4;
            ItemStack itemStack = minecraft.thePlayer.getHeldItem();
            if (itemStack == null) continue;
            int n5 = (int)PlayerUtil.getItemAtkDamage(itemStack);
            if ((n5 = (int)((float)n5 + EnchantmentHelper.getModifierForCreature((ItemStack)itemStack, (EnumCreatureAttribute)EnumCreatureAttribute.UNDEFINED))) <= n3) continue;
            n3 = n5;
            n2 = n4;
        }
        if (n2 != -1) {
            return n2;
        }
        return n;
    }

    public static /* bridge */ void shiftClick(Item item) {
        for (int i = 9; i < 37; ++i) {
            ItemStack itemStack = PlayerUtil.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (itemStack == null || itemStack.getItem() != item) continue;
            PlayerUtil.mc.playerController.windowClick(0, i, 0, 1, (EntityPlayer)PlayerUtil.mc.thePlayer);
            break;
        }
    }

    public static /* bridge */ boolean hotbarIsFull() {
        for (int i = 0; i <= 36; ++i) {
            ItemStack itemStack = PlayerUtil.mc.thePlayer.inventory.getStackInSlot(i);
            if (itemStack != null) continue;
            return false;
        }
        return true;
    }

    public static /* bridge */ Vec3 getLook(float f, float f2) {
        float f3 = MathHelper.cos((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.sin((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = -MathHelper.cos((float)(-f * ((float)Math.PI / 180)));
        float f6 = MathHelper.sin((float)(-f * ((float)Math.PI / 180)));
        return new Vec3((double)(f4 * f5), (double)f6, (double)(f3 * f5));
    }

    public static /* bridge */ void tellPlayer(String string) {
        PlayerUtil.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText(string));
    }

    public static /* bridge */ void setSpeed(double d) {
        PlayerUtil.mc.thePlayer.motionX = -Math.sin(PlayerUtil.getDirection()) * d;
        PlayerUtil.mc.thePlayer.motionZ = Math.cos(PlayerUtil.getDirection()) * d;
    }

    public static /* bridge */ Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    public static /* bridge */ EntityPlayerSP getPlayer() {
        return PlayerUtil.getMinecraft().thePlayer;
    }

    public static /* bridge */ double getIncremental(double d, double d2) {
        double d3 = 1.0 / d2;
        return (double)Math.round(d * d3) / d3;
    }

    public static /* bridge */ boolean isMoving() {
        if (PlayerUtil.mc.gameSettings.keyBindForward.isKeyDown()) {
            return true;
        }
        if (PlayerUtil.mc.gameSettings.keyBindBack.isKeyDown()) {
            return true;
        }
        if (PlayerUtil.mc.gameSettings.keyBindLeft.isKeyDown()) {
            return true;
        }
        if (PlayerUtil.mc.gameSettings.keyBindRight.isKeyDown()) {
            return true;
        }
        if (PlayerUtil.mc.thePlayer.movementInput.moveForward != 0.0f) {
            return true;
        }
        return PlayerUtil.mc.thePlayer.movementInput.moveStrafe != 0.0f;
    }

    public static /* bridge */ boolean isMoving2() {
        return PlayerUtil.mc.thePlayer.field_70701_bs != 0.0f || PlayerUtil.mc.thePlayer.field_70702_br != 0.0f;
    }

    public static /* bridge */ boolean isOnLiquid() {
        AxisAlignedBB axisAlignedBB = PlayerUtil.mc.thePlayer.getEntityBoundingBox();
        if (axisAlignedBB == null) {
            return false;
        }
        axisAlignedBB = axisAlignedBB.contract(0.01, 0.0, 0.01).offset(0.0, -0.01, 0.0);
        boolean bl = false;
        int n = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor_double((double)axisAlignedBB.minX); i < MathHelper.floor_double((double)(axisAlignedBB.maxX + 1.0)); ++i) {
            for (int j = MathHelper.floor_double((double)axisAlignedBB.minZ); j < MathHelper.floor_double((double)(axisAlignedBB.maxZ + 1.0)); ++j) {
                Block block = PlayerUtil.mc.theWorld.getBlockState(new BlockPos(i, n, j)).getBlock();
                if (block == Blocks.air) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                bl = true;
            }
        }
        return bl;
    }

    public static /* bridge */ double roundToPlace(double d, int n) {
        if (n < 0) {
            return d;
        }
        BigDecimal bigDecimal = new BigDecimal(d);
        bigDecimal = bigDecimal.setScale(n, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public static /* bridge */ BlockPos getHypixelBlockpos(String string) {
        int n = 92;
        if (string != null && string.length() > 1) {
            char[] cArray = string.toCharArray();
            int n2 = cArray.length;
            for (int i = 0; i < n2; ++i) {
                n += cArray[i] * string.length() * string.length() + string.charAt(0) + string.charAt(1);
            }
            n /= string.length();
        }
        return new BlockPos(n, -n % 255, n);
    }

    public static /* bridge */ BlockPos GetHypixelBlockpos(String string) {
        int n = 20;
        if (string != null && string.length() > 1) {
            char[] cArray = string.toCharArray();
            int n2 = cArray.length;
            for (int i = 0; i < n2; ++i) {
                n += cArray[i] * string.length() / string.length() - string.charAt(PlayerUtil.random(1.0, 4.0));
            }
            n /= string.length();
        }
        return new BlockPos(n, -n % 255, n);
    }

    public static /* bridge */ int random(double d, double d2) {
        Random random = new Random();
        return (int)(d + random.nextDouble() * (d2 - d));
    }

    public static /* bridge */ void hypixelTeleport(double[] dArray, BlockPos blockPos) {
        double d = dArray[0] - (double)blockPos.func_177958_n() + 0.5;
        double d2 = dArray[1] - (double)blockPos.func_177956_o();
        double d3 = dArray[2] - (double)blockPos.func_177952_p() + 0.5;
        double d4 = Math.sqrt(PlayerUtil.mc.thePlayer.getDistanceSq(blockPos));
        double d5 = 0.31 + (double)(MoveUtil.getSpeedEffect() / 20);
        double d6 = 0.0;
        if (d4 > d5) {
            double d7 = Math.round(d4 / d5 + 0.49999999999) - 1L;
            double d8 = PlayerUtil.mc.thePlayer.posX;
            double d9 = PlayerUtil.mc.thePlayer.posY;
            d6 = PlayerUtil.mc.thePlayer.posZ;
            double d10 = 0.0;
            int n = 1;
            while ((double)n < d7) {
                double d11 = ((double)blockPos.func_177958_n() - PlayerUtil.mc.thePlayer.posX) / d7;
                double d12 = ((double)blockPos.func_177952_p() - PlayerUtil.mc.thePlayer.posZ) / d7;
                double d13 = ((double)blockPos.func_177956_o() - PlayerUtil.mc.thePlayer.posY) / d7;
                d10 += 1.0;
                if (!PlayerUtil.mc.theWorld.getBlockState(new BlockPos(d8 += d11, (d9 += d13) - 1.0, d6 += d12)).getBlock().isBlockNormalCube()) {
                    if (d10 <= 2.0) {
                        d9 += 2.0E-8;
                    } else if (d10 >= 4.0) {
                        d10 = 0.0;
                    }
                }
                C03PacketPlayer.C04PacketPlayerPosition c04PacketPlayerPosition = new C03PacketPlayer.C04PacketPlayerPosition(d8, d9, d6, false);
                PlayerUtil.mc.thePlayer.sendQueue.addToSendQueue((Packet)c04PacketPlayerPosition);
                ++n;
            }
            PlayerUtil.mc.thePlayer.setPosition((double)blockPos.func_177958_n() + 0.5, (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p() + 0.5);
        } else {
            PlayerUtil.mc.thePlayer.setPosition((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p());
        }
    }

    public static /* bridge */ void teleport(double[] dArray, BlockPos blockPos) {
        double d = dArray[0] - (double)blockPos.func_177958_n() + 0.5;
        double d2 = dArray[1] - (double)blockPos.func_177956_o();
        double d3 = dArray[2] - (double)blockPos.func_177952_p() + 0.5;
        double d4 = Math.sqrt(PlayerUtil.mc.thePlayer.getDistanceSq(blockPos));
        double d5 = 5.0;
        double d6 = 0.0;
        if (d4 > d5) {
            double d7 = Math.round(d4 / d5 + 0.49999999999) - 1L;
            double d8 = PlayerUtil.mc.thePlayer.posX;
            double d9 = PlayerUtil.mc.thePlayer.posY;
            d6 = PlayerUtil.mc.thePlayer.posZ;
            double d10 = 0.0;
            int n = 1;
            while ((double)n < d7) {
                double d11 = ((double)blockPos.func_177958_n() - PlayerUtil.mc.thePlayer.posX) / d7;
                double d12 = ((double)blockPos.func_177952_p() - PlayerUtil.mc.thePlayer.posZ) / d7;
                double d13 = ((double)blockPos.func_177956_o() - PlayerUtil.mc.thePlayer.posY) / d7;
                d10 += 1.0;
                C03PacketPlayer.C04PacketPlayerPosition c04PacketPlayerPosition = new C03PacketPlayer.C04PacketPlayerPosition(d8 += d11, d9 += d13, d6 += d12, true);
                PlayerUtil.mc.thePlayer.sendQueue.addToSendQueue((Packet)c04PacketPlayerPosition);
                ++n;
            }
            PlayerUtil.mc.thePlayer.setPosition((double)blockPos.func_177958_n() + 0.5, (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p() + 0.5);
        } else {
            PlayerUtil.mc.thePlayer.setPosition((double)blockPos.func_177958_n(), (double)blockPos.func_177956_o(), (double)blockPos.func_177952_p());
        }
    }

    public static /* bridge */ boolean isInLiquid() {
        AxisAlignedBB axisAlignedBB = PlayerUtil.mc.thePlayer.getEntityBoundingBox();
        if (PlayerUtil.mc.thePlayer == null) {
            return false;
        }
        for (int i = MathHelper.floor_double((double)axisAlignedBB.minX); i < MathHelper.floor_double((double)axisAlignedBB.maxX) + 1; ++i) {
            for (int j = MathHelper.floor_double((double)axisAlignedBB.minZ); j < MathHelper.floor_double((double)axisAlignedBB.maxZ) + 1; ++j) {
                BlockPos blockPos = new BlockPos(i, (int)axisAlignedBB.minY, j);
                Block block = PlayerUtil.mc.theWorld.getBlockState(blockPos).getBlock();
                if (block == null || block instanceof BlockAir) continue;
                return block instanceof BlockLiquid;
            }
        }
        return false;
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}

