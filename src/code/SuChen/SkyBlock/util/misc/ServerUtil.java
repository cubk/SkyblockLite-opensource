/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.GuiConnecting
 *  net.minecraft.client.multiplayer.ServerData
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package code.SuChen.SkyBlock.util.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public final class ServerUtil {
    private static Minecraft mc;
    public static ServerData serverData;

    public static /* bridge */ void connectToLastServer() {
        if (serverData == null) {
            return;
        }
        mc.displayGuiScreen((GuiScreen)new GuiConnecting((GuiScreen)new GuiMultiplayer((GuiScreen)new GuiMainMenu()), mc, serverData));
    }

    public static /* bridge */ String getRemoteIp() {
        ServerData serverData;
        String string = "Singleplayer";
        if (ServerUtil.mc.theWorld.isRemote && (serverData = mc.getCurrentServerData()) != null) {
            string = serverData.serverIP;
        }
        return string;
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}

