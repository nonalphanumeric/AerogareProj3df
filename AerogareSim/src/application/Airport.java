package application;

import engine.InitData;
import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.Logger;

import java.util.List;

public class Airport extends SimEntity {
    public Airport(SimEngine engine, InitData initData) {

        super(engine, initData);
        toString();
    }

    @Override
    protected void init() {
        super.init();

    }
    @Override
    public String toString() {
        return "Aeroport de Brest";
    }
}
