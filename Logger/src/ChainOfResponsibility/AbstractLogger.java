package ChainOfResponsibility;

import Enums.LogType;
import Observer.LogObservable;

public abstract class AbstractLogger {
    public AbstractLogger nextLogger;
    public LogType logType;

    public AbstractLogger(LogType logType){
        this.logType = logType;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(LogType logType,String message, LogObservable observable){
        if(this.logType.equals(logType)){
            display(message);
            observable.notifyObserver(logType,message);
        }
        else if(nextLogger != null){
            nextLogger.log(logType,message,observable);
        }
    }

    public abstract void  display(String message);
}
