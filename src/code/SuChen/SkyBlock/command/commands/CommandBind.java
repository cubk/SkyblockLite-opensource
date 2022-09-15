/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package code.SuChen.SkyBlock.command.commands;

import code.SuChen.SkyBlock.command.Command;
import net.minecraft.client.Minecraft;

public class CommandBind
extends Command {
    public Minecraft mc;

    public CommandBind(String[] stringArray) {
        super(stringArray);
        this.mc = Minecraft.getMinecraft();
        this.setArgs(".bind <mod> <key>");
    }
}

