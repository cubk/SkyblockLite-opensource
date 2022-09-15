/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.command.Command;
import code.SuChen.SkyBlock.command.commands.CommandManager;
import code.SuChen.SkyBlock.events.ChatEvent;
import com.darkmagician6.eventapi.EventTarget;

public class Commands
extends Module {
    public Commands() {
        super("Commands", ModuleType.WORLD);
    }

    @EventTarget
    public void onChat(ChatEvent chatEvent) {
        String string = CommandManager.removeSpaces(chatEvent.getMessage());
        if (chatEvent.getMessage().startsWith("-")) {
            for (Command command : CommandManager.getCommands()) {
                for (int i = 0; i < command.getCommands().length; ++i) {
                    if (!string.toLowerCase().split(" ")[0].equals("-" + command.getCommands()[i].toLowerCase())) continue;
                    chatEvent.setCancelled(true);
                    command.onCmd(string.split(" "));
                    return;
                }
            }
            return;
        }
    }
}

