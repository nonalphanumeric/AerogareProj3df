package application;

import engine.ScenarioInitData;
import engine.SimEngine;
import engine.SimEntity;

public class Piste extends SimEntity {
    public boolean libre;

    public Piste(SimEngine engine, ScenarioInitData scInit) {
        super(engine, scInit);

    }
}
