public abstract class Logger {
    public Logger nextLogger;

    public Logger(Logger logger) {
        this.nextLogger = logger;
    }
    public abstract void Log(LogType logType, String message);
}
