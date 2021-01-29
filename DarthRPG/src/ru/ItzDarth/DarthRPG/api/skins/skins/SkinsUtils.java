package ru.ItzDarth.DarthRPG.api.skins.skins;

import org.bukkit.Bukkit;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import ru.ItzDarth.DarthRPG.api.skins.SkinsAPI;
import ru.ItzDarth.DarthRPG.api.skins.exceptions.InvalidMojangPlayerException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SkinsUtils {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String POST_REQUEST = "https://api.mineskin.org/generate/upload";
    private static final String MOJANG_REQUEST = "https://api.ashcon.app/mojang/v2/user/%s";

    protected static void loadSkins(String player, File image, SkinType skinType){


        long start;

        switch(skinType){

            case IMAGE:

                start = System.currentTimeMillis();

                Bukkit.getScheduler().runTaskAsynchronously(SkinsAPI.getPlugin(), (() -> {

                        Connection connection = null;

                        try {
                            connection = Jsoup
                                    .connect(POST_REQUEST)
                                    .userAgent(USER_AGENT)
                                    .method(Connection.Method.POST)
                                    .data("file", image.getName(), new FileInputStream(image))
                                    .ignoreContentType(true)
                                    .ignoreHttpErrors(true)
                                    .timeout(40000);

                            String body = connection.execute().body();
                            JSONObject json = new JSONObject(body);

                            String value = json.getJSONObject("data").getJSONObject("texture").getString("value");
                            String signature = json.getJSONObject("data").getJSONObject("texture").getString("signature");

                            SkinsAPI.skinsManager.loadedImageSkin.put(image, new Skin(value, signature));
                            SkinsAPI.getPlugin().getLogger().info("Image skin load takes " + (System.currentTimeMillis()-start) + " ms!");
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                }));

                break;

            case MOJANG:

                start = System.currentTimeMillis();

                Bukkit.getScheduler().runTaskAsynchronously(SkinsAPI.getPlugin(), (() -> {
                    Connection connection = null;

                    try{
                        connection = Jsoup
                                .connect(MOJANG_REQUEST.replace("%s", player))
                                .userAgent(USER_AGENT)
                                .method(Connection.Method.GET)
                                .ignoreContentType(true)
                                .ignoreHttpErrors(true)
                                .timeout(40000);

                        String body = connection.execute().body();
                        JSONObject json = new JSONObject(body);

                        if(json.isNull("textures")){
                            throw new InvalidMojangPlayerException("Player not valid!");
                        }

                        String value = json.getJSONObject("textures").getJSONObject("raw").getString("value");
                        String signature = json.getJSONObject("textures").getJSONObject("raw").getString("signature");

                        SkinsAPI.skinsManager.loadedMojangSkin.put(player, new Skin(value, signature));
                        SkinsAPI.getPlugin().getLogger().info("Mojang skin load takes " + (System.currentTimeMillis()-start) + " ms!");
                    } catch (IOException | InvalidMojangPlayerException | JSONException e) {
                        e.printStackTrace();
                    }

                }));
                break;


        }



    }

}
