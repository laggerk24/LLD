public class CloudWatchObserver implements Observer{
    @Override
    public void notify(String message) {
        System.out.println("Cloud Watch Recieved : " + message);
    }
}
