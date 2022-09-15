/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package code.SuChen.SkyBlock.injection.mixins.render;

import code.SuChen.SkyBlock.events.RenderTextEvent;
import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@SideOnly(value=Side.CLIENT)
@Mixin(value={FontRenderer.class})
public class MixinFontRenderer {
    @ModifyVariable(method="renderString", at=@At(value="HEAD"), ordinal=0)
    private String renderString(String string) {
        if (string == null) {
            return string;
        }
        RenderTextEvent textEvent = new RenderTextEvent(string);
        EventManager.call(textEvent);
        return textEvent.getText();
    }

    @ModifyVariable(method="getStringWidth", at=@At(value="HEAD"), ordinal=0)
    private String getStringWidth(String string) {
        if (string == null) {
            return string;
        }
        RenderTextEvent textEvent = new RenderTextEvent(string);
        EventManager.call(textEvent);
        return textEvent.getText();
    }
}

