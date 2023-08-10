package me.nahu.scheduler.wrapper.type;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Enum for implementation types.
 * Inspired by FoliaLib.
 */
public enum ImplementationType {

    /**
     * Folia server implementation.
     */
    FOLIA (
        "io.papermc.paper.threadedregions.RegionizedServer",
        "io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler",
        "io.papermc.paper.threadedregions.scheduler.RegionScheduler"
    ),
    /**
     * Bukkit server implementation.
     * We will fall back to this.
     */
    BUKKIT("org.bukkit.scheduler.BukkitScheduler"),
    /**
     * Unknown type, used for defaulting.
     */
    UNKNOWN,
    ;

    private final String[] classNames;

    /**
     * Main constructor for the enum.
     *
     * @param classNames Names of classes.
     */
    ImplementationType(String... classNames) {
        this.classNames = classNames;
    }

    /**
     * Get the array of classes that reference the implementations.
     *
     * @return Array of class names.
     */
    public String[] getClassNames() {
        return classNames;
    }

    /**
     * Check if this implementation type is applicable to the server context.
     *
     * @return {@code true} if it is applicable, {@code false} otherwise.
     */
    public boolean isApplicable() {
        for (String className : getClassNames()) {
            try {
                Class.forName(className);
                return true;
            } catch (ClassNotFoundException ignored) { }
        }
        return false;
    }

    /**
     * Check if the implementation type is unknown.
     *
     * @return {@code true} if it is unknown, {@code false} otherwise.
     */
    public boolean isUnknown() {
        return this == UNKNOWN;
    }

    /**
     * Find the implementation type applicable for this server.
     * <p>
     * If nothing is found, which shouldn't happen, we will default to {@link ImplementationType#UNKNOWN}.
     *
     * @return {@link ImplementationType} implementation type.
     */
    @NotNull
    public static ImplementationType find() {
        return Arrays.stream(ImplementationType.values())
            .filter(ImplementationType::isApplicable)
            .findFirst()
            .orElse(ImplementationType.UNKNOWN);
    }
}