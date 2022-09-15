/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C07PacketPlayerDigging
 *  net.minecraft.network.play.client.C07PacketPlayerDigging$Action
 *  net.minecraft.network.play.client.C09PacketHeldItemChange
 *  net.minecraft.util.BlockPos
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.events.PacketEvent;
import code.SuChen.SkyBlock.events.TickEvent;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;

public class AutoTool
extends Module {
    boolean open;
    public ValueManager<Boolean> SwordCheck;

    public AutoTool() {
        super("AutoTool", ModuleType.PLAYER);
        this.SwordCheck = new ValueManager<Boolean>("AutoTool_SwordCheck", true);
    }

    @EventTarget
    public void handle(TickEvent tickEvent) {
        if (this.open && AutoTool.mc.thePlayer.getHeldItem() == null) {
            this.open = false;
        }
        this.open = AutoTool.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword;
    }

    @EventTarget
    public void handle(PacketEvent packetEvent) {
        if (!(packetEvent.getPacket() instanceof C07PacketPlayerDigging)) {
            return;
        }
        C07PacketPlayerDigging c07PacketPlayerDigging = (C07PacketPlayerDigging)packetEvent.getPacket();
        if (c07PacketPlayerDigging.getStatus() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            AutoTool.autotool(c07PacketPlayerDigging.getPosition());
        }
    }

    private static /* bridge */ void autotool(BlockPos blockPos) {
        Block block = Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock();
        int n = AutoTool.getStrongestItem(block);
        if (n < 0) {
            return;
        }
        float f = AutoTool.getStrengthAgainstBlock(block, Minecraft.getMinecraft().thePlayer.inventory.mainInventory[n]);
        if (Minecraft.getMinecraft().thePlayer.getHeldItem() != null && AutoTool.getStrengthAgainstBlock(block, Minecraft.getMinecraft().thePlayer.getHeldItem()) >= f) {
            return;
        }
        Minecraft.getMinecraft();
        Minecraft.getMinecraft().thePlayer.inventory.currentItem = n;
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue((Packet)new C09PacketHeldItemChange(n));
    }

    public static /* bridge */ void toggledSword() {
        ItemSword itemSword = null;
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i];
            if (itemStack == null || itemStack.getItem() == null || !(itemStack.getItem() instanceof ItemSword)) continue;
            ItemSword itemSword2 = (ItemSword)itemStack.getItem();
            if (itemSword == null) {
                itemSword = itemSword2;
                Minecraft.getMinecraft().thePlayer.inventory.currentItem = i;
                continue;
            }
            if (itemSword.getDamageVsEntity() > itemSword2.getDamageVsEntity()) continue;
            itemSword = itemSword2;
            Minecraft.getMinecraft().thePlayer.inventory.currentItem = i;
        }
    }

    private static /* bridge */ int getStrongestItem(Block block) {
        float f = Float.NEGATIVE_INFINITY;
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            float f2;
            ItemStack itemStack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[i];
            if (itemStack == null || itemStack.getItem() == null) continue;
            float f3 = AutoTool.getStrengthAgainstBlock(block, itemStack);
            if (!(f2 > f) || f3 == 1.0f) continue;
            n = i;
            f = f3;
        }
        return n;
    }

    public static /* bridge */ float getStrengthAgainstBlock(Block block, ItemStack itemStack) {
        float f = itemStack.getStrVsBlock(block);
        int n = Block.getIdFromBlock((Block)block);
        if (itemStack.getUnlocalizedName().contains("Stone") && (n == 14 || n == 129 || n == 56 || n == 74 || n == 73)) {
            return 5.0f;
        }
        if (!EnchantmentHelper.getEnchantments((ItemStack)itemStack).containsKey(Enchantment.efficiency.effectId) || f == 1.0f) {
            return f;
        }
        int n2 = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.efficiency.effectId, (ItemStack)itemStack);
        return f + (float)(n2 * n2 + 1);
    }
}

