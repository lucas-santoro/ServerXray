package santoro.serverXRay;

import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import santoro.serverXRay.command.ToggleXRayCommand;
import santoro.serverXRay.service.BlockFinderService;
import santoro.serverXRay.service.HighlightService;
import santoro.serverXRay.session.SessionManager;
import santoro.serverXRay.util.ConfigUtil;

import java.util.Map;

public class ServerXRay extends JavaPlugin {

    private static ServerXRay instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        GlowingBlocks glowing = new GlowingBlocks(this);
        Map<Material, ChatColor> highlightConfig = ConfigUtil.loadHighlights();
        BlockFinderService finderService = new BlockFinderService(highlightConfig.keySet());
        HighlightService highlightService = new HighlightService(glowing, highlightConfig);
        SessionManager sessionManager = new SessionManager(finderService, highlightService);

        getCommand("xray").setExecutor(new ToggleXRayCommand(sessionManager));
    }



    public static ServerXRay get() {
        return instance;
    }
}
