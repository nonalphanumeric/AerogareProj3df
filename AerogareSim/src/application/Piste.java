package application;

import engine.ScenarioInitData;
import engine.SimEngine;
import engine.SimEntity;

public class Piste extends SimEntity {
    int numPiste;
    public boolean dispoPiste ;

    public Piste(SimEngine engine, InitPiste initPiste, int num_de_la_piste, boolean dispoPiste) {
        super(engine, initPiste);
        this.numPiste = num_de_la_piste;
        System.out.println("Numero de la piste : " + getNumPiste());
        this.dispoPiste = dispoPiste;
    }

    public int getNumPiste(){
        return numPiste;
    }
    public boolean getDispoPiste() {
        return dispoPiste;
    }
    public boolean setDispoPiste(boolean dispo){
        dispoPiste = dispo;
        return dispoPiste;

    }

    public boolean liberationPiste(){
        dispoPiste = true;
        System.out.println(("piste n°"+ getNumPiste() + " libre."));
        return dispoPiste;
    }

    public boolean reservationPiste(){
        dispoPiste = false;
        return dispoPiste;
    }

}
