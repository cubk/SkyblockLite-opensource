/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.command.commands;

import code.SuChen.SkyBlock.command.Command;

import java.util.ArrayList;

public class CommandManager {
    private static ArrayList<Command> commands;

    public CommandManager() {
        this.add(new CommandHelp(new String[]{"help"}));
        this.add(new CommandBind(new String[]{"bind"}));
        this.add(new CommandFish(new String[]{"Fish"}));
        this.add(new CommandToggle(new String[]{"toggle", "t"}));
    }

    public static /* bridge */ ArrayList<Command> getCommands() {
        return commands;
    }

    public static /* bridge */ String removeSpaces(String string) {
        String string2 = " ";
        String string3 = "  ";
        while (string.contains(string3)) {
            string = string.replace(string3, string2);
        }
        return string;
    }

    static {
        commands = new ArrayList();
    }
}

