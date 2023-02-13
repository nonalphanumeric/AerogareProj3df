package application;

import engine.ScenarioInitData;
import engine.SimEngine;
import engine.SimEntity;

public class TW1 extends SimEntity {
    private int dispo; //si tw libre dispo = 1, sinon dispo = 0
    public boolean libre;

    public TW1(SimEngine engine, ScenarioInitData scInit,int dispo) {
        super(engine, scInit);
        this.dispo = dispo;
    }

    public int getDispo(){
        return this.dispo;
    }

    public int setDispo(int dispo){
        this.dispo = dispo;
        return this.dispo;
    }
    public void liberationTW1(){
        setDispo(1);
    }

    public void reservationTW1(){
        setDispo(0);
    }
}
