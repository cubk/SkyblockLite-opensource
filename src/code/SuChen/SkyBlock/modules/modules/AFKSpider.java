/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C02PacketUseEntity
 *  net.minecraft.network.play.client.C02PacketUseEntity$Action
 *  net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook
 *  org.lwjgl.input.Keyboard
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.util.world.HypixelUtil;
import code.SuChen.SkyBlock.events.LivingEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.util.player.MoveUtil;
import code.SuChen.SkyBlock.util.player.PlayerUtil;
import code.SuChen.SkyBlock.util.timers.TimerHelper;
import code.SuChen.SkyBlock.events.UpdateEvent;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import java.util.Random;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class AFKSpider
extends Module {
    private TimerHelper APS;
    private Random random;
    public ValueManager<Boolean> AntiAFK;
    public ValueManager<Boolean> AutoHome;
    public ValueManager<Boolean> PlyerMove;
    public ValueManager<Double> TIMERCheck;
    private boolean Spider;
    private int Spiderticks;
    private int AFKticks;
    private int Hometicks;
    private boolean Home;
    private int MoveTick;
    private int Combat;
    private int Experience;
    private int Slot;
    private boolean Rod;

    public AFKSpider() {
        super("AFKSpider", ModuleType.SKYBLOCK);
        this.APS = new TimerHelper();
        this.random = new Random();
        this.AntiAFK = new ValueManager<Boolean>("AFKSpider_AntiAFK", true);
        this.AutoHome = new ValueManager<Boolean>("AFKSpider_AutoHome", true);
        this.PlyerMove = new ValueManager<Boolean>("AFKSpider_PlyerMove", false);
        this.TIMERCheck = new ValueManager<Double>("AFKSpider_Click Dely", 300.0, 100.0, 1000.0, 100.0);
        this.Spider = false;
        this.Home = false;
        this.Rod = false;
    }

    @EventTarget
    public void onClientUpdate(UpdateEvent updateEvent) {
        int n = this.TIMERCheck.getValueState().intValue() + this.random.nextInt(100) - 30;
        if (HypixelUtil.Island()) {
            if (this.Hometicks != 0) {
                this.Hometicks = 0;
            }
            if (this.Home) {
                this.Home = false;
                this.Spider = true;
            }
            if (AFKSpider.mc.gameSettings.keyBindSprint.isPressed() && this.canSword((EntityPlayer)AFKSpider.mc.thePlayer)) {
                boolean bl = this.Spider = !this.Spider;
                if (this.Spider) {
                    this.Spiderticks = 0;
                }
                if (this.Spider) {
                    this.Slot = AFKSpider.mc.thePlayer.inventory.currentItem;
                }
            }
            if (this.canSword((EntityPlayer)AFKSpider.mc.thePlayer) && this.Spider) {
                if (this.PlyerMove.getValueState().booleanValue()) {
                    ++this.MoveTick;
                    if (this.MoveTick >= 60) {
                        this.MoveTick = 0;
                    }
                    if (this.MoveTick >= 30) {
                        if (!Keyboard.isKeyDown((int)AFKSpider.mc.gameSettings.keyBindLeft.getKeyCode())) {
                            KeyBinding.setKeyBindState((int)AFKSpider.mc.gameSettings.keyBindLeft.getKeyCode(), (boolean)false);
                        }
                        KeyBinding.setKeyBindState((int)AFKSpider.mc.gameSettings.keyBindRight.getKeyCode(), (boolean)true);
                    } else {
                        if (!Keyboard.isKeyDown((int)AFKSpider.mc.gameSettings.keyBindRight.getKeyCode())) {
                            KeyBinding.setKeyBindState((int)AFKSpider.mc.gameSettings.keyBindRight.getKeyCode(), (boolean)false);
                        }
                        KeyBinding.setKeyBindState((int)AFKSpider.mc.gameSettings.keyBindLeft.getKeyCode(), (boolean)true);
                    }
                }
                if (this.APS.delay(n)) {
                    ++this.Spiderticks;
                    if (AFKSpider.mc.objectMouseOver.entityHit instanceof Entity) {
                        AFKSpider.mc.thePlayer.swingItem();
                        mc.getNetHandler().addToSendQueue((Packet)new C02PacketUseEntity(AFKSpider.mc.objectMouseOver.entityHit, C02PacketUseEntity.Action.ATTACK));
                    }
                    this.APS.reset();
                }
                if (this.Spiderticks >= 2) {
                    ++this.AFKticks;
                    this.Spiderticks = 0;
                }
            } else if (this.Spider) {
                this.Spider = false;
            }
        } else if (this.Spider) {
            this.Home = true;
            this.Spider = false;
        }
        if (this.AutoHome.getValueState().booleanValue() && this.Home) {
            ++this.Hometicks;
            if (this.Hometicks >= 40 && PlayerUtil.isMoving2()) {
                this.Home = false;
            }
            if (this.Hometicks >= 50) {
                if (HypixelUtil.isSkyBlock()) {
                    AFKSpider.mc.thePlayer.sendChatMessage("/warp home");
                } else if (AFKSpider.mc.theWorld.getScoreboard() != null) {
                    AFKSpider.mc.thePlayer.sendChatMessage("/play skyblock");
                } else if (AFKSpider.mc.thePlayer.posX == -23.5 && AFKSpider.mc.thePlayer.posY == 31.0 && AFKSpider.mc.thePlayer.posZ == 21.5) {
                    AFKSpider.mc.thePlayer.sendChatMessage("/l skyblock");
                }
                this.Hometicks = 0;
            }
        }
    }

    @EventTarget
    public void onRender(LivingEvent livingEvent) {
        float f = (float)(Math.random() / (90.0 / (80.0 + MoveUtil.getRandomInRange(1.2354235325235234E-4, 0.0024708470650470467)) * 3.0 / 1.2 - 337.5) + 90.0 / (80.0 + MoveUtil.getRandomInRange(1.2354235325235234E-4, 0.0024708470650470467)) * 3.0 / 0.8);
        if (this.AntiAFK.getValueState().booleanValue()) {
            this.setDisplayName("Ticks " + this.AFKticks);
            if (this.AFKticks >= 10) {
                if (AFKSpider.mc.thePlayer.rotationPitch <= 80.0f) {
                    mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(AFKSpider.mc.thePlayer.rotationYaw, AFKSpider.mc.thePlayer.rotationPitch + 5.0f / f, AFKSpider.mc.thePlayer.onGround));
                } else if (AFKSpider.mc.thePlayer.rotationPitch > 80.0f) {
                    mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(AFKSpider.mc.thePlayer.rotationYaw, AFKSpider.mc.thePlayer.rotationPitch - 5.0f / f, AFKSpider.mc.thePlayer.onGround));
                }
                this.AFKticks = 0;
            }
        } else {
            if (this.AFKticks != 0) {
                this.AFKticks = 0;
            }
            this.setDisplayName("");
        }
    }
}

