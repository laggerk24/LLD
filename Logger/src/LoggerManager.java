public class LoggerManager {
    public static AbstractLogger loggerInitializer(){
        InfoLogger infoLogger = new InfoLogger(1);
        ErrorLogger errorLogger = new ErrorLogger(2);
        DebugLogger debugLogger = new DebugLogger(3);
        NullLogger nullLogger = new NullLogger();
        infoLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);
        errorLogger.setNextLogger(nullLogger);
        return infoLogger;
    }
}



// info
// error
// debug