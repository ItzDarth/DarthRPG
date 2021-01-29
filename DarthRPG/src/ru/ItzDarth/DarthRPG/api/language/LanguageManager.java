package ru.ItzDarth.DarthRPG.api.language;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public static void loadSite(Language language) throws IOException {
		URL oracle = new URL("https://raw.githubusercontent.com/DarthBoomerPlay/Desirex-Langs/main/"+language.name().toLowerCase()+".yml");
        Map<Object, Object> map = YAML.loadAs(new InputStreamReader(oracle.openStream()), LinkedHashMap.class);
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
        
        Bukkit.getConsoleSender().sendMessage("§c[DarthRPG] §fÇàãðóæåíî §c"+map.entrySet().size()+" §fñòðîê â §c"+language.name().toLowerCase()+".yml");
	}
	
}
