package me.byquanton.plugin.egghunt.listener;

import me.byquanton.plugin.egghunt.EggHuntPlugin;
import me.byquanton.plugin.egghunt.util.PlayerHeads;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EggListener implements Listener {
  private final EggHuntPlugin plugin;

  public EggListener(EggHuntPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerInteract(PlayerInteractEvent event) {
    if(event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.PLAYER_HEAD) {
      Skull skull = (Skull)event.getClickedBlock().getState();
      if(skull.getPlayerProfile() != null && skull.getPlayerProfile().getTextures().getSkin() != null) {
        if(plugin.getConfig().getStringList("heads").contains(skull.getPlayerProfile().getTextures().getSkin().toString())) {
          Player player = event.getPlayer();
          if(!plugin.scoreUtil.hasEgg(player, event.getClickedBlock().getLocation())){
            plugin.scoreUtil.addEgg(player, event.getClickedBlock().getLocation());
            plugin.leaderboard.setScoreBoard(player, plugin.scoreUtil.getEggs(player).size());
            player.sendMessage(plugin.messageUtil.getMessage("foundHead"));
          } else {
            player.sendMessage(plugin.messageUtil.getMessage("alreadyFound"));
        }
      }
    }
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onChunkLoad(ChunkLoadEvent event){
    Chunk chunk = event.getChunk();
    new BukkitRunnable() {
      @Override
      public void run() {
        if(chunk.contains(Material.DIAMOND_BLOCK.createBlockData())){
          for(int x=0; x <= 15; x++){
            for(int z=0; z <= 15; z++){
              for(int y=0; y <= chunk.getChunkSnapshot().getHighestBlockYAt(x,z); y++){
                Block block = chunk.getBlock(x,y,z);
                if(block.getType() == Material.DIAMOND_BLOCK){
                  plugin.getLogger().info("Found a diamond block! " + x + " " + y + " " + z);
                  new BukkitRunnable() {
                    @Override
                    public void run() {
                      block.setType(Material.PLAYER_HEAD);
                      Skull skull = (Skull) block.getState();
                      skull.setPlayerProfile(PlayerHeads.getRandomPlayerProfile(plugin));
                      skull.update();
                    }
                  }.runTask(plugin);
                }
              }
            }
          }
        }
      }
    }.runTaskAsynchronously(plugin);
  }


}
