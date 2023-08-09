package me.nahu.scheduler.wrapper.implementation.bukkit;

import me.nahu.scheduler.wrapper.WrappedScheduler;
import me.nahu.scheduler.wrapper.implementation.bukkit.task.BukkitWrappedTask;
import me.nahu.scheduler.wrapper.task.WrappedTask;
import me.nahu.scheduler.wrapper.type.ImplementationType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Bukkit's implementation for schedulers.
 */
public class BukkitWrappedScheduler implements WrappedScheduler {

    private static final ImplementationType IMPLEMENTATION_TYPE = ImplementationType.BUKKIT;

    private final Plugin plugin;
    private final BukkitScheduler scheduler;

    /**
     * Main constructor for the wrapped scheduler.
     *
     * @param plugin {@link Plugin} owning plugin.
     */
    public BukkitWrappedScheduler(@NotNull Plugin plugin) {
        this.plugin = Objects.requireNonNull(plugin, "Plugin cannot be null!");
        this.scheduler = Bukkit.getScheduler();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull ImplementationType getImplementationType() {
        return IMPLEMENTATION_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelAllTasks() {
        scheduler.cancelTasks(plugin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelTask(@NotNull WrappedTask wrappedTask) {
        wrappedTask.cancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTask(@NotNull Runnable runnable) {
        return setupTask(scheduler.runTask(plugin, runnable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskAsynchronously(@NotNull Runnable runnable) {
        return setupTask(scheduler.runTaskAsynchronously(plugin, runnable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskAtEntity(@NotNull Entity entity, @NotNull Runnable runnable) {
        return runTask(runnable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskAtLocation(@NotNull Location location, @NotNull Runnable runnable) {
        return runTask(runnable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskTimer(@NotNull Runnable runnable, long delay, long period) {
        return setupTask(scheduler.runTaskTimer(plugin, runnable, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskTimerAsynchronously(@NotNull Runnable runnable, long delay, long period) {
        return setupTask(scheduler.runTaskTimerAsynchronously(plugin, runnable, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskTimerAtEntity(@NotNull Entity entity, @NotNull Runnable runnable, long delay, long period) {
        return runTaskTimer(runnable, delay, period);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskTimerAtLocation(@NotNull Location location, @NotNull Runnable runnable, long delay, long period) {
        return runTaskTimer(runnable, delay, period);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskLater(@NotNull Runnable runnable, long delay) {
        return setupTask(scheduler.runTaskLater(plugin, runnable, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskLaterAsynchronously(@NotNull Runnable runnable, long delay) {
        return setupTask(scheduler.runTaskLaterAsynchronously(plugin, runnable, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskLaterAtEntity(@NotNull Entity entity, @NotNull Runnable runnable, long delay) {
        return runTaskLater(runnable, delay);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskLaterAtLocation(@NotNull Location location, @NotNull Runnable runnable, long delay) {
        return runTaskLater(runnable, delay);
    }

    /**
     * Set up the wrapped task for the given task.
     *
     * @param task {@link BukkitTask} task.
     * @return {@link WrappedTask} wrapped task.
     */
    @NotNull
    private WrappedTask setupTask(@NotNull BukkitTask task) {
        return new BukkitWrappedTask(task);
    }
}
