package me.byquanton.plugin.egghunt;

import me.byquanton.plugin.egghunt.command.CommandManager;
import me.byquanton.plugin.egghunt.listener.BuildListener;
import me.byquanton.plugin.egghunt.listener.EggListener;
import me.byquanton.plugin.egghunt.listener.JoinListener;
import me.byquanton.plugin.egghunt.util.LeaderBoard;
import me.byquanton.plugin.egghunt.util.MessageUtil;
import me.byquanton.plugin.egghunt.util.ScoreUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public final class EggHuntPlugin extends JavaPlugin {

  private final List<OfflinePlayer> builders = new ArrayList<>();
  public LeaderBoard leaderboard;
  public ScoreUtil scoreUtil;
  public MessageUtil messageUtil;

  @Override
  public void onLoad() {
    this.saveDefaultConfig();
    this.messageUtil = new MessageUtil(this, new File(getDataFolder(), "messages.yml"));
    this.scoreUtil = new ScoreUtil(this, new File(getDataFolder(), "scores.yml"));
  }

  @Override
  public void onEnable() {
    new EggListener(this);
    new JoinListener(this);
    new BuildListener(this);

    try {
      new CommandManager(this);
      getLogger().info("Registered Commands");
    } catch (Exception e) {
      getLogger().warning("Failed to initialize Commands" + e.getMessage());
    }


    leaderboard = new LeaderBoard(this);
    leaderboard.runTaskTimerAsynchronously(this,0,20);

  }

  @Override
  public void onDisable() {
    leaderboard.cancel();
  }

  public boolean canBuild(OfflinePlayer offlinePlayer){
    return builders.contains(offlinePlayer);
  }

  public void setBuild(OfflinePlayer offlinePlayer, boolean canBuild){
    if(canBuild){
      builders.add(offlinePlayer);
    }else{
      builders.remove(offlinePlayer);
    }
  }
}
