package santoro.serverXRay;

import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerXRay extends JavaPlugin {

    private static ServerXRay instance;
    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("ServerXRay Plugin enabled!");
        getCommand("xray").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) return true;

        if (XRayRenderer.isActive(p)) {
            XRayRenderer.disable(p);
            p.sendMessage("§cXRay deactivated.");
        } else {
            new XRayRenderer(p).start();
            p.sendMessage("§aXRay activated.");
        }

        return true;
    }

    public static ServerXRay get() {
        return instance;
    }
}
