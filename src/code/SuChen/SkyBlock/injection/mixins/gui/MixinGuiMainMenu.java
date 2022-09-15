//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Documents\mcp918\conf"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiMainMenu
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.I18n
 */
package code.SuChen.SkyBlock.injection.mixins.gui;

import code.SuChen.SkyBlock.gui.altlogin.GuiAltLogin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GuiMainMenu.class})
public abstract class MixinGuiMainMenu
extends GuiScreen {
    @Shadow
    private GuiButton realmsButton;

    @Overwrite
    private void addSingleplayerMultiplayerButtons(int p_addSingleplayerMultiplayerButtons_1_, int p_addSingleplayerMultiplayerButtons_2_) {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, p_addSingleplayerMultiplayerButtons_1_, I18n.format((String)"menu.singleplayer", (Object[])new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, p_addSingleplayerMultiplayerButtons_1_ + p_addSingleplayerMultiplayerButtons_2_ * 1, I18n.format((String)"menu.multiplayer", (Object[])new Object[0])));
        this.realmsButton = new GuiButton(185485, this.width / 2 + 2, p_addSingleplayerMultiplayerButtons_1_ + p_addSingleplayerMultiplayerButtons_2_ * 2, 98, 20, I18n.format((String)"menu.online", (Object[])new Object[0]).replace("Minecraft", "").trim());
        this.buttonList.add(this.realmsButton);
        this.realmsButton.visible = false;
        this.buttonList.add(new GuiButton(14, this.width / 2 + 2, p_addSingleplayerMultiplayerButtons_1_ + p_addSingleplayerMultiplayerButtons_2_ * 2, 98, 20, "AltLogin"));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 100, p_addSingleplayerMultiplayerButtons_1_ + p_addSingleplayerMultiplayerButtons_2_ * 2, 98, 20, I18n.format((String)"fml.menu.mods", (Object[])new Object[0])));
    }

    @Inject(method="actionPerformed", at={@At(value="HEAD")})
    private void onActionPerformed(GuiButton p_actionPerformed_1_, CallbackInfo info) {
        if (p_actionPerformed_1_.id == 14) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiAltLogin(this));
        }
    }
}

