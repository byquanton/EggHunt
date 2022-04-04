package me.byquanton.plugin.egghunt.listener;

import me.byquanton.plugin.egghunt.EggHuntPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;

public class BuildListener implements Listener {

  private final EggHuntPlugin plugin;

  public BuildListener(EggHuntPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    if(!plugin.canBuild(event.getPlayer())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onBlockBreak(BlockPlaceEvent event) {
    if(!plugin.canBuild(event.getPlayer())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    if(!plugin.canBuild(event.getPlayer())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onExplode(EntityExplodeEvent event){
    event.setCancelled(true);
  }

  @EventHandler
  public void onVehicleEntityCollision(VehicleEntityCollisionEvent event){
    event.setCancelled(true);
  }

  @EventHandler
  public void onHangingBreak(HangingBreakByEntityEvent event){
    event.setCancelled(true);
  }

  @EventHandler
  public void onArmorStandManipulate(PlayerArmorStandManipulateEvent event){
    event.setCancelled(true);
  }

  @EventHandler
  public void onBlockSpread(BlockSpreadEvent event){
    event.setCancelled(true);
  }

  @EventHandler
  public void onBlockFertilize(BlockFertilizeEvent event){
    event.setCancelled(true);
  }

  @EventHandler
  public void onPlayerDropItem(PlayerDropItemEvent event){
    if(!plugin.canBuild(event.getPlayer())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onPlayerAttemptPickupItem(PlayerAttemptPickupItemEvent event){
    if(!plugin.canBuild(event.getPlayer())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onEntityDamage(EntityDamageEvent event){
    if(event.getEntity() instanceof Player){
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onEntityDamageByPlayer(EntityDamageByEntityEvent event){
    if(event.getDamager() instanceof Player player){
      if(!plugin.canBuild(player)){
        event.setCancelled(true);
      }
    }
  }
}
