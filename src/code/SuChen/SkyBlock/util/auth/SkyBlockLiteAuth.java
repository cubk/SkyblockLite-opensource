/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package code.SuChen.SkyBlock.util.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import net.minecraft.client.Minecraft;

public class SkyBlockLiteAuth {
    public static String SkyBlockLiteCheckLoad;
    public static String UserCheck;
    public static String SkyBlockLiteVer;
    public static String Name;
    public static String SkyBlockLiteGG;

    public static /* bridge */ String getSubString(String string, String string2, String string3) {
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

    public static /* bridge */ String UserCheck() {
        String string = "";
        string = SkyBlockLiteAuth.getSubString(UserCheck, "User[", "]");
        return string;
    }

    public static /* bridge */ boolean GiftboxCheckUser() {
        return SkyBlockLiteAuth.UserCheck().contains(Minecraft.getMinecraft().thePlayer.getName());
    }

    public static /* bridge */ void Load() {
        new Thread(() -> {
            UserCheck = SkyBlockLiteAuth.getSubString(SkyBlockLiteAuth.sendGet("https://sharechain.qq.com/3c5bf690b72d1f2738cdaaea71b2a5d3"), "<font><span>User[", "]Check");
            SkyBlockLiteVer = SkyBlockLiteAuth.getSubString(SkyBlockLiteAuth.sendGet("https://sharechain.qq.com/3c5bf690b72d1f2738cdaaea71b2a5d3"), "SkyBlockLiteVer[", "]Check");
            SkyBlockLiteCheckLoad = SkyBlockLiteAuth.getSubString(SkyBlockLiteAuth.sendGet("https://sharechain.qq.com/3c5bf690b72d1f2738cdaaea71b2a5d3"), "SkyBlockLiteCheckLoad[", "]Check");
            Name = SkyBlockLiteAuth.getSubString(SkyBlockLiteAuth.sendGet("https://sharechain.qq.com/3c5bf690b72d1f2738cdaaea71b2a5d3"), "SkyBlockLiteName[", "]Check");
            SkyBlockLiteGG = SkyBlockLiteAuth.getSubString(SkyBlockLiteAuth.sendGet("https://sharechain.qq.com/1e4ededb159d62e456ac5e10ca7a9f9d"), "SkyBlockLiteGG[", "]Check");
        }).start();
    }

    public static /* bridge */ String sendGet(String string) {
        String string2 = "";
        try {
            String string3 = string;
            URL uRL = new URL(string3);
            HttpURLConnection httpURLConnection = (HttpURLConnection)uRL.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String string4 = bufferedReader.readLine();
            while (string4 != null) {
                string2 = String.valueOf(string2) + string4 + "\n";
                string4 = bufferedReader.readLine();
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        }
        catch (MalformedURLException malformedURLException) {
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return string2;
    }

    static {
        SkyBlockLiteCheckLoad = "SuChen :D";
        UserCheck = "SuChen :D";
        SkyBlockLiteVer = "SuChen :D";
        Name = "SuChen :D";
        SkyBlockLiteGG = "SuChen :D";
    }
}

