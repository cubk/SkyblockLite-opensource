/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.GuiConnecting
 *  net.minecraft.client.multiplayer.ServerData
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package code.SuChen.SkyBlock.injection.mixins.gui;

import code.SuChen.SkyBlock.util.misc.ServerUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(value=Side.CLIENT)
@Mixin(value={GuiConnecting.class})
public abstract class MixinGuiConnecting
extends GuiScreen {
    @Inject(method="connect", at={@At(value="HEAD")})
    private void headConnect(String ip, int port, CallbackInfo callbackInfo) {
        ServerUtil.serverData = new ServerData("", ip + ":" + port, false);
    }
}

