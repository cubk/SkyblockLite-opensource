/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.util.vector.Vector3f
 */
package code.SuChen.SkyBlock.util.misc;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.util.vector.Vector3f;

public class WrappedEntity {
    protected Minecraft mc;
    private float startX;
    private float startY;
    private float startZ;
    private float endX;
    private float endY;
    private float endZ;
    private static final float MAX_STEP = 0.1f;
    private ArrayList<Vector3f> positions;
    private EntityLivingBase entity;

    public WrappedEntity(EntityLivingBase entityLivingBase) {
        this.mc = Minecraft.getMinecraft();
        this.positions = new ArrayList();
        this.startX = (float)this.mc.thePlayer.posX;
        this.startY = (float)this.mc.thePlayer.posY + 1.0f;
        this.startZ = (float)this.mc.thePlayer.posZ;
        this.endX = (float)entityLivingBase.posX;
        this.endY = (float)entityLivingBase.posY + entityLivingBase.height / 2.0f;
        this.endZ = (float)entityLivingBase.posZ;
        this.entity = entityLivingBase;
        this.positions.clear();
        this.addPositions();
    }
}

