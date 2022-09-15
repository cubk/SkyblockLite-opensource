/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.events.LivingEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AutoMelody
extends Module {
    public AutoMelody() {
        super("AutoMelody", ModuleType.SKYBLOCK);
    }

    @EventTarget
    public void onUpdate(LivingEvent livingEvent) {
        if (AutoMelody.mc.currentScreen instanceof GuiChest) {
            Container container = Minecraft.getMinecraft().thePlayer.openContainer;
            ContainerChest containerChest = (ContainerChest)container;
            String string = containerChest.getLowerChestInventory().func_145748_c_().getUnformattedText().toLowerCase();
            String string2 = containerChest.getLowerChestInventory().func_145748_c_().getUnformattedText();
            boolean bl = false;
            for (int i = 0; i < containerChest.getLowerChestInventory().getSizeInventory(); ++i) {
                ItemStack itemStack = containerChest.getLowerChestInventory().getStackInSlot(i);
                if (i < 28 || i > 34 || Item.getIdFromItem((Item)itemStack.getItem()) != 35) continue;
                AutoMelody.mc.playerController.windowClick(containerChest.field_75152_c, i + 9, 0, 0, (EntityPlayer)AutoMelody.mc.thePlayer);
                AutoMelody.mc.playerController.windowClick(containerChest.field_75152_c, -999, 0, 0, (EntityPlayer)AutoMelody.mc.thePlayer);
            }
        }
    }
}

