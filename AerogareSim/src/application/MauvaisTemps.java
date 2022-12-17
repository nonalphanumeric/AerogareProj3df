package application;

import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;

/**
 * Mauvais temps 1 jour sur7
 */
public class MauvaisTemps extends SimEvent {
    public MauvaisTemps(LogicalDateTime d) {
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
