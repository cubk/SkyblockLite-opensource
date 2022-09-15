/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.util.world;

import code.SuChen.SkyBlock.events.PreUpdateEvent;

public class Location {
    public int ticks;
    public PreUpdateEvent location;
    public String text;

    public Location(PreUpdateEvent preUpdateEvent, String string) {
        this.location = preUpdateEvent;
        this.text = string;
        this.ticks = 0;
    }
}

