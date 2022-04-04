package me.byquanton.plugin.egghunt.command;

import cloud.commandframework.context.CommandContext;
import me.byquanton.plugin.egghunt.EggHuntPlugin;
import me.byquanton.plugin.egghunt.util.PlayerHeads;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand extends CommandHandler {

    protected BuildCommand(EggHuntPlugin plugin, CommandManager commandManager) {
        super(plugin, commandManager);
    }

    @Override
    public void register() {
        commandManager.command(commandManager.commandBuilder("build").permission("build").handler(this::build));
    }

    private void build(CommandContext<CommandSender> context) {
        if(context.getSender() instanceof Player player){
            if(plugin.canBuild(player)){
              plugin.setBuild(player, false);
              player.sendMessage("Build mode disabled");
              player.setGameMode(GameMode.ADVENTURE);
              player.getInventory().clear();
              player.getInventory().setHeldItemSlot(4);
            }else{
              plugin.setBuild(player, true);
              player.sendMessage("Build mode enabled");
              player.setGameMode(GameMode.CREATIVE);
              player.getInventory().clear();
              plugin.getConfig().getStringList("heads").forEach(head -> player.getInventory().addItem(PlayerHeads.getPlayerHead(head)));
              player.getInventory().addItem(PlayerHeads.getRandomPlayerHead(plugin));
            }
        }
    }
}
