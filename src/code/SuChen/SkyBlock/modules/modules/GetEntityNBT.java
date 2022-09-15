/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.gui.altlogin.GuiClientButton;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.events.RenderEvent;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class GetEntityNBT
extends Module {
    private GuiClientButton handler;
    private GuiClientButton handler1;
    private GuiClientButton handler2;

    public GetEntityNBT() {
        super("GetEntityNBT", ModuleType.SKYBLOCK);
        this.handler = new GuiClientButton(0);
        this.handler1 = new GuiClientButton(1);
        this.handler2 = new GuiClientButton(2);
    }

    @EventTarget
    public void onRender(RenderEvent renderEvent) {
        Entity entity = GetEntityNBT.mc.objectMouseOver.entityHit;
        if (GetEntityNBT.mc.objectMouseOver != null) {
            if (GetEntityNBT.mc.objectMouseOver.entityHit != null && this.handler2.canExcecute()) {
                if (entity instanceof EntityArmorStand) {
                    GetEntityNBT.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cDEBUG\u00a78]\u00a7r\u00a7r" + ((EntityArmorStand)entity).getEquipmentInSlot(4).serializeNBT().toString()));
                } else {
                    GetEntityNBT.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cDEBUG\u00a78]\u00a7r\u00a7r" + entity));
                    System.out.println(entity.getDisplayName().getFormattedText());
                    System.err.println(entity.getCustomNameTag());
                    System.out.println(entity.getName());
                    System.err.println(mc.getNetHandler().getPlayerInfo(entity.getUniqueID()).getResponseTime());
                    System.out.println(mc.getNetHandler().getPlayerInfo(entity.getUniqueID()));
                    GetEntityNBT.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cDEBUG\u00a78]\u00a7r\u00a7r" + entity.getDisplayName().getFormattedText()));
                    GetEntityNBT.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cDEBUG\u00a78]\u00a7r\u00a7r" + entity.getCustomNameTag()));
                    GetEntityNBT.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cDEBUG\u00a78]\u00a7r\u00a7r" + entity.getName()));
                    GetEntityNBT.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cDEBUG\u00a78]\u00a7r\u00a7r" + mc.getNetHandler().getPlayerInfo(entity.getUniqueID()).getResponseTime()));
                    GetEntityNBT.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cDEBUG\u00a78]\u00a7r\u00a7r" + mc.getNetHandler().getPlayerInfo(entity.getUniqueID())));
                }
            }
        }
    }
}

