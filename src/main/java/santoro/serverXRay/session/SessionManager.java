package santoro.serverXRay.session;

import org.bukkit.entity.Player;
import santoro.serverXRay.xray.XRayRenderer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
    private static final Map<UUID, XRayRenderer> sessions = new HashMap<>();

    public static boolean isActive(Player player) {
        return sessions.containsKey(player.getUniqueId());
    }

    public static void toggle(Player player) {
        if (isActive(player)) {
            disable(player);
        } else {
            enable(player);
        }
    }

    public static void enable(Player player) {
        XRayRenderer renderer = new XRayRenderer(player);
        sessions.put(player.getUniqueId(), renderer);
        renderer.start();
    }

    public static void disable(Player player) {
        XRayRenderer renderer = sessions.remove(player.getUniqueId());
        if (renderer != null) {
            renderer.stop();
        }
    }
}
