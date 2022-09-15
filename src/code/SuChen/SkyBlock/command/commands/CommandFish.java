/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package code.SuChen.SkyBlock.command.commands;

import code.SuChen.SkyBlock.command.Command;
import net.minecraft.client.Minecraft;

public class CommandFish
extends Command {
    public static Minecraft mc;

    public CommandFish(String[] stringArray) {
        super(stringArray);
        this.setArgs("Fish");
    }

    static {
        mc = Minecraft.getMinecraft();
    }
}

