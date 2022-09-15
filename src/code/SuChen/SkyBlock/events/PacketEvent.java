/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 */
package code.SuChen.SkyBlock.events;

import com.darkmagician6.eventapi.events.callables.EventCancellable;
import com.darkmagician6.eventapi.types.EventType;
import net.minecraft.network.Packet;

public class PacketEvent
extends EventCancellable {
    private final EventType eventType;
    public Packet packet;

    public PacketEvent(EventType eventType, Packet packet) {
        this.eventType = eventType;
        this.packet = packet;
    }
}

