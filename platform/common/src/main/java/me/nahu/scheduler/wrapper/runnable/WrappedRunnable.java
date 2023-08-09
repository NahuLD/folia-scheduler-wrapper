package me.nahu.scheduler.wrapper.runnable;

import me.nahu.scheduler.wrapper.WrappedJavaPlugin;
import me.nahu.scheduler.wrapper.WrappedScheduler;
import me.nahu.scheduler.wrapper.task.WrappedTask;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Wrapped runnable intended to replace {@link org.bukkit.scheduler.BukkitRunnable}.
 */
public abstract class WrappedRunnable implements Runnable {

    private WrappedTask wrappedTask;

    /**
     * Run a new task.
     * <p>
     * Folia: Synced with the server daylight cycle tick.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTask(@NotNull WrappedScheduler scheduler) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTask(this));
    }

    /**
     * Run a new task.
     * <p>
     * Folia: Synced with the server daylight cycle tick.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTask(@NotNull WrappedJavaPlugin plugin) {
        return runTask(plugin.getScheduler());
    }

    /**
     * Run a new task.
     * <p>
     * Folia: Run in the dedicated async thread.
     * <p>
     * Paper: Run in the dedicated async thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskAsynchronously(@NotNull WrappedScheduler scheduler) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskAsynchronously(this));
    }

    /**
     * Run a new task.
     * <p>
     * Folia: Run in the dedicated async thread.
     * <p>
     * Paper: Run in the dedicated async thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskAsynchronously(@NotNull WrappedJavaPlugin plugin) {
        return runTaskAsynchronously(plugin.getScheduler());
    }

    /**
     * Run a new task.
     * <p>
     * Folia: Synced with the tick of the region of the entity (even if the entity moves).
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param entity Entity to run the task at.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskAtEntity(@NotNull WrappedScheduler scheduler, @NotNull Entity entity) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskAtEntity(entity, this));
    }
    /**
     * Run a new task.
     * <p>
     * Folia: Synced with the tick of the region of the entity (even if the entity moves).
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param entity Entity to run the task at.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskAtEntity(@NotNull WrappedJavaPlugin plugin, @NotNull Entity entity) {
        return runTaskAtEntity(plugin.getScheduler(), entity);
    }

    /**
     * Run a new task.
     * <p>
     * Folia: Synced with the tick of the region of the chunk of the location.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param location Location to run the task at.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskAtLocation(@NotNull WrappedScheduler scheduler, @NotNull Location location) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskAtLocation(location, this));
    }

    /**
     * Run a new task.
     * <p>
     * Folia: Synced with the tick of the region of the chunk of the location.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param location Location to run the task at.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskAtLocation(@NotNull WrappedJavaPlugin plugin, @NotNull Location location) {
        return runTaskAtLocation(plugin.getScheduler(), location);
    }

    /**
     * Run a new task timer.
     * <p>
     * Folia: Synced with the server daylight cycle tick.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param delay Delay before first execution. Must be greater than zero.
     * @param period Delay between executions. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskTimer(@NotNull WrappedScheduler scheduler, long delay, long period) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskTimer(this, delay, period));
    }

    /**
     * Run a new task timer.
     * <p>
     * Folia: Synced with the server daylight cycle tick.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param delay Delay before first execution. Must be greater than zero.
     * @param period Delay between executions. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskTimer(@NotNull WrappedJavaPlugin plugin, long delay, long period) {
        return runTaskTimer(plugin.getScheduler(), delay, period);
    }

    /**
     * Run a new task timer asynchronously.
     * <p>
     * Folia: Run in the dedicated async thread.
     * <p>
     * Paper: Run in the dedicated async thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param delay Delay before first execution. Must be greater than zero.
     * @param period Delay between executions. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskTimerAsynchronously(@NotNull WrappedScheduler scheduler, long delay, long period) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskTimerAsynchronously(this, delay, period));
    }

    /**
     * Run a new task timer asynchronously.
     * <p>
     * Folia: Run in the dedicated async thread.
     * <p>
     * Paper: Run in the dedicated async thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param delay Delay before first execution. Must be greater than zero.
     * @param period Delay between executions. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskTimerAsynchronously(@NotNull WrappedJavaPlugin plugin, long delay, long period) {
        return runTaskTimerAsynchronously(plugin.getScheduler(), delay, period);
    }

    /**
     * Run a new task timer.
     * <p>
     * Folia: Synced with the tick of the region of the entity (even if the entity moves).
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param entity Entity to run the task at.
     * @param delay Delay before first execution. Must be greater than zero.
     * @param period Delay between executions. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskTimerAtEntity(@NotNull WrappedScheduler scheduler, @NotNull Entity entity, long delay, long period) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskTimerAtEntity(entity, this, delay, period));
    }

    /**
     * Run a new task timer.
     * <p>
     * Folia: Synced with the tick of the region of the entity (even if the entity moves).
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param entity Entity to run the task at.
     * @param delay Delay before first execution. Must be greater than zero.
     * @param period Delay between executions. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskTimerAtEntity(@NotNull WrappedJavaPlugin plugin, @NotNull Entity entity, long delay, long period) {
        return runTaskTimerAtEntity(plugin.getScheduler(), entity, delay, period);
    }

    /**
     * Run a new task timer.
     * <p>
     * Folia: Synced with the tick of the region of the chunk of the location.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param location Location to run the task at.
     * @param delay Delay before first execution. Must be greater than zero.
     * @param period Delay between executions. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskTimerAtLocation(@NotNull WrappedScheduler scheduler, @NotNull Location location, long delay, long period) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskTimerAtLocation(location, this, delay, period));
    }

    /**
     * Run a new task timer.
     * <p>
     * Folia: Synced with the tick of the region of the chunk of the location.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param location Location to run the task at.
     * @param delay Delay before first execution. Must be greater than zero.
     * @param period Delay between executions. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskTimerAtLocation(@NotNull WrappedJavaPlugin plugin, @NotNull Location location, long delay, long period) {
        return runTaskTimerAtLocation(plugin.getScheduler(), location, delay, period);
    }

    /**
     * Run a new task later.
     * <p>
     * Folia: Synced with the server daylight cycle tick.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param delay Delay before first execution. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskLater(@NotNull WrappedScheduler scheduler, long delay) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskLater(this, delay));
    }

    /**
     * Run a new task later.
     * <p>
     * Folia: Synced with the server daylight cycle tick.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param delay Delay before first execution. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskLater(@NotNull WrappedJavaPlugin plugin, long delay) {
        return runTaskLater(plugin.getScheduler(), delay);
    }

    /**
     * Run a new task later asynchronously.
     * <p>
     * Folia: Run in the dedicated async thread.
     * <p>
     * Paper: Run in the dedicated async thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param delay Delay before first execution. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskLaterAsynchronously(@NotNull WrappedScheduler scheduler, long delay) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskLaterAsynchronously(this, delay));
    }

    /**
     * Run a new task later asynchronously.
     * <p>
     * Folia: Run in the dedicated async thread.
     * <p>
     * Paper: Run in the dedicated async thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param delay Delay before first execution. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskLaterAsynchronously(@NotNull WrappedJavaPlugin plugin, long delay) {
        return runTaskLaterAsynchronously(plugin.getScheduler(), delay);
    }

    /**
     * Run a new task later.
     * <p>
     * Folia: Synced with the tick of the region of the entity (even if the entity moves).
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param entity Entity to run the task at.
     * @param delay Delay before first execution. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskLaterAtEntity(@NotNull WrappedScheduler scheduler, @NotNull Entity entity, long delay) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskLaterAtEntity(entity, this, delay));
    }

    /**
     * Run a new task later.
     * <p>
     * Folia: Synced with the tick of the region of the entity (even if the entity moves).
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param entity Entity to run the task at.
     * @param delay Delay before first execution. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskLaterAtEntity(@NotNull WrappedJavaPlugin plugin, @NotNull Entity entity, long delay) {
        return runTaskLaterAtEntity(plugin.getScheduler(), entity, delay);
    }

    /**
     * Run a new task later.
     * <p>
     * Folia: Synced with the tick of the region of the chunk of the location.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param scheduler {@link WrappedScheduler} scheduler.
     * @param location Location to run the task at.
     * @param delay Delay before first execution. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskLaterAtLocation(@NotNull WrappedScheduler scheduler, @NotNull Location location, long delay) {
        checkNotYetScheduled();
        return setupTask(scheduler.runTaskLaterAtLocation(location, this, delay));
    }

    /**
     * Run a new task later.
     * <p>
     * Folia: Synced with the tick of the region of the chunk of the location.
     * <p>
     * Paper: Synced with the server main thread.
     *
     * @param plugin {@link WrappedJavaPlugin} plugin.
     * @param location Location to run the task at.
     * @param delay Delay before first execution. Must be greater than zero.
     * @return {@link WrappedTask} task reference.
     */
    @NotNull
    public WrappedTask runTaskLaterAtLocation(@NotNull WrappedJavaPlugin plugin, @NotNull Location location, long delay) {
        return runTaskLaterAtLocation(plugin.getScheduler(), location, delay);
    }

    /**
     * Returns true if this task has been cancelled.
     *
     * @return true if the task has been cancelled
     * @throws IllegalStateException if task was not scheduled yet
     */
    public synchronized boolean isCancelled() throws IllegalStateException {
        checkScheduled();
        return wrappedTask.isCancelled();
    }

    /**
     * Attempts to cancel this task.
     *
     * @throws IllegalStateException if task was not scheduled yet
     */
    public synchronized void cancel() throws IllegalStateException {
        checkScheduled();
        wrappedTask.cancel();
    }

    private void checkScheduled() {
        if (wrappedTask == null) {
            throw new IllegalStateException("Not scheduled yet");
        }
    }

    private void checkNotYetScheduled() {
        if (wrappedTask != null) {
            throw new IllegalStateException("Task is already scheduled!");
        }
    }

    @NotNull
    private WrappedTask setupTask(@NotNull final WrappedTask wrappedTask) {
        this.wrappedTask = wrappedTask;
        return wrappedTask;
    }
}
