package ChainOfResponsibility;

import Enums.LogType;

public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(LogType logType) {
        super(logType);
    }

    @Override
    public void display(String message) {
        System.out.println("ERROR : " + message);
    }
}
