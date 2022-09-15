//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Documents\mcp918\conf"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.FoodStats
 */
package code.SuChen.SkyBlock.injection.mixins.entity;

import code.SuChen.SkyBlock.events.TickEvent;
import com.darkmagician6.eventapi.EventManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityPlayer.class})
public abstract class MixinEntityPlayer
extends MixinEntityLivingBase {
    @Shadow
    protected float speedInAir;
    @Shadow
    private int itemInUseCount;
    @Shadow
    protected int flyToggleTimer;
    @Shadow
    public PlayerCapabilities capabilities;

    @Shadow
    public abstract boolean isUsingItem();

    @Shadow
    public abstract ItemStack getHeldItem();

    @Shadow
    public abstract FoodStats getFoodStats();

    public float getSpeedInAir() {
        this.speedInAir = 0.02f;
        return 0.02f;
    }

    public void setSpeedInAir(float speed) {
        this.speedInAir = speed;
    }

    public int getitemInUseCount() {
        return this.itemInUseCount;
    }

    public void setitemInUseCount(int itemInUseCount) {
        this.itemInUseCount = itemInUseCount;
    }

    @Inject(method="onUpdate", at={@At(value="HEAD")})
    private void Tick(CallbackInfo ci) {
        EventManager.call(new TickEvent());
    }
}

