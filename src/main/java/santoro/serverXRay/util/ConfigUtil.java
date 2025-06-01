package santoro.serverXRay.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import santoro.serverXRay.ServerXRay;

import java.util.HashMap;
import java.util.Map;

public class ConfigUtil {

    public static Map<Material, ChatColor> loadHighlights() {
        Map<Material, ChatColor> result = new HashMap<>();
        ConfigurationSection section = ServerXRay.get().getConfig().getConfigurationSection("xray.highlights");
        if (section == null) return result;

        for (String key : section.getKeys(false)) {
            try {
                Material material = Material.valueOf(key.toUpperCase());
                ChatColor color = ChatColor.valueOf(section.getString(key).toUpperCase());
                result.put(material, color);
            } catch (IllegalArgumentException e) {
                ServerXRay.get().getLogger().warning("Invalid material or color in config: " + key + " -> " + section.getString(key));
            }
        }

        return result;
    }
}
