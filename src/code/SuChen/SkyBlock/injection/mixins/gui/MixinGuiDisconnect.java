//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Documents\mcp918\conf"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiDisconnected
 */
package code.SuChen.SkyBlock.injection.mixins.gui;

import code.SuChen.SkyBlock.util.misc.ServerUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GuiDisconnected.class})
public abstract class MixinGuiDisconnect
extends MixinGuiScreen {
    @Shadow
    private int field_175353_i;
    private GuiButton reconnectButton;
    private GuiButton forgeBypassButton;
    private int reconnectTimer;

    @Inject(method="initGui", at={@At(value="RETURN")})
    private void initGui(CallbackInfo callbackInfo) {
        this.reconnectTimer = 0;
        this.reconnectButton = new GuiButton(1, this.width / 2 - 100, this.height / 2 + this.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT + 22, "Reconnect");
        this.buttonList.add(this.reconnectButton);
    }

    @Inject(method="actionPerformed", at={@At(value="HEAD")})
    private void actionPerformed(GuiButton button, CallbackInfo callbackInfo) {
        switch (button.id) {
            case 1: {
                ServerUtil.connectToLastServer();
            }
        }
    }

    @Override
    public void updateScreen() {
        ++this.reconnectTimer;
        if (this.reconnectTimer > 200) {
            ServerUtil.connectToLastServer();
        }
    }

    @Inject(method="drawScreen", at={@At(value="RETURN")})
    private void drawScreen(CallbackInfo callbackInfo) {
        this.reconnectButton.displayString = "Reconnect (" + (10 - this.reconnectTimer / 20) + ")";
    }
}

