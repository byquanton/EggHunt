package me.byquanton.plugin.egghunt.listener;

import me.byquanton.plugin.egghunt.EggHuntPlugin;
import me.byquanton.plugin.egghunt.util.PlayerHeads;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinListener implements Listener {

  private final EggHuntPlugin plugin;

  public JoinListener(EggHuntPlugin plugin) {
    this.plugin = plugin;
    plugin.getServer().getPluginManager().registerEvents(this, plugin);
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    event.getPlayer().sendPlayerListHeader(Component.text("EggHunt").color(NamedTextColor.GOLD));
    event.getPlayer().sendPlayerListFooter(Component.text("Viel Glück beim suchen!").color(NamedTextColor.GRAY));
    event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize("<gold>Willkommen beim EggHunt Event!<br><gray>Viel Spaß beim Eier suchen!</gray>"));
    event.getPlayer().setGameMode(GameMode.ADVENTURE);
    event.getPlayer().getInventory().setHeldItemSlot(4);
    event.getPlayer().getInventory().clear();
    event.getPlayer().getInventory().setHelmet(PlayerHeads.getRandomPlayerHead(plugin));
    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 9999,10,true,false,false));
  }


  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event){
    Player player = event.getPlayer();
    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 9999,10,true,false,false));
  }

}
