public class InfoLogger extends AbstractLogger{
    public InfoLogger(int level) {
        super(level);
    }

    @Override
    public void display(String message) {
        System.out.println("Info : " + message);
    }
}
