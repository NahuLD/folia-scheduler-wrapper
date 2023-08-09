package me.nahu.scheduler.wrapper.type;

/**
 * Enum for implementation types.
 * Inspired by FoliaLib.
 */
public enum ImplementationType {

    /**
     * Folia server implementation.
     */
    FOLIA ("io.papermc.paper.threadedregions.RegionizedServer"),
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
     * Check if the implementation type is unknown.
     *
     * @return {@code true} if it is unknown, {@code false} otherwise.
     */
    public boolean isUnknown() {
        return this == UNKNOWN;
    }
}