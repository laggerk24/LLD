import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogObservable {
    HashMap<Integer, List<Observer>> levelObserverMapper;

    LogObservable(){
        levelObserverMapper = new HashMap<>();
    }

    public void addObserver(int level, Observer observer){
        List<Observer> observers = levelObserverMapper.getOrDefault(level,new ArrayList<Observer>());
        observers.add(observer);
        levelObserverMapper.put(level,observers);
    }

    public void notifyObserver(int level,String message){
        List<Observer> observers = levelObserverMapper.getOrDefault(level,null);
        if(observers != null){
            for(Observer observer: observers){
                observer.notify(message);
            }
        }
    }
}
