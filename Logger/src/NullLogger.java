public class NullLogger extends AbstractLogger{
    public NullLogger() {
        super(0);
    }

    @Override
    public void display(String message) {
        System.out.println("Incorrect Log Level");
    }
}
