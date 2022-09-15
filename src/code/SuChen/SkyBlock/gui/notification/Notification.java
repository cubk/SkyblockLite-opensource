/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package code.SuChen.SkyBlock.gui.notification;

import code.SuChen.SkyBlock.util.timers.TimerUtil;
import code.SuChen.SkyBlock.modules.ModuleManager;
import code.SuChen.SkyBlock.modules.modules.ClickGuiModule;
import code.SuChen.SkyBlock.util.render.Colors;
import Code_By_SuChen.NotificationTypes;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Notification {
    private String message;
    private TimerUtil timer;
    private double lastY;
    private double posY;
    private double width;
    private double height;
    private double animationX;
    private int color;
    private int imageWidth;
    private ResourceLocation image;
    private long stayTime;

    public Notification(String string, NotificationTypes notificationTypes) {
        ClickGuiModule clickGuiModule = (ClickGuiModule) ModuleManager.getModByClass(ClickGuiModule.class);
        this.message = string;
        this.timer = new TimerUtil();
        this.timer.reset();
        this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(string) + 35;
        this.height = 15.0;
        this.animationX = this.width;
        this.stayTime = 1500L;
        this.imageWidth = 11;
        this.posY = -1.0;
        this.image = new ResourceLocation("client/notification/" + notificationTypes.name().toLowerCase() + ".png");
        if (notificationTypes.equals((Object)NotificationTypes.INFO)) {
            this.color = NotificationManager.reAlpha(Colors.getColor(Color.BLUE), 0.5f);
            if (clickGuiModule.Sound.getValueState().booleanValue()) {
                Minecraft.getMinecraft().thePlayer.playSound("random.click", 20.0f, 20.0f);
            }
        } else if (notificationTypes.equals((Object)NotificationTypes.ERROR)) {
            this.color = NotificationManager.reAlpha(Colors.getColor(Color.RED), 0.5f);
            if (clickGuiModule.Sound.getValueState().booleanValue()) {
                Minecraft.getMinecraft().thePlayer.playSound("random.click", 0.5f, 0.8f);
            }
        } else if (notificationTypes.equals((Object)NotificationTypes.SUCCESS)) {
            this.color = NotificationManager.reAlpha(Colors.getColor(Color.GREEN), 0.5f);
            if (clickGuiModule.Sound.getValueState().booleanValue()) {
                Minecraft.getMinecraft().thePlayer.playSound("random.click", 0.5f, 1.0f);
            }
        } else if (notificationTypes.equals((Object)NotificationTypes.WARNING)) {
            this.color = NotificationManager.reAlpha(Colors.getColor(Color.YELLOW), 0.5f);
            if (clickGuiModule.Sound.getValueState().booleanValue()) {
                Minecraft.getMinecraft().thePlayer.playSound("random.orb", 0.5f, 1.0f);
            }
        }
    }
}

