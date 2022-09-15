/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.util.BlockPos
 */
package code.SuChen.SkyBlock.injection.mixins.render;

import code.SuChen.SkyBlock.modules.modules.XRayModule;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EffectRenderer.class})
public class MixinEffectRenderer {
    @Inject(method="addBlockDestroyEffects", at={@At(value="HEAD")}, cancellable=true)
    public void doRender(BlockPos pos, IBlockState state, CallbackInfo info) {
        if (XRayModule.BlockEffect) {
            info.cancel();
        }
    }
}

