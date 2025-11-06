import java.util.Observable;

public class LoggerManager {
    public static AbstractLogger loggerInitializer(){
        InfoLogger infoLogger = new InfoLogger(LogType.INFO);
        ErrorLogger errorLogger = new ErrorLogger(LogType.ERROR);
        DebugLogger debugLogger = new DebugLogger(LogType.DEBUG);
        NullLogger nullLogger = new NullLogger();
        infoLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(nullLogger);
        return infoLogger;
    }

    public static LogObservable observableInitializer(){
        LogObservable logObservable = new LogObservable();
        CloudWatchObserver cloudWatchObserver = new CloudWatchObserver();
        ConsoleObserver consoleObserver = new ConsoleObserver();
        CoralogixObserver coralogixObserver = new CoralogixObserver();
        logObservable.addObserver(LogType.INFO,consoleObserver);
        logObservable.addObserver(LogType.ERROR,cloudWatchObserver);
        logObservable.addObserver(LogType.DEBUG,coralogixObserver);
        return logObservable;
    }
}



// info
// error
// debug