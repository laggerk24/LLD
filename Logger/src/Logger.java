import java.io.Serial;
import java.io.Serializable;

public class Logger implements Cloneable, Serializable {
    private static volatile Logger logger;
    private static volatile AbstractLogger baseLogger;
    private static volatile LogObservable logObservable;
    private Logger(){}
    public static Logger getLogger(){
        if(logger == null){
            synchronized (Logger.class){
                if(logger == null){
                    logger = new Logger();
                    baseLogger = LoggerManager.loggerInitializer();
                    logObservable = LoggerManager.observableInitializer();
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
        baseLogger.log(1,message,logObservable);
    }

    public void error(String message){
        baseLogger.log(2,message,logObservable);
    }

    public void debug(String message){
        baseLogger.log(3,message,logObservable);
    }
    StringBuilder
}
