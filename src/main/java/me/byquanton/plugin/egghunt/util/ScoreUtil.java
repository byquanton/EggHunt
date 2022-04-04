package me.byquanton.plugin.egghunt.util;

import me.byquanton.plugin.egghunt.EggHuntPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ScoreUtil {

  private final File messageConfig;
  private final FileConfiguration messageConfiguration;


  public ScoreUtil(EggHuntPlugin plugin, File messageConfig){
    this.messageConfig = messageConfig;
    if (!messageConfig.exists()) {
      plugin.saveResource(messageConfig.getName(), false);
    }
    messageConfiguration = YamlConfiguration.loadConfiguration(messageConfig);
  }

  public List<UUID> getPlayers(){
    List<UUID> players = new ArrayList<>();
    Objects.requireNonNull(messageConfiguration.getRoot()).getKeys(false).forEach(player -> players.add(UUID.fromString(player)));
    return players;
  }

  public List<String> getEggs(Player player){
    return new ArrayList<>(messageConfiguration.getStringList(player.getUniqueId().toString()));
  }

  public boolean hasEgg(Player player, Location location){
    String egg = location.getBlockX() + "." + location.getBlockY() + "." + location.getBlockZ();
    return getEggs(player).contains(egg);
  }

  public void addEgg(Player player, Location location){
    String egg = location.getBlockX() + "." + location.getBlockY() + "." + location.getBlockZ();
    List<String> eggs = getEggs(player);
    eggs.add(egg);
    messageConfiguration.set(player.getUniqueId().toString(), eggs);
    save();
  }

  public void save(){
    try {
      messageConfiguration.save(messageConfig);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
