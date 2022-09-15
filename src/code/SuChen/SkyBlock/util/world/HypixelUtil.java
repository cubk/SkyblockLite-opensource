/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 *  net.minecraft.scoreboard.Score
 *  net.minecraft.scoreboard.ScoreObjective
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Scoreboard
 *  net.minecraft.scoreboard.Team
 */
package code.SuChen.SkyBlock.util.world;

import code.SuChen.SkyBlock.modules.modules.XRayModule;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;

public class HypixelUtil {
    public static Minecraft mc;
    private static final Pattern STRIP_COLOR_PATTERN;

    public static /* bridge */ boolean isSkyBlock() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            String[] stringArray;
            String string = HypixelUtil.stripColor(scoreObjective.getDisplayName());
            for (String string2 : stringArray = new String[]{"SKYBLOCK", "\u7a7a\u5c9b\u751f\u5b58"}) {
                if (!string.contains(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean isSkyWars() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            String[] stringArray;
            String string = HypixelUtil.stripColor(scoreObjective.getDisplayName());
            for (String string2 : stringArray = new String[]{"SKYWARS", "\u7a7a\u5c9b\u6218\u4e89"}) {
                if (!string.contains(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean isBlitz() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            String[] stringArray;
            String string = HypixelUtil.stripColor(scoreObjective.getDisplayName());
            for (String string2 : stringArray = new String[]{"BLITZ SG", "\u95ea\u7535\u9965\u997f\u6e38\u620f"}) {
                if (!string.contains(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean isPit() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            String[] stringArray;
            String string = HypixelUtil.stripColor(scoreObjective.getDisplayName());
            for (String string2 : stringArray = new String[]{"Pit"}) {
                if (!string.contains(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean Island() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            Collection collection = scoreboard.getSortedScores(scoreObjective);
            if (collection.size() > 15) {
                collection = Lists.newArrayList((Iterable)Iterables.skip((Iterable)collection, (int)(collection.size() - 15)));
            }
            for (Score score : collection) {
                ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
                String string = HypixelUtil.stripColor(ScorePlayerTeam.formatPlayerName((Team)scorePlayerTeam, (String)score.getPlayerName()));
                if (!string.contains("Your Isla") || !string.contains("nd")) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean ZoomGames() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            String[] stringArray;
            String string = HypixelUtil.stripColor(scoreObjective.getDisplayName());
            for (String string2 : stringArray = new String[]{"UHC", "\u6781\u9650\u751f\u5b58\u51a0\u519b", "\u6781\u9650\u751f\u5b58", "\u95ea\u7535\u9965\u997f\u6e38\u620f", "BLITZ SG", "MEGA WALLS", "\u8d85\u7ea7\u6218\u5899", "\u4e22\u9505\u5927\u6218"}) {
                if (!string.contains(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean inDeathmatch() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            Collection collection = scoreboard.getSortedScores(scoreObjective);
            if (collection.size() > 15) {
                collection = Lists.newArrayList((Iterable)Iterables.skip((Iterable)collection, (int)(collection.size() - 15)));
            }
            for (Score score : collection) {
                ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
                String string = HypixelUtil.stripColor(ScorePlayerTeam.formatPlayerName((Team)scorePlayerTeam, (String)score.getPlayerName()));
                if (!string.contains("Game ends in") && !string.contains("\u6e38\u620f\u7ed3\u675f\u5012\u8ba1\ufffd?") || !HypixelUtil.isUHCgame()) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean inUHCPVPStart() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            Collection collection = scoreboard.getSortedScores(scoreObjective);
            if (collection.size() > 15) {
                collection = Lists.newArrayList((Iterable)Iterables.skip((Iterable)collection, (int)(collection.size() - 15)));
            }
            for (Score score : collection) {
                ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
                String string = HypixelUtil.stripColor(ScorePlayerTeam.formatPlayerName((Team)scorePlayerTeam, (String)score.getPlayerName()));
                if (!string.contains("Deathmatch in") && !string.contains("\u6b7b\u4ea1\u7ade\u8d5b\u5012\u8ba1\ufffd?") || !HypixelUtil.isUHCgame()) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean isMWgame() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            String[] stringArray;
            String string = HypixelUtil.stripColor(scoreObjective.getDisplayName());
            for (String string2 : stringArray = new String[]{"MEGA WALLS", "\u8d85\u7ea7\u6218\u5899"}) {
                if (!string.contains(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean isUHCgame() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            String[] stringArray;
            String string = HypixelUtil.stripColor(scoreObjective.getDisplayName());
            for (String string2 : stringArray = new String[]{"HYPIXEL UHC", "UHC", "UHC CHAMPIONS", "\u6781\u9650\u751f\u5b58\u51a0\u519b", "\u6781\u9650\u751f\u5b58"}) {
                if (!string.contains(string2)) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean isBlitzGame() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            Collection collection = scoreboard.getSortedScores(scoreObjective);
            if (collection.size() > 15) {
                collection = Lists.newArrayList((Iterable)Iterables.skip((Iterable)collection, (int)(collection.size() - 15)));
            }
            for (Score score : collection) {
                ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
                String string = HypixelUtil.stripColor(ScorePlayerTeam.formatPlayerName((Team)scorePlayerTeam, (String)score.getPlayerName()));
                if (!string.contains("\ufffd?\ufffd?") && !string.contains("\u5012\u8ba1\ufffd?") && !string.contains("\ufffd?\ufffd?") && !string.contains("Starting") && !string.contains("Open")) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean isMurder() {
        String[] stringArray;
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        String string = HypixelUtil.stripColor(scoreObjective.getDisplayName());
        for (String string2 : stringArray = new String[]{"MURDER MYSTERY", "\u5bc6\u5ba4\ufffd?\ufffd?"}) {
            if (!string.contains(string2)) continue;
            return true;
        }
        return false;
    }

    public static /* bridge */ boolean isDragons() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            Collection collection = scoreboard.getSortedScores(scoreObjective);
            if (collection.size() > 15) {
                collection = Lists.newArrayList((Iterable)Iterables.skip((Iterable)collection, (int)(collection.size() - 15)));
            }
            for (Score score : collection) {
                ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
                String string = HypixelUtil.keepLettersOnly(HypixelUtil.stripColor(ScorePlayerTeam.formatPlayerName((Team)scorePlayerTeam, (String)score.getPlayerName())));
                if (!string.contains("Dragons Nest")) continue;
                return true;
            }
        }
        return false;
    }

    public static /* bridge */ boolean isDungeon() {
        Scoreboard scoreboard = HypixelUtil.mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = HypixelUtil.mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            Collection collection = scoreboard.getSortedScores(scoreObjective);
            if (collection.size() > 15) {
                collection = Lists.newArrayList((Iterable)Iterables.skip((Iterable)collection, (int)(collection.size() - 15)));
            }
            for (Score score : collection) {
                ScorePlayerTeam scorePlayerTeam = scoreboard.getPlayersTeam(score.getPlayerName());
                String string = HypixelUtil.keepLettersOnly(HypixelUtil.stripColor(ScorePlayerTeam.formatPlayerName((Team)scorePlayerTeam, (String)score.getPlayerName())));
                if (!string.contains("Dungeon Cleared")) continue;
                return true;
            }
        }
        return false;
    }

    private static /* bridge */ String keepLettersOnly(String string) {
        return Pattern.compile("[^a-z A-Z]").matcher(string).replaceAll("");
    }

    private static /* bridge */ String stripColor(String string) {
        return STRIP_COLOR_PATTERN.matcher(string).replaceAll("");
    }

    public static /* bridge */ boolean getCNHypixelServer() {
        return XRayModule.onServer("x19hypixel") || XRayModule.onServer("59.111.137.84");
    }

    static {
        mc = Minecraft.getMinecraft();
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)\u00a7[0-9A-FK-OR]");
    }
}

