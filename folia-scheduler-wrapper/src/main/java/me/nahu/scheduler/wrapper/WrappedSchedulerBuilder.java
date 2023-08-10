package me.nahu.scheduler.wrapper;

import com.google.common.base.Preconditions;
import me.nahu.scheduler.wrapper.implementation.bukkit.BukkitWrappedScheduler;
import me.nahu.scheduler.wrapper.implementation.folia.FoliaWrappedScheduler;
import me.nahu.scheduler.wrapper.type.ImplementationType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Builder for wrapped schedulers.
 */
public final class WrappedSchedulerBuilder {

    private Plugin plugin;
    private ImplementationType implementationType;

    /**
     * Private constructor for the wrapped scheduler.
     */
    private WrappedSchedulerBuilder() {
        this.implementationType = ImplementationType.find();
    }

    /**
     * Change the plugin for the wrapped scheduler.
     *
     * @param plugin New plugin.
     * @return {@link WrappedSchedulerBuilder} builder instance.
     */
    @NotNull
    public WrappedSchedulerBuilder plugin(@NotNull Plugin plugin) {
        this.plugin = Objects.requireNonNull(plugin);
        return this;
    }

    /**
     * Change the implementation type utilized. Beware that this might have unintended consequences, so use at your
     * own caution.
     *
     * @param implementationType New implementation type.
     * @return {@link WrappedSchedulerBuilder} builder instance.
     */
    @NotNull
    public WrappedSchedulerBuilder implementationType(@NotNull ImplementationType implementationType) {
        this.implementationType = Objects.requireNonNull(implementationType);
        return this;
    }

    /**
     * Build the wrapped scheduler with the information given.
     *
     * @return {@link WrappedScheduler} scheduler.
     */
    @NotNull
    public WrappedScheduler build() {
        Objects.requireNonNull(plugin, "Plugin cannot be null!");
        Objects.requireNonNull(implementationType, "Implementation type cannot be null!");

        Preconditions.checkArgument(implementationType.isUnknown(), "Implementation type cannot be unknown!");

        //noinspection SwitchStatementWithTooFewBranches
        return switch (implementationType) {
            case FOLIA -> new FoliaWrappedScheduler(plugin);
            default -> new BukkitWrappedScheduler(plugin);
        };
    }

    /**
     * Get a new builder instance.
     *
     * @return {@link WrappedSchedulerBuilder} new builder.
     */
    @NotNull
    public static WrappedSchedulerBuilder builder() {
        return new WrappedSchedulerBuilder();
    }

    /**
     * @deprecated Please use {@link ImplementationType#find()} instead.
     */
    @NotNull
    @Deprecated(forRemoval = true)
    public static ImplementationType find() {
        return ImplementationType.find();
    }
}
