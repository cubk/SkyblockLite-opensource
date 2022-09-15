//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Administrator\Documents\mcp918\conf"!

/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.main.GameConfiguration
 *  net.minecraft.client.resources.LanguageManager
 *  net.minecraft.util.Session
 *  net.minecraft.util.Timer
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.input.Keyboard
 */
package code.SuChen.SkyBlock.injection.mixins.client;

import code.SuChen.SkyBlock.events.KeyInputEvent;
import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleManager;
import code.SuChen.SkyBlock.util.render.RenderUtil;
import code.SuChen.SkyBlock.SkyBlockLite;
import com.darkmagician6.eventapi.EventManager;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(value=Side.CLIENT)
@Mixin(value={Minecraft.class})
public abstract class MixinMinecraft {
    @Shadow
    public GuiScreen currentScreen;
    long lastFrame;
    @Shadow
    private Timer timer;
    @Shadow
    private int leftClickCounter;
    @Shadow
    private LanguageManager mcLanguageManager;
    @Shadow
    public int rightClickDelayTimer;
    @Shadow
    @Mutable
    @Final
    private Session session;

    @Shadow
    protected abstract void clickMouse();

    @Shadow
    protected abstract void rightClickMouse();

    @Inject(method="<init>", at={@At(value="RETURN")})
    private void minecraftConstructor(GameConfiguration gameConfig, CallbackInfo ci) {
        new SkyBlockLite();
    }

    @Inject(method="startGame", at={@At(value="FIELD", target="Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", shift=At.Shift.AFTER)})
    private void startGame(CallbackInfo ci) {
        SkyBlockLite.instance.onClientStart();
    }

    @Inject(method="runTick", at={@At(value="INVOKE", target="Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift=At.Shift.AFTER)})
    private void onKey(CallbackInfo ci) throws IOException {
        if (Keyboard.getEventKeyState() && this.currentScreen == null) {
            EventManager.call(new KeyInputEvent(Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey()));
        }
        if (Keyboard.getEventKeyState() && this.currentScreen == null) {
            for (Module m : ModuleManager.modList) {
                if (m.getKey() != (Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey())) continue;
                m.set(!m.isEnabled());
            }
        }
    }

    @Inject(method="runGameLoop", at={@At(value="HEAD")})
    private void runGameLoop(CallbackInfo ci) throws IOException {
        long i = System.nanoTime();
        long thisFrame = System.currentTimeMillis();
        RenderUtil.delta = (float)(thisFrame - this.lastFrame) / 1000.0f;
        this.lastFrame = thisFrame;
    }

    public Timer getTimer() {
        return this.timer;
    }
}

