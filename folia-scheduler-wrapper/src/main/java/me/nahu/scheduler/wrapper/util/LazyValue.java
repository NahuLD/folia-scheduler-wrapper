package me.nahu.scheduler.wrapper.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Lazy value facilitator.
 *
 * @param <T> The type of the value that will be lazily loaded.
 */
public final class LazyValue<T> {

    private final Object synchronizedLock = new Object();

    private final Supplier<T> valueSupplier;
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<T> value = Optional.empty();

    /**
     * Main constructor for a new lazy value.
     *
     * @param valueSupplier Supplier for the value.
     */
    public LazyValue(@NotNull Supplier<T> valueSupplier) {
        this.valueSupplier = Objects.requireNonNull(valueSupplier, "value supplier cannot be null!");
    }

    /**
     * Constructor that directly provides the value.
     *
     * @param value Value to utilize.
     */
    public LazyValue(@NotNull T value) {
        Objects.requireNonNull(value, "value cannot be null!");
        this.value = Optional.of(value);
        this.valueSupplier = null;
    }

    /**
     * Check if the value is loaded.
     *
     * @return {@code true} if it has been loaded, {@code false} otherwise.
     */
    public boolean isLoaded() {
        return value.isPresent();
    }

    /**
     * Load the value without interacting with the object.
     */
    public void eager() {
        if (!isLoaded()) {
            synchronized (synchronizedLock) {
                if (!isLoaded()) {
                    T value = valueSupplier.get();
                    this.value = Optional.ofNullable(value);
                }
            }
        }
    }

    /**
     * Get and load the value if necessary. This does not guarantee that the supplier will providea non-null value,
     * thus it is marked as such.
     * <p></p>
     * For an optional wrapped object, you can call {@link #eager()} to load, and then {@link #getIfLoaded()}.
     *
     * @return {@link T} value.
     */
    @Nullable
    public T getValue() {
        if (!isLoaded()) {
            synchronized (synchronizedLock) {
                if (!isLoaded()) {
                    T value = valueSupplier.get();
                    this.value = Optional.ofNullable(value);
                    return value;
                }
            }
        }
        return value.get();
    }

    /**
     * Get the value if it has been loaded.
     *
     * @return {@link Optional} holding the loaded object, empty if it has not, or the returned object is null.
     */
    @NotNull
    public Optional<T> getIfLoaded() {
        return value;
    }
}