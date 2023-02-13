package application;

import application.avion.Avion;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

/**
 * correspond à l'evenement atterissage
 */
public class Atterissage extends SimEvent {
    private String id;
    Avion avion ;
    LogicalDateTime date;
    public Atterissage(LogicalDateTime date, Avion avion) {
        super(date);
        this.date = date;
        this.avion = avion;

    }

    @Override
    public int compareTo(SimEvent simEvent) {
        return 0;
    }

    @Override
    public void process() {
        //evenement atterissage

        Logger.Detail(entitePorteuseEvenement, "atterissage.Process","Atterissage de l'avion :" + avion.getInitData().getId());
        System.out.println("Atterissage de l'avion :" + avion.getInitData().getId());
    }
}
