/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package code.SuChen.SkyBlock.injection.mixins.gui;

import code.SuChen.SkyBlock.events.RenderGameOverlayEvent;
import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(value=Side.CLIENT)
@Mixin(value={GuiIngame.class})
public class MixinGuiIngame {
    @Inject(method="renderTooltip", at={@At(value="HEAD")})
    private void renderTooltip(CallbackInfo ci) {
    }

    @Inject(method="renderTooltip", at={@At(value="RETURN")})
    private void renderTooltip(ScaledResolution sr, float partialTicks, CallbackInfo ci) {
        EventManager.call(new RenderGameOverlayEvent(partialTicks));
    }
}

