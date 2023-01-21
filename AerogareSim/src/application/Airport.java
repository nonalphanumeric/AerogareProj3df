package application;

import engine.InitData;
import engine.SimEngine;
import engine.SimEntity;

public class Airport extends SimEntity {
    public Airport(SimEngine engine, InitData initData) {

        super(engine, initData);
        toString();
    }

    @Override
    public String toString() {
        return "Aeroport de Brest";
    }
}
