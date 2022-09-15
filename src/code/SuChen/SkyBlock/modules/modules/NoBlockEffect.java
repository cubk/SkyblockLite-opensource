/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.events.UpdateEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleManager;
import code.SuChen.SkyBlock.modules.ModuleType;
import com.darkmagician6.eventapi.EventTarget;

public class NoBlockEffect
extends Module {
    public NoBlockEffect() {
        super("NoBlockEffect", ModuleType.WORLD);
    }

    @EventTarget
    private void update(UpdateEvent updateEvent) {
        XRayModule.BlockEffect = ModuleManager.getModByClass(NoBlockEffect.class).isEnabled();
    }
}

