/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLadder
 *  net.minecraft.block.BlockVine
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package code.SuChen.SkyBlock.gui.notification;

import Code_By_SuChen.NotificationTypes;
import code.SuChen.SkyBlock.SkyBlockLite;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public enum NotificationManager {
    INSTANCE;

    private static ArrayList<Notification> notifications;

    public static /* bridge */ int reAlpha(int n, float f) {
        Color color = new Color(n);
        float f2 = 0.003921569f * (float)color.getRed();
        float f3 = 0.003921569f * (float)color.getGreen();
        float f4 = 0.003921569f * (float)color.getBlue();
        return new Color(f2, f3, f4, f).getRGB();
    }

    public static /* bridge */ void sendChatMessage(String string, NotificationTypes notificationTypes) {
        if (notificationTypes == NotificationTypes.INFO) {
            Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7c" + SkyBlockLite.CLIENT_name + "\u00a78]\u00a7r\u00a7r" + string));
        } else if (notificationTypes == NotificationTypes.SUCCESS) {
            Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7c" + SkyBlockLite.CLIENT_name + "\u00a78]\u00a7r\u00a7e" + string));
        } else if (notificationTypes == NotificationTypes.ERROR) {
            Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7c" + SkyBlockLite.CLIENT_name + "\u00a78]\u00a7r\u00a74" + string));
        } else if (notificationTypes == NotificationTypes.WARNING) {
            Minecraft.getMinecraft().thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7c" + SkyBlockLite.CLIENT_name + "\u00a78]\u00a7r\u00a76" + string));
        }
    }

    public static /* bridge */ void sendClientMessage(String string, NotificationTypes notificationTypes) {
        notifications.add(new Notification(string, notificationTypes));
    }

    public static /* bridge */ boolean isBlockBetween(BlockPos blockPos, BlockPos blockPos2) {
        int n = blockPos.func_177958_n();
        int n2 = blockPos.func_177956_o();
        int n3 = blockPos.func_177952_p();
        int n4 = blockPos2.func_177958_n();
        int n5 = blockPos2.func_177956_o();
        int n6 = blockPos2.func_177952_p();
        double d = n4 - n;
        double d2 = n5 - n2;
        double d3 = n6 - n3;
        double d4 = n;
        double d5 = n2;
        double d6 = n3;
        double d7 = 0.1;
        int n7 = (int)Math.max(Math.abs(d), Math.max(Math.abs(d2), Math.abs(d3))) * 4;
        for (int i = 0; i < n7 - 1; ++i) {
            BlockPos blockPos3;
            Block block;
            if ((d4 += d / (double)n7) == (double)n4 && (d5 += d2 / (double)n7) == (double)n5 && (d6 += d3 / (double)n7) == (double)n6 || (block = Minecraft.getMinecraft().theWorld.getBlockState(blockPos3 = new BlockPos(d4, d5, d6)).getBlock()).getMaterial() == Material.air || block.getMaterial() == Material.water || block instanceof BlockVine || block instanceof BlockLadder) continue;
            return true;
        }
        return false;
    }

    static {
        notifications = new ArrayList();
    }
}

