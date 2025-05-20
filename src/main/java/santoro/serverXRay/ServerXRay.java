package santoro.serverXRay;

import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerXRay extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("XRay Plugin ativado!");
        getCommand("xray").setExecutor(this);
    }
}
