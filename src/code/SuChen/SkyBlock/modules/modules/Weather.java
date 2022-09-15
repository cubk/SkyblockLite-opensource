/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.server.S03PacketTimeUpdate
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.ValueManager;
import code.SuChen.SkyBlock.events.PacketEvent;
import code.SuChen.SkyBlock.events.TickEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class Weather
extends Module {
    public static ValueManager<Double> TIME;

    public Weather() {
        super("Weather", ModuleType.WORLD);
    }

    @EventTarget
    public void onpacket(PacketEvent packetEvent) {
        if (packetEvent.getPacket() instanceof S03PacketTimeUpdate) {
            packetEvent.setCancelled(true);
        }
    }

    @EventTarget
    public void ontick(TickEvent tickEvent) {
        Weather.mc.theWorld.setWorldTime((long)TIME.getValueState().intValue());
    }

    static {
        TIME = new ValueManager<Double>("Weather_Time", 11000.0, 0.0, 24000.0, 500.0);
    }
}

