public class ConsoleLogger extends Logger{

    public ConsoleLogger(Logger logger){
        super(logger);
    }

    @Override
    public void Log(LogType logType, String message) {
        if(logType == LogType.CONSOLE){
            System.out.println("Conole log message: " + message);
        }
        else if(nextLogger != null){
            nextLogger.Log(logType,message);
        }
    }
}
