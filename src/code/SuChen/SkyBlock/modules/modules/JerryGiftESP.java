/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.events.RenderEvent;
import code.SuChen.SkyBlock.util.render.RenderUtil;
import code.SuChen.SkyBlock.gui.altlogin.GuiClientButton;
import code.SuChen.SkyBlock.util.world.HypixelUtil;
import com.darkmagician6.eventapi.EventTarget;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class JerryGiftESP
extends Module {
    private GuiClientButton handler1;
    private GuiClientButton handler2;
    public static List<Entity> Souls;

    public JerryGiftESP() {
        super("JerryGiftESP", ModuleType.SKYBLOCK);
        this.handler1 = new GuiClientButton(0);
        this.handler2 = new GuiClientButton(1);
    }

    @EventTarget
    public void onRender(RenderEvent renderEvent) {
        if (!HypixelUtil.isSkyBlock()) {
            return;
        }
        Entity entity = JerryGiftESP.mc.objectMouseOver.entityHit;
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        for (Object e : JerryGiftESP.mc.theWorld.loadedEntityList) {
            EntityLivingBase entityLivingBase;
            if (!(e instanceof EntityLivingBase) || !this.isvalid(entityLivingBase = (EntityLivingBase)e)) continue;
            float f = (float)(JerryGiftESP.mc.thePlayer.posX - entityLivingBase.posX);
            float f2 = (float)(JerryGiftESP.mc.thePlayer.posY - entityLivingBase.posY);
            float f3 = (float)(JerryGiftESP.mc.thePlayer.posZ - entityLivingBase.posZ);
            float f4 = MathHelper.sqrt_float((float)(f * f + f2 * f2 + f3 * f3));
            if (JerryGiftESP.mc.objectMouseOver != null) {
                if (JerryGiftESP.mc.objectMouseOver.entityHit != null && (this.handler1.canExcecute() || this.handler2.canExcecute()) && !Souls.contains(entity)) {
                    Souls.add(entity);
                }
            }
            if (!Souls.contains(entityLivingBase)) {
                RenderUtil.SoulsCheckESP((Entity)entityLivingBase, new Color(255, 255, 255), renderEvent);
            }
            if (!Souls.contains(entityLivingBase)) continue;
            RenderUtil.SoulsCheckESP((Entity)entityLivingBase, new Color(0, 255, 0), renderEvent);
        }
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
    }

    static {
        Souls = new ArrayList<Entity>();
    }
}

