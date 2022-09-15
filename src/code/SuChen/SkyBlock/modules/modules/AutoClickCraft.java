/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C02PacketUseEntity
 *  net.minecraft.network.play.client.C02PacketUseEntity$Action
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.util.timers.TimerHelper;
import code.SuChen.SkyBlock.events.UpdateEvent;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class AutoClickCraft
extends Module {
    public static ValueManager<String> mode;
    private TimerHelper menu;
    private TimerHelper click;
    private TimerHelper Shop;
    private TimerHelper CraftTimer;
    private boolean openshop;
    private boolean Craft;
    private boolean IceCraft;
    private String[] item;

    public AutoClickCraft() {
        super("AutoClickCraft", ModuleType.SKYBLOCK);
        this.menu = new TimerHelper();
        this.click = new TimerHelper();
        this.Shop = new TimerHelper();
        this.CraftTimer = new TimerHelper();
        this.openshop = false;
        this.Craft = false;
        this.IceCraft = false;
        this.item = new String[]{"End Stone", "Obsidian", "Ice"};
        AutoClickCraft.mode.mode.add("End_Stone");
        AutoClickCraft.mode.mode.add("Obsidian");
        AutoClickCraft.mode.mode.add("Ice");
    }

    @EventTarget
    public void onUpdate(UpdateEvent updateEvent) {
        ItemStack itemStack;
        int n;
        int[] nArray;
        String string;
        ContainerChest containerChest;
        Object object2;
        boolean bl = true;
        ItemStack[] itemStackArray = AutoClickCraft.mc.thePlayer.inventory.mainInventory;
        int n2 = AutoClickCraft.mc.thePlayer.inventory.mainInventory.length;
        for (int i = 0; i < n2; ++i) {
            object2 = itemStackArray[i];
            if (object2 != null) continue;
            bl = false;
            break;
        }
        if (this.getEndICE() > 160) {
            this.IceCraft = true;
        }
        if (bl && this.getBlockSton() > 160) {
            this.Craft = true;
        }
        if (this.Craft) {
            if (this.menu.delay(1000.0f) && !(AutoClickCraft.mc.currentScreen instanceof GuiChest)) {
                AutoClickCraft.mc.thePlayer.sendChatMessage("/sbmenu");
                this.menu.reset();
            }
            if (this.getBlockSton() < 160) {
                AutoClickCraft.mc.thePlayer.closeScreen();
                this.Craft = false;
            }
            if (this.CraftTimer.delay(100.0f)) {
                if (AutoClickCraft.mc.currentScreen instanceof GuiChest) {
                    GuiChest guiChest = (GuiChest)AutoClickCraft.mc.currentScreen;
                    object2 = Minecraft.getMinecraft().thePlayer.openContainer;
                    containerChest = (ContainerChest)object2;
                    string = guiChest.lowerChestInventory.func_145748_c_().getUnformattedText().toLowerCase();
                    if (string.contains("skyblock menu")) {
                        AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 31, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    }
                    if (string.contains("craft item")) {
                        nArray = new int[]{16, 25, 34};
                        for (n = 0; n < 3; ++n) {
                            itemStack = containerChest.getLowerChestInventory().getStackInSlot(nArray[n]);
                            if (!itemStack.getDisplayName().contains(this.item[mode.getCurrentMode()]) || !itemStack.isItemEnchanted()) continue;
                            AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, nArray[n], 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                            if (mode.getCurrentMode() != 2) continue;
                            for (int i = 9; i < 45; ++i) {
                                if (!AutoClickCraft.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) continue;
                                ItemStack itemStack2 = AutoClickCraft.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
                                if (itemStack2.getDisplayName().contains(this.item[mode.getCurrentMode()]) && itemStack2.isItemEnchanted() && !itemStack2.getDisplayName().contains("Packed Ice") && itemStack2.stackSize <= 63) {
                                    if (!this.menu.delay(500.0f)) continue;
                                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, i + 45, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                                    this.menu.reset();
                                    continue;
                                }
                                if (!this.menu.delay(1000.0f)) continue;
                                AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 9 + new Random().nextInt(9) + 45, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                                this.menu.reset();
                            }
                        }
                    }
                }
                this.CraftTimer.reset();
            }
        }
        if (this.IceCraft) {
            if (this.menu.delay(1000.0f) && !(AutoClickCraft.mc.currentScreen instanceof GuiChest)) {
                AutoClickCraft.mc.thePlayer.sendChatMessage("/sbmenu");
                this.menu.reset();
            }
            if (this.getEndICE() < 160) {
                AutoClickCraft.mc.thePlayer.closeScreen();
                this.IceCraft = false;
            }
            if (this.CraftTimer.delay(100.0f)) {
                if (AutoClickCraft.mc.currentScreen instanceof GuiChest) {
                    GuiChest guiChest = (GuiChest)AutoClickCraft.mc.currentScreen;
                    object2 = Minecraft.getMinecraft().thePlayer.openContainer;
                    containerChest = (ContainerChest)object2;
                    string = guiChest.lowerChestInventory.func_145748_c_().getUnformattedText().toLowerCase();
                    if (string.contains("skyblock menu")) {
                        AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 31, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    }
                    if (string.contains("craft item")) {
                        nArray = new int[]{16, 25, 34};
                        for (n = 0; n < 3; ++n) {
                            itemStack = containerChest.getLowerChestInventory().getStackInSlot(nArray[n]);
                            if (!itemStack.getDisplayName().contains("Packed Ice") || !itemStack.isItemEnchanted()) continue;
                            AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, nArray[n], 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                        }
                    }
                }
                this.CraftTimer.reset();
            }
        }
        if (AutoClickCraft.mc.currentScreen instanceof GuiChest) {
            GuiChest guiChest = (GuiChest)AutoClickCraft.mc.currentScreen;
            object2 = Minecraft.getMinecraft().thePlayer.openContainer;
            containerChest = (ContainerChest)object2;
            string = guiChest.lowerChestInventory.func_145748_c_().getUnformattedText();
            if (string.contains("The End Shop") || string.contains("Winter Shop")) {
                if (bl) {
                    AutoClickCraft.mc.thePlayer.closeScreen();
                }
                if (!bl && this.click.delay(50.0f)) {
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    AutoClickCraft.mc.playerController.windowClick(containerChest.field_75152_c, 22, 0, 0, (EntityPlayer)AutoClickCraft.mc.thePlayer);
                    this.click.reset();
                }
            }
        }
        for (Object object2 : AutoClickCraft.mc.theWorld.loadedEntityList) {
            containerChest = (Entity)object2;
            if (!(containerChest instanceof EntityArmorStand)) continue;
            if (mode.getCurrentMode() == 2 && !bl && containerChest.posX == 22.5 && containerChest.posY == 75.625 && containerChest.posZ == 99.5 && this.Shop.delay(1000.0f) && AutoClickCraft.mc.thePlayer.getDistanceToEntity((Entity)containerChest) <= 4.0f && !(AutoClickCraft.mc.currentScreen instanceof GuiChest)) {
                AutoClickCraft.mc.thePlayer.swingItem();
                AutoClickCraft.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C02PacketUseEntity((Entity)containerChest, C02PacketUseEntity.Action.ATTACK));
                this.Shop.reset();
            }
            if (!containerChest.getDisplayName().toString().contains(this.item[mode.getCurrentMode()]) || bl || !this.Shop.delay(1000.0f) || !(AutoClickCraft.mc.thePlayer.getDistanceToEntity((Entity)containerChest) <= 4.0f) || AutoClickCraft.mc.currentScreen instanceof GuiChest) continue;
            AutoClickCraft.mc.thePlayer.swingItem();
            AutoClickCraft.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C02PacketUseEntity((Entity)containerChest, C02PacketUseEntity.Action.ATTACK));
            this.Shop.reset();
        }
    }

    static {
        mode = new ValueManager("AutoClickCraft", "CraftBlock", 0);
    }
}

