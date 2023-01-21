package application;

import engine.ScenarioInitData;
import engine.SimEngine;
import engine.SimEntity;

public class Piste extends SimEntity {
    int numPiste;
    public boolean libre;

    public Piste(SimEngine engine, ScenarioInitData scInit, int num_de_la_piste) {
        super(engine, scInit);
        this.numPiste = num_de_la_piste;
        System.out.println("Numero de la piste : " + getNumPiste());

    }

    public int getNumPiste(){
        return numPiste;
    }


}
