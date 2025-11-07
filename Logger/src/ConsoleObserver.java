import java.time.LocalDateTime;

public class ConsoleObserver implements Observer{
    @Override
    public void notify(String message) {
        System.out.println(LocalDateTime.now() + " Console Sink updated : " + message);
    }
}
