# Repro for a bug that started with upgrade from Gradle 6.8.1 to Gradle 7.0

Run the following twice
```
./gradlew app:mergeReleaseJavaResource app:mergeDebugJavaResource
```

It is expected that both of these tasks are up to date on the second run.

## The same issue does not happen if:
- If each task is run twice separately, they are up to date
```
./gradlew app:mergeReleaseJavaResource
./gradlew app:mergeReleaseJavaResource
./gradlew app:mergeDebugJavaResource
./gradlew app:mergeReleaseJavaResource
```
- If the tasks are run in R & R D or D & D R order
```
./gradlew app:mergeReleaseJavaResource
./gradlew app:mergeReleaseJavaResource app:mergeDebugJavaResource
```
- If using Gradle 6.8.1

Changing version of Android Gradle Plugin between 4.2.0-rc01 and 7.0.0-alpha14 has no effect,
it is broken in the same way.

## Other observations:
It seems that Gradle is doing some sort of configuration reuse optimization, when the classpath
of releaseImplementation and debugImplementation are identical in content (but different files),
Gradle seems to be reusing one over the other, which is not correct as there are separate tasks
producing these two jars.
