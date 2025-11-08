package Models;

import Enum.*;

public class Request {

    private int targetFloor;
    private Direction direction;
    private RequestSource requestSource;

    public Request(int targetFloor, Direction direction, RequestSource requestSource) {
        this.targetFloor = targetFloor;
        this.direction = direction;
        this.requestSource = requestSource;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestSource getRequestSource() {
        return requestSource;
    }

}
