public class DebugLogger extends AbstractLogger{
    public DebugLogger(int level) {
        super(level);
    }

    @Override
    public void display(String message) {
        System.out.println("Debug : " + message);
    }
}
