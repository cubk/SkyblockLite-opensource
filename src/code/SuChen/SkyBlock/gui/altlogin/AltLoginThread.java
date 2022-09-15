/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package code.SuChen.SkyBlock.gui.altlogin;

import net.minecraft.client.Minecraft;

public final class AltLoginThread
extends Thread {
    private final String password;
    private String status;
    private final String username;
    private Minecraft mc;

    public AltLoginThread(String string, String string2) {
        super("Alt Login Thread");
        this.mc = Minecraft.getMinecraft();
        this.username = string;
        this.password = string2;
        this.status = "Waiting...";
    }
}

