/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  org.lwjgl.input.Mouse
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.events.TickEvent;
import code.SuChen.SkyBlock.util.timers.TimerUtil;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import java.text.DecimalFormat;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Mouse;

public class AutoClick
extends Module {
    public ValueManager<Double> StartDelay;
    public ValueManager<Double> maxcps;
    public ValueManager<Double> mincps;
    private double tick;
    private double startDelay;
    private double delay;
    private TimerUtil time;
    private TimerUtil time2;
    Random random;

    public AutoClick() {
        super("AutoClick", ModuleType.COMBAT);
        this.StartDelay = new ValueManager<Double>("AutoClick_StartDelay", 3.0, 0.0, 10.0, 1.0);
        this.maxcps = new ValueManager<Double>("AutoClick_MaxCPS", 12.0, 1.0, 20.0, 1.0);
        this.mincps = new ValueManager<Double>("AutoClick_MinCPS", 8.0, 1.0, 20.0, 1.0);
        this.delay = 0.0;
        this.time = new TimerUtil();
        this.time2 = new TimerUtil();
        this.random = new Random();
    }

    @EventTarget
    private void startDelay(TickEvent tickEvent) {
        if (this.time2.delay(100.0) && Mouse.isButtonDown((int)0)) {
            this.startDelay += 1.0;
            this.time2.reset();
            return;
        }
        if (Mouse.isButtonDown((int)0)) {
            return;
        }
        this.startDelay = 0.0;
    }

    /*
     * Unable to fully structure code
     */
    @EventTarget
    public void onUpdate(TickEvent var1_1) {
        var2_2 = new DecimalFormat("0.00");
        var3_3 = AutoClick.mc.thePlayer.rayTrace(4.0, 0.0f).getBlockPos();
        if (AutoClick.mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) ** GOTO lbl-1000
        if (AutoClick.mc.theWorld.getBlockState(AutoClick.mc.objectMouseOver.getBlockPos()).getBlock() == Blocks.air) ** GOTO lbl-1000
        if (AutoClick.mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
            v0 = true;
        } else lbl-1000:
        // 3 sources

        {
            v0 = false;
        }
        var4_4 = v0;
        this.setDisplayName("CPS:" + this.mincps.getValueState().intValue() + "-" + this.maxcps.getValueState().intValue());
        if (!AutoClick.mc.gameSettings.keyBindAttack.isKeyDown()) {
            return;
        }
        if (!this.time.delay(this.delay)) {
            return;
        }
        if (AutoClick.mc.currentScreen != null) {
            return;
        }
        if (this.startDelay < this.StartDelay.getValueState()) {
            return;
        }
        if (var4_4) {
            return;
        }
        AutoClick.mc.clickMouse();
        AutoClick.mc.leftClickCounter = 0;
        this.delay();
        this.time.reset();
    }
}

