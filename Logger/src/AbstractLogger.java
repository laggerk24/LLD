public abstract class AbstractLogger {
    public AbstractLogger nextLogger;
    public int level;

    public AbstractLogger(int level){
        this.level = level;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int level,String message, LogObservable observable){
        if(level == this.level){
            display(message);
            observable.notifyObserver(level,message);
        }
        else if(nextLogger != null){
            nextLogger.log(level,message,observable);
        }
    }

    public abstract void  display(String message);
}
