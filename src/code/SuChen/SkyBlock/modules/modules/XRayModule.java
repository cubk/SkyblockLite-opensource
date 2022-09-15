/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.util.BlockPos
 */
package code.SuChen.SkyBlock.modules.modules;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;

public class XRayModule {
    public static Minecraft mc;
    public static ArrayList xrayBlocks;
    public static boolean UHCBypass;
    public static boolean BlockEffect;
    public static boolean xray;
    public static HashSet blockIDs;
    public static Boolean bypass;
    public static int opacity;
    public static List<Block> dimblock;
    public static ArrayList<BlockPos> glow;

    protected static /* bridge */ List<Entity> getLoadedEntities() {
        Minecraft.getMinecraft();
        return XRayModule.mc.theWorld.loadedEntityList;
    }

    public static /* bridge */ boolean hasArmor(EntityPlayer entityPlayer) {
        if (entityPlayer.inventory == null) {
            return false;
        }
        ItemStack itemStack = entityPlayer.inventory.armorInventory[0];
        ItemStack itemStack2 = entityPlayer.inventory.armorInventory[1];
        ItemStack itemStack3 = entityPlayer.inventory.armorInventory[2];
        ItemStack itemStack4 = entityPlayer.inventory.armorInventory[3];
        return itemStack != null || itemStack2 != null || itemStack3 != null || itemStack4 != null;
    }

    public static /* bridge */ Minecraft mc() {
        return Minecraft.getMinecraft();
    }

    public static /* bridge */ void sendPacket(Packet packet) {
        XRayModule.mc().getNetHandler().addToSendQueue(packet);
    }

    public static /* bridge */ boolean onServer(String string) {
        return !mc.isSingleplayer() && XRayModule.mc.getCurrentServerData().serverIP.toLowerCase().contains(string);
    }

    public static /* bridge */ boolean containsID(int n) {
        return blockIDs.contains(n);
    }

    static {
        mc = Minecraft.getMinecraft();
        xrayBlocks = Lists.newArrayList((Object[])new Block[]{Blocks.flowing_lava, Blocks.lava, Blocks.flowing_water, Blocks.water, Blocks.gold_ore, Blocks.iron_ore, Blocks.coal_ore, Blocks.lapis_ore, Blocks.gold_block, Blocks.iron_block, Blocks.tnt, Blocks.mossy_cobblestone, Blocks.mob_spawner, Blocks.diamond_ore, Blocks.diamond_block, Blocks.furnace, Blocks.lit_furnace, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.redstone_block, Blocks.jukebox, Blocks.glowstone, Blocks.melon_block, Blocks.enchanting_table, Blocks.brewing_stand, Blocks.cauldron, Blocks.end_portal_frame, Blocks.emerald_ore, Blocks.emerald_block, Blocks.command_block, Blocks.anvil, Blocks.quartz_ore, Blocks.hopper});
        BlockEffect = false;
        xray = false;
        blockIDs = new HashSet();
        bypass = true;
        dimblock = new ArrayList<Block>();
        glow = new ArrayList();
    }
}

