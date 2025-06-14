public abstract class AbstractLogger {
    public AbstractLogger nextLogger;
    public int level;

    public AbstractLogger(int level){
        this.level = level;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int level,String message){
        if(level == this.level){
            display(message);
        }
        else if(nextLogger != null){
            nextLogger.log(level,message);
        }
    }

    public abstract void  display(String message);
}
