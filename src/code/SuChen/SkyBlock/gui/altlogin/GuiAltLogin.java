/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 */
package code.SuChen.SkyBlock.gui.altlogin;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public final class GuiAltLogin
extends GuiScreen {
    private GuiInputBox password;
    private final GuiScreen previousScreen;
    private AltLoginThread thread;
    private GuiTextField username;
    private GuiButton loginButton;
    private GuiButton backButton;
    private GuiButton importbutton;

    public GuiAltLogin(GuiScreen guiScreen) {
        this.previousScreen = guiScreen;
    }
}

