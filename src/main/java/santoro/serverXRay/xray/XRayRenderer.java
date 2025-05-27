package santoro.serverXRay.xray;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import santoro.serverXRay.ServerXRay;
import santoro.serverXRay.service.BlockFinderService;
import santoro.serverXRay.service.HighlightService;

import java.util.List;

public class XRayRenderer {

    private final Player player;
    private BukkitRunnable task;
    private final BlockFinderService blockFinderService;
    private final HighlightService highlightService;

    public XRayRenderer(Player player, BlockFinderService blockFinderService, HighlightService highlightService) {
        this.player = player;
        this.blockFinderService = blockFinderService;
        this.highlightService = highlightService;
    }

    public void start() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                Location center = player.getLocation();
                int radius = 30;

                highlightService.clear(player);
                List<Block> ores = blockFinderService.findNearbyOres(center, radius);
                highlightService.highlight(player, ores);
            }
        };

        task.runTaskTimer(ServerXRay.get(), 0L, 40L);
    }

    public void stop() {
        if (task != null) task.cancel();
        highlightService.clear(player);
    }
}
