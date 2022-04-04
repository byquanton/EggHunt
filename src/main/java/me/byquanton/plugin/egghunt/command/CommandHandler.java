package me.byquanton.plugin.egghunt.command;

import me.byquanton.plugin.egghunt.EggHuntPlugin;

public abstract class CommandHandler {
  protected final EggHuntPlugin plugin;
  protected final CommandManager commandManager;

  protected CommandHandler(
    final EggHuntPlugin plugin,
    final CommandManager commandManager
  ) {
    this.plugin = plugin;
    this.commandManager = commandManager;
  }

  public abstract void register();
}
