package santoro.serverXRay.service;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.*;

public class BlockFinderService {

    private final Set<Material> validBlocks;

    public BlockFinderService(Set<Material> validBlocks) {
        this.validBlocks = validBlocks;
    }

    public List<Block> findNearbyOres(Location center, int radius) {
        List<Block> found = new ArrayList<>();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    Location loc = center.clone().add(dx, dy, dz);
                    Block block = loc.getBlock();
                    if (validBlocks.contains(block.getType())) {
                        found.add(block);
                    }
                }
            }
        }

        return found;
    }
}
