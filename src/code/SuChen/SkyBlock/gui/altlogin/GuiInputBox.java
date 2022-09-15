/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 */
package code.SuChen.SkyBlock.gui.altlogin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class GuiInputBox
extends Gui {
    private final FontRenderer fontRenderer;
    private final int xPos;
    private final int yPos;
    private final int width;
    private final int height;
    private String text;
    private int maxStringLength;
    private int cursorCounter;
    private boolean enableBackgroundDrawing;
    private boolean canLoseFocus;
    public boolean isFocused;
    private boolean isEnabled;
    private int i;
    private int cursorPosition;
    private int selectionEnd;
    private int enabledColor;
    private int disabledColor;
    private boolean b;

    public GuiInputBox(FontRenderer fontRenderer, int n, int n2, int n3, int n4) {
        this.text = "";
        this.maxStringLength = 50;
        this.enableBackgroundDrawing = true;
        this.canLoseFocus = true;
        this.isFocused = false;
        this.isEnabled = true;
        this.i = 0;
        this.cursorPosition = 0;
        this.selectionEnd = 0;
        this.enabledColor = 0xE0E0E0;
        this.disabledColor = 0x707070;
        this.b = true;
        this.fontRenderer = fontRenderer;
        this.xPos = n;
        this.yPos = n2;
        this.width = n3;
        this.height = n4;
    }
}

