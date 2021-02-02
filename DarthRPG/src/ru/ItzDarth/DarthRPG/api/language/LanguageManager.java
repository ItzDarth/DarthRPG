package ru.ItzDarth.DarthRPG.api.language;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Bukkit;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.PropertyUtils;

public class LanguageManager {
	
	private static final Yaml YAML = newSnakeYaml();

    private static Yaml newSnakeYaml() {
        Constructor constructor = new Constructor();
        PropertyUtils propertyUtils = new PropertyUtils();
        propertyUtils.setSkipMissingProperties(true);
        constructor.setPropertyUtils(propertyUtils);

        return new Yaml(constructor);
    }
	
	/*public LanguageManager() {
		try {
			loadSite(Language.RUSSIAN);
			loadSite(Language.ENGLISH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public static void loadSite(Language language) {
		try {
			URL oracle = new URL("https://raw.githubusercontent.com/YaDarth2709/DarthRPG/main/Languages/"+language.name().toLowerCase()+".yml");
			URLConnection connection = oracle.openConnection();
			String token = "2cbbc38423c40c8559c46b95cb03f4251870ee31:x-oauth-basic";
			String authString = "Basic " + Base64.encodeBase64String(token.getBytes());
			connection.setRequestProperty("Authorization", authString);
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			Map<Object, Object> map = YAML.loadAs(new InputStreamReader(connection.getInputStream()), LinkedHashMap.class);
	        if (map == null)
	            return;
	        
	        for (Map.Entry<Object, Object> entry : map.entrySet()) {
	            String key = entry.getKey().toString();
	            Object data = entry.getValue();

	            if (data instanceof List) {
	                language.addListMessage(key, (List<String>) data);
	                continue;
	            }

	            language.addMessage(key, data.toString());
	        }
	        
	        Bukkit.getConsoleSender().sendMessage("§c[DarthRPG] §fЗагружено §c"+map.entrySet().size()+" §fстрок в §c"+language.name().toLowerCase()+".yml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
