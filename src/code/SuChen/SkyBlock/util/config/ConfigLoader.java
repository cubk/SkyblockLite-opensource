/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package code.SuChen.SkyBlock.util.config;

import code.SuChen.SkyBlock.SkyBlockLite;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.Minecraft;

public class ConfigLoader {
    private Minecraft mc;
    private String fileDir;

    public ConfigLoader() {
        this.mc = Minecraft.getMinecraft();
        this.fileDir = String.valueOf(this.mc.mcDataDir.getAbsolutePath()) + "/" + SkyBlockLite.CLIENT_NAME;
        File file = new File(this.fileDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            this.loadKeys();
            this.loadValues();
            this.loadMods();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}

