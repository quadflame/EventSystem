# EventSystem

This is a reflection-based event system library for Java applications.

## Usage

1. Install the library to your local maven repo using `mvn install`
2. Add the library as a dependency to project using the following dependency:

```xml
<dependency>
    <groupId>com.quadflame</groupId>
    <artifactId>EventSystem</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Example

```java
public class CustomEvent implements Event {
    // Your code here for custom event implementation
}
```
```java
public class CustomListener implements Listener {
    @EventHandler
    public void onEvent(CustomEvent event) { // Your custom event here
        // Code to execute when the event is called
    }
}
```
```java
// Create your an event manager
EventManager eventManager = new EventManager();

// Register event listener
CustomListener customListener = new CustomListener();
eventManager.register(customListener);

// Call events
eventManager.call(new CustomEvent());

// Unregister event listeners when you are finished with them
eventManager.register(customListener);
```