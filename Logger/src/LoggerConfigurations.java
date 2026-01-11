import ChainOfResponsibility.*;
import Enums.LogType;
import Observer.*;
import Observer.ConsoleObserver;
import Observer.CoralogixObserver;

public class LoggerConfigurations {
    public static AbstractLogger loggerInitializer(){
        // I can create a factory which would give me object of loggers here
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