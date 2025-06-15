import java.util.Observable;

public class LoggerManager {
    public static AbstractLogger loggerInitializer(){
        InfoLogger infoLogger = new InfoLogger(1);
        ErrorLogger errorLogger = new ErrorLogger(2);
        DebugLogger debugLogger = new DebugLogger(3);
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
        logObservable.addObserver(1,consoleObserver);
        logObservable.addObserver(2,cloudWatchObserver);
        logObservable.addObserver(3,coralogixObserver);
        return logObservable;
    }
}



// info
// error
// debug