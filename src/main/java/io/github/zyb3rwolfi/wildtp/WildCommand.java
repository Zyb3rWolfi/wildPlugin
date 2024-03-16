package io.github.zyb3rwolfi.wildtp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;


import java.util.Random;

public class WildCommand  implements CommandExecutor {

    private final int timeout;
    private BukkitTask teleportTask;
    // Getting the timeout from config.yml
    public WildCommand(int timeout) {
        this.timeout = timeout;
    }

    // Runs when the command is ran
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Checks if the player already ran the command or not
        if (teleportTask != null) {
            sender.sendMessage("Please wait before running the command again");
            return true;
        }
        // Checks if it is a player running the command
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player");
            return true;
        }

        // We are getting the Bukkit schedular and the player
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        Player player = (Player) sender;

        // Checking if the player has the correct permissions
        if (!player.hasPermission("wildtp.command.wild")) {
            player.sendMessage("You do not have the permission to use this command!");
            return true;
        }
        // Sending a message if the timeout > 0
        World world = player.getWorld();
        if (this.timeout != 0 ){
            player.sendMessage("You will teleport in " + this.timeout + "seconds");

        }

        // Running the task
        teleportTask = scheduler.runTaskLater(WildTp.getInstance(), ()-> teleportPlayer(world, player), this.timeout * 20);

        return true;
    }

    // Function resonsible for teleporting the player
    private void teleportPlayer(World world, Player player) {

        // Creating random co-ordinates
        Random random = new Random();
        double x = random.nextDouble() * 2000 - 1000;
        double y = 100;
        double z = random.nextDouble() * 2000 - 1000;

        // Checks if the location is safe via the isSafeLocation function
        if (!isSafeLocation(world, x, y, z)) {
            player.sendMessage("This location is not safe");
            return;
        }
        // If it is we teleport the player and sets the task to null
        player.teleport(new org.bukkit.Location(world, x, y, z));
        sendTitleToPlayer(player, x, y, z);
        teleportTask = null;

    }

    // Checks if the location is safe by seeing what block the player is on
    private boolean isSafeLocation(World world, double x, double y, double z) {
        Block block = world.getBlockAt((int) x, (int) y, (int) z);
        Material material = block.getType();

        return material.isAir() || material == Material.WATER || material == Material.CAVE_AIR || material == Material.VOID_AIR;
    }

    // Responsible for sending a title to player
    private void sendTitleToPlayer(Player player, double x, double y, double z) {

        String subTitle = String.join("enjoy");

        player.sendTitle("You have been teleported", subTitle, 10, 40, 50);
    }
}
