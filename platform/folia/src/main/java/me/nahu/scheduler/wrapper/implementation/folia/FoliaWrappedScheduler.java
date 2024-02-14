package me.nahu.scheduler.wrapper.implementation.folia;

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import io.papermc.paper.threadedregions.scheduler.RegionScheduler;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import me.nahu.scheduler.wrapper.WrappedScheduler;
import me.nahu.scheduler.wrapper.implementation.folia.task.FoliaWrappedTask;
import me.nahu.scheduler.wrapper.task.WrappedTask;
import me.nahu.scheduler.wrapper.type.ImplementationType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Folia's implementation for schedulers.
 */
public final class FoliaWrappedScheduler implements WrappedScheduler {

    private static final ImplementationType IMPLEMENTATION_TYPE = ImplementationType.FOLIA;

    /**
     * Empty runnable for
     */
    private static final Runnable EMPTY_RUNNABLE = () -> {
    };

    private final Plugin plugin;

    private final GlobalRegionScheduler globalRegionScheduler;
    private final AsyncScheduler asyncScheduler;
    private final RegionScheduler regionScheduler;

    /**
     * Main constructor for the wrapped scheduler.
     *
     * @param plugin {@link Plugin} owning plugin.
     */
    public FoliaWrappedScheduler(@NotNull Plugin plugin) {
        this.plugin = Objects.requireNonNull(plugin, "Plugin cannot be null!");

        this.globalRegionScheduler = Bukkit.getGlobalRegionScheduler();
        this.asyncScheduler = Bukkit.getAsyncScheduler();
        this.regionScheduler = Bukkit.getRegionScheduler();
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
        globalRegionScheduler.cancelTasks(plugin);
        asyncScheduler.cancelTasks(plugin);
        // cannot target regional tasks
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
        return setupTask(globalRegionScheduler.run(plugin, __ -> runnable.run()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskAsynchronously(@NotNull Runnable runnable) {
        return setupTask(asyncScheduler.runNow(plugin, __ -> runnable.run()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nullable WrappedTask runTaskAtEntity(@NotNull Entity entity, @NotNull Runnable runnable) {
        return setupTask(entity.getScheduler().run(plugin, __ -> runnable.run(), EMPTY_RUNNABLE));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskAtLocation(@NotNull Location location, @NotNull Runnable runnable) {
        return setupTask(regionScheduler.run(plugin, location, __ -> runnable.run()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskTimer(@NotNull Runnable runnable, long delay, long period) {
        return setupTask(globalRegionScheduler.runAtFixedRate(plugin, __ -> runnable.run(), delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskTimerAsynchronously(@NotNull Runnable runnable, long delay, long period) {
        return setupTask(asyncScheduler.runAtFixedRate(plugin, __ -> runnable.run(), toMillis(delay), toMillis(period), TimeUnit.MILLISECONDS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nullable WrappedTask runTaskTimerAtEntity(@NotNull Entity entity, @NotNull Runnable runnable, long delay, long period) {
        return setupTask(entity.getScheduler().runAtFixedRate(plugin, __ -> runnable.run(), EMPTY_RUNNABLE, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskTimerAtLocation(@NotNull Location location, @NotNull Runnable runnable, long delay, long period) {
        return setupTask(regionScheduler.runAtFixedRate(plugin, location, __ -> runnable.run(), delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskLater(@NotNull Runnable runnable, long delay) {
        return setupTask(globalRegionScheduler.runDelayed(plugin, __ -> runnable.run(), delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskLaterAsynchronously(@NotNull Runnable runnable, long delay) {
        return setupTask(asyncScheduler.runDelayed(plugin, __ -> runnable.run(), toMillis(delay), TimeUnit.MILLISECONDS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nullable WrappedTask runTaskLaterAtEntity(@NotNull Entity entity, @NotNull Runnable runnable, long delay) {
        return setupTask(entity.getScheduler().runDelayed(plugin, __ -> runnable.run(), EMPTY_RUNNABLE, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedTask runTaskLaterAtLocation(@NotNull Location location, @NotNull Runnable runnable, long delay) {
        return setupTask(regionScheduler.runDelayed(plugin, location, __ -> runnable.run(), delay));
    }

    /**
     * Set up the wrapped task for the given task.
     *
     * @param task {@link ScheduledTask} task.
     * @return {@link WrappedTask} wrapped task.
     */
    @UnknownNullability
    private WrappedTask setupTask(@Nullable ScheduledTask task) {
        if (task == null) {
            return null;
        }
        return new FoliaWrappedTask(task);
    }

    /**
     * Transform ticks to milliseconds. Each tick is roughly 50ms.
     *
     * @param ticks Ticks to transform.
     * @return Milliseconds fetched from ticks.
     */
    public static long toMillis(long ticks) {
        return ticks * 50L;
    }
}
