/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.command.ICommand
 */
package code.SuChen.SkyBlock.util.timers;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;

public class SpeedTimer
implements ICommand {
    public static Minecraft mc;

    static {
        mc = Minecraft.getMinecraft();
    }
}

