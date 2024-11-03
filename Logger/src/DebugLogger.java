public class DebugLogger extends Logger{
    public DebugLogger(Logger logger){
        super(logger);
    }

    public DebugLogger(){
        super(null);
    }


    @Override
    public void Log(LogType logType, String message) {
        if(logType == LogType.DEBUG){
            System.out.println("Debug log message: " + message);
        }
        else if(nextLogger != null){
            nextLogger.Log(logType,message);
        }
        else {
            System.out.println("INVALID LOG TYPE");
        }
    }
}
