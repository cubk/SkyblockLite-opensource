/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.modules;

import code.SuChen.SkyBlock.modules.modules.*;
import code.SuChen.SkyBlock.util.world.SoulsCheck;
import code.SuChen.SkyBlock.util.world.SumEyeCheck;

import java.util.ArrayList;

public class ModuleManager {
    public static ArrayList<Module> modList;
    public static ArrayList<Module> sortedModList;

    public ModuleManager() {
        this.addMod(new AutoClick());
        this.addMod(new Sprint());
        this.addMod(new InvMove());
        this.addMod(new HUD());
        this.addMod(new ClickGuiModule());
        this.addMod(new FullBright());
        this.addMod(new AutoTool());
        this.addMod(new Weather());
        this.addMod(new NoBlockEffect());
        this.addMod(new Commands());
        this.addMod(new AntiAFK());
        this.addMod(new AutoFish());
        this.addMod(new AFKSpider());
        this.addMod(new AutoMining());
        this.addMod(new AutoSlayer());
        this.addMod(new AutoMelody());
        this.addMod(new AutoClickCraft());
        this.addMod(new SoulsCheck());
        this.addMod(new JerryGiftESP());
        this.addMod(new GiftboxCheck());
        this.addMod(new SumEyeCheck());
        sortedModList = (ArrayList)modList.clone();
    }

    public static /* bridge */ Module getModByName(String string) {
        for (Module module : modList) {
            if (!module.getName().equalsIgnoreCase(string)) continue;
            return module;
        }
        return null;
    }

    public static /* bridge */ Module getModByClass(Class<? extends Module> clazz) {
        for (Module module : modList) {
            if (module.getClass() != clazz) continue;
            return module;
        }
        return null;
    }

    public static /* bridge */ ArrayList<Module> getModList() {
        return modList;
    }

    static {
        modList = new ArrayList();
        sortedModList = new ArrayList();
    }
}

