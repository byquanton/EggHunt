package me.byquanton.plugin.egghunt.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import me.byquanton.plugin.egghunt.EggHuntPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class PlayerHeads {

  public static ItemStack getPlayerHead(String skinURL) {
    ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
    meta.setPlayerProfile(getPlayerProfile(skinURL));
    itemStack.setItemMeta(meta);
    return itemStack;
  }

  public static ItemStack getRandomPlayerHead(EggHuntPlugin plugin) {
    ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
    meta.setPlayerProfile(getRandomPlayerProfile(plugin));
    itemStack.setItemMeta(meta);
    return itemStack;
  }

  public static PlayerProfile getPlayerProfile(String skinURL) {
    PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
    PlayerTextures textures = profile.getTextures();
    try {
      textures.setSkin(new URL(skinURL));
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    profile.setTextures(textures);
    return profile;
  }

  public static PlayerProfile getRandomPlayerProfile(EggHuntPlugin plugin) {
      List<String> heads = plugin.getConfig().getStringList("heads");
      String skinURL = heads.get(new Random().nextInt(heads.size()));
      return getPlayerProfile(skinURL);
  }

}
