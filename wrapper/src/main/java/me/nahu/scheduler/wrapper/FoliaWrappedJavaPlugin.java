package me.nahu.scheduler.wrapper;

import me.nahu.scheduler.wrapper.util.LazyValue;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Simple class intended to make access to the {@link WrappedScheduler} easier.
 * <p>
 * Can be avoided and initialize the scheduler by itself as well.
 */
public abstract class FoliaWrappedJavaPlugin extends WrappedJavaPlugin {

    private final LazyValue<WrappedScheduler> scheduler = new LazyValue<>(() -> WrappedSchedulerBuilder.builder().plugin(this).build());

    /**
     * {@inheritDoc}
     */
    @Override
    public @NotNull WrappedScheduler getScheduler() {
        return Objects.requireNonNull(scheduler.getValue(), "WrappedScheduler has not been initialized!");
    }
}
