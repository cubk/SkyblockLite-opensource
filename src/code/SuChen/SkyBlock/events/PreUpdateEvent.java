/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package code.SuChen.SkyBlock.events;

import net.minecraft.entity.Entity;

public class PreUpdateEvent {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public PreUpdateEvent(double d, double d2, double d3, float f, float f2) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.yaw = f;
        this.pitch = f2;
    }

    public PreUpdateEvent(double d, double d2, double d3) {
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }

    public PreUpdateEvent(Entity entity) {
        this.x = entity.posX;
        this.y = entity.posY;
        this.z = entity.posZ;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }

    public PreUpdateEvent(int n, int n2, int n3) {
        this.x = n;
        this.y = n2;
        this.z = n3;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }
}

