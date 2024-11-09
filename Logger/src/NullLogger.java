public class NullLogger extends Logger{
    public NullLogger() {
        super(null);
    }

    @Override
    public void Log(LogType logType, String message) {
        System.out.println("Invalid LogType");
    }
}
