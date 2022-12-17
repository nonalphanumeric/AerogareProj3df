package application;

import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;

/**
 * correspond à l'evenement decollage
 */
public class Decollage extends SimEvent {
    public Decollage(LogicalDateTime d) {
        super(d);
    }

    @Override
    public int compareTo(SimEvent simEvent) {
        return 0;
    }

    @Override
    public void process() {

    }
}
