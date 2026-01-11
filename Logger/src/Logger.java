import ChainOfResponsibility.AbstractLogger;
import Enums.LogType;
import Observer.LogObservable;

import java.io.Serial;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Logger implements Cloneable, Serializable {
    private static volatile Logger logger;
    private static volatile AbstractLogger baseLogger;
    private static volatile LogObservable logObservable;

    // can add this to make logger non-blocking in nature
    private static final ExecutorService executor =
            Executors.newSingleThreadExecutor(r -> {
                Thread t = new Thread(r, "async-logger-thread");
                t.setDaemon(true); // won't block JVM shutdown
                return t;
            });

    private Logger(){}
    public static Logger getLogger(){
        if(logger == null){
            synchronized (Logger.class){
                if(logger == null){
                    logger = new Logger();
                    baseLogger = LoggerConfigurations.loggerInitializer();
                    logObservable = LoggerConfigurations.observableInitializer();
                }
            }
        }
        return logger;
    }

    @Override
    protected Logger clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Serial
    protected Object readResolve(){
        return logger;
    }

    public void info(String message){
        baseLogger.log(LogType.INFO,message,logObservable);
    }

    public void error(String message){
        baseLogger.log(LogType.ERROR,message,logObservable);
    }

    public void debug(String message){
        baseLogger.log(LogType.DEBUG,message,logObservable);
    }
}
