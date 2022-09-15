/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Keyboard
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.events.UpdateEvent;
import com.darkmagician6.eventapi.EventTarget;
import java.util.Objects;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class InvMove
extends Module {
    public InvMove() {
        super("InvMove", ModuleType.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent updateEvent) {
        block3: {
            KeyBinding[] keyBindingArray;
            block2: {
                KeyBinding[] keyBindingArray2 = new KeyBinding[6];
                keyBindingArray2[0] = InvMove.mc.gameSettings.keyBindForward;
                keyBindingArray2[1] = InvMove.mc.gameSettings.keyBindBack;
                keyBindingArray2[2] = InvMove.mc.gameSettings.keyBindLeft;
                keyBindingArray2[3] = InvMove.mc.gameSettings.keyBindRight;
                keyBindingArray2[4] = InvMove.mc.gameSettings.keyBindSprint;
                keyBindingArray2[5] = InvMove.mc.gameSettings.keyBindJump;
                keyBindingArray = keyBindingArray2;
                if (InvMove.mc.currentScreen == null) break block2;
                if (InvMove.mc.currentScreen instanceof GuiChat) break block2;
                KeyBinding[] keyBindingArray3 = keyBindingArray;
                int n = keyBindingArray.length;
                for (int i = 0; i < n; ++i) {
                    KeyBinding keyBinding = keyBindingArray3[i];
                    KeyBinding.setKeyBindState((int)keyBinding.getKeyCode(), (boolean)Keyboard.isKeyDown((int)keyBinding.getKeyCode()));
                }
                break block3;
            }
            if (!Objects.isNull(InvMove.mc.currentScreen)) break block3;
            KeyBinding[] keyBindingArray4 = keyBindingArray;
            int n = keyBindingArray.length;
            for (int i = 0; i < n; ++i) {
                KeyBinding keyBinding = keyBindingArray4[i];
                if (Keyboard.isKeyDown((int)keyBinding.getKeyCode())) continue;
                KeyBinding.setKeyBindState((int)keyBinding.getKeyCode(), (boolean)false);
            }
        }
    }
}

