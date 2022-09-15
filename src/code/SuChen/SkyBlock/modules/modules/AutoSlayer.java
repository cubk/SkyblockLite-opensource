/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.server.S02PacketChat
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.events.PacketEvent;
import code.SuChen.SkyBlock.util.timers.TimerHelper;
import code.SuChen.SkyBlock.events.UpdateEvent;
import code.SuChen.SkyBlock.modules.ValueManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.network.play.server.S02PacketChat;

public class AutoSlayer
extends Module {
    private TimerHelper openmenu;
    private boolean Spider;
    private boolean Zombie;
    private boolean Wolf;
    public int stage;
    public static ValueManager<String> mode;

    public AutoSlayer() {
        super("AutoSlayer", ModuleType.SKYBLOCK);
        this.openmenu = new TimerHelper();
        this.stage = 0;
        AutoSlayer.mode.mode.add("Tier 4");
    }

    @EventTarget
    private void packet(PacketEvent packetEvent) {
        S02PacketChat s02PacketChat = (S02PacketChat)packetEvent.getPacket();
        String string = s02PacketChat.getChatComponent().toString();
        String string2 = s02PacketChat.getChatComponent().getFormattedText();
        if (string.toString().contains("text='\u00a72\u00a7l[OPEN MENU]'")) {
            AutoSlayer.mc.thePlayer.sendChatMessage(AutoSlayer.getHimName(string, "clickEvent=ClickEvent{action=RUN_COMMAND, value='", "'}"));
        }
        if (string2.contains("Talk to Maddox to claim your Zombie Slayer XP!")) {
            this.Zombie = true;
            this.OpenMaddox();
        }
        if (string2.contains("Talk to Maddox to claim your Spider Slayer XP!")) {
            this.Spider = true;
            this.OpenMaddox();
        }
        if (string2.contains("Talk to Maddox to claim your Wolf Slayer XP!")) {
            this.Wolf = true;
            this.OpenMaddox();
        }
        if (string2.contains("Slay 4,800 Combat XP worth of Zombies.") || string2.contains("Slay 2,000 Combat XP worth of Spiders.") || string2.contains("Slay 3,000 Combat XP worth of Wolves.")) {
            this.Zombie = false;
            this.Spider = false;
            this.Wolf = false;
        }
        if (AutoSlayer.mc.thePlayer.inventory.getCurrentItem().getDisplayName().contains("Maddox Batphone") && string2.contains("Ring... Ring... Ring...") && (this.Zombie || this.Spider || this.Wolf)) {
            this.Zombie = false;
            this.Spider = false;
            this.Wolf = false;
        }
    }

    @EventTarget
    private void update(UpdateEvent updateEvent) {
        if (this.Zombie) {
            this.Revenant_Horror();
        }
        if (this.Spider) {
            this.Tarantula_Broodfather();
        }
        if (this.Wolf) {
            this.Sven_Packmaster();
        }
    }

    public static /* bridge */ String getHimName(String string, String string2, String string3) {
        int n;
        String string4 = "";
        n = string2 == null || string2.isEmpty() ? 0 : ((n = string.indexOf(string2)) > -1 ? (n += string2.length()) : 0);
        int n2 = string.indexOf(string3, n);
        if (n2 < 0 || string3 == null || string3.isEmpty()) {
            n2 = string.length();
        }
        string4 = string.substring(n, n2);
        return string4;
    }

    static {
        mode = new ValueManager("AutoSlayer", "Boss Tier", 0);
    }
}

