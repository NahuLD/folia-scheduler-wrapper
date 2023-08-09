package me.nahu.scheduler.wrapper;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Abstract class holding the scheduler reference.
 */
public abstract class WrappedJavaPlugin extends JavaPlugin {

    /**
     * Get the scheduler for this plugin.
     *
     * @return {@link WrappedScheduler} scheduler.
     */
    @NotNull
    public abstract WrappedScheduler getScheduler();
}
