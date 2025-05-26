package santoro.serverXRay.xray;

import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import santoro.serverXRay.ServerXRay;
import santoro.serverXRay.service.BlockFinderService;

import java.util.*;

public class XRayRenderer {

    private static final Map<UUID, XRayRenderer> active = new HashMap<>();
    private final Player player;
    private BukkitRunnable task;
    private final List<Block> highlighted = new ArrayList<>();
    private static GlowingBlocks glowing;
    private final BlockFinderService blockFinderService = new BlockFinderService(); // novo

    public XRayRenderer(Player player) {
        this.player = player;
    }

    public static void init(GlowingBlocks glowingBlocksInstance) {
        glowing = glowingBlocksInstance;
    }

    public void start() {
        active.put(player.getUniqueId(), this);

        task = new BukkitRunnable() {
            @Override
            public void run() {
                Location center = player.getLocation();
                int radius = 30;

                clear();

                List<Block> ores = blockFinderService.findNearbyOres(center, radius);
                for (Block block : ores) {
                    try {
                        glowing.setGlowing(block, player, ChatColor.AQUA);
                        highlighted.add(block);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        task.runTaskTimer(ServerXRay.get(), 0L, 40L);
    }

    public void clear() {
        for (Block b : highlighted) {
            try {
                glowing.unsetGlowing(b, player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        highlighted.clear();
    }

    public void stop() {
        if (task != null) task.cancel();
        clear();
    }

    public static boolean isActive(Player player) {
        return active.containsKey(player.getUniqueId());
    }

    public static void disable(Player player) {
        XRayRenderer renderer = active.remove(player.getUniqueId());
        if (renderer != null && renderer.task != null) {
            renderer.task.cancel();
            renderer.clear();
        }
    }
}
