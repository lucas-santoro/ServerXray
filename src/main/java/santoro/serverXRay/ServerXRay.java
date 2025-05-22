package santoro.serverXRay;

import fr.skytasul.glowingentities.GlowingBlocks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import santoro.serverXRay.xray.XRayRenderer;

public class ServerXRay extends JavaPlugin {

    private static ServerXRay instance;
    private GlowingBlocks glowing;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("ServerXRay Plugin enabled!");

        glowing = new GlowingBlocks(this);
        XRayRenderer.init(glowing);
        getCommand("xray").setExecutor(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("ServerXRay Plugin disabled!");
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
