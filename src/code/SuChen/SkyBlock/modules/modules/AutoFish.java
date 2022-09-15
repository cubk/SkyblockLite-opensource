/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook
 *  net.minecraft.network.play.server.S02PacketChat
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.util.world.HypixelUtil;
import code.SuChen.SkyBlock.events.LivingEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.events.MotionEvent;
import code.SuChen.SkyBlock.util.player.MoveUtil;
import code.SuChen.SkyBlock.events.PacketEvent;
import code.SuChen.SkyBlock.util.timers.TimerHelper;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import java.lang.reflect.Field;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class AutoFish
extends Module {
    private boolean Fish;
    private boolean IslandFish;
    private long ticksInWater;
    private boolean caughtFish;
    private boolean fishing;
    private long castQueuedAt;
    private int AFKticks;
    private int Hometicks;
    private boolean Home;
    private long lastFishEntityServerY;
    private static final double MOTION_Y_THRESHOLD = -0.02;
    private static final double MOTION_Y_MAYBE_THRESHOLD = -0.008;
    private static final long SERVER_MOTION_Y_THRESHOLD = -500L;
    private TimerHelper APS;
    private TimerHelper PotTimer;
    private TimerHelper time;
    private Random random;
    public static ValueManager<String> mode;
    public ValueManager<Double> Ticks;
    public ValueManager<Double> ticksWater;
    public ValueManager<Double> RightTick;
    public ValueManager<Boolean> info;
    public ValueManager<Boolean> AutoHome;
    public ValueManager<Boolean> AntiAFK;
    public ValueManager<Boolean> AFKChat;
    public static int Yeti;
    public static int Emperor;
    public static int Hydra;
    public static int Protector;
    public static int CarrotKing;
    public static int Grand_Experience;
    public static int Titanic_Experience;
    private boolean Ghast;
    private int Ghastticks;
    private int MoveTick;
    private int Combat;
    private int Experience;
    private int Slot;
    private boolean Rod;

    public AutoFish() {
        super("AutoFish", ModuleType.SKYBLOCK);
        this.Fish = false;
        this.IslandFish = false;
        this.Home = false;
        this.lastFishEntityServerY = 0L;
        this.APS = new TimerHelper();
        this.PotTimer = new TimerHelper();
        this.time = new TimerHelper();
        this.random = new Random();
        this.Ticks = new ValueManager<Double>("AutoFish_AFKTicks", 5.0, 1.0, 10.0, 1.0);
        this.ticksWater = new ValueManager<Double>("AutoFish_WaterTicks", 60.0, 20.0, 120.0, 1.0);
        this.RightTick = new ValueManager<Double>("AutoFish_RightDeay", 10.0, 1.0, 40.0, 1.0);
        this.info = new ValueManager<Boolean>("AutoFish_Info", false);
        this.AutoHome = new ValueManager<Boolean>("AutoFish_AutoHome", true);
        this.AntiAFK = new ValueManager<Boolean>("AutoFish_AntiAFK", true);
        this.AFKChat = new ValueManager<Boolean>("AutoFish_AFKChat", true);
        this.Ghast = false;
        this.Rod = false;
        AutoFish.mode.mode.add("Update");
        AutoFish.mode.mode.add("Packet");
        this.ticksInWater = 0L;
        this.caughtFish = false;
        this.fishing = false;
        this.castQueuedAt = -1L;
    }

    @EventTarget
    public void Packet(PacketEvent packetEvent) {
        S02PacketChat s02PacketChat = (S02PacketChat)packetEvent.getPacket();
        String string = s02PacketChat.getChatComponent().getUnformattedText();
        if (string.contains("The Sea Emperor arises from the depths...") && !string.contains(":")) {
            AutoFish.mc.thePlayer.playSound("mob.enderdragon.growl", 1.0f, 1.0f);
            AutoFish.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cAutoFish\u00a78]\u00a7r\u00a7r \u9493\u51fa\u4e00\u53ea\u6d77\u7687!!! \u5df2\u9493\u51fa: \u00a7c" + ++Emperor + "\u53ea~!"));
            if (this.AFKChat.getValueState().booleanValue()) {
                AutoFish.mc.thePlayer.sendChatMessage("/cc \u9493\u51fa\u4e00\u53ea\u6d77\u7687!!!");
            }
        }
        if (string.contains("The Water Hydra has come to test your strength.") && !string.contains(":")) {
            AutoFish.mc.thePlayer.playSound("mob.wither.spawn", 1.0f, 1.0f);
            AutoFish.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cAutoFish\u00a78]\u00a7r\u00a7r \u9493\u51fa\u4e00\u53ea\u4e5d\u5934\u86c7!!! \u5df2\u9493\u51fa: \u00a7c" + ++Hydra + "\u53ea~!"));
            if (this.AFKChat.getValueState().booleanValue()) {
                AutoFish.mc.thePlayer.sendChatMessage("/cc \u9493\u51fa\u4e00\u53ea\u4e5d\u5934\u86c7!!!");
            }
        }
        if (string.contains("You have awoken the Deep Sea Protector, prepare for a battle!") && !string.contains(":")) {
            AutoFish.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cAutoFish\u00a78]\u00a7r\u00a7r \u9493\u51fa\u4e00\u53ea\u94c1\u5080\u5121!!! \u5df2\u9493\u51fa: \u00a7c" + ++Protector + "\u53ea~!"));
        }
        if (string.contains("Is this even a fish? It's the Carrot King!") && !string.contains(":")) {
            AutoFish.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cAutoFish\u00a78]\u00a7r\u00a7r \u9493\u51fa\u4e00\u53ea\u5154\u5b50!!! \u5df2\u9493\u51fa: \u00a7c" + ++CarrotKing + "\u53ea~!"));
        }
        if (string.contains("GOOD CATCH! You found a Grand Experience Bottle.") && !string.contains(":")) {
            AutoFish.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cAutoFish\u00a78]\u00a7r\u00a7r \u9493\u51fa\u4e00\u6b21\u7ecf\u9a8c\u74f6!!! \u5df2\u9493\u51fa: \u00a7c" + ++Grand_Experience + "\u6b21~!"));
        }
        if (string.contains("GREAT CATCH! You found a Titanic Experience Bottle.") && !string.contains(":")) {
            AutoFish.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cAutoFish\u00a78]\u00a7r\u00a7r \u9493\u51fa\u4e00\u6b21\u6cf0\u5766\u74f6!!! \u5df2\u9493\u51fa: \u00a7c" + ++Titanic_Experience + "\u6b21~!"));
        }
    }

    @EventTarget
    public void onClientTickEvent(MotionEvent motionEvent) {
        Minecraft minecraft = Minecraft.getMinecraft();
        if (!minecraft.isGamePaused() && minecraft.thePlayer != null) {
            EntityPlayerSP entityPlayerSP = minecraft.thePlayer;
            if (this.canCast((EntityPlayer)entityPlayerSP)) {
                this.fishing = true;
                if (entityPlayerSP.fishEntity != null) {
                    if (!this.caughtFish && entityPlayerSP.fishEntity.func_70090_H()) {
                        ++this.ticksInWater;
                        if (this.ticksInWater > this.ticksWater.getValueState().longValue() && Math.abs(AutoFish.mc.thePlayer.fishEntity.field_70181_x) > 0.02) {
                            ++this.AFKticks;
                            this.caughtFish = true;
                            if (HypixelUtil.Island()) {
                                this.IslandFish = true;
                            }
                            minecraft.playerController.sendUseItem((EntityPlayer)entityPlayerSP, (World)minecraft.theWorld, entityPlayerSP.getHeldItem());
                            this.castQueuedAt = minecraft.theWorld.getTotalWorldTime();
                        }
                    }
                } else if (this.castQueued() && minecraft.theWorld.getTotalWorldTime() > this.castQueuedAt + this.RightTick.getValueState().longValue() + (long)this.random.nextInt(10)) {
                    minecraft.playerController.sendUseItem((EntityPlayer)entityPlayerSP, (World)minecraft.theWorld, entityPlayerSP.getHeldItem());
                    this.castQueuedAt = -1L;
                } else {
                    this.ticksInWater = 0L;
                    this.caughtFish = false;
                }
            } else if (this.fishing) {
                this.fishing = false;
                this.ticksInWater = 0L;
                this.caughtFish = false;
                this.castQueuedAt = -1L;
                this.lastFishEntityServerY = 0L;
            }
        }
        if (HypixelUtil.Island()) {
            this.Home = false;
            if (!this.canCast((EntityPlayer)AutoFish.mc.thePlayer)) {
                this.IslandFish = false;
            }
            if (this.time.delay(2000.0f) && this.Fish) {
                minecraft.playerController.sendUseItem((EntityPlayer)AutoFish.mc.thePlayer, (World)minecraft.theWorld, AutoFish.mc.thePlayer.getHeldItem());
                this.Fish = false;
                this.time.reset();
            }
        } else {
            this.Home = true;
        }
        if (this.AutoHome.getValueState().booleanValue() && this.Home && this.IslandFish) {
            ++this.Hometicks;
            if (this.Hometicks >= 160 && AutoFish.isMoving()) {
                this.Home = false;
            }
            if (this.Hometicks >= 200) {
                if (HypixelUtil.isSkyBlock()) {
                    AutoFish.mc.thePlayer.sendChatMessage("/warp home");
                } else if (AutoFish.mc.theWorld.getScoreboard() != null) {
                    AutoFish.mc.thePlayer.sendChatMessage("/play skyblock");
                } else if (AutoFish.mc.thePlayer.posX == -23.5 && AutoFish.mc.thePlayer.posY == 31.0 && AutoFish.mc.thePlayer.posZ == 21.5) {
                    AutoFish.mc.thePlayer.sendChatMessage("/l skyblock");
                }
                this.Fish = true;
                this.Hometicks = 0;
            }
        }
    }

    @EventTarget
    public void onRender(LivingEvent livingEvent) {
        float f = (float)(Math.random() / (90.0 / (80.0 + MoveUtil.getRandomInRange(1.2354235325235234E-4, 0.0024708470650470467)) * 3.0 / 1.2 - 337.5) + 90.0 / (80.0 + MoveUtil.getRandomInRange(1.2354235325235234E-4, 0.0024708470650470467)) * 3.0 / 0.8);
        if (this.AntiAFK.getValueState().booleanValue()) {
            this.setDisplayName("Ticks " + this.AFKticks);
            if (this.AFKticks >= this.Ticks.getValueState().intValue()) {
                if (AutoFish.mc.thePlayer.rotationPitch <= 80.0f) {
                    if (mode.isCurrentMode("Update")) {
                        livingEvent.setYaw(AutoFish.mc.thePlayer.rotationYaw + 50.0f / f);
                        livingEvent.setYaw(AutoFish.mc.thePlayer.rotationYaw + 10.0f / f);
                    } else {
                        mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(AutoFish.mc.thePlayer.rotationYaw, AutoFish.mc.thePlayer.rotationPitch + 5.0f / f, AutoFish.mc.thePlayer.onGround));
                    }
                    if (this.info.getValueState().booleanValue()) {
                        AutoFish.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cAutoFish\u00a78]\u00a7r\u00a7r Pitch +" + 5.0f / f));
                    }
                } else if (AutoFish.mc.thePlayer.rotationPitch > 80.0f) {
                    if (mode.isCurrentMode("Update")) {
                        livingEvent.setYaw(AutoFish.mc.thePlayer.rotationYaw - 50.0f / f);
                        livingEvent.setYaw(AutoFish.mc.thePlayer.rotationYaw - 10.0f / f);
                    } else {
                        mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(AutoFish.mc.thePlayer.rotationYaw, AutoFish.mc.thePlayer.rotationPitch - 5.0f / f, AutoFish.mc.thePlayer.onGround));
                    }
                    if (this.info.getValueState().booleanValue()) {
                        AutoFish.mc.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("\u00a78[\u00a7cAutoFish\u00a78]\u00a7r\u00a7r Pitch -" + 5.0f / f));
                    }
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

    public static /* bridge */ int getPrivateIntFieldFromObject(Object object, String string, String string2) throws NoSuchFieldException, SecurityException, NumberFormatException, IllegalArgumentException, IllegalAccessException {
        Field field = null;
        try {
            field = object.getClass().getDeclaredField(string);
        }
        catch (NoSuchFieldException noSuchFieldException) {
            field = object.getClass().getDeclaredField(string2);
        }
        if (field != null) {
            field.setAccessible(true);
            return Integer.valueOf(field.get(object).toString());
        }
        return 0;
    }

    public static /* bridge */ boolean isMoving() {
        if (Minecraft.getMinecraft().thePlayer.movementInput.moveForward != 0.0f) {
            return true;
        }
        return Minecraft.getMinecraft().thePlayer.movementInput.moveStrafe != 0.0f;
    }

    static {
        mode = new ValueManager("AutoFish", "AFKMode", 0);
    }
}

