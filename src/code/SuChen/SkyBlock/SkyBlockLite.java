/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Mod
 */
package code.SuChen.SkyBlock;

import code.SuChen.SkyBlock.command.commands.CommandManager;
import code.SuChen.SkyBlock.gui.clickgui.ClickGui;
import code.SuChen.SkyBlock.modules.ModuleManager;
import code.SuChen.SkyBlock.util.config.ConfigLoader;
import code.SuChen.SkyBlock.util.fontManager.FontManager;
import net.minecraftforge.fml.common.Mod;

@Mod(modid="keystrokesmod", name="KeystrokesMod", version="1.0")
public class SkyBlockLite {
    public static String CLIENT_name;
    public static String CLIENT_NAME;
    public static double CLEINT_VERSION;
    public static String Clientcode;
    public static boolean isClientLoading;
    public static SkyBlockLite instance;
    public static FontManager fontManager;
    public FontManager fontMgr;
    public ModuleManager modMgr;
    public ConfigLoader fileMgr;
    public static int taskbarprogress;
    public CommandManager cmdMgr;
    public ClickGui Newclickface;

    public SkyBlockLite() {
        instance = this;
        isClientLoading = true;
    }

    static {
        CLIENT_name = "SkyBlockLite";
        CLIENT_NAME = "SkyBlock";
        CLEINT_VERSION = 4.6;
        Clientcode = "SuChen";
    }
}

