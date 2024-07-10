import com.quadflame.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class TestOfEventSystem {

    @Test
    public void test() {
        EventManager eventManager = new EventManager();

        eventManager.register(new Listener() {
            @EventHandler
            public void onEvent(Event event) {
                System.out.println("Event received");
            }

            @EventHandler(priority = EventPriority.LOW)
            public void onCancellableEvent(CancellableEvent event) {
                event.setCancelled(true);
                System.out.println("Cancelled event received");
            }

            @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
            public void onTestEvent(TestEvent event) {
                System.out.println("Test event received");
            }
        });

        System.out.println("Calling Event");
        eventManager.call(new Event() {
        });

        System.out.println();

        System.out.println("Calling CancellableEvent");
        eventManager.call(new CancellableEvent() {
        });

        System.out.println();

        System.out.println("Calling TestEvent");
        eventManager.call(new TestEvent());
    }
}
