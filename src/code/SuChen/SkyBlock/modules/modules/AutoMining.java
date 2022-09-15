/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.util.world.HypixelUtil;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.util.timers.TimerHelper;
import code.SuChen.SkyBlock.events.UpdateEvent;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public class AutoMining
extends Module {
    private TimerHelper menu;
    private TimerHelper craft;
    private TimerHelper DropItem;
    private TimerHelper head;
    private TimerHelper HubTimer;
    private Random random;
    public static ValueManager<String> mode;
    public static ValueManager<String> PlayerMove;
    public ValueManager<Boolean> AntiAFK;
    public ValueManager<Boolean> AutoCraft;
    public ValueManager<Boolean> AutoHome;
    public ValueManager<Boolean> Drop;
    private Block[] Hitblock;
    private Block[] Hitblocks;
    private String[] item;
    private int Hometicks;
    private boolean Home;
    private boolean Craft;
    private boolean drop;
    private boolean mine;
    private int AFKticks;
    private BlockPos PlayerDonw;
    private BlockPos MoveBlock;
    private int farmetick;

    public AutoMining() {
        super("AutoMining", ModuleType.SKYBLOCK);
        this.menu = new TimerHelper();
        this.craft = new TimerHelper();
        this.DropItem = new TimerHelper();
        this.head = new TimerHelper();
        this.HubTimer = new TimerHelper();
        this.random = new Random();
        this.AntiAFK = new ValueManager<Boolean>("AutoMining_AntiAFK", true);
        this.AutoCraft = new ValueManager<Boolean>("AutoMining_AutoCraft", true);
        this.AutoHome = new ValueManager<Boolean>("AutoMining_AutoHome", true);
        this.Drop = new ValueManager<Boolean>("AutoMining_DropEnchantedItem", true);
        this.Hitblock = new Block[]{Blocks.cobblestone, Blocks.pumpkin, Blocks.log};
        this.Hitblocks = new Block[]{Blocks.cobblestone, Blocks.pumpkin, Blocks.log2};
        this.item = new String[]{"Cobblestone", "Pumpkin", "Wood"};
        this.Home = false;
        this.Craft = false;
        this.drop = false;
        this.mine = false;
        AutoMining.mode.mode.add("Stone");
        AutoMining.mode.mode.add("Pumpkin");
        AutoMining.mode.mode.add("Wood");
        AutoMining.PlayerMove.mode.add("Forward");
        AutoMining.PlayerMove.mode.add("Backward");
        AutoMining.PlayerMove.mode.add("Left");
        AutoMining.PlayerMove.mode.add("Right");
    }

    @EventTarget
    public void onUpdate(UpdateEvent updateEvent) {
        if (HypixelUtil.Island() && this.canCast((EntityPlayer)AutoMining.mc.thePlayer)) {
            this.Home = false;
            if (AutoMining.mc.gameSettings.keyBindSprint.isPressed()) {
                boolean bl = this.mine = !this.mine;
            }
            if (this.mine && mode.getCurrentMode() == 1) {
                float f;
                float f2;
                float f3;
                float f4;
                float f5;
                this.PlayerDonw = new BlockPos(AutoMining.mc.thePlayer.posX, (double)((int)AutoMining.mc.thePlayer.getEntityBoundingBox().minY - 1), AutoMining.mc.thePlayer.posZ);
                if (this.MoveBlock == null) {
                    this.MoveBlock = new BlockPos(AutoMining.mc.thePlayer.posX, (double)((int)AutoMining.mc.thePlayer.getEntityBoundingBox().minY - 1), AutoMining.mc.thePlayer.posZ);
                }
                if (AutoMining.isGoldBlock() && !this.PlayerDonw.toString().contains(this.MoveBlock.toString())) {
                    this.MoveBlock = new BlockPos(AutoMining.mc.thePlayer.posX, (double)((int)AutoMining.mc.thePlayer.getEntityBoundingBox().minY - 1), AutoMining.mc.thePlayer.posZ);
                    f5 = (float)((double)this.MoveBlock.func_177958_n() + 0.5 - AutoMining.mc.thePlayer.posX);
                    f4 = (float)(AutoMining.mc.thePlayer.posY - (double)this.MoveBlock.func_177956_o());
                    f3 = (float)((double)this.MoveBlock.func_177952_p() + 0.5 - AutoMining.mc.thePlayer.posZ);
                    f2 = MathHelper.sqrt_float((float)(f5 * f5 + f4 * f4 + f3 * f3));
                    f = (float)(Math.atan2(f3, f5) * 180.0 / Math.PI) - 90.0f;
                    if (AutoMining.mc.thePlayer.getDistanceSqToCenter(this.MoveBlock) > 0.5) {
                        AutoMining.mc.gameSettings.keyBindForward.pressed = true;
                    } else {
                        AutoMining.mc.gameSettings.keyBindForward.pressed = false;
                        AutoMining.mc.thePlayer.rotationYaw -= 90.0f;
                        this.facing();
                    }
                }
                if (AutoMining.isDiaBlock() && !this.PlayerDonw.toString().contains(this.MoveBlock.toString())) {
                    this.MoveBlock = new BlockPos(AutoMining.mc.thePlayer.posX, (double)((int)AutoMining.mc.thePlayer.getEntityBoundingBox().minY - 1), AutoMining.mc.thePlayer.posZ);
                    f5 = (float)((double)this.MoveBlock.func_177958_n() + 0.5 - AutoMining.mc.thePlayer.posX);
                    f4 = (float)(AutoMining.mc.thePlayer.posY - (double)this.MoveBlock.func_177956_o());
                    f3 = (float)((double)this.MoveBlock.func_177952_p() + 0.5 - AutoMining.mc.thePlayer.posZ);
                    f2 = MathHelper.sqrt_float((float)(f5 * f5 + f4 * f4 + f3 * f3));
                    f = (float)(Math.atan2(f3, f5) * 180.0 / Math.PI) - 90.0f;
                    if (AutoMining.mc.thePlayer.getDistanceSqToCenter(this.MoveBlock) > 0.5) {
                        AutoMining.mc.gameSettings.keyBindForward.pressed = true;
                    } else {
                        AutoMining.mc.gameSettings.keyBindForward.pressed = false;
                        AutoMining.mc.thePlayer.rotationYaw += 90.0f;
                        this.facing();
                    }
                }
            }
            if (this.mine) {
                if (mode.getCurrentMode() == 0 && this.HubTimer.delay(600000.0f)) {
                    AutoMining.mc.thePlayer.sendChatMessage("/warp hub");
                    this.HubTimer.reset();
                }
                if (PlayerMove.getCurrentMode() == 0) {
                    AutoMining.mc.gameSettings.keyBindForward.pressed = true;
                }
                if (PlayerMove.getCurrentMode() == 1) {
                    AutoMining.mc.gameSettings.keyBindBack.pressed = true;
                }
                if (PlayerMove.getCurrentMode() == 2) {
                    AutoMining.mc.gameSettings.keyBindLeft.pressed = true;
                }
                if (PlayerMove.getCurrentMode() == 3) {
                    AutoMining.mc.gameSettings.keyBindRight.pressed = true;
                }
            }
            if (this.head.delay(5000.0f) && Minecraft.getMinecraft().thePlayer.rotationPitch != -90.0f && this.mine && mode.getCurrentMode() == 0) {
                Minecraft.getMinecraft().thePlayer.rotationPitch = -90.0f;
                this.head.reset();
            }
            if (this.mine) {
                if (AutoMining.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    BlockPos blockPos = AutoMining.mc.objectMouseOver.getBlockPos();
                    EnumFacing enumFacing = AutoMining.mc.objectMouseOver.sideHit;
                    if (!(AutoMining.mc.theWorld.getBlockState(blockPos).getBlock() == Blocks.air || AutoMining.mc.theWorld.getBlockState(blockPos).getBlock() != this.Hitblock[mode.getCurrentMode()] && AutoMining.mc.theWorld.getBlockState(blockPos).getBlock() != this.Hitblocks[mode.getCurrentMode()] || this.Craft)) {
                        ++this.AFKticks;
                        AutoMining.mc.thePlayer.swingItem();
                        AutoMining.mc.playerController.onPlayerDamageBlock(blockPos, enumFacing);
                    }
                }
            }
        } else if (!HypixelUtil.Island()) {
            this.Home = true;
        } else if (!this.canCast((EntityPlayer)AutoMining.mc.thePlayer)) {
            this.mine = false;
        }
        if (this.AntiAFK.getValueState().booleanValue() && this.AFKticks >= 20) {
            if (Minecraft.getMinecraft().thePlayer.rotationPitch <= 80.0f) {
                Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(Minecraft.getMinecraft().thePlayer.rotationYaw, Minecraft.getMinecraft().thePlayer.rotationPitch + (float)((double)0.1f + AutoMining.getRandomInRange(0.001235, 0.0247)), Minecraft.getMinecraft().thePlayer.onGround));
            } else if (Minecraft.getMinecraft().thePlayer.rotationPitch > 80.0f) {
                Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(Minecraft.getMinecraft().thePlayer.rotationYaw, Minecraft.getMinecraft().thePlayer.rotationPitch - (float)((double)0.1f + AutoMining.getRandomInRange(0.001235, 0.0247)), Minecraft.getMinecraft().thePlayer.onGround));
            }
            this.AFKticks = 0;
        }
    }

    @EventTarget
    public void onCraft(UpdateEvent updateEvent) {
        ItemStack itemStack;
        int n;
        Block block = AutoMining.mc.theWorld.getBlockState(new BlockPos(AutoMining.mc.thePlayer.posX, AutoMining.mc.thePlayer.posY - 0.01, AutoMining.mc.thePlayer.posZ)).getBlock();
        if (block == Blocks.end_portal_frame) {
            ++this.farmetick;
            if (this.farmetick >= 100) {
                if (AutoMining.mc.thePlayer.onGround) {
                    AutoMining.mc.thePlayer.jump();
                }
                this.farmetick = 0;
            }
        }
        boolean bl = true;
        ItemStack[] itemStackArray = AutoMining.mc.thePlayer.inventory.mainInventory;
        int n2 = AutoMining.mc.thePlayer.inventory.mainInventory.length;
        for (n = 0; n < n2; ++n) {
            itemStack = itemStackArray[n];
            if (itemStack != null) continue;
            bl = false;
            break;
        }
        if (this.AutoCraft.getValueState().booleanValue() && bl && HypixelUtil.Island() && this.getBlockSton() > 160) {
            this.Craft = true;
        }
        if (this.Craft) {
            if (this.menu.delay(1000.0f) && !(AutoMining.mc.currentScreen instanceof GuiChest)) {
                AutoMining.mc.thePlayer.sendChatMessage("/sbmenu");
                this.menu.reset();
            }
            if (this.mine) {
                if (PlayerMove.getCurrentMode() == 0) {
                    AutoMining.mc.gameSettings.keyBindBack.pressed = true;
                }
                if (PlayerMove.getCurrentMode() == 1) {
                    AutoMining.mc.gameSettings.keyBindForward.pressed = true;
                }
                if (PlayerMove.getCurrentMode() == 2) {
                    AutoMining.mc.gameSettings.keyBindRight.pressed = true;
                }
                if (PlayerMove.getCurrentMode() == 3) {
                    AutoMining.mc.gameSettings.keyBindLeft.pressed = true;
                }
            }
            if (this.getBlockSton() < 160) {
                AutoMining.mc.thePlayer.closeScreen();
                AutoMining.mc.gameSettings.keyBindBack.pressed = false;
                AutoMining.mc.gameSettings.keyBindForward.pressed = false;
                AutoMining.mc.gameSettings.keyBindRight.pressed = false;
                AutoMining.mc.gameSettings.keyBindLeft.pressed = false;
                this.Craft = false;
            }
            if (this.craft.delay(100.0f)) {
                if (AutoMining.mc.currentScreen instanceof GuiChest) {
                    GuiChest guiChest = (GuiChest)AutoMining.mc.currentScreen;
                    itemStack = Minecraft.getMinecraft().thePlayer.openContainer;
                    ContainerChest containerChest = (ContainerChest)itemStack;
                    String string = guiChest.lowerChestInventory.func_145748_c_().getUnformattedText().toLowerCase();
                    if (string.contains("skyblock menu")) {
                        AutoMining.mc.playerController.windowClick(containerChest.field_75152_c, 31, 0, 0, (EntityPlayer)AutoMining.mc.thePlayer);
                    }
                    if (string.contains("craft item")) {
                        int[] nArray = new int[]{16, 25, 34};
                        for (int i = 0; i < 3; ++i) {
                            ItemStack itemStack2 = containerChest.getLowerChestInventory().getStackInSlot(nArray[i]);
                            if (!itemStack2.getDisplayName().contains(this.item[mode.getCurrentMode()]) || !itemStack2.isItemEnchanted()) continue;
                            AutoMining.mc.playerController.windowClick(containerChest.field_75152_c, nArray[i], 0, 0, (EntityPlayer)AutoMining.mc.thePlayer);
                        }
                    }
                }
                this.craft.reset();
            }
        }
        if (this.AutoHome.getValueState().booleanValue() && this.Home && this.mine) {
            ++this.Hometicks;
            if (this.Hometicks >= 50) {
                if (HypixelUtil.isSkyBlock()) {
                    AutoMining.mc.thePlayer.sendChatMessage("/warp home");
                } else if (AutoMining.mc.theWorld.getScoreboard() != null) {
                    AutoMining.mc.thePlayer.sendChatMessage("/play skyblock");
                } else if (AutoMining.mc.thePlayer.posX == -23.5 && AutoMining.mc.thePlayer.posY == 31.0 && AutoMining.mc.thePlayer.posZ == 21.5) {
                    AutoMining.mc.thePlayer.sendChatMessage("/l skyblock");
                }
                this.Hometicks = 0;
            }
        }
        if (this.Drop.getValueState().booleanValue() && this.mine && HypixelUtil.Island() && mode.getCurrentMode() != 1) {
            if (this.getBlockEnchanted_Ston() >= 128) {
                this.drop = true;
            }
            if (this.drop) {
                if (this.getBlockEnchanted_Ston() <= 0) {
                    this.drop = false;
                }
                for (n = 9; n < 45; ++n) {
                    if (!AutoMining.mc.thePlayer.inventoryContainer.getSlot(n).getHasStack()) continue;
                    itemStack = AutoMining.mc.thePlayer.inventoryContainer.getSlot(n).getStack();
                    if (!this.DropItem.delay(500.0f) || !itemStack.getDisplayName().contains(this.item[mode.getCurrentMode()]) || !itemStack.isItemEnchanted()) continue;
                    Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(Minecraft.getMinecraft().thePlayer.rotationYaw, 90.0f, Minecraft.getMinecraft().thePlayer.onGround));
                    this.drop(n);
                    Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(Minecraft.getMinecraft().thePlayer.rotationYaw, Minecraft.getMinecraft().thePlayer.rotationPitch, Minecraft.getMinecraft().thePlayer.onGround));
                    this.DropItem.reset();
                }
            }
        }
    }

    public static /* bridge */ double getRandomInRange(double d, double d2) {
        double d3;
        Random random = new Random();
        double d4 = d2 - d;
        double d5 = random.nextDouble() * d4;
        if (d5 > d2) {
            d5 = d2;
        }
        if ((d3 = d5 + d) > d2) {
            d3 = d2;
        }
        return d3;
    }

    public static /* bridge */ boolean isDiaBlock() {
        AxisAlignedBB axisAlignedBB = AutoMining.mc.thePlayer.getEntityBoundingBox();
        if (AutoMining.mc.thePlayer == null) {
            return false;
        }
        BlockPos blockPos = new BlockPos(AutoMining.mc.thePlayer.posX, (double)((int)axisAlignedBB.minY - 1), AutoMining.mc.thePlayer.posZ);
        Block block = AutoMining.mc.theWorld.getBlockState(blockPos).getBlock();
        return block == Blocks.diamond_block;
    }

    public static /* bridge */ boolean isGoldBlock() {
        AxisAlignedBB axisAlignedBB = AutoMining.mc.thePlayer.getEntityBoundingBox();
        if (AutoMining.mc.thePlayer == null) {
            return false;
        }
        BlockPos blockPos = new BlockPos(AutoMining.mc.thePlayer.posX, (double)((int)axisAlignedBB.minY - 1), AutoMining.mc.thePlayer.posZ);
        Block block = AutoMining.mc.theWorld.getBlockState(blockPos).getBlock();
        return block == Blocks.gold_block;
    }

    static {
        mode = new ValueManager("AutoMining", "HitBlock", 0);
        PlayerMove = new ValueManager("AutoMining", "PlayerMove", 0);
    }
}

