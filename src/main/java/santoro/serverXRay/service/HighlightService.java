package santoro.serverXRay.service;

import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.*;

public class HighlightService {

    private final GlowingBlocks glowing;
    private final Map<UUID, List<Block>> highlightedBlocks = new HashMap<>();

    public HighlightService(GlowingBlocks glowing) {
        this.glowing = glowing;
    }

    public void highlight(Player player, List<Block> blocks) {
        List<Block> highlighted = new ArrayList<>();

        for (Block block : blocks) {
            try {
                glowing.setGlowing(block, player, ChatColor.AQUA);
                highlighted.add(block);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        highlightedBlocks.put(player.getUniqueId(), highlighted);
    }

    public void clear(Player player) {
        List<Block> blocks = highlightedBlocks.remove(player.getUniqueId());
        if (blocks == null) return;

        for (Block block : blocks) {
            try {
                glowing.unsetGlowing(block, player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
