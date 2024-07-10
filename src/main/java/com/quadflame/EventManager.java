package com.quadflame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused")
public class EventManager {
    private final Set<Listener> listeners = new HashSet<>();

    public void register(Listener listener) {
        listeners.add(listener);
    }

    public void unregister(Listener listener) {
        listeners.remove(listener);
    }

    public void call(Event event) {
        for (EventPriority priority : EventPriority.values()) {
            for (Listener listener : listeners) {
                Method[] methods = listener.getClass().getDeclaredMethods();
                for (Method method : methods) {
                    if (method.getParameterCount() == 1 && method.getParameterTypes()[0].isAssignableFrom(event.getClass()) && method.isAnnotationPresent(EventHandler.class)) {
                        EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                        if (eventHandler.priority() != priority) continue;
                        if (event instanceof CancellableEvent && !eventHandler.ignoreCancelled() && ((CancellableEvent) event).isCancelled())
                            continue;
                        method.setAccessible(true);
                        try {
                            method.invoke(listener, event);
                        } catch (InvocationTargetException | IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
