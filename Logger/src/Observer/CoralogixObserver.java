package Observer;

public class CoralogixObserver implements Observer {
    @Override
    public void notify(String message) {
        System.out.println("Coralogix Recieved : " + message);
    }
}
