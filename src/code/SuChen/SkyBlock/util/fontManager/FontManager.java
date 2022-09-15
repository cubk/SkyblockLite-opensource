/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package code.SuChen.SkyBlock.util.fontManager;

import code.SuChen.SkyBlock.util.fontManager.UnicodeFontRenderer;
import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class FontManager {
    private HashMap<String, HashMap<Float, UnicodeFontRenderer>> fonts = new HashMap();
    public UnicodeFontRenderer tahoma15;
    public UnicodeFontRenderer tahoma16;
    public UnicodeFontRenderer tahoma17;
    public UnicodeFontRenderer tahoma30;
    public UnicodeFontRenderer sansation18;
    public UnicodeFontRenderer simpleton14;
    public UnicodeFontRenderer simpleton17;
    public UnicodeFontRenderer simpleton25;
    public UnicodeFontRenderer simpleton30;
    public UnicodeFontRenderer simpleton41;
    public UnicodeFontRenderer verdana12;
    public UnicodeFontRenderer verdana13;
    public UnicodeFontRenderer verdana15;
    public UnicodeFontRenderer consolasbold15;
    public UnicodeFontRenderer arraylist18 = this.getFont("arraylist", 18.0f);

    public FontManager() {
        this.tahoma15 = this.getFont("tahoma", 15.0f);
        this.tahoma16 = this.getFont("tahoma", 16.0f);
        this.tahoma17 = this.getFont("tahoma", 17.0f);
        this.tahoma30 = this.getFont("tahoma", 30.0f);
        this.sansation18 = this.getFont("sansation", 18.0f);
        this.simpleton14 = this.getFont("simpleton", 14.0f, true);
        this.simpleton17 = this.getFont("simpleton", 17.0f, true);
        this.simpleton25 = this.getFont("simpleton", 25.0f, true);
        this.simpleton30 = this.getFont("simpleton", 30.0f, true);
        this.simpleton41 = this.getFont("simpleton", 41.0f, true);
        this.verdana12 = this.getFont("verdana", 12.0f);
        this.verdana13 = this.getFont("verdana", 13.0f);
        this.verdana15 = this.getFont("verdana", 15.0f);
    }

    public UnicodeFontRenderer getFont(String name, float size) {
        UnicodeFontRenderer unicodeFont = null;
        try {
            if (this.fonts.containsKey(name) && this.fonts.get(name).containsKey(Float.valueOf(size))) {
                return this.fonts.get(name).get(Float.valueOf(size));
            }
            InputStream inputStream = this.getClass().getResourceAsStream("fonts/" + name + ".ttf");
            Font font = null;
            font = Font.createFont(0, inputStream);
            unicodeFont = new UnicodeFontRenderer(font.deriveFont(size));
            unicodeFont.setUnicodeFlag(true);
            unicodeFont.setBidiFlag(Minecraft.getMinecraft().getLanguageManager().isCurrentLanguageBidirectional());
            HashMap<Float, UnicodeFontRenderer> map = new HashMap<Float, UnicodeFontRenderer>();
            if (this.fonts.containsKey(name)) {
                map.putAll((Map)this.fonts.get(name));
            }
            map.put(Float.valueOf(size), unicodeFont);
            this.fonts.put(name, map);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return unicodeFont;
    }

    public UnicodeFontRenderer getFont(String name, float size, boolean b) {
        UnicodeFontRenderer unicodeFont = null;
        try {
            if (this.fonts.containsKey(name) && this.fonts.get(name).containsKey(Float.valueOf(size))) {
                return this.fonts.get(name).get(Float.valueOf(size));
            }
            InputStream inputStream = this.getClass().getResourceAsStream("fonts/" + name + ".otf");
            Font font = null;
            font = Font.createFont(0, inputStream);
            unicodeFont = new UnicodeFontRenderer(font.deriveFont(size));
            unicodeFont.setUnicodeFlag(true);
            unicodeFont.setBidiFlag(Minecraft.getMinecraft().getLanguageManager().isCurrentLanguageBidirectional());
            HashMap<Float, UnicodeFontRenderer> map = new HashMap<Float, UnicodeFontRenderer>();
            if (this.fonts.containsKey(name)) {
                map.putAll((Map)this.fonts.get(name));
            }
            map.put(Float.valueOf(size), unicodeFont);
            this.fonts.put(name, map);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return unicodeFont;
    }
}

