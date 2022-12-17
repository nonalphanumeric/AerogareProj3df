package application;

import application.avion.Avion;
import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

import java.util.List;

/**
 * correspond à l'evenement atterissage
 */
public class Atterissage extends SimEvent {
    private String id;
    public Atterissage(LogicalDateTime d, String id) {
        super(d);
        this.id = id;
    }

    @Override
    public int compareTo(SimEvent simEvent) {
        return 0;
    }

    @Override
    public void process() {
        Logger.Detail(entitePorteuseEvenement, "atterissage.Process","Atterissage de l'avion :" + id);
        List<SimEntity> Avions = entitePorteuseEvenement.recherche(e -> ((e instanceof Avion)));
    }
}
