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
    // You can adjust when and how your events are handled by changing the following optional attributes
    @EventHandler( 
        priority = EventPriority.LOW,    // default EventPriority.NORMAL 
        ignoreCancelled = true           // default false
    )
    public void onEvent(CustomEvent event) { // Your custom event here
        // Code to execute when the event is called
    }
}
```
```java
// Create your own event manager
EventManager eventManager = new EventManager();

// Register event listener
CustomListener customListener = new CustomListener();
eventManager.register(customListener);

// Call events
eventManager.call(new CustomEvent());

// Unregister event listeners when you are finished with them
eventManager.register(customListener);
```
However, if you wanted to create a an event system for objects you cannot modify, you can use the `AbstractEventManager<T>` class to create events of your specified type. This could be useful in scenarios similar to using the `Packet<?>` class in spigot development.
```java
AbstractEventManager<NotAnEvent> notAnEventManager = new AbstractEventManager<>();

notAnEventManager.register(new Listener() {
    @EventHandler
    public void onNotAnEvent(NotAnEvent event) {
        System.out.println("NotAnEvent received");
    }
});

notAnEventManager.call(new NotAnEvent());
```
