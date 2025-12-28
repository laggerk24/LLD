package Models;

import java.util.List;

public class Move {
    private final List<Actions> actions;

    public Move(List<Actions> actions) {
        this.actions = actions;
    }

    public List<Actions> getActions() {
        return actions;
    }
}
