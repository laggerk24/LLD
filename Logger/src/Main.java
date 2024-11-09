public class Main {
    public static void main(String[] args) {
        Logger log = new ConsoleLogger(new DebugLogger(new ErrorLogger(new NullLogger())));
        log.Log(LogType.ERROR,"1st log message");
        log.Log(LogType.DEBUG,"2nd log message");
        log.Log(LogType.CONSOLE,"3rd log message");
    }
}