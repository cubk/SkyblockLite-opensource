/*
 * Decompiled with CFR 0.152.
 */
package code.SuChen.SkyBlock.modules.modules;

import code.SuChen.SkyBlock.modules.Module;
import code.SuChen.SkyBlock.modules.ModuleType;
import code.SuChen.SkyBlock.modules.ValueManager;

public class ClickGuiModule
extends Module {
    public ValueManager<Boolean> BlurBlock;
    public ValueManager<Boolean> Sound;

    public ClickGuiModule() {
        super("ClickGui", ModuleType.RENDER);
        this.BlurBlock = new ValueManager<Boolean>("ClickGui_BlurBlockground", false);
        this.Sound = new ValueManager<Boolean>("ClickGui_Sound", false);
        this.setKey(24);
    }
}

