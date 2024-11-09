public class ErrorLogger extends Logger{
    public ErrorLogger(Logger logger){
        super(logger);
    }

    @Override
    public void Log(LogType logType, String message) {
        if(logType == LogType.ERROR){
            System.out.println("Error log message: " + message);
        }
        else if(nextLogger != null){
            nextLogger.Log(logType,message);
        }
        else {
            System.out.println("INVALID LOG TYPE");
        }
    }
}
