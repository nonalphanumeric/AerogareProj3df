package application.avion;

import application.Airport;
import application.Atterissage;
import application.InitAvion;
import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import java.util.List;

public class Avion extends SimEntity{
    public Avion(SimEngine engine, InitAvion initAvion) {
        super(engine, initAvion);
        //int numVol;

    }

    @Override
    protected void init() {
        super.init();

        List<SimEntity> airport = recherche(e -> ((e instanceof Airport)));
        Post(new Atterissage(Now().add(LogicalDuration.ofMinutes(1)), getInitData().getId()));
        //Log post apres init
        Logger.Detail(this, "Avion.init","Avion initialisé: " + getInitData().getId());
    }

    public void decollage(){
        //Decollage decollage = new Decollage();
        Runnable behaviour = this::atterissage; // syntax 1, method reference
/*        behaviour = () -> this.atterissage(); // syntax 2, lambda

        behaviour = new Runnable() {
            @Override
            public void run() {
                Avion.this.atterissage();
            }
        };*/

        getEngine().post(getEngine().SimulationDate(), behaviour);
    }

    public void atterissage(){
        //Atterissage atterissage = new Atterissage();
        getEngine().post(getEngine().SimulationDate(), this::decollage);

    }
}