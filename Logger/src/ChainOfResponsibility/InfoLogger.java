package ChainOfResponsibility;

import Enums.LogType;

public class InfoLogger extends AbstractLogger {
    public InfoLogger(LogType logType) {
        super(logType);
    }

    @Override
    public void display(String message) {
        System.out.println("Info : " + message);
    }
}
