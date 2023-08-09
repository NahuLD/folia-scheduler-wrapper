package me.nahu.scheduler.wrapper.implementation.folia.task;

import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import me.nahu.scheduler.wrapper.task.WrappedTask;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Bukkit implementation for wrapped tasks.
 */
public class FoliaWrappedTask implements WrappedTask {

    private final ScheduledTask task;

    /**
     * Main constructor for the wrapped task.
     *
     * @param task {@link ScheduledTask} task.
     */
    public FoliaWrappedTask(@NotNull ScheduledTask task) {
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
        return task.getOwningPlugin();
    }
}
