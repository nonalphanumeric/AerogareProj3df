package application;

import engine.ScenarioInitData;
import engine.SimEngine;
import engine.SimEntity;

public class Porte extends SimEntity {
    public boolean libre;

    public Porte(SimEngine engine, ScenarioInitData scInit) {
        super(engine, scInit);
    }
}
