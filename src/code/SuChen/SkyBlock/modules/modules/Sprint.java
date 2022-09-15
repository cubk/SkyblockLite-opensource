/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.ValueManager;
import code.SuChen.SkyBlock.events.RenderGameOverlayEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import com.darkmagician6.eventapi.EventTarget;

public class Sprint
extends Module {
    public static boolean backward;
    public ValueManager<Boolean> omni;

    public Sprint() {
        super("Sprint", ModuleType.MOVEMENT);
        this.omni = new ValueManager<Boolean>("Sprint_Omni", false);
    }

    @EventTarget
    public void onUpdate(RenderGameOverlayEvent renderGameOverlayEvent) {
        backward = this.omni.getValueState();
        if (this.canSprint()) {
            Sprint.mc.thePlayer.setSprinting(true);
        }
    }

    static {
        backward = true;
    }
}

