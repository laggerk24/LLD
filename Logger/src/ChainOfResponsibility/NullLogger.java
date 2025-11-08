package ChainOfResponsibility;

public class NullLogger extends AbstractLogger {
    public NullLogger() {
        super(null);
    }

    @Override
    public void display(String message) {
        System.out.println("Incorrect Log Level");
    }
}
