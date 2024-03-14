package io.guthub.zyb3rwolfi.wild2;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class WildCommand  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player");
            return true;
        }

        Player player = (Player) sender;
        World world = player.getWorld();

        Random random = new Random();
        double x = random.nextDouble() * 2000 - 1000;
        double y = 100;
        double z = random.nextDouble() * 2000 - 1000;
        player.teleport(new org.bukkit.Location(world, x, y, z));

        player.sendMessage("You been teleported");
        return true;
    }
}
