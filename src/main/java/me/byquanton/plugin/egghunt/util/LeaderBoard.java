package me.byquanton.plugin.egghunt.util;

import me.byquanton.plugin.egghunt.EggHuntPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class LeaderBoard extends BukkitRunnable {

  private final EggHuntPlugin plugin;
  private final Objective objective;


  public LeaderBoard(EggHuntPlugin plugin) {
    Scoreboard board = plugin.getServer().getScoreboardManager().getMainScoreboard();
    if(board.getObjective("EggHunt") == null) {
      this.objective = board.registerNewObjective("EggHunt", "dummy", Component.text("EggHunt"));
    }else {
      this.objective = board.getObjective("EggHunt");
    }

    assert objective != null;
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    this.plugin = plugin;
  }

  @Override
  public void run() {
    for (Player player : plugin.getServer().getOnlinePlayers()) {
      showActionBar(player);
    }
  }

  public void setScoreBoard(Player player, Integer integer) {
    objective.getScore(player).setScore(integer);
  }

  public void showActionBar(Player player) {
    if(plugin.canBuild(player)) {
      player.sendActionBar(MiniMessage.miniMessage().deserialize("<gray>Build: <green>Aktiv"));
    }else {
      player.sendActionBar(plugin.messageUtil.getMessage("actionbar", Placeholder.unparsed("count", ""+plugin.scoreUtil.getEggs(player).size())));
    }
  }
}
