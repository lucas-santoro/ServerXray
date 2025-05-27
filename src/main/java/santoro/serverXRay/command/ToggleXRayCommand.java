package santoro.serverXRay.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import santoro.serverXRay.session.SessionManager;

public class ToggleXRayCommand implements CommandExecutor {

    private final SessionManager sessionManager;

    public ToggleXRayCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        sessionManager.toggle(player);
        player.sendMessage(sessionManager.isActive(player) ?
                "§aXRay activated." :
                "§cXRay deactivated.");
        return true;
    }
}
