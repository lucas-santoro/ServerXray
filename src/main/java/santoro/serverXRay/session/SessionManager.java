package santoro.serverXRay.session;

import org.bukkit.entity.Player;
import santoro.serverXRay.service.BlockFinderService;
import santoro.serverXRay.service.HighlightService;
import santoro.serverXRay.xray.XRayRenderer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {

    private final BlockFinderService finderService;
    private final HighlightService highlightService;
    private final Map<UUID, XRayRenderer> sessions = new HashMap<>();

    public SessionManager(BlockFinderService finderService, HighlightService highlightService) {
        this.finderService = finderService;
        this.highlightService = highlightService;
    }

    public boolean isActive(Player player) {
        return sessions.containsKey(player.getUniqueId());
    }

    public void toggle(Player player) {
        if (isActive(player)) {
            disable(player);
        } else {
            enable(player);
        }
    }

    public void enable(Player player) {
        XRayRenderer renderer = new XRayRenderer(player, finderService, highlightService);
        sessions.put(player.getUniqueId(), renderer);
        renderer.start();
    }

    public void disable(Player player) {
        XRayRenderer renderer = sessions.remove(player.getUniqueId());
        if (renderer != null) {
            renderer.stop();
        }
    }
}
