import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogObservable {
    HashMap<LogType, List<Observer>> levelObserverMapper;

    LogObservable(){
        levelObserverMapper = new HashMap<>();
    }

    public void addObserver(LogType logType, Observer observer){
        List<Observer> observers = levelObserverMapper.getOrDefault(logType,new ArrayList<Observer>());
        observers.add(observer);
        levelObserverMapper.put(logType,observers);
    }

    public void notifyObserver(LogType logType,String message){
        List<Observer> observers = levelObserverMapper.getOrDefault(logType,null);
        if(observers != null){
            for(Observer observer: observers){
                observer.notify(message);
            }
        }
    }
}
