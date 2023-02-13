package application;

import application.avion.Avion;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Retard extends SimEvent {
    Avion avion;
    LogicalDateTime date;

    public Retard(LogicalDateTime logicalDateTime, Avion avion) {
        super(logicalDateTime);
        this.date = logicalDateTime;
        this.avion = avion;

    }

    @Override
    public void process() {
        //retard de 5mn
        Logger.DataSimple("Retard", avion.getInitData(),getDateOccurence(),getDateOccurence().add(LogicalDuration.ofMinutes(5)));

    }
}
