/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook
 *  net.minecraft.util.BlockPos
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.events.LivingEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.util.player.MoveUtil;
import code.SuChen.SkyBlock.gui.notification.NotificationManager;
import Code_By_SuChen.NotificationTypes;
import code.SuChen.SkyBlock.util.timers.TimerHelper;
import code.SuChen.SkyBlock.events.UpdateEvent;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import java.util.Random;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

public class AntiAFK
extends Module {
    private TimerHelper APS;
    boolean back;
    public ValueManager<Double> TIMERCheck;
    public ValueManager<Boolean> Move;
    public ValueManager<Boolean> rotation;
    private BlockPos pos;
    private BlockPos pos2;
    private Random random;

    public AntiAFK() {
        super("AntiAFK", ModuleType.SKYBLOCK);
        this.APS = new TimerHelper();
        this.back = true;
        this.TIMERCheck = new ValueManager<Double>("AntiAFK_Time Check", 12000.0, 1000.0, 30000.0, 1000.0);
        this.Move = new ValueManager<Boolean>("AntiAFK_Move", true);
        this.rotation = new ValueManager<Boolean>("AntiAFK_rotation", true);
        this.random = new Random();
    }

    @EventTarget
    public void onClientUpdate(UpdateEvent updateEvent) {
        if (this.Move.getValueState().booleanValue() && AntiAFK.mc.thePlayer.onGround) {
            AntiAFK.mc.thePlayer.jump();
        }
    }

    @EventTarget
    public void onRender(LivingEvent livingEvent) {
        if (this.rotation.getValueState().booleanValue()) {
            int n = this.TIMERCheck.getValueState().intValue() + this.random.nextInt(500) - 30;
            float f = (float)(Math.random() / (90.0 / (80.0 + MoveUtil.getRandomInRange(1.2354235325235234E-4, 0.0024708470650470467)) * 3.0 / 1.2 - 337.5) + 90.0 / (80.0 + MoveUtil.getRandomInRange(1.2354235325235234E-4, 0.0024708470650470467)) * 3.0 / 0.8);
            if (this.APS.delay(n)) {
                NotificationManager.sendClientMessage("AntiAFK....", NotificationTypes.INFO);
                if (AntiAFK.mc.thePlayer.rotationPitch <= 80.0f) {
                    mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(AntiAFK.mc.thePlayer.rotationYaw, AntiAFK.mc.thePlayer.rotationPitch + 10.0f / f, AntiAFK.mc.thePlayer.onGround));
                } else if (AntiAFK.mc.thePlayer.rotationPitch > 80.0f) {
                    mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(AntiAFK.mc.thePlayer.rotationYaw, AntiAFK.mc.thePlayer.rotationPitch - 10.0f / f, AntiAFK.mc.thePlayer.onGround));
                }
                this.APS.reset();
            }
        }
    }
}

