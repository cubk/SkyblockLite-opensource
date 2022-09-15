//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Documents\mcp918\conf"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package code.SuChen.SkyBlock.injection.mixins.entity;

import code.SuChen.SkyBlock.injection.mixins.entity.MixinEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={EntityLivingBase.class})
public abstract class MixinEntityLivingBase
extends MixinEntity {
    @Shadow
    public int jumpTicks;

    @Shadow
    public abstract boolean isPotionActive(Potion var1);

    @Shadow
    public abstract PotionEffect getActivePotionEffect(Potion var1);

    @Shadow
    public void onLivingUpdate() {
    }
}

