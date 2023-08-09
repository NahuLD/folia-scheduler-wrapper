package me.nahu.scheduler.wrapper.implementation.bukkit.task;

import me.nahu.scheduler.wrapper.task.WrappedTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

/**
 * Bukkit implementation for wrapped tasks.
 */
public class BukkitWrappedTask implements WrappedTask {

    private final BukkitTask task;

    /**
     * Main constructor for the wrapped task.
     *
     * @param task {@link BukkitTask} task.
     */
    public BukkitWrappedTask(@NotNull BukkitTask task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancel() {
        task.cancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCancelled() {
        return task.isCancelled();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Plugin getOwningPlugin() {
        return task.getOwner();
    }
}
