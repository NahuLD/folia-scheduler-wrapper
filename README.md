# folia-scheduler-wrapper
[![CodeFactor](https://www.codefactor.io/repository/github/nahuld/folia-scheduler-wrapper/badge)](https://www.codefactor.io/repository/github/nahuld/folia-scheduler-wrapper)
[![](https://jitpack.io/v/NahuLD/folia-scheduler-wrapper.svg)](https://jitpack.io/#NahuLD/folia-scheduler-wrapper)

Wrapper for Folia schedulers intended to make migration as simple as possible while still maintaining cross-compatibility with the Bukkit scheduler API.

## Requirements
- Java 17 or above.
- Bukkit, Paper or Folia server.

## Dependency

> [!WARNING]\
> You are expected to relocate the package to your own sub-package to prevent conflicts with other plugins!

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
	<dependency>
	    <groupId>com.github.NahuLD.folia-scheduler-wrapper</groupId>
	    <artifactId>folia-scheduler-wrapper</artifactId>
	    <version>v0.0.3</version>
	</dependency>
</dependencies>
```

### Gradle
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.NahuLD.folia-scheduler-wrapper:folia-scheduler-wrapper:v0.0.3'
}
```

### Gradle (Kotlin)
```groovy
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.NahuLD.folia-scheduler-wrapper:folia-scheduler-wrapper:v0.0.3")
}
```

## Usage
There are multiple ways of accessing an instance of `WrappedScheduler`.

One way is creating it by yourself, utilizing a new instance of `WrappedSchedulerBuilder`.
```java
final WrappedSchedulerBuilder schedulerBuilder = WrappedSchedulerBuilder.builder().plugin(getPlugin());
final WrappedScheduler scheduler = schedulerBuilder.build(); // Scheduler ready to use, yay!
```

The other way is by simple replacing the `JavaPlugin` extension on your plugin for `FoliaWrappedJavaPlugin`. Note that the only addition this provides is a lazy-object loader for the `WrappedSchedulerBuilder` and does not change anything else.

```java
public final class YourPluginMainClass extends FoliaWrappedJavaPlugin {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnable() {
        final WrappedScheduler scheduler = getScheduler(); // Scheduler ready, WOW!
    }
}
```

This wrapper attempts to make migration as simple as possible, thus keeping a similar naming scheme and parameter format to Bukkit's scheduler.

```java
final WrappedScheduler scheduler = getScheduler();

// run on bukkit main thread, or folia's daylight cicle
scheduler.runTask(() -> getLogger().info("What a fancy main thread!"));

// run on async threads for both platforms!
scheduler.runTaskTimerAsynchronously(
    () -> getLogger().info("Task timer on the async thread, NICE!"),
    20L, 
    20L
);

// run task at the regional thread for the provided entity!
final Player player = getPlayer();
scheduler.runTaskAtEntity(
    player,
    () -> player.updateInventory()
);

// Bukkit-runnable replacements built-in!
new WrappedRunnable() {
    @Override
    public void run() {
        cancel(); // STOP THIS!
    }
}.runTaskLater(scheduler, 100L); // you may use your plugin here if it extends WrappedJavaPlugin!

// and so on...
```

You can find all methods available and the corresponding documentation for each on the [WrappedScheduler](platform/common/src/main/java/me/nahu/scheduler/wrapper/WrappedScheduler.java) file. The published artifacts have sources and JavaDocs bundled with them to make working with it seamless as well.

## How to build

1. Run `./gradlew build` in the project's root!
2. Fetch your jar at `build/`.

_Please use a dependency management system like Gradle or Maven instead though!_

## License

The contents of this repository are licensed under the MIT license.
So do with this code as you wish! 
A copy of the MIT license can be found in [LICENSE](LICENSE).
