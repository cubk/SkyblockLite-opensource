/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 */
package code.SuChen.SkyBlock.injection.mixins.client;

import code.SuChen.SkyBlock.events.BlockEvent;
import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PlayerControllerMP.class})
public abstract class MixinPlayerControllerMP {
    @Inject(method="onPlayerDamageBlock", at={@At(value="HEAD")}, cancellable=true)
    public void onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing, CallbackInfoReturnable callbackInfoReturnable) {
        BlockEvent event = new BlockEvent(posBlock, directionFacing);
        EventManager.call(event);
        if (event.isCancelled()) {
            callbackInfoReturnable.cancel();
        }
    }
}

