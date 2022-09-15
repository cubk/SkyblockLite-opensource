/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 */
package code.SuChen.SkyBlock.util.world;

import code.SuChen.SkyBlock.modules.ValueManager;
import code.SuChen.SkyBlock.events.RenderEvent;
import code.SuChen.SkyBlock.gui.notification.NotificationManager;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import Code_By_SuChen.NotificationTypes;
import code.SuChen.SkyBlock.util.render.RenderUtil;
import com.darkmagician6.eventapi.EventTarget;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class SumEyeCheck
extends Module {
    private int open;
    public static List<EntityLivingBase> Eye;
    public static List<EntityLivingBase> Eyesize;
    public ValueManager<Boolean> GolemESP;
    public ValueManager<Boolean> ESP;
    public ValueManager<Boolean> Tracers;

    public SumEyeCheck() {
        super("SumEyeCheck", ModuleType.SKYBLOCK);
        this.GolemESP = new ValueManager<Boolean>("SumEyeCheck_GolemESP", true);
        this.ESP = new ValueManager<Boolean>("SumEyeCheck_ESP", true);
        this.Tracers = new ValueManager<Boolean>("SumEyeCheck_Tracers", true);
    }

    @EventTarget
    public void pre(RenderEvent renderEvent) {
        EntityLivingBase entityLivingBase;
        if (HypixelUtil.isDragons()) {
            ++this.open;
            if (this.open == 1) {
                NotificationManager.sendChatMessage("\u4f60\u5df2\u7ecf\u8fdb\u5165Dragons\ufffd?\u542f\u76d1\u6d4bSummoning Eye", NotificationTypes.SUCCESS);
            }
        } else {
            if (this.open > 0) {
                NotificationManager.sendChatMessage("\u4f60\u5df2\u7ecf\u79bb\ufffd?Dragons\u5173\u95ed\u76d1\u6d4bSummoning Eye", NotificationTypes.ERROR);
                this.open = 0;
            }
            return;
        }
        for (Object e : SumEyeCheck.mc.theWorld.loadedEntityList) {
            if (!(e instanceof EntityLivingBase) || !this.Golem(entityLivingBase = (EntityLivingBase)e) || !this.ESP.getValueState().booleanValue()) continue;
            RenderUtil.SumEyeCheckbox((Entity)entityLivingBase, new Color(0, 125, 255), renderEvent);
        }
        for (Object e : SumEyeCheck.mc.theWorld.loadedEntityList) {
            if (!(e instanceof EntityLivingBase) || !this.Eye(entityLivingBase = (EntityLivingBase)e)) continue;
            if (this.Tracers.getValueState().booleanValue()) {
                this.drawLine(entityLivingBase);
            }
            if (this.ESP.getValueState().booleanValue()) {
                RenderUtil.SumEyeCheckbox((Entity)entityLivingBase, new Color(255, 255, 255), renderEvent);
            }
            if (!Eye.contains(entityLivingBase) && !(entityLivingBase.getHealth() <= 0.0f)) {
                float f = (float)(SumEyeCheck.mc.thePlayer.posX - entityLivingBase.posX);
                float f2 = (float)(SumEyeCheck.mc.thePlayer.posY - entityLivingBase.posY);
                float f3 = (float)(SumEyeCheck.mc.thePlayer.posZ - entityLivingBase.posZ);
                float f4 = MathHelper.sqrt_float((float)(f * f + f2 * f2 + f3 * f3));
                Eye.add(entityLivingBase);
                Eyesize.add(entityLivingBase);
                NotificationManager.sendChatMessage("\u00a7a\u8ddd\u79bb\u00a7c" + (int)f4 + "\u00a7a\u7c73\u53d1\u73b0\u7b2c\u00a76" + Eyesize.size() + "\u00a7a\u53eaSummoning Eye\u76ee\u6807\u5df2\u6807\ufffd?!,\u5750\u6807\u00a7BX: \u00a7r" + (int)entityLivingBase.posX + " \u00a7BY: \u00a7r" + (int)entityLivingBase.posY + " \u00a7BZ: \u00a7r" + (int)entityLivingBase.posZ, NotificationTypes.SUCCESS);
                SumEyeCheck.mc.thePlayer.playSound("note.bass", 20.0f, 20.0f);
            }
            if (!Eye.contains(entityLivingBase) || !(entityLivingBase.getHealth() <= 0.0f)) continue;
            NotificationManager.sendChatMessage("\u00a73\u76ee\u6807\u5df2\u7ecf\u88ab\u51fb\u6bd9\uff01\uff01\uff01\uff01\uff01", NotificationTypes.SUCCESS);
            SumEyeCheck.mc.thePlayer.playSound("note.snare", 20.0f, 1.0f);
            Eye.remove(entityLivingBase);
        }
    }

    static {
        Eye = new ArrayList<EntityLivingBase>();
        Eyesize = new ArrayList<EntityLivingBase>();
    }
}

