public class DebugLogger extends AbstractLogger{
    public DebugLogger(LogType logType) {
        super(logType);
    }

    @Override
    public void display(String message) {
        System.out.println("Debug : " + message);
    }
}
