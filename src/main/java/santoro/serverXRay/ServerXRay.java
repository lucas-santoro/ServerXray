package santoro.serverXRay;

import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.plugin.java.JavaPlugin;
import santoro.serverXRay.command.ToggleXRayCommand;
import santoro.serverXRay.service.BlockFinderService;
import santoro.serverXRay.service.HighlightService;
import santoro.serverXRay.session.SessionManager;

public class ServerXRay extends JavaPlugin {

    private static ServerXRay instance;

    @Override
    public void onEnable() {
        instance = this;

        GlowingBlocks glowing = new GlowingBlocks(this);
        BlockFinderService finderService = new BlockFinderService();
        HighlightService highlightService = new HighlightService(glowing);
        SessionManager sessionManager = new SessionManager(finderService, highlightService);

        getCommand("xray").setExecutor(new ToggleXRayCommand(sessionManager));
    }


    public static ServerXRay get() {
        return instance;
    }
}
