public class ConsoleObserver implements Observer{
    @Override
    public void notify(String message) {
        System.out.println("Console Sink updated : " + message);
    }
}
