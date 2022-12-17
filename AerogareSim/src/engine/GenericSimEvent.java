package engine;

import enstabretagne.base.time.LogicalDateTime;

public class GenericSimEvent extends SimEvent {

    private Runnable behaviour;

    public GenericSimEvent(LogicalDateTime d, Runnable behaviour) {
        super(d);
        this.behaviour = behaviour;
    }

    @Override
    public void process() {
        behaviour.run();
    }

}
