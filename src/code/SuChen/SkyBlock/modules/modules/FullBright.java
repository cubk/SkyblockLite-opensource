/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.events.UpdateEvent;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FullBright
extends Module {
    public ValueManager<String> mode;
    private float gamma;

    public FullBright() {
        super("FullBright", ModuleType.RENDER);
        this.mode = new ValueManager("FullBright", "FullBright Mode", 0);
        this.mode.mode.add("Bright");
        this.mode.mode.add("Potion");
    }

    @EventTarget
    public void onUpdate(UpdateEvent updateEvent) {
        if (this.mode.isCurrentMode("Potion")) {
            FullBright.mc.thePlayer.func_70690_d(new PotionEffect(Potion.nightVision.getId(), 5200, 1));
        }
    }
}

