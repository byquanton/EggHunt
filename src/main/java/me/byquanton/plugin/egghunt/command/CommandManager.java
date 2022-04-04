package me.byquanton.plugin.egghunt.command;

import cloud.commandframework.brigadier.CloudBrigadierManager;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import me.byquanton.plugin.egghunt.EggHuntPlugin;
import org.bukkit.command.CommandSender;

import java.util.function.UnaryOperator;

public class CommandManager extends PaperCommandManager<CommandSender> {


    public CommandManager(final EggHuntPlugin plugin) throws Exception {
        super(
                plugin,
                CommandExecutionCoordinator.simpleCoordinator(),
                UnaryOperator.identity(),
                UnaryOperator.identity()
        );

        if (this.queryCapability(CloudBukkitCapabilities.NATIVE_BRIGADIER)) {
            try {
                this.registerBrigadier();
                final CloudBrigadierManager<?, ?> brigManager = this.brigadierManager();
                if (brigManager != null) {
                    brigManager.setNativeNumberSuggestions(false);
                }
            } catch (final Exception e) {
                plugin.getLogger().warning("Failed to initialize Brigadier support: " + e.getMessage());
            }
        }

        if (this.queryCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
            this.registerAsynchronousCompletions();
        }


        ImmutableList.of(
          new BuildCommand(plugin,this)
        ).forEach(CommandHandler::register);


    }


}
