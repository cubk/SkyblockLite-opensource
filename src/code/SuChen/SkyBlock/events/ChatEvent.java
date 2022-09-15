/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 */
package code.SuChen.SkyBlock.events;

import com.darkmagician6.eventapi.events.Event;
import net.minecraft.network.Packet;

public class ChatEvent
implements Event {
    public Packet packet;
    public String message;
    public boolean cancelled;

    public ChatEvent(Packet packet) {
        this.packet = packet;
    }

    public ChatEvent(String string) {
        this.message = string;
    }
}

